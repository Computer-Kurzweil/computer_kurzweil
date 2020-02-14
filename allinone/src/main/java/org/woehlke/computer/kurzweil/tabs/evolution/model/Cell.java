package org.woehlke.computer.kurzweil.tabs.evolution.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.woehlke.computer.kurzweil.model.LatticePoint;
import org.woehlke.computer.kurzweil.tabs.evolution.SimulatedEvolutionContext;

import java.io.Serializable;

/**
 * The Cell of one Bacterium.
 * It's state is position, orientation and LifeCycle.
 * The Cell has a CellCore with the DNA Genome for Moving around.
 * <p>
 * Simulated Evolution.
 * Artificial Life Simulation of Bacteria Motion depending on DNA.
 *
 * @author Thomas Woehlke
 * Date: 04.02.2006
 * Time: 19:06:43
 * @see CellCore
 * @see CellLifeCycle
 * @see CellLifeCycleStatus
 * <p>
 * &copy; 2006 - 2008 Thomas Woehlke.
 * http://thomas-woehlke.de/p/simulated-evolution/
 */
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Cell implements Serializable {

  private static final long serialVersionUID = -7194182402841173981L;

  /**
   * Contains the DNA for Random based Moving.
   */
  private CellCore cellCore;

  /**
   * The Cell's state is position, orientation and LifeCycle.
   */
  private LatticePoint position;

  /**
   * The Cell's state is position, orientation and LifeCycle.
   */
  private CellOrientation orientation;

  /**
   * The Cell's state is position, orientation and LifeCycle.
   */
  private CellLifeCycle lifeCycle;

  private final SimulatedEvolutionContext appCtx;

  public Cell(
      SimulatedEvolutionContext appCtx
  ) {
    this.appCtx = appCtx;
    this.position = this.appCtx.getCtx().getNextRandomLatticePoint();
    this.lifeCycle =  this.appCtx.getCtx().getNewCellLifeCycle();
    this.cellCore = this.appCtx.getCtx().getNewCellCore();
    this.orientation = getRandomOrientation();
  }

    private Cell(Cell other) {
        this.appCtx = other.appCtx;
        this.position = other.position;
        this.lifeCycle = other.lifeCycle.reproduction();
        this.cellCore = other.cellCore.reproductionMitosis();
        this.orientation = this.getRandomOrientation();
    }

  private CellOrientation getRandomOrientation() {
    int dnaBase = this.appCtx.getCtx().getRandom().nextInt(CellOrientation.values().length);
    return CellOrientation.values()[(dnaBase < 0)?(dnaBase * -1):dnaBase];
  }

  private void getNextOrientation() {
    CellOrientation randomOrientation = cellCore.getRandomOrientation();
    int iOrientation = orientation.ordinal();
    int iRandomOrientation = randomOrientation.ordinal();
    int newOrientation = (iOrientation + iRandomOrientation) % CellOrientation.values().length;
    orientation = CellOrientation.values()[newOrientation];
  }

  /**
   * The Cell moves on the Step in a Direction choosen by Random and DNA.
   */
  public void move() {
    if (lifeCycle.move()) {
      getNextOrientation();
      position.add(orientation.getMove());
      position.add(this.appCtx.getCtx().getWorldDimensions());
      position.normalize(this.appCtx.getCtx().getWorldDimensions());
    }
  }

  /**
   * After performing Reproduction by Cell Division this Cell is one of the two Children this Method
   * returns the other Child.
   *
   * @return the other Child.
   * @see CellCore#reproductionMitosis()
   */
  public Cell reproductionByCellDivision() {
    Cell child = new Cell(this);
    return child;
  }

  /**
   * @return The new Position after the last move.
   */
  public LatticePoint getPosition() {
    return position;
  }

  /**
   * @return true, if this Cell is able to perform Reproduction by Cell Division.
   */
  public boolean isAbleForReproduction() {
    return lifeCycle.isAbleForReproduction();
  }

  /**
   * Eat the available Food in this Position.
   *
   * @param food the available Food in this Position.
   */
  public void eat(int food) {
    lifeCycle.eat(food);
  }

  /**
   * @return true, if this Cell died of hunger.
   */
  public boolean died() {
    return lifeCycle.isDead();
  }

  /**
   * @return the LifeCycleStatus of this Cell.
   */
  public CellLifeCycleStatus getLifeCycleStatus() {
    return lifeCycle.getStatus();
  }

}
