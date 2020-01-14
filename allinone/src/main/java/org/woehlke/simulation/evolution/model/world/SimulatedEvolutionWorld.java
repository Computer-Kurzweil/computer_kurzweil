package org.woehlke.simulation.evolution.model.world;


import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.evolution.model.SimulatedEvolutionContext;
import org.woehlke.simulation.allinone.model.LatticePoint;
import org.woehlke.simulation.evolution.model.statistics.SimulatedEvolutionPopulationCensus;
import org.woehlke.simulation.evolution.model.statistics.SimulatedEvolutionStatistics;
import org.woehlke.simulation.evolution.model.cell.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
@Component
public class SimulatedEvolutionWorld {

    /**
    * List of the Simulated Bacteria Cells.
    */
    private List<Cell> cells;

    private final SimulatedEvolutionContext ctx;

    @Getter
    private final SimulatedEvolutionWorldLattice worldLattice;

    @Getter
    private final SimulatedEvolutionStatistics statisticsContainer;

  public SimulatedEvolutionWorld(
      SimulatedEvolutionContext ctx
  ) {
      this.ctx = ctx;
      this.worldLattice = new SimulatedEvolutionWorldLattice(this.ctx);
      this.statisticsContainer = new SimulatedEvolutionStatistics(this.ctx);
      /**
       * Create the initial Population of Bacteria Cells and give them their position in the World.
       */
      cells = new ArrayList<>();
      for (int i = 0; i < ctx.getProperties().getCellPopulation().getInitialPopulation(); i++) {
          Cell cell = new Cell(ctx);
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
  public void letLivePopulation() {
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
        ctx.toggleGardenOfEden();
        worldLattice.toggleGardenOfEden();
    }

}
