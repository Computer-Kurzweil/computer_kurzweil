package org.woehlke.simulation.evolution.config;

import org.woehlke.simulation.evolution.model.Point;

import java.beans.Transient;

/**
 * TODO write doc.
 */
public class WorldConfig implements WorldConfigDefault {

  private final int width;
  private final int height;
  private final int scale;
  private final int initialPopulation;

  public WorldConfig() {
    this.width = WIDTH;
    this.height = HEIGHT;
    this.scale = SCALE;
    this.initialPopulation = INITIAL_POPULATION;
  }

  @Transient
  public Point getWorldDimensions() {
    return new Point(
      this.getWidth() * this.getScale(),
      this.getHeight() * this.getScale()
    );
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public int getScale() {
    return scale;
  }

  public int getInitialPopulation() {
    return initialPopulation;
  }
}
