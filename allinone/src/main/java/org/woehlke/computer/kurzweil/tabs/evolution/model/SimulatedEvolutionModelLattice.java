package org.woehlke.computer.kurzweil.tabs.evolution.model;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.model.LatticePoint;
import org.woehlke.computer.kurzweil.tabs.evolution.SimulatedEvolutionContext;
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
public class SimulatedEvolutionModelLattice implements Startable {

  /**
   * Grid of World where every Place can have food.
   */
  private int[][] worldMapFoodLattice;

  @Getter private SimulatedEvolutionContext appCtx;

  private final  int x;
    private final  int y;

  public SimulatedEvolutionModelLattice(
      SimulatedEvolutionContext appCtx
  ) {
      this.appCtx = appCtx;
      x =  this.appCtx.getCtx().getWorldDimensions().getX();
      y =  this.appCtx.getCtx().getWorldDimensions().getY();
      worldMapFoodLattice = new int[x][y];
  }

  private void resetLattice(){
    for(int iY = 0; iY < y; iY++){
        for(int iX = 0; iX < x; iX++){
            worldMapFoodLattice[iX][iY]=0;
        }
    }
  }

    private void letFoodGrowGardenOfEden() {
        if (this.appCtx.getCtx().getProperties().getEvolution().getGardenOfEden().getGardenOfEdenEnabled()) {
            int food = 0;
            int gardenOfEdenParts = 3;
            int startX = ( x / gardenOfEdenParts );
            int startY = ( y / gardenOfEdenParts );
            while (food < this.appCtx.getCtx().getProperties().getEvolution().getGardenOfEden().getFoodPerDay()) {
                food++;
                int posX = this.appCtx.getCtx().getRandom().nextInt(startX);
                int posY = this.appCtx.getCtx().getRandom().nextInt(startY);
                posX *= Integer.signum(posX);
                posY *= Integer.signum(posY);
                worldMapFoodLattice[posX + startX][posY + startY]++;
            }
        }
    }

    private void letFoodGrowWorld() {
        int food = 0;
        final int foodPerDay = this.appCtx.getCtx().getProperties().getEvolution().getFood().getFoodPerDay();
        while (food < foodPerDay) {
            food++;
            int newFoodPosX = this.appCtx.getCtx().getRandom().nextInt(x);
            int newFoodPosY = this.appCtx.getCtx().getRandom().nextInt(y);
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
        this.appCtx.getCtx().getWorldDimensions()
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
        resetLattice();
    }

    @Override
    public void stop() {

    }

}
