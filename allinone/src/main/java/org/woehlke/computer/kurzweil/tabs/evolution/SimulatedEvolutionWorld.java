package org.woehlke.computer.kurzweil.tabs.evolution;


import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.tabs.TabModel;
import org.woehlke.computer.kurzweil.model.LatticePoint;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.tabs.evolution.cell.Cell;
import org.woehlke.computer.kurzweil.tabs.evolution.population.SimulatedEvolutionPopulationCensus;

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
 * @see SimulatedEvolutionWorldLattice
 * <p>
 * Simulated Evolution.
 * Artificial Life Simulation of Bacteria Motion depending on DNA.
 * <p>
 * &copy; 2006 - 2008 Thomas Woehlke.
 * http://thomas-woehlke.de/p/simulated-evolution/
 */
@Log
@Getter
@ToString(exclude={"cells","appCtx"})
public class SimulatedEvolutionWorld implements Startable, TabModel {

    /**
    * List of the Simulated Bacteria Cells.
    */
    private List<Cell> cells;
    private final SimulatedEvolutionContext appCtx;
    private final SimulatedEvolutionWorldLattice worldLattice;
    private final SimulatedEvolutionCanvasStatistics statisticsContainer;

  public SimulatedEvolutionWorld(
      SimulatedEvolutionContext appCtx
  ) {
      this.appCtx = appCtx;
      this.worldLattice = new SimulatedEvolutionWorldLattice(  this.appCtx);
      this.statisticsContainer = new SimulatedEvolutionCanvasStatistics( this.appCtx);
      cells = new ArrayList<>();
      createInitialPopulation();
  }

  private void createInitialPopulation(){
      cells.clear();
      for (int i = 0; i <  this.appCtx.getCtx().getProperties().getEvolution().getPopulation().getInitialPopulation(); i++) {
          Cell cell = new Cell(this.appCtx);
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
        worldLattice.toggleGardenOfEden();
    }

    @Override
    public void start() {
        log.info("start");
        createInitialPopulation();
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