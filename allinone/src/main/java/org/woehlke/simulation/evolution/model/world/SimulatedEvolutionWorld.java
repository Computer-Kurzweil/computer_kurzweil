package org.woehlke.simulation.evolution.model.world;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.evolution.config.SimulatedEvolutionProperties;
import org.woehlke.simulation.evolution.model.SimulatedEvolutionContext;
import org.woehlke.simulation.evolution.model.Point;
import org.woehlke.simulation.evolution.model.statistics.SimulatedEvolutionWorldStatistics;
import org.woehlke.simulation.evolution.model.statistics.SimulatedEvolutionWorldStatisticsContainer;
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
 * @see SimulatedEvolutionWorldMapFood
 * <p>
 * Simulated Evolution.
 * Artificial Life Simulation of Bacteria Motion depending on DNA.
 * <p>
 * &copy; 2006 - 2008 Thomas Woehlke.
 * http://thomas-woehlke.de/p/simulated-evolution/
 */
@Component
public class SimulatedEvolutionWorld {

  /**
   * List of the Simulated Bacteria Cells.
   */
  private List<Cell> cells;

    private final SimulatedEvolutionContext ctx;
    private final SimulatedEvolutionProperties properties;
    private final SimulatedEvolutionWorldMapFood worldMapFood;
    private final SimulatedEvolutionWorldStatisticsContainer statisticsContainer;

  /**
   * TODO write doc.
   */
  @Autowired
  public SimulatedEvolutionWorld(
      SimulatedEvolutionContext ctx,
      SimulatedEvolutionProperties simulatedEvolutionProperties,
      SimulatedEvolutionWorldMapFood simulatedEvolutionWorldMapFood,
      SimulatedEvolutionWorldStatisticsContainer simulatedEvolutionWorldStatisticsContainer
  ) {
      this.ctx = ctx;
      this.properties = simulatedEvolutionProperties;
      this.worldMapFood = simulatedEvolutionWorldMapFood;
      this.statisticsContainer = simulatedEvolutionWorldStatisticsContainer;
      cells = new ArrayList<>();
      createPopulation();
  }

  /**
   * Create the initial Population of Bacteria Cells and give them their position in the World.
   */
  private void createPopulation() {
    for (int i = 0; i < properties.getInitialPopulation(); i++) {
      int worldMapFoodX = ctx.getRandom().nextInt(properties.getWidth());
      int worldMapFoodY = ctx.getRandom().nextInt(properties.getHeight());
      worldMapFoodX *= Integer.signum(worldMapFoodX);
      worldMapFoodY *= Integer.signum(worldMapFoodY);
      Point position = new Point(worldMapFoodX, worldMapFoodY);
      Cell cell = new Cell(position,properties, ctx);
      cells.add(cell);
    }
    SimulatedEvolutionWorldStatistics simulatedEvolutionWorldStatistics = new SimulatedEvolutionWorldStatistics();
    for (Cell cell : cells) {
      simulatedEvolutionWorldStatistics.countStatusOfOneCell(cell.getLifeCycleStatus());
    }
    log.info(simulatedEvolutionWorldStatistics.toString());
    statisticsContainer.add(simulatedEvolutionWorldStatistics);
  }

  /**
   * One Step of Time in the World in which the Population of Bacteria Cell perform Life.
   * Every Cell moves, eats, dies of hunger, and it has sex. splitting into two children with changed DNA.
   */
  public void letLivePopulation() {
    worldMapFood.letFoodGrow();
    Point pos;
    List<Cell> children = new ArrayList<>();
    List<Cell> died = new ArrayList<>();
    for (Cell cell : cells) {
      cell.move();
      if (cell.died()) {
        died.add(cell);
      } else {
        pos = cell.getPosition();
        int food = worldMapFood.eat(pos);
        cell.eat(food);
        if (cell.isPregnant()) {
          Cell child = cell.performReproductionByCellDivision();
          children.add(child);
        }
      }
    }
    for (Cell dead : died) {
      cells.remove(dead);
    }
    cells.addAll(children);
    SimulatedEvolutionWorldStatistics oneStatisticsTimestamp = new SimulatedEvolutionWorldStatistics();
    for (Cell cell : cells) {
        oneStatisticsTimestamp.countStatusOfOneCell(cell.getLifeCycleStatus());
    }
    statisticsContainer.add(oneStatisticsTimestamp);
  }

  public List<Cell> getAllCells() {
    return cells;
  }

  public boolean hasFood(int x, int y) {
    return worldMapFood.hasFood(x, y);
  }

    public void toggleGardenOfEden() {
        ctx.toggleGardenOfEden();
        worldMapFood.toggleGardenOfEden();
    }

    private static Logger log = Logger.getLogger(SimulatedEvolutionWorld.class.getName());
}
