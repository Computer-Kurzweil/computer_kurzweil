package org.woehlke.computer.kurzweil.tabs.evolution;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.tabs.evolution.model.Cell;
import org.woehlke.computer.kurzweil.tabs.evolution.widgets.SimulatedEvolutionButtonRowPanel;
import org.woehlke.computer.kurzweil.tabs.evolution.widgets.SimulatedEvolutionStatisticsPanel;
import org.woehlke.computer.kurzweil.commons.tabs.TabCanvas;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.model.LatticePoint;
import org.woehlke.computer.kurzweil.tabs.evolution.model.SimulatedEvolutionModel;
import org.woehlke.computer.kurzweil.widgets.layouts.CanvasLayout;

import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.List;

import static org.woehlke.computer.kurzweil.tabs.evolution.widgets.SimulatedEvolutionWorldColor.COLOR_FOOD;
import static org.woehlke.computer.kurzweil.tabs.evolution.widgets.SimulatedEvolutionWorldColor.COLOR_WATER;


/**
 * View for the World Data Model for Displaying Food and Bacteria Cells.
 * <p>
 * Simulated Evolution.
 * Artificial Life Simulation of Bacteria Motion depending on DNA.
 * <p>
 * &copy; 2006 - 2018 Thomas Woehlke.
 * http://thomas-woehlke.de/p/simulated-evolution/
 *
 * @author Thomas Woehlke
 * Date: 05.02.2006
 * Time: 00:51:51
 */
@Log
@Getter
public class SimulatedEvolutionCanvas extends JComponent implements
    Serializable, TabCanvas, Startable {

    private static final long serialVersionUID = -27002509360079509L;
    private final static int startX = 0;
    private final static int startY = 0;
    private final int worldX;
    private final int worldY;
    private final SimulatedEvolutionModel world;
    private final SimulatedEvolutionStatisticsPanel statisticsPanel;
    private final SimulatedEvolutionButtonRowPanel panelButtons;
    private final SimulatedEvolutionContext tabCtx;

    public SimulatedEvolutionCanvas(
        SimulatedEvolutionContext tabCtx
    ) {
        this.tabCtx = tabCtx;
        this.world = new SimulatedEvolutionModel(this.tabCtx);
        this.statisticsPanel = new SimulatedEvolutionStatisticsPanel(this.tabCtx);
        this.panelButtons = new SimulatedEvolutionButtonRowPanel(this.tabCtx);
        this.setLayout(new CanvasLayout(this));
        this.setBackground(COLOR_WATER.getColor());
        this.worldX = this.tabCtx.getCtx().getWorldDimensions().getWidth();
        this.worldY = this.tabCtx.getCtx().getWorldDimensions().getHeight();
        Dimension preferredSize = new Dimension(worldX,worldY);
        this.setPreferredSize(preferredSize);
    }

  /**
   * Paint the World on the Canvas and show Food and Bacteria Cells.
   *
   * @param graphics Graphics Context with the Tools for Painting.
   */
  public void paint(Graphics graphics) {
    super.paintComponent(graphics);
       // paintBackground(graphics);
      graphics.setColor(COLOR_WATER.getColor());
      graphics.fillRect(startX, startY, worldX, worldY);
    //paintFood(graphics);
      graphics.setColor(COLOR_FOOD.getColor());
      for (int posY = 0; posY < worldY; posY++) {
          for (int posX = 0; posX < worldX; posX++) {
              if (world.hasFood(posX, posY)) {
                  graphics.drawLine(posX, posY, posX, posY);
              }
          }
      }
    //paintPopulation(graphics);
      graphics.setColor(COLOR_FOOD.getColor());
      for (int posY = 0; posY <  worldY; posY++) {
          for (int posX = 0; posX < worldX; posX++) {
              if (world.hasFood(posX, posY)) {
                  graphics.drawLine(posX, posY, posX, posY);
              }
          }
      }
  }

    private void paintPopulation(Graphics graphics){
        List<Cell> population = world.getAllCells();
        for (Cell cell : population) {
            LatticePoint[] square = cell.getPosition().getNeighbourhood(this.tabCtx.getCtx().getWorldDimensions());
            graphics.setColor(cell.getLifeCycleStatus().getColor());
            for (LatticePoint pixel : square) {
                graphics.drawLine(pixel.getX(), pixel.getY(), pixel.getX(), pixel.getY());
            }
        }
    }

    private void paintFood(Graphics graphics){
        graphics.setColor(COLOR_FOOD.getColor());
        for (int posY = 0; posY < worldY; posY++) {
            for (int posX = 0; posX < worldX; posX++) {
                if (world.hasFood(posX, posY)) {
                    graphics.drawLine(posX, posY, posX, posY);
                }
            }
        }
    }

  private void paintBackground(Graphics graphics){
      graphics.setColor(COLOR_WATER.getColor());
      graphics.fillRect(startX, startY, worldX, worldY);
  }

  public void update(Graphics graphics) {
    Dimension preferredSize = new Dimension(worldX, worldY);
    this.setPreferredSize(preferredSize);
    paint(graphics);
  }

    @Override
    public void showMe() {
        this.repaint();
    }


    @Override
    public void update() {
        repaint();
    }

    @Override
    public void start() {
        this.world.start();
    }

    @Override
    public void stop() {
        this.world.stop();
    }
}
