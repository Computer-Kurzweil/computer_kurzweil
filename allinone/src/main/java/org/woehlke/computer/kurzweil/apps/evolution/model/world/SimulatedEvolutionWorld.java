package org.woehlke.computer.kurzweil.apps.evolution.model.world;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.Stepper;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.trashcan.signals.UserSignal;
import org.woehlke.computer.kurzweil.model.LatticePoint;
import org.woehlke.computer.kurzweil.apps.evolution.ctx.SimulatedEvolutionStateService;
import org.woehlke.computer.kurzweil.apps.evolution.model.statistics.SimulatedEvolutionPopulationCensus;
import org.woehlke.computer.kurzweil.apps.evolution.model.statistics.SimulatedEvolutionStatistics;
import org.woehlke.computer.kurzweil.apps.evolution.model.cell.Cell;
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
 * @see SimulatedEvolutionWorldLattice
 * <p>
 * Simulated Evolution.
 * Artificial Life Simulation of Bacteria Motion depending on DNA.
 * <p>
 * &copy; 2006 - 2008 Thomas Woehlke.
 * http://thomas-woehlke.de/p/simulated-evolution/
 */
@Log
public class SimulatedEvolutionWorld implements Startable, Stepper {

    /**
    * List of the Simulated Bacteria Cells.
    */
    private List<Cell> cells;

    @Getter @Setter
    private SimulatedEvolutionStateService appCtx;

    @Getter
    private final SimulatedEvolutionWorldLattice worldLattice;

    @Getter
    private final SimulatedEvolutionStatistics statisticsContainer;

    @Getter
    private final ComputerKurzweilApplicationContext ctx;

  public SimulatedEvolutionWorld(
      ComputerKurzweilApplicationContext ctx
  ) {
      this.ctx = ctx;
      this.worldLattice = new SimulatedEvolutionWorldLattice( this.ctx);
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

  /**
   * One Step of Time in the World in which the Population of Bacteria Cell perform Life.
   * Every Cell moves, eats, dies of hunger, and it has sex. splitting into two children with changed DNA.
   */
  private void letLivePopulation() {
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
  }

  public List<Cell> getAllCells() {
    return cells;
  }

  public boolean hasFood(int x, int y) {
    return worldLattice.hasFood(x, y);
  }

    public void toggleGardenOfEden() {
        appCtx.toggleGardenOfEden();
        worldLattice.toggleGardenOfEden();
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void step() {
        this.letLivePopulation();
    }

    @Override
    public void update() {

    }

    //@Override
    public void handleUserSignal(UserSignal userSignal) {

    }
}
