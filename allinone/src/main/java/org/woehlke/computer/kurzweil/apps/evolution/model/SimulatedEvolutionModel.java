package org.woehlke.computer.kurzweil.apps.evolution.model;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.tabs.TabModel;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.model.LatticePoint;
import org.woehlke.computer.kurzweil.apps.evolution.SimulatedEvolutionContext;
import org.woehlke.computer.kurzweil.commons.Startable;

import java.util.ArrayList;
import java.util.List;

/**
 * The World contains Water, Cells and Food.
 * It is the Data Model of the Simulation in a MVC Pattern.
 *
 * @author Thomas Woehlke
 * User: thomas
 * Date: 04.02.2006
 * Time: 19:06:20
 * @see Cell
 * @see SimulatedEvolutionModelLattice
 * <p>
 * Simulated Evolution.
 * Artificial Life Simulation of Bacteria Motion depending on DNA.
 * <p>
 * &copy; 2006 - 2008 Thomas Woehlke.
 * http://thomas-woehlke.de/p/simulated-evolution/
 */
@Log
@Getter
public class SimulatedEvolutionModel implements Startable, TabModel {

    /**
    * List of the Simulated Bacteria Cells.
    */
    private List<Cell> cells;

    @Setter
    private SimulatedEvolutionContext appCtx;

    private final ComputerKurzweilApplicationContext ctx;
    private final SimulatedEvolutionModelLattice worldLattice;
    private final SimulatedEvolutionStatistics statisticsContainer;

  public SimulatedEvolutionModel(
      ComputerKurzweilApplicationContext ctx
  ) {
      this.ctx = ctx;
      this.worldLattice = new SimulatedEvolutionModelLattice( this.ctx);
      this.statisticsContainer = new SimulatedEvolutionStatistics( this.ctx);
      /**
       * Create the initial Population of Bacteria Cells and give them their position in the World.
       */
      cells = new ArrayList<>();
      for (int i = 0; i < this.ctx.getProperties().getEvolution().getPopulation().getInitialPopulation(); i++) {
          Cell cell = new Cell(this.ctx);
          cells.add(cell);
      }
      SimulatedEvolutionPopulationCensus populationCensus = new SimulatedEvolutionPopulationCensus();
      for (Cell cell : cells) {
          populationCensus.countStatusOfOneCell(cell.getLifeCycleStatus());
      }
      log.info(populationCensus.toString());
      statisticsContainer.push(populationCensus);
  }

  public List<Cell> getAllCells() {
    return cells;
  }

  public boolean hasFood(int x, int y) {
    return worldLattice.hasFood(x, y);
  }

    public void toggleGardenOfEden() {
        log.info("toggleGardenOfEden");
        appCtx.toggleGardenOfEden();
        worldLattice.toggleGardenOfEden();
    }

    @Override
    public void start() {
        log.info("start");
    }

    @Override
    public void stop() {
        log.info("stop");
    }

    /**
     * One Step of Time in the World in which the Population of Bacteria Cell perform Life.
     * Every Cell moves, eats, dies of hunger, and it has sex. splitting into two children with changed DNA.
     */
    @Override
    public void step() {
      log.info("step");
        worldLattice.letFoodGrow();
        LatticePoint pos;
        List<Cell> children = new ArrayList<>();
        List<Cell> died = new ArrayList<>();
        for (Cell cell : cells) {
            cell.move();
            if (cell.died()) {
                died.add(cell);
            } else {
                pos = cell.getPosition();
                int food = worldLattice.eat(pos);
                cell.eat(food);
                if (cell.isAbleForReproduction()) {
                    Cell child = cell.reproductionByCellDivision();
                    children.add(child);
                }
            }
        }
        for (Cell dead : died) {
            cells.remove(dead);
        }
        cells.addAll(children);
        SimulatedEvolutionPopulationCensus oneStatisticsTimestamp = new SimulatedEvolutionPopulationCensus();
        for (Cell cell : cells) {
            oneStatisticsTimestamp.countStatusOfOneCell(cell.getLifeCycleStatus());
        }
        statisticsContainer.push(oneStatisticsTimestamp);
      log.info("stepped");
    }

}
