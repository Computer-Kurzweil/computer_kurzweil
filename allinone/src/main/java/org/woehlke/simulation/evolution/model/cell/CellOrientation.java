package org.woehlke.simulation.evolution.model.cell;

import org.woehlke.simulation.allinone.model.lattice.LatticePoint;

/**
 * Orientation defines the new position after next move.
 * <p>
 * Simulated Evolution.
 * Artificial Life Simulation of Bacteria Motion depending on DNA.
 * <p>
 * &copy; 2006 - 2008 Thomas Woehlke.
 * http://thomas-woehlke.de/p/simulated-evolution/
 *
 * @author Thomas Woehlke
 * Date: 04.02.2006
 * Time: 19:50:51
 */
public enum CellOrientation {

  FORWARD(0, 2),
  HARD_RIGHT(2, 1),
  SOFT_RIGHT(2, -1),
  BACKWARDS(0, -2),
  SOFT_LEFT(-2, -1),
  HARD_LEFT(-2, 1);

  private LatticePoint move;

  public LatticePoint getMove() {
    return move;
  }

  CellOrientation(int x, int y) {
    move = new LatticePoint(x, y);
  }

}
