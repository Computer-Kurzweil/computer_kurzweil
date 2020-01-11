package org.woehlke.simulation.evolution.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.evolution.config.SimulatedEvolutionProperties;
import org.woehlke.simulation.evolution.control.ObjectRegistry;

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
 * @see WorldMapFood
 * <p>
 * Simulated Evolution.
 * Artificial Life Simulation of Bacteria Motion depending on DNA.
 * <p>
 * &copy; 2006 - 2008 Thomas Woehlke.
 * http://thomas-woehlke.de/p/simulated-evolution/
 */
@Component
public class World {

  /**
   * List of the Simulated Bacteria Cells.
   */
  private List<Cell> cells;

    private final ObjectRegistry ctx;
    private final SimulatedEvolutionProperties simulatedEvolutionProperties;
    private final WorldMapFood worldMapFood;
    private final LifeCycleCountContainer lifeCycleCountContainer;

  /**
   * TODO write doc.
   */
  @Autowired
  public World(ObjectRegistry ctx, SimulatedEvolutionProperties simulatedEvolutionProperties, WorldMapFood worldMapFood, LifeCycleCountContainer lifeCycleCountContainer) {
    this.ctx = ctx;
      this.simulatedEvolutionProperties = simulatedEvolutionProperties;
      this.worldMapFood = worldMapFood;
      this.lifeCycleCountContainer = lifeCycleCountContainer;
      cells = new ArrayList<>();
    createPopulation();
  }

  /**
   * Create the initial Population of Bacteria Cells and give them their position in the World.
   */
  private void createPopulation() {
    LifeCycleCount lifeCycleCount = new LifeCycleCount();
    for (int i = 0; i < simulatedEvolutionProperties.getInitialPopulation(); i++) {
      int worldMapFoodX = ctx.getRandom().nextInt(simulatedEvolutionProperties.getWidth());
      int worldMapFoodY = ctx.getRandom().nextInt(simulatedEvolutionProperties.getHeight());
      worldMapFoodX *= Integer.signum(worldMapFoodX);
      worldMapFoodY *= Integer.signum(worldMapFoodY);
      Point position = new Point(worldMapFoodX, worldMapFoodY);
      Cell cell = new Cell(simulatedEvolutionProperties.getWorldDimensions(), position, ctx.getRandom());
      cells.add(cell);
    }
    for (Cell cell : cells) {
      lifeCycleCount.countStatusOfOneCell(cell.getLifeCycleStatus());
    }
    System.out.println(lifeCycleCount);
      lifeCycleCountContainer.add(lifeCycleCount);
  }

  /**
   * One Step of Time in the World in which the Population of Bacteria Cell perform Life.
   * Every Cell moves, eats, dies of hunger, and it has sex. splitting into two children with changed DNA.
   */
  public void letLivePopulation() {
    LifeCycleCount lifeCycleCount = new LifeCycleCount();
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
    for (Cell cell : cells) {
      lifeCycleCount.countStatusOfOneCell(cell.getLifeCycleStatus());
    }
      lifeCycleCountContainer.add(lifeCycleCount);
  }

  public List<Cell> getAllCells() {
    return cells;
  }

  public boolean hasFood(int x, int y) {
    return worldMapFood.hasFood(x, y);
  }

}
