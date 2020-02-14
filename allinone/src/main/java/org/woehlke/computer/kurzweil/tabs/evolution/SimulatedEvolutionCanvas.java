package org.woehlke.computer.kurzweil.tabs.evolution;

import lombok.Getter;
import lombok.extern.java.Log;
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
    Serializable, TabCanvas, ActionListener {

    private static final long serialVersionUID = -27002509360079509L;
    private final static int startX = 0;
    private final static int startY = 0;
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
        Dimension preferredSize = new Dimension(
            this.tabCtx.getCtx().getWorldDimensions().getWidth(),
            this.tabCtx.getCtx().getWorldDimensions().getHeight()
        );
        this.setPreferredSize(preferredSize);
        this.panelButtons.getFoodPanel().getButtonFoodPerDayIncrease().addActionListener(this);
        this.panelButtons.getFoodPanel().getButtonFoodPerDayDecrease().addActionListener(this);
        this.panelButtons.getGardenOfEdenPanel().getButtonToggleGardenOfEden().addActionListener(this);
    }

  /**
   * Paint the World on the Canvas and show Food and Bacteria Cells.
   *
   * @param graphics Graphics Context with the Tools for Painting.
   */
  public void paint(Graphics graphics) {
    super.paintComponent(graphics);
    //paintBackground(graphics);
      graphics.setColor(COLOR_WATER.getColor());
      graphics.fillRect(startX, startY,
          this.tabCtx.getCtx().getWorldDimensions().getWidth(),
          this.tabCtx.getCtx().getWorldDimensions().getHeight()
      );
    //paintFood(graphics);
      graphics.setColor(COLOR_FOOD.getColor());
      for (int posY = 0; posY <  this.tabCtx.getCtx().getWorldDimensions().getHeight(); posY++) {
          for (int posX = 0; posX <  this.tabCtx.getCtx().getWorldDimensions().getWidth(); posX++) {
              if (world.hasFood(posX, posY)) {
                  graphics.drawLine(posX, posY, posX, posY);
              }
          }
      }
    //paintPopulation(graphics);
      graphics.setColor(COLOR_FOOD.getColor());
      for (int posY = 0; posY <  this.tabCtx.getCtx().getWorldDimensions().getHeight(); posY++) {
          for (int posX = 0; posX < this.tabCtx.getCtx().getWorldDimensions().getWidth(); posX++) {
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
        for (int posY = 0; posY < this.tabCtx.getCtx().getWorldDimensions().getHeight(); posY++) {
            for (int posX = 0; posX < this.tabCtx.getCtx().getWorldDimensions().getWidth(); posX++) {
                if (world.hasFood(posX, posY)) {
                    graphics.drawLine(posX, posY, posX, posY);
                }
            }
        }
    }

  private void paintBackground(Graphics graphics){
      graphics.setColor(COLOR_WATER.getColor());
      graphics.fillRect(
          startX, startY,
          this.tabCtx.getCtx().getWorldDimensions().getWidth(),
          this.tabCtx.getCtx().getWorldDimensions().getHeight()
      );
  }

  public void update(Graphics graphics) {
    Dimension preferredSize = new Dimension(
        this.tabCtx.getCtx().getWorldDimensions().getWidth(),
        this.tabCtx.getCtx().getWorldDimensions().getHeight()
    );
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

    //TODO: move to TabCtx Object.
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.panelButtons.getFoodPanel().getButtonFoodPerDayIncrease()) {
            tabCtx.increaseFoodPerDay();
            this.panelButtons.getFoodPanel().getFoodPerDayField().setText(
                tabCtx.getSimulatedEvolution().getFoodPerDay()+""
            );
        } else if (ae.getSource() == this.panelButtons.getFoodPanel().getButtonFoodPerDayDecrease()) {
            tabCtx.decreaseFoodPerDay();
            this.panelButtons.getFoodPanel().getFoodPerDayField().setText(
                tabCtx.getSimulatedEvolution().getFoodPerDay()+""
            );
        } else if (ae.getSource() == this.panelButtons.getGardenOfEdenPanel().getButtonToggleGardenOfEden()) {
            tabCtx.toggleGardenOfEden();
            this.world.toggleGardenOfEden();
            boolean selected = tabCtx.getSimulatedEvolution().isGardenOfEdenEnabled();
            this.panelButtons.getGardenOfEdenPanel().getGardenOfEdenEnabled().setSelected(selected);
        }
    }
}