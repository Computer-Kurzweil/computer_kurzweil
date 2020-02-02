package org.woehlke.computer.kurzweil.apps.evolution.view.widgets;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.apps.evolution.model.SimulatedEvolutionStateService;
import org.woehlke.computer.kurzweil.apps.evolution.model.cell.Cell;
import org.woehlke.computer.kurzweil.model.LatticePoint;
import org.woehlke.computer.kurzweil.apps.evolution.model.world.SimulatedEvolutionWorld;

import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.List;

import static org.woehlke.computer.kurzweil.apps.evolution.model.world.SimulatedEvolutionWorldColor.COLOR_FOOD;
import static org.woehlke.computer.kurzweil.apps.evolution.model.world.SimulatedEvolutionWorldColor.COLOR_WATER;


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
public class SimulatedEvolutionCanvas extends JComponent implements Serializable {

    private static final long serialVersionUID = -27002509360079509L;

    @Getter
    private final SimulatedEvolutionWorld world;

    private final SimulatedEvolutionStateService stateService;

    public SimulatedEvolutionCanvas(
        SimulatedEvolutionStateService stateService
    ) {
        this.stateService = stateService;
        this.world = new SimulatedEvolutionWorld(this.stateService);
        this.setBackground(COLOR_WATER.getColor());
        Dimension preferredSize = new Dimension(
            this.stateService.getCtx().getWorldDimensions().getWidth(),
            this.stateService.getCtx().getWorldDimensions().getHeight()
        );
        this.setPreferredSize(preferredSize);
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
          this.stateService.getCtx().getWorldDimensions().getWidth(),
          this.stateService.getCtx().getWorldDimensions().getHeight()
      );
    //paintFood(graphics);
      graphics.setColor(COLOR_FOOD.getColor());
      for (int posY = 0; posY <  this.stateService.getCtx().getWorldDimensions().getHeight(); posY++) {
          for (int posX = 0; posX <  this.stateService.getCtx().getWorldDimensions().getWidth(); posX++) {
              if (world.hasFood(posX, posY)) {
                  graphics.drawLine(posX, posY, posX, posY);
              }
          }
      }
    //paintPopulation(graphics);
      graphics.setColor(COLOR_FOOD.getColor());
      for (int posY = 0; posY <  this.stateService.getCtx().getWorldDimensions().getHeight(); posY++) {
          for (int posX = 0; posX < this.stateService.getCtx().getWorldDimensions().getWidth(); posX++) {
              if (world.hasFood(posX, posY)) {
                  graphics.drawLine(posX, posY, posX, posY);
              }
          }
      }
  }

    private void paintPopulation(Graphics graphics){
        List<Cell> population = world.getAllCells();
        for (Cell cell : population) {
            LatticePoint[] square = cell.getPosition().getNeighbourhood(this.stateService.getCtx().getWorldDimensions());
            graphics.setColor(cell.getLifeCycleStatus().getColor());
            for (LatticePoint pixel : square) {
                graphics.drawLine(pixel.getX(), pixel.getY(), pixel.getX(), pixel.getY());
            }
        }
    }

    private void paintFood(Graphics graphics){
        graphics.setColor(COLOR_FOOD.getColor());
        for (int posY = 0; posY < this.stateService.getCtx().getWorldDimensions().getHeight(); posY++) {
            for (int posX = 0; posX < this.stateService.getCtx().getWorldDimensions().getWidth(); posX++) {
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
          this.stateService.getCtx().getWorldDimensions().getWidth(),
          this.stateService.getCtx().getWorldDimensions().getHeight()
      );
  }

    private final static int startX = 0;
    private final static int startY = 0;

  public void update(Graphics graphics) {
    Dimension preferredSize = new Dimension(
        this.stateService.getCtx().getWorldDimensions().getWidth(),
        this.stateService.getCtx().getWorldDimensions().getHeight()
    );
    this.setPreferredSize(preferredSize);
    paint(graphics);
  }

}
