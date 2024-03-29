package org.woehlke.computer.kurzweil.tabs.simulatedevolution;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.WorldPoint;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.Serializable;

/**
 * The Container for running the Simulation.
 * It containes a World Data Model, a Controller Thread and a WorldCanvas View.
 *
 * (C) 2013 Thomas Woehlke.
 * http://thomas-woehlke.de/p/simulated-evolution/
 * @author Thomas Woehlke
 * Date: 04.02.2006
 * Time: 18:33:14
 */
@Log
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("deprecated")
public class SimulatedEvolutionApplet extends JApplet implements ImageObserver, MenuContainer, Serializable, Accessible, SimulatedEvolution {

    private static final long serialVersionUID = 7526471155622776147L;

    private Label title = new Label("      Artificial Life Simulation of Bacteria Motion depending on DNA - (C) 2013 Thomas Woehlke");

    /**
     * ControllerThread for Interachtions between Model and View (MVC-Pattern).
     */
    private SimulatedEvolutionController simulatedEvolutionController;

    /**
     * The View for the World. Food and Cells are painted to the Canvas.
     */
    private SimulatedEvolutionCanvas canvas;

    /**
     * Data Model for the Simulation. The World contains the Bacteria Cells and the Food.
     */
    private SimulatedEvolutionModel simulatedEvolutionModel;

    public void init() {
        int scale = 2;
        int width = 320 * scale;
        int height = 234 * scale;
        this.setLayout(new BorderLayout());
        this.add(title, BorderLayout.NORTH);
        simulatedEvolutionController = new SimulatedEvolutionController();
        WorldPoint worldDimensions = new WorldPoint(width,height);
        simulatedEvolutionModel = new SimulatedEvolutionModel(worldDimensions);
        canvas = new SimulatedEvolutionCanvas(worldDimensions);
        canvas.setTabModel(simulatedEvolutionModel);
        this.add(canvas, BorderLayout.CENTER);
        simulatedEvolutionController.setCanvas(canvas);
        simulatedEvolutionController.setSimulatedEvolutionModel(simulatedEvolutionModel);
        simulatedEvolutionController.start();
    }

    public void destroy() {
    }

    public void stop() {
    }

    public WorldPoint getCanvasDimensions() {
        return canvas.getWorldDimensions();
    }
}
