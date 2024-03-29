package org.woehlke.computer.kurzweil.tabs.simulatedevolution.canvas;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.model.LatticeNeighbourhood;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.config.SimulatedEvolution;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.config.SimulatedEvolutionContext;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.SimulatedEvolutionModel;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolutionTab;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.cell.Cell;
import org.woehlke.computer.kurzweil.commons.tabs.TabCanvas;
import org.woehlke.computer.kurzweil.commons.model.LatticePoint;
import org.woehlke.computer.kurzweil.commons.layouts.LayoutCanvas;

import javax.swing.JComponent;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;

import static org.woehlke.computer.kurzweil.tabs.simulatedevolution.canvas.WorldColor.COLOR_FOOD;
import static org.woehlke.computer.kurzweil.tabs.simulatedevolution.canvas.WorldColor.COLOR_WATER;


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
@ToString(callSuper = true, exclude={"tabCtx","border","preferredSize","layout","tab"})
@EqualsAndHashCode(callSuper=true, exclude={"tabCtx","border","preferredSize","layout","tab"})
public class SimulatedEvolutionCanvas extends JComponent implements TabCanvas, SimulatedEvolution {

    private static final long serialVersionUID = 7526471155622776147L;

    private final SimulatedEvolutionContext tabCtx;
    private final Border border;
    private final LayoutCanvas layout;
    private final Dimension preferredSize;
    private final SimulatedEvolutionTab tab;
    private final SimulatedEvolutionModel tabModel;

    private final static int startX = 0;
    private final static int startY = 0;
    private final int worldX;
    private final int worldY;

    public SimulatedEvolutionCanvas(
        SimulatedEvolutionContext tabCtx
    ) {
        this.tabCtx = tabCtx;
        this.tab = tabCtx.getTab();
        this.tabModel = new SimulatedEvolutionModel(this.tabCtx);
        this.border = this.tabCtx.getCtx().getCanvasBorder();
        this.worldX = this.tabCtx.getCtx().getWorldDimensions().getWidth();
        this.worldY = this.tabCtx.getCtx().getWorldDimensions().getHeight();
        this.preferredSize = new Dimension(worldX,worldY);
        this.layout = new LayoutCanvas(this);
        this.setLayout(layout);
        this.setBackground(COLOR_WATER.getColor());
        this.setSize(preferredSize);
        this.setPreferredSize(preferredSize);
        this.setMinimumSize(preferredSize);
        this.setMaximumSize(preferredSize);
    }

  /**
   * Paint the World on the Canvas and show Food and Bacteria Cells.
   *
   * @param graphics Graphics Context with the Tools for Painting.
   */
  public void paint(Graphics graphics) {
    super.paintComponent(graphics);
      //log.info("paint START (Graphics graphics)");
      //log.info("paint Background (Graphics graphics)");
      // paint Background
      graphics.setColor(COLOR_WATER.getColor());
      graphics.fillRect(startX, startY, worldX, worldY);
      // paint Food
      //log.info("paint Food (Graphics graphics)");
      graphics.setColor(COLOR_FOOD.getColor());
      int posX;
      int posY;
      for (posY = 0; posY < worldY; posY++) {
          for (posX = 0; posX < worldX; posX++) {
              if (tabModel.hasFood(posX, posY)) {
                  graphics.drawLine(posX, posY, posX, posY);
              }
          }
      }
      // paint Population
      //log.info("paint Population (Graphics graphics)");
      List<Cell> population = tabModel.getAllCells();
      for (Cell cell : population) {
          posX = cell.getPosition().getX();
          posY = cell.getPosition().getY();
          LatticePoint[] square = LatticeNeighbourhood.get(worldX,worldY,posX,posY);
          graphics.setColor(cell.getLifeCycleStatus().getColor());
          for (LatticePoint pixel : square) {
              graphics.drawLine(pixel.getX(), pixel.getY(), pixel.getX(), pixel.getY());
          }
      }
      //log.info("paint DONE (Graphics graphics)");
  }

  public void update(Graphics graphics) {
      //log.info("update (Graphics graphics)");
      this.setSize(preferredSize);
      paint(graphics);
      //log.info("updated (Graphics graphics)");
  }

    public void update() {
        Graphics graphics = this.getGraphics();
        this.update(graphics);
    }

    @Override
    public void showMe() {
        //log.info("showMe");
        //log.info("this: "+this.toString());
    }

}
