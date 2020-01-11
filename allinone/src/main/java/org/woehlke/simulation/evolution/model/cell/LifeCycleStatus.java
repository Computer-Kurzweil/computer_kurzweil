package org.woehlke.simulation.evolution.model.cell;

import java.awt.Color;

/**
 * The Status of the Cell's LifeCycle.
 * It is Displayed as Color of the Cell.
 *
 * @author Thomas Woehlke
 * Date: 25.08.13
 * Time: 12:40
 * @see LifeCycle
 * <p>
 * Simulated Evolution.
 * Artificial Life Simulation of Bacteria Motion depending on DNA.
 * <p>
 * &copy; 2006 - 2008 Thomas Woehlke.
 * http://thomas-woehlke.de/p/simulated-evolution/
 */
public enum LifeCycleStatus {

  YOUNG(java.awt.Color.BLUE),
  YOUNG_AND_FAT(java.awt.Color.YELLOW),
  FULL_AGE(java.awt.Color.RED),
  HUNGRY(java.awt.Color.LIGHT_GRAY),
  OLD(java.awt.Color.DARK_GRAY),
  DEAD(java.awt.Color.BLACK);

  private Color color;

  LifeCycleStatus(Color color) {
    this.color = color;
  }

  public Color getColor() {
    return color;
  }
}
