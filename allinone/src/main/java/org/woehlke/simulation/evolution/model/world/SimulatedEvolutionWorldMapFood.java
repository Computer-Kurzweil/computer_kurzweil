package org.woehlke.simulation.evolution.model.world;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.evolution.config.SimulatedEvolutionProperties;
import org.woehlke.simulation.evolution.control.SimulatedEvolutionContext;
import org.woehlke.simulation.evolution.model.Point;
import org.woehlke.simulation.evolution.model.cell.LifeCycle;


/**
 * Map of World where every Place can have food needed by the Bacteria Cells for eating.
 * <p>
 * Simulated Evolution.
 * Artificial Life Simulation of Bacteria Motion depending on DNA.
 * <p>
 * &copy; 2006 - 2013 Thomas Woehlke.
 * http://thomas-woehlke.de/p/simulated-evolution/
 *
 * @author Thomas Woehlke
 * Date: 24.08.13
 * Time: 12:37
 */
@Component
public class SimulatedEvolutionWorldMapFood {

  /**
   * Grid of World where every Place can have food.
   */
  private int[][] worldMapFood;

  private final SimulatedEvolutionProperties simulatedEvolutionProperties;
  private final SimulatedEvolutionContext simulatedEvolutionContext;

  @Autowired
  public SimulatedEvolutionWorldMapFood(SimulatedEvolutionProperties simulatedEvolutionProperties, SimulatedEvolutionContext simulatedEvolutionContext) {
      this.simulatedEvolutionProperties = simulatedEvolutionProperties;
      this.simulatedEvolutionContext = simulatedEvolutionContext;
      int x = simulatedEvolutionProperties.getWorldDimensions().getX();
      int y = simulatedEvolutionProperties.getWorldDimensions().getY();
      worldMapFood = new int[x][y];
  }

  /**
   * Delivers new food to random positions.
   */
  public void letFoodGrow() {
    int food = 0;
    while (food < simulatedEvolutionProperties.getFoodPerDay()) {
      food++;
      int posX = simulatedEvolutionContext.getRandom().nextInt(simulatedEvolutionProperties.getWorldDimensions().getX());
      int posY = simulatedEvolutionContext.getRandom().nextInt(simulatedEvolutionProperties.getWorldDimensions().getY());
      posX *= Integer.signum(posX);
      posY *= Integer.signum(posY);
      worldMapFood[posX][posY]++;
    }
    if (simulatedEvolutionProperties.getGardenOfEdenEnabled()) {
      food = 0;
      int gardenOfEdenParts = 5;
      int gardenOfEdenPartsPadding = 2;
      int startX = ( simulatedEvolutionProperties.getWorldDimensions().getX() / gardenOfEdenParts ) * gardenOfEdenPartsPadding;
      int startY = ( simulatedEvolutionProperties.getWorldDimensions().getY() / gardenOfEdenParts ) * gardenOfEdenPartsPadding;
      while (food < simulatedEvolutionProperties.getGardenOfEdenFoodPerDay()) {
        food++;
        int posX = simulatedEvolutionContext.getRandom().nextInt(startX);
        int posY = simulatedEvolutionContext.getRandom().nextInt(startY);
        posX *= Integer.signum(posX);
        posY *= Integer.signum(posY);
        worldMapFood[posX + startX][posY + startY]++;
      }
    }
  }

  /**
   * TODO write doc.
   */
  public boolean hasFood(int x, int y) {
    return worldMapFood[x][y] > 0;
  }

  /**
   * Reduces Food in the Grid by eating and delivers the food energy to the eating Cell.
   *
   * @param position where is the food and the eating cell
   * @return the engergy of the food, will be added to cell's fat.
   * @see LifeCycle
   */
  public int eat(Point position) {
    Point[] neighbourhood = position.getNeighbourhood(simulatedEvolutionProperties.getWorldDimensions());
    int food = 0;
    for (Point neighbourhoodPosition : neighbourhood) {
      food += worldMapFood[neighbourhoodPosition.getX()][neighbourhoodPosition.getY()];
      worldMapFood[neighbourhoodPosition.getX()][neighbourhoodPosition.getY()] = 0;
    }
    return food;
  }

  public void toggleGardenOfEden() {
    if (!simulatedEvolutionProperties.getGardenOfEdenEnabled()) {
      int startx = simulatedEvolutionProperties.getWorldDimensions().getX() / 5;
      int starty = simulatedEvolutionProperties.getWorldDimensions().getY() / 5;
      for (int posX = 0; posX < startx; posX++) {
        for (int posY = 0; posY < starty; posY++) {
          worldMapFood[posX + startx * 2][posY + starty * 2] = 0;
        }
      }
    }
  }

}
