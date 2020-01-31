package org.woehlke.simulation.allinone.model.lattice;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.simulation.allinone.config.ComputerKurzweilApplicationContext;

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
@Log
@ToString
@EqualsAndHashCode
public class LatticePoint {

  /**
   * Horizontal X-Coordinate. Also used as Width;
   */
  private int x = 0;

  /**
   * Vertical Y-Coordinate. Also used as Height;
   */
  private int y = 0;


    public LatticePoint() {
    }

    public LatticePoint(LatticePoint other) {
        this.x = other.getX();
        this.y = other.getY();
    }

      public LatticePoint(int x, int y) {
        this.x = x;
        this.y = y;
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

  public void killNagative() {
        absoluteValue();
    }

  public void add(LatticePoint p) {
    this.x += p.getX();
    this.y += p.getY();
    absoluteValue();
  }

  public void normalize(LatticePoint p) {
    this.x %= p.getX();
    this.y %= p.getY();
  }

    public void moveUp() {
        y--;
    }

    public void moveRight() {
        x++;
    }

    public void moveDown() {
        y++;
    }

    public void moveLeft() {
        x--;
    }


    public LatticePoint copy() {
        return new LatticePoint(this);
    }

    public static LatticePoint start(LatticePoint worldDimensions){
        return new LatticePoint((worldDimensions.getX()-2),(worldDimensions.getY()/2+11));
    }

  /**
   * Get Neighbourhood.
   *
   * @param max - limit the dimensions of the world around.
   * @return The Set of Points belonging to the Neighbourhood of the position given by this Point Object.
   */
  public LatticePoint[] getNeighbourhood(LatticePoint max) {
    LatticePoint neighbourhood[] = new LatticePoint[9];
    int maxX = max.getX();
    int maxY = max.getY();
    neighbourhood[0] = new LatticePoint((this.x + maxX - 1) % maxX, (this.y + maxY - 1) % maxY);
    neighbourhood[1] = new LatticePoint((this.x + maxX - 1) % maxX, this.y);
    neighbourhood[2] = new LatticePoint((this.x + maxX - 1) % maxX, (this.y + maxY + 1) % maxY);
    neighbourhood[3] = new LatticePoint(this.x, (this.y + maxY - 1) % maxY);
    neighbourhood[4] = new LatticePoint(this.x, this.y);
    neighbourhood[5] = new LatticePoint(this.x, (this.y + maxY + 1) % maxY);
    neighbourhood[6] = new LatticePoint((this.x + maxX + 1) % maxX, (this.y + maxY - 1) % maxY);
    neighbourhood[7] = new LatticePoint((this.x + maxX + 1) % maxX, this.y);
    neighbourhood[8] = new LatticePoint((this.x + maxX + 1) % maxX, (this.y + maxY + 1) % maxY);
    return neighbourhood;
  }

    public LatticePoint[] getNeighbourhood(ComputerKurzweilApplicationContext ctx) {
        return this.getNeighbourhood(ctx.getWorldDimensions());
    }
}
