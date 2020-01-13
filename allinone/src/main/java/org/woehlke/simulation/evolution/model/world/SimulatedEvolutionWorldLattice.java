package org.woehlke.simulation.evolution.model.world;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.evolution.model.SimulatedEvolutionContext;
import org.woehlke.simulation.all.model.LatticePoint;
import org.woehlke.simulation.evolution.model.cell.CellLifeCycle;


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
public class SimulatedEvolutionWorldLattice {

  /**
   * Grid of World where every Place can have food.
   */
  private int[][] worldMapFoodLattice;

  private final SimulatedEvolutionContext ctx;

  @Autowired
  public SimulatedEvolutionWorldLattice(
      SimulatedEvolutionContext ctx
  ) {
      this.ctx = ctx;
      int x = ctx.getWorldDimensions().getX();
      int y = ctx.getWorldDimensions().getY();
      worldMapFoodLattice = new int[x][y];
      ctx.setLattice(this);
  }

    private void letFoodGrowGardenOfEden() {
        if (ctx.getProperties().getGardenOfEden().getGardenOfEdenEnabled()) {
            int food = 0;
            int gardenOfEdenParts = 3;
            int startX = ( ctx.getWorldDimensions().getX() / gardenOfEdenParts );
            int startY = ( ctx.getWorldDimensions().getY() / gardenOfEdenParts );
            while (food < ctx.getProperties().getGardenOfEden().getFoodPerDay()) {
                food++;
                int posX = ctx.getRandom().nextInt(startX);
                int posY = ctx.getRandom().nextInt(startY);
                posX *= Integer.signum(posX);
                posY *= Integer.signum(posY);
                worldMapFoodLattice[posX + startX][posY + startY]++;
            }
        }
    }

    private void letFoodGrowWorld() {
        int food = 0;
        while (food < ctx.getProperties().getFood().getFoodPerDay()) {
            food++;
            int posX = ctx.getRandom().nextInt(ctx.getWorldDimensions().getX());
            int posY = ctx.getRandom().nextInt(ctx.getWorldDimensions().getY());
            posX *= Integer.signum(posX);
            posY *= Integer.signum(posY);
            worldMapFoodLattice[posX][posY]++;
        }
    }

  /**
   * Delivers new food to random positions.
   */
  public void letFoodGrow() {
      letFoodGrowWorld();
      letFoodGrowGardenOfEden();
  }

  /**
   * TODO write doc.
   */
  public boolean hasFood(int x, int y) {
    return worldMapFoodLattice[x][y] > 0;
  }

  /**
   * Reduces Food in the Grid by eating and delivers the food energy to the eating Cell.
   *
   * @param position where is the food and the eating cell
   * @return the engergy of the food, will be added to cell's fat.
   * @see CellLifeCycle
   */
  public int eat(LatticePoint position) {
    LatticePoint[] neighbourhood = position.getNeighbourhood(ctx.getWorldDimensions());
    int food = 0;
    for (LatticePoint neighbourhoodPosition : neighbourhood) {
      food += worldMapFoodLattice[neighbourhoodPosition.getX()][neighbourhoodPosition.getY()];
      worldMapFoodLattice[neighbourhoodPosition.getX()][neighbourhoodPosition.getY()] = 0;
    }
    return food;
  }

  public void toggleGardenOfEden() {
      ctx.toggleGardenOfEden();
      letFoodGrowGardenOfEden();
  }

}
