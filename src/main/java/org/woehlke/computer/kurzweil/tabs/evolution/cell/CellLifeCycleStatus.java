package org.woehlke.computer.kurzweil.tabs.evolution.cell;

import java.awt.Color;

import static java.awt.Color.*;

/**
 * The Status of the Cell's LifeCycle.
 * It is Displayed as Color of the Cell.
 *
 * @author Thomas Woehlke
 * Date: 25.08.13
 * Time: 12:40
 * @see CellLifeCycle
 * <p>
 * Simulated Evolution.
 * Artificial Life Simulation of Bacteria Motion depending on DNA.
 * <p>
 * &copy; 2006 - 2008 Thomas Woehlke.
 * http://thomas-woehlke.de/p/simulated-evolution/
 */
public enum CellLifeCycleStatus {

  YOUNG(BLUE, WHITE),
  YOUNG_AND_FAT(YELLOW, BLACK),
  FULL_AGE(RED, BLACK),
  HUNGRY(LIGHT_GRAY, BLACK),
  OLD(DARK_GRAY, WHITE),
  DEAD(BLACK, BLACK),
  POPULATION(WHITE,BLACK);

  private Color color;
  private Color colorFont;

  CellLifeCycleStatus(Color color, Color colorFont) {
    this.color = color;
    this.colorFont = colorFont;
  }

  public Color getColor() {
    return color;
  }

  public Color getColorForeground() {
        return colorFont;
    }
    public Color getColorBackground() {
        return color;
    }

}
