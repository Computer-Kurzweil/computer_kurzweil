package org.woehlke.simulation.evolution.view;

import org.springframework.stereotype.Component;
import org.woehlke.simulation.evolution.config.SimulatedEvolutionProperties;
import org.woehlke.simulation.evolution.control.SimulatedEvolutionContext;
import org.woehlke.simulation.evolution.model.cell.Cell;
import org.woehlke.simulation.evolution.model.Point;
import org.woehlke.simulation.evolution.model.world.SimulatedEvolutionWorld;

import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.List;

import static org.woehlke.simulation.evolution.model.world.SimulatedEvolutionWorldColor.COLOR_FOOD;
import static org.woehlke.simulation.evolution.model.world.SimulatedEvolutionWorldColor.COLOR_WATER;


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
@Component
public class SimulatedEvolutionWorldCanvas extends JComponent implements Serializable {

  private static final long serialVersionUID = -27002509360079509L;

  /**
   * Reference to the Data Model.
   */
  private final SimulatedEvolutionContext ctx;
    private final SimulatedEvolutionProperties simulatedEvolutionProperties;
    private final SimulatedEvolutionWorld simulatedEvolutionWorld;

  public SimulatedEvolutionWorldCanvas(SimulatedEvolutionContext ctx, SimulatedEvolutionProperties simulatedEvolutionProperties, SimulatedEvolutionWorld simulatedEvolutionWorld) {
    this.ctx = ctx;
      this.simulatedEvolutionProperties = simulatedEvolutionProperties;
      this.simulatedEvolutionWorld = simulatedEvolutionWorld;
      this.setBackground(COLOR_WATER.getColor());
    Dimension preferredSize = new Dimension(
        this.simulatedEvolutionProperties.getWorldDimensions().getWidth(),
        this.simulatedEvolutionProperties.getWorldDimensions().getHeight()
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
    int width =  this.simulatedEvolutionProperties.getWorldDimensions().getWidth();
    int height = this.simulatedEvolutionProperties.getWorldDimensions().getHeight();
    graphics.setColor(COLOR_WATER.getColor());
    graphics.fillRect(0, 0, width, height);
    graphics.setColor(COLOR_FOOD.getColor());
    for (int posY = 0; posY < height; posY++) {
      for (int posX = 0; posX < width; posX++) {
        if (simulatedEvolutionWorld.hasFood(posX, posY)) {
          graphics.drawLine(posX, posY, posX, posY);
        }
      }
    }
    List<Cell> population = simulatedEvolutionWorld.getAllCells();
    for (Cell cell : population) {
      Point[] square = cell.getPosition().getNeighbourhood(this.simulatedEvolutionProperties.getWorldDimensions());
      graphics.setColor(cell.getLifeCycleStatus().getColor());
      for (Point pixel : square) {
        graphics.drawLine(pixel.getX(), pixel.getY(), pixel.getX(), pixel.getY());
      }
    }
  }

  public void update(Graphics graphics) {
    Dimension preferredSize = new Dimension(
        this.simulatedEvolutionProperties.getWorldDimensions().getWidth(),
        this.simulatedEvolutionProperties.getWorldDimensions().getHeight()
    );
    this.setPreferredSize(preferredSize);
    paint(graphics);
  }

}
