package org.woehlke.simulation.evolution.config;


public class GuiConfig implements GuiConfigDefault {

  private final String title;
  private final String subtitle;
  private final String footer;
  private final Integer scale;
  private final Integer width;
  private final Integer height;

  public GuiConfig() {
    this.title = TITLE;
    this.scale = SCALE;
    this.width = WIDTH;
    this.height = HEIGHT;
    this.subtitle = SUB_TITLE;
    this.footer = COPYRIGHT;
  }


  public String getTitle() {
    return title;
  }

  public String getSubtitle() {
    return subtitle;
  }

  public String getFooter() {
    return footer;
  }

  public int getScale() {
    return scale;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

}
