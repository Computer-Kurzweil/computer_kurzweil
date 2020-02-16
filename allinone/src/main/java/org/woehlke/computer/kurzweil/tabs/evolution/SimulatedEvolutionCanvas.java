package org.woehlke.computer.kurzweil.tabs.evolution;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.tabs.evolution.model.Cell;
import org.woehlke.computer.kurzweil.tabs.evolution.widgets.SimulatedEvolutionButtonRowPanel;
import org.woehlke.computer.kurzweil.tabs.evolution.widgets.SimulatedEvolutionStatisticsPanel;
import org.woehlke.computer.kurzweil.commons.tabs.TabCanvas;
import org.woehlke.computer.kurzweil.model.LatticePoint;
import org.woehlke.computer.kurzweil.tabs.evolution.model.SimulatedEvolutionModel;
import org.woehlke.computer.kurzweil.widgets.layouts.CanvasLayout;

import javax.swing.JComponent;
import javax.swing.border.CompoundBorder;
import java.awt.Dimension;
import java.awt.Graphics;
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
    private final SimulatedEvolutionTab tab;
    private final SimulatedEvolutionModel world;
    private final SimulatedEvolutionStatisticsPanel statisticsPanel;
    private final SimulatedEvolutionButtonRowPanel panelButtons;
    private final SimulatedEvolutionContext tabCtx;
    private final CompoundBorder border;
    private final Dimension preferredSize;
    private final CanvasLayout layout;

    public SimulatedEvolutionCanvas(
        SimulatedEvolutionTab tab
    ) {
        this.tab = tab;
        this.tabCtx = tab.getTabCtx();
        this.worldX = this.tabCtx.getCtx().getWorldDimensions().getWidth();
        this.worldY = this.tabCtx.getCtx().getWorldDimensions().getHeight();
        this.border = this.tabCtx.getCtx().getBorder();
        this.world = new SimulatedEvolutionModel(this.tabCtx);
        this.statisticsPanel = new SimulatedEvolutionStatisticsPanel(this.tabCtx);
        this.panelButtons = new SimulatedEvolutionButtonRowPanel(this.tabCtx);
        this.preferredSize = new Dimension(worldX,worldY);
        this.layout = new CanvasLayout(this);
        this.setBorder(border);
        this.setLayout(layout);
        this.setBackground(COLOR_WATER.getColor());
        this.setSize(preferredSize);
        this.setPreferredSize(preferredSize);
    }

  /**
   * Paint the World on the Canvas and show Food and Bacteria Cells.
   *
   * @param graphics Graphics Context with the Tools for Painting.
   */
  public void paint(Graphics graphics) {
    super.paintComponent(graphics);
      log.info("paint START (Graphics graphics)");
      log.info("paint Background (Graphics graphics)");
      // paint Background
      graphics.setColor(COLOR_WATER.getColor());
      graphics.fillRect(startX, startY, worldX, worldY);
      // paint Food
      log.info("paint Food (Graphics graphics)");
      graphics.setColor(COLOR_FOOD.getColor());
      int posX;
      int posY;
      for (posY = 0; posY < worldY; posY++) {
          for (posX = 0; posX < worldX; posX++) {
              if (world.hasFood(posX, posY)) {
                  graphics.drawLine(posX, posY, posX, posY);
              }
          }
      }
      // paint Population
      log.info("paint Population (Graphics graphics)");
      List<Cell> population = world.getAllCells();
      for (Cell cell : population) {
          posX = cell.getPosition().getX();
          posY = cell.getPosition().getY();
          LatticePoint[] square = LatticePoint.getNeighbourhood(worldX,worldY,posX,posY);
          graphics.setColor(cell.getLifeCycleStatus().getColor());
          for (LatticePoint pixel : square) {
              graphics.drawLine(pixel.getX(), pixel.getY(), pixel.getX(), pixel.getY());
          }
      }
      log.info("paint DONE (Graphics graphics)");
  }

  public void update(Graphics graphics) {
      log.info("update (Graphics graphics)");
      this.setSize(preferredSize);
      paint(graphics);
      log.info("updated (Graphics graphics)");
  }

    @Override
    public void showMe() {
        log.info("showMe");
    }

    @Override
    public void update() {
        log.info("update");
        String getFoodPerDay = tabCtx.getSimulatedEvolution().getFoodPerDay()+"";
        boolean selected = tabCtx.getSimulatedEvolution().isGardenOfEdenEnabled();
        this.panelButtons.getFoodPanel().getFoodPerDayField().setText(getFoodPerDay);
        this.panelButtons.getGardenOfEdenPanel().getGardenOfEdenEnabled().setSelected(selected);
        log.info("updated");
    }

    @Override
    public void start() {
        log.info("start");
        this.world.start();
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stop");
        this.world.stop();
        log.info("stopped");
    }
}
