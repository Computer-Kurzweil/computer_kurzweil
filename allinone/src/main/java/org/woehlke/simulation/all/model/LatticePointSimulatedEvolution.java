package org.woehlke.simulation.all.model;

import org.woehlke.simulation.evolution.model.SimulatedEvolutionContext;

/**
 * A Point is used to define the Position of Cell or as a Vector for defining Dimensions.
 * <p>
 * Simulated Evolution.
 * Artificial Life Simulation of Bacteria Motion depending on DNA.
 * <p>
 * &copy; 2006 - 2008 Thomas Woehlke.
 * http://thomas-woehlke.de/p/simulated-evolution/
 *
 * @author Thomas Woehlke
 * Date: 04.02.2006
 * Time: 23:47:05
 */
public class LatticePointSimulatedEvolution {

  /**
   * Horizontal X-Coordinate. Also used as Width;
   */
  private int x = 0;

  /**
   * Vertical Y-Coordinate. Also used as Height;
   */
  private int y = 0;

      public LatticePointSimulatedEvolution(int x, int y) {
        this.x = x;
        this.y = y;
      }

    public LatticePointSimulatedEvolution(SimulatedEvolutionContext ctx) {
        this.x = ctx.getRandom().nextInt(ctx.getProperties().getLattice().getWidth());
        this.y = ctx.getRandom().nextInt(ctx.getProperties().getLattice().getHeight());
        this.normalize(ctx.getWorldDimensions());
        this.absoluteValue();
    }

    public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getWidth() {
    return x;
  }

  public void setWidth(int width) {
    this.x = width;
  }

  public int getHeight() {
    return y;
  }

  public void setHeight(int height) {
    this.y = height;
  }

  public void absoluteValue() {
      x *= Integer.signum(x);
      y *= Integer.signum(y);
  }

  public void add(LatticePointSimulatedEvolution p) {
    this.x += p.getX();
    this.y += p.getY();
    absoluteValue();
  }

  public void normalize(LatticePointSimulatedEvolution p) {
    this.x %= p.getX();
    this.y %= p.getY();
  }


  /**
   * Get Neighbourhood.
   *
   * @param max - limit the dimensions of the world around.
   * @return The Set of Points belonging to the Neighbourhood of the position given by this Point Object.
   */
  public LatticePointSimulatedEvolution[] getNeighbourhood(LatticePointSimulatedEvolution max) {
    LatticePointSimulatedEvolution neighbourhood[] = new LatticePointSimulatedEvolution[9];
    int maxX = max.getX();
    int maxY = max.getY();
    neighbourhood[0] = new LatticePointSimulatedEvolution((this.x + maxX - 1) % maxX, (this.y + maxY - 1) % maxY);
    neighbourhood[1] = new LatticePointSimulatedEvolution((this.x + maxX - 1) % maxX, this.y);
    neighbourhood[2] = new LatticePointSimulatedEvolution((this.x + maxX - 1) % maxX, (this.y + maxY + 1) % maxY);
    neighbourhood[3] = new LatticePointSimulatedEvolution(this.x, (this.y + maxY - 1) % maxY);
    neighbourhood[4] = new LatticePointSimulatedEvolution(this.x, this.y);
    neighbourhood[5] = new LatticePointSimulatedEvolution(this.x, (this.y + maxY + 1) % maxY);
    neighbourhood[6] = new LatticePointSimulatedEvolution((this.x + maxX + 1) % maxX, (this.y + maxY - 1) % maxY);
    neighbourhood[7] = new LatticePointSimulatedEvolution((this.x + maxX + 1) % maxX, this.y);
    neighbourhood[8] = new LatticePointSimulatedEvolution((this.x + maxX + 1) % maxX, (this.y + maxY + 1) % maxY);
    return neighbourhood;
  }
}
