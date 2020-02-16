package org.woehlke.computer.kurzweil.tabs.evolution;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.model.LatticeNeighbourhood;
import org.woehlke.computer.kurzweil.model.LatticePoint;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.tabs.evolution.cell.CellLifeCycle;


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
@Getter
public class SimulatedEvolutionWorldLattice implements Startable {

  /**
   * Grid of World where every Place can have food.
   */
  @ToString.Exclude
  private int[][] worldMapFoodLattice;

    @ToString.Exclude
  private SimulatedEvolutionContext appCtx;

    private final int gardenOfEdenParts = 3;
    private final int noFood = 0;
    private final static int startX = 0;
    private final static int startY = 0;
    private final int worldX;
    private final int worldY;

    private boolean gardenOfEdenEnabled;

  public SimulatedEvolutionWorldLattice(
      SimulatedEvolutionContext appCtx
  ) {
      this.appCtx = appCtx;
      worldX =  this.appCtx.getCtx().getWorldDimensions().getX();
      worldY =  this.appCtx.getCtx().getWorldDimensions().getY();
      worldMapFoodLattice = new int[worldX][worldY];
      gardenOfEdenEnabled = this.appCtx.getCtx().getProperties().getEvolution().getGardenOfEden().getGardenOfEdenEnabled();
  }

  private void resetLattice(){
      int x;
      int y;
      for(y = 0; y < worldY; y++){
        for(x = 0; x < worldX; x++){
            worldMapFoodLattice[x][y]=0;
        }
    }
  }

    private void letFoodGrowGardenOfEden() {
        log.info("letFoodGrowGardenOfEden");
        if (gardenOfEdenEnabled) {
            int food = 0;

            int startX = ( worldX / gardenOfEdenParts );
            int startY = ( worldY / gardenOfEdenParts );
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
        log.info("letFoodGrowWorld");
        int food = 0;
        final int foodPerDay = this.appCtx.getCtx().getProperties().getEvolution().getFood().getFoodPerDay();
        while (food < foodPerDay) {
            food++;
            int newFoodPosX = this.appCtx.getCtx().getRandom().nextInt(worldX);
            int newFoodPosY = this.appCtx.getCtx().getRandom().nextInt(worldY);
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
    LatticePoint[] neighbourhood = LatticeNeighbourhood.get(
        worldX,worldY,position.getX(),position.getY()
    );
    int food = 0;
    for (LatticePoint neighbourhoodPosition : neighbourhood) {
      food += worldMapFoodLattice[neighbourhoodPosition.getX()][neighbourhoodPosition.getY()];
      worldMapFoodLattice[neighbourhoodPosition.getX()][neighbourhoodPosition.getY()] = noFood;
    }
    return food;
  }

  public void toggleGardenOfEden() {
      log.info("toggleGardenOfEden");
      this.gardenOfEdenEnabled = ! gardenOfEdenEnabled;
      this.letFoodGrowGardenOfEden();
      log.info("toggleGardenOfEden done");
  }

    @Override
    public void start() {
        log.info("start");
        resetLattice();
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stop");
    }

}
