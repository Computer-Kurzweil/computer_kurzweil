package org.woehlke.computer.kurzweil.apps.evolution.model.world;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.trashcan.signals.UserSignal;
import org.woehlke.computer.kurzweil.model.LatticePoint;
import org.woehlke.computer.kurzweil.apps.evolution.ctx.SimulatedEvolutionContext;
import org.woehlke.computer.kurzweil.apps.evolution.model.cell.CellLifeCycle;
import org.woehlke.computer.kurzweil.commons.Startable;


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
@Log
public class SimulatedEvolutionWorldLattice implements Startable {

  /**
   * Grid of World where every Place can have food.
   */
  private int[][] worldMapFoodLattice;

    @Getter @Setter
    private SimulatedEvolutionContext appCtx;

  @Getter private final ComputerKurzweilApplicationContext ctx;

  public SimulatedEvolutionWorldLattice(
      ComputerKurzweilApplicationContext ctx
  ) {
      this.ctx = ctx;
      int x =  this.ctx.getWorldDimensions().getX();
      int y =  this.ctx.getWorldDimensions().getY();
      worldMapFoodLattice = new int[x][y];
  }

    private void letFoodGrowGardenOfEden() {
        if (this.ctx.getProperties().getEvolution().getGardenOfEden().getGardenOfEdenEnabled()) {
            int food = 0;
            int gardenOfEdenParts = 3;
            int startX = ( this.ctx.getWorldDimensions().getX() / gardenOfEdenParts );
            int startY = ( this.ctx.getWorldDimensions().getY() / gardenOfEdenParts );
            while (food < this.ctx.getProperties().getEvolution().getGardenOfEden().getFoodPerDay()) {
                food++;
                int posX = this.ctx.getRandom().nextInt(startX);
                int posY = this.ctx.getRandom().nextInt(startY);
                posX *= Integer.signum(posX);
                posY *= Integer.signum(posY);
                worldMapFoodLattice[posX + startX][posY + startY]++;
            }
        }
    }

    private void letFoodGrowWorld() {
        int food = 0;
        final int foodPerDay = this.ctx.getProperties().getEvolution().getFood().getFoodPerDay();
        while (food < foodPerDay) {
            food++;
            int newFoodPosX = this.ctx.getRandom().nextInt(
                this.ctx.getWorldDimensions().getX()
            );
            int newFoodPosY = this.ctx.getRandom().nextInt(
                this.ctx.getWorldDimensions().getY()
            );
            newFoodPosX *= Integer.signum(newFoodPosX);
            newFoodPosY *= Integer.signum(newFoodPosY);
            worldMapFoodLattice[newFoodPosX][newFoodPosY]++;
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
    LatticePoint[] neighbourhood = position.getNeighbourhood(
        this.ctx.getWorldDimensions()
    );
    int food = 0;
    for (LatticePoint neighbourhoodPosition : neighbourhood) {
      food += worldMapFoodLattice[neighbourhoodPosition.getX()][neighbourhoodPosition.getY()];
      worldMapFoodLattice[neighbourhoodPosition.getX()][neighbourhoodPosition.getY()] = 0;
    }
    return food;
  }

  public void toggleGardenOfEden() {
      this.appCtx.toggleGardenOfEden();
      this.letFoodGrowGardenOfEden();
  }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    //@Override
    public void handleUserSignal(UserSignal userSignal) {

    }
}
