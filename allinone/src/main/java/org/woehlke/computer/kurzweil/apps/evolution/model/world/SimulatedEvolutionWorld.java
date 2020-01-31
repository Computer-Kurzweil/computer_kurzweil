package org.woehlke.computer.kurzweil.apps.evolution.model.world;


import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import org.woehlke.computer.kurzweil.control.Stepper;
import org.woehlke.computer.kurzweil.model.LatticePoint;
import org.woehlke.computer.kurzweil.apps.evolution.model.SimulatedEvolutionStateService;
import org.woehlke.computer.kurzweil.apps.evolution.model.statistics.SimulatedEvolutionPopulationCensus;
import org.woehlke.computer.kurzweil.apps.evolution.model.statistics.SimulatedEvolutionStatistics;
import org.woehlke.computer.kurzweil.apps.evolution.model.cell.Cell;
import org.woehlke.computer.kurzweil.model.Startable;

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

    @Getter
    private final SimulatedEvolutionStateService stateService;

    @Getter
    private final SimulatedEvolutionWorldLattice worldLattice;

    @Getter
    private final SimulatedEvolutionStatistics statisticsContainer;

  public SimulatedEvolutionWorld(
      SimulatedEvolutionStateService stateService
  ) {
      this.stateService = stateService;
      this.worldLattice = new SimulatedEvolutionWorldLattice(this.stateService);
      this.statisticsContainer = new SimulatedEvolutionStatistics(this.stateService.getCtx());
      /**
       * Create the initial Population of Bacteria Cells and give them their position in the World.
       */
      cells = new ArrayList<>();
      for (int i = 0; i < this.stateService.getCtx().getProperties().getEvolution().getPopulation().getInitialPopulation(); i++) {
          Cell cell = new Cell(this.stateService.getCtx());
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
        stateService.toggleGardenOfEden();
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
}
