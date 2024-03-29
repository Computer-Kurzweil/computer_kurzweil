package org.woehlke.computer.kurzweil.tabs.simulatedevolution;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.tabs.TabCanvas;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.Cell;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.WorldPoint;

import javax.swing.*;
import java.awt.*;
import java.util.List;


/**
 * View for the World Data Model for Displaying Food and Bacteria Cells.
 *
 * &copy; 2006 - 2008 Thomas Woehlke.
 * http://thomas-woehlke.de/p/simulated-evolution/
 * @author Thomas Woehlke
 * Date: 05.02.2006
 * Time: 00:51:51
 */
@Log
@Getter
public class SimulatedEvolutionCanvas extends JComponent implements TabCanvas, SimulatedEvolution {

    private static final long serialVersionUID = 7526471155622776147L;

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
