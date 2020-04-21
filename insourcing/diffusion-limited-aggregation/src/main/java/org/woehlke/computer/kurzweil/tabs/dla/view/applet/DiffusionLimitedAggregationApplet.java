package org.woehlke.computer.kurzweil.tabs.dla.view.applet;

import org.woehlke.computer.kurzweil.tabs.dla.DiffusionLimitedAggregation;
import org.woehlke.computer.kurzweil.tabs.dla.control.ControllerThread;
import org.woehlke.computer.kurzweil.tabs.dla.model.Particles;
import org.woehlke.computer.kurzweil.tabs.dla.model.Point;
import org.woehlke.computer.kurzweil.tabs.dla.view.WorldCanvas;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.Serializable;

/**
 * Diffusion Limited Aggregation.
 *
 * (C) 2006 - 2013 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/diffusion-limited-aggregation.html
 * @author Thomas Woehlke
 *
 * Date: 04.02.2006
 * Time: 18:33:14
 */
public class DiffusionLimitedAggregationApplet extends JApplet implements
    ImageObserver, MenuContainer, Serializable, Accessible, DiffusionLimitedAggregation {

    static final long serialVersionUID = 242L;


    private Label title = new Label(TITLE);
    private ControllerThread controllerThread;
    private WorldCanvas canvas;
    private Particles particles;

    public void init() {
        int scale = 2;
        int width = 320 * scale;
        int height = 234 * scale;
        this.setLayout(new BorderLayout());
        this.add(title, BorderLayout.NORTH);
        Point worldDimensions = new Point(width,height);
        particles = new Particles(worldDimensions);
        canvas = new WorldCanvas(worldDimensions,particles);
        this.add(canvas, BorderLayout.CENTER);
        controllerThread = new ControllerThread(canvas,particles);
        controllerThread.start();
    }

    public void destroy() {
    }

    public void stop() {
    }

    public Point getCanvasDimensions() {
        return canvas.getWorldDimensions();
    }
}
