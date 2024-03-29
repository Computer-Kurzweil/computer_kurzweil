package org.woehlke.computer.kurzweil.simulated.evolution.view.canvas;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.simulated.evolution.model.cell.Cell;
import org.woehlke.computer.kurzweil.simulated.evolution.model.world.WorldPoint;
import org.woehlke.computer.kurzweil.simulated.evolution.model.SimulatedEvolutionModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;


/**
 * View for the World Data Model for Displaying Food and Bacteria Cells.
 *
 * &copy; 2006 - 2008 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see <a href="https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html">Blog Article</a>
 * @see <a href="https://github.com/Computer-Kurzweil/simulated-evolution">Github Repository</a>
 * @see <a href="https://java.woehlke.org/simulated-evolution/">Maven Project Repository</a>
 *
 * Date: 05.02.2006
 * Time: 00:51:51
 */
@Log4j2
@Getter
public class SimulatedEvolutionCanvas extends JComponent {

    static final long serialVersionUID = 242L;

    /**
     * Reference to the Data Model.
     */
    @Setter
    private SimulatedEvolutionModel tabModel;

    private WorldPoint worldDimensions;

    private final Color WATER = Color.BLACK;
    private final Color FOOD = Color.GREEN;

    public SimulatedEvolutionCanvas(WorldPoint worldDimensions) {
        this.worldDimensions = worldDimensions;
        this.setBackground(WATER);
        this.setSize(this.worldDimensions.getX(), this.worldDimensions.getY());
    }

    /**
     * Paint the World on the Canvas and show Food and Bacteria Cells.
     * @param g Graphics Context with the Tools for Painting.
     */
    public void paint(Graphics g) {
        super.paintComponent(g);
        int width = worldDimensions.getX();
        int height = worldDimensions.getY();
        g.setColor(WATER);
        g.fillRect(0,0,width,height);
        g.setColor(FOOD);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (tabModel.hasFood(x, y)) {
                    g.drawLine(x,y,x,y);
                }
            }
        }
        List<Cell> population = tabModel.getAllCells();
        for (Cell p:population) {
            WorldPoint[] square = p.getPosition().getNeighbourhood(worldDimensions);
            g.setColor(p.getLifeCycleStatus().getColor());
            for(WorldPoint pixel:square){
                g.drawLine(pixel.getX(),pixel.getY(),pixel.getX(),pixel.getY());
            }
        }
    }

    public void update(Graphics g) {
        paint(g);
    }

}
