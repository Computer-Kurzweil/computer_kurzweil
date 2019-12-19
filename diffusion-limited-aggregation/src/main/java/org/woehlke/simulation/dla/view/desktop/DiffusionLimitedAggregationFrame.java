package org.woehlke.simulation.dla.view.desktop;

import org.woehlke.simulation.dla.control.ControllerThread;
import org.woehlke.simulation.dla.model.Particles;
import org.woehlke.simulation.dla.model.LatticePoint;
import org.woehlke.simulation.dla.view.WorldCanvas;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.ImageObserver;
import java.io.Serializable;

import static org.woehlke.simulation.dla.config.ConfigProperties.SUBTITLE;
import static org.woehlke.simulation.dla.config.ConfigProperties.TITLE;

/**
 * Diffusion Limited Aggregation.
 *
 * (C) 2006 - 2013 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/diffusion-limited-aggregation.html
 * @author Thomas Woehlke
 *
 * Date: 04.02.2006
 * Time: 18:47:46
 */
public class DiffusionLimitedAggregationFrame extends JFrame implements ImageObserver,
        MenuContainer,
        Serializable,
        Accessible,
        WindowListener {

    private JLabel subtitle = new JLabel(SUBTITLE);
    private ControllerThread controllerThread;
    private WorldCanvas canvas;
    private Particles particles;

    public DiffusionLimitedAggregationFrame() {
        super(TITLE);
        init();
        setBounds(100, 100, getCanvasDimensions().getX(), getCanvasDimensions().getY() + 30);
        pack();
        setVisible(true);
        toFront();
        addWindowListener(this);
    }

    public void init() {
        int scale = 2;
        int width = 320 * scale;
        int height = 234 * scale;
        this.setLayout(new BorderLayout());
        this.add(subtitle, BorderLayout.NORTH);
        LatticePoint worldDimensions = new LatticePoint(width,height);
        particles = new Particles(worldDimensions);
        canvas = new WorldCanvas(worldDimensions,particles);
        this.add(canvas, BorderLayout.CENTER);
        controllerThread = new ControllerThread(canvas,particles);
        controllerThread.start();
    }

    public void windowOpened(WindowEvent e) {
        setBounds(100, 100, getCanvasDimensions().getX(), getCanvasDimensions().getY() + 30);
        setVisible(true);
        toFront();
    }

    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    public void windowClosed(WindowEvent e) {
        System.exit(0);
    }

    public void windowIconified(WindowEvent e) {

    }

    public void windowDeiconified(WindowEvent e) {
        setBounds(100, 100, getCanvasDimensions().getX(), getCanvasDimensions().getY() + 30);
        setVisible(true);
        toFront();
    }

    public void windowActivated(WindowEvent e) {
        toFront();
    }

    public void windowDeactivated(WindowEvent e) {
    }

    public LatticePoint getCanvasDimensions() {
        return canvas.getWorldDimensions();
    }
}
