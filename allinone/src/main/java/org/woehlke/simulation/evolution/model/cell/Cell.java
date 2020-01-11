package org.woehlke.simulation.evolution.model.cell;

import org.woehlke.simulation.evolution.config.SimulatedEvolutionProperties;
import org.woehlke.simulation.evolution.model.SimulatedEvolutionContext;
import org.woehlke.simulation.evolution.model.*;

import java.util.Random;

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
 * @see LifeCycle
 * @see LifeCycleStatus
 * <p>
 * &copy; 2006 - 2008 Thomas Woehlke.
 * http://thomas-woehlke.de/p/simulated-evolution/
 */
public class Cell {

  private static final long serialVersionUID = -7194182402841173981L;

  /**
   * Contains the DNA for Random based Moving.
   */
  private CellCore cellCore;

  /**
   * The Cell's state is position, orientation and LifeCycle.
   */
  private Point position;

  /**
   * The Cell's state is position, orientation and LifeCycle.
   */
  private Orientation orientation;

  /**
   * The Cell's state is position, orientation and LifeCycle.
   */
  private LifeCycle lifeCycle;

  private SimulatedEvolutionProperties properties;
  private SimulatedEvolutionContext ctx;

  public Cell(
      Point position,
      SimulatedEvolutionProperties properties,
      SimulatedEvolutionContext ctx
  ) {
    this.position = new Point(position);
    this.ctx = ctx;
    this.properties = properties;
    this.lifeCycle = new LifeCycle(properties);
    this.cellCore = new CellCore(ctx);
    this.position.setX(ctx.getRandom().nextInt() % properties.getWorldDimensions().getX());
    this.position.setY(ctx.getRandom().nextInt() % properties.getWorldDimensions().getY());
    this.position.absoluteValue();
    this.orientation = getRandomOrientation();

  }

  private Cell(
      int fat,
      CellCore rna,
      Point position,
      SimulatedEvolutionProperties properties,
      SimulatedEvolutionContext ctx
  ) {
      this.properties=properties;
      this.lifeCycle = new LifeCycle(fat, properties);
    this.position = new Point(position);
    this.ctx = ctx;
    this.cellCore = rna;
    orientation = getRandomOrientation();
  }

  private Orientation getRandomOrientation() {
    int dnaLength = Orientation.values().length;
    int dnaBase = this.ctx.getRandom().nextInt(dnaLength);
    if (dnaBase < 0) {
      dnaBase *= -1;
    }
    return Orientation.values()[dnaBase];
  }

  private void getNextOrientation() {
    Orientation randomOrientation = cellCore.getRandomOrientation();
    int iOrientation = orientation.ordinal();
    int iRandomOrientation = randomOrientation.ordinal();
    int newOrientation = (iOrientation + iRandomOrientation) % Orientation.values().length;
    orientation = Orientation.values()[newOrientation];
  }

  /**
   * The Cell moves on the Step in a Direction choosen by Random and DNA.
   */
  public void move() {
    if (lifeCycle.move()) {
      getNextOrientation();
      position.add(orientation.getMove());
      position.add(properties.getWorldDimensions());
      position.normalize(properties.getWorldDimensions());
    }
  }

  /**
   * After performing Reproduction by Cell Division this Cell is one of the two Children this Method
   * returns the other Child.
   *
   * @return the other Child.
   * @see CellCore#performMitosis()
   */
  public Cell performReproductionByCellDivision() {
    CellCore rna = cellCore.performMitosis();
    lifeCycle.haveSex();
    Cell child = new Cell(lifeCycle.getFat(), rna, position, properties, ctx);
    return child;
  }

  /**
   * @return The new Position after the last move.
   */
  public Point getPosition() {
    return position;
  }

  /**
   * @return true, if this Cell is able to perform Reproduction by Cell Division.
   */
  public boolean isPregnant() {
    return lifeCycle.isPregnant();
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
  public LifeCycleStatus getLifeCycleStatus() {
    return lifeCycle.getLifeCycleStatus();
  }

}
