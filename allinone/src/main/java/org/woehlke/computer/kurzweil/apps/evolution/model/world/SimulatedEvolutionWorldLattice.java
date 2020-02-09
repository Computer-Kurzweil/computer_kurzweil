package org.woehlke.computer.kurzweil.apps.evolution.model.world;

import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.trashcan.signals.UserSignal;
import org.woehlke.computer.kurzweil.model.LatticePoint;
import org.woehlke.computer.kurzweil.apps.evolution.model.SimulatedEvolutionStateService;
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

  private final SimulatedEvolutionStateService simulatedEvolutionStateService;

  public SimulatedEvolutionWorldLattice(
      SimulatedEvolutionStateService simulatedEvolutionStateService
  ) {
      this.simulatedEvolutionStateService = simulatedEvolutionStateService;
      int x = this.simulatedEvolutionStateService.getCtx().getWorldDimensions().getX();
      int y = this.simulatedEvolutionStateService.getCtx().getWorldDimensions().getY();
      worldMapFoodLattice = new int[x][y];
  }

    private void letFoodGrowGardenOfEden() {
        if (this.simulatedEvolutionStateService.getCtx().getProperties().getEvolution().getGardenOfEden().getGardenOfEdenEnabled()) {
            int food = 0;
            int gardenOfEdenParts = 3;
            int startX = ( this.simulatedEvolutionStateService.getCtx().getWorldDimensions().getX() / gardenOfEdenParts );
            int startY = ( this.simulatedEvolutionStateService.getCtx().getWorldDimensions().getY() / gardenOfEdenParts );
            while (food < this.simulatedEvolutionStateService.getCtx().getProperties().getEvolution().getGardenOfEden().getFoodPerDay()) {
                food++;
                int posX = this.simulatedEvolutionStateService.getCtx().getRandom().nextInt(startX);
                int posY = this.simulatedEvolutionStateService.getCtx().getRandom().nextInt(startY);
                posX *= Integer.signum(posX);
                posY *= Integer.signum(posY);
                worldMapFoodLattice[posX + startX][posY + startY]++;
            }
        }
    }

    private void letFoodGrowWorld() {
        int food = 0;
        final int foodPerDay = this.simulatedEvolutionStateService.getCtx().getProperties().getEvolution().getFood().getFoodPerDay();
        while (food < foodPerDay) {
            food++;
            int newFoodPosX = this.simulatedEvolutionStateService.getCtx().getRandom().nextInt(
                this.simulatedEvolutionStateService.getCtx().getWorldDimensions().getX()
            );
            int newFoodPosY = this.simulatedEvolutionStateService.getCtx().getRandom().nextInt(
                this.simulatedEvolutionStateService.getCtx().getWorldDimensions().getY()
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
        this.simulatedEvolutionStateService.getCtx().getWorldDimensions()
    );
    int food = 0;
    for (LatticePoint neighbourhoodPosition : neighbourhood) {
      food += worldMapFoodLattice[neighbourhoodPosition.getX()][neighbourhoodPosition.getY()];
      worldMapFoodLattice[neighbourhoodPosition.getX()][neighbourhoodPosition.getY()] = 0;
    }
    return food;
  }

  public void toggleGardenOfEden() {
      simulatedEvolutionStateService.toggleGardenOfEden();
      letFoodGrowGardenOfEden();
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
