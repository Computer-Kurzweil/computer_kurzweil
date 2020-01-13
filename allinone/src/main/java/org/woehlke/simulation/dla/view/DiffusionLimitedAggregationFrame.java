package org.woehlke.simulation.dla.view;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.allinone.model.LatticePoint;
import org.woehlke.simulation.allinone.view.PanelSubtitle;
import org.woehlke.simulation.cca.model.CyclicCellularAutomatonContext;
import org.woehlke.simulation.dla.control.DiffusionLimitedAggregationControllerThread;
import org.woehlke.simulation.dla.model.DiffusionLimitedAggregationWorld;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.ImageObserver;
import java.io.Serializable;

import static org.woehlke.simulation.dla.config.DiffusionLimitedAggregationPropertiesI.SUBTITLE;
import static org.woehlke.simulation.dla.config.DiffusionLimitedAggregationPropertiesI.TITLE;

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
@Log
@Component
public class DiffusionLimitedAggregationFrame extends JFrame implements ImageObserver,
        MenuContainer,
        Serializable,
        Accessible,
        WindowListener {

    private PanelSubtitle subtitle = new PanelSubtitle(SUBTITLE);
    private DiffusionLimitedAggregationControllerThread controllerThread;
    private DiffusionLimitedAggregationCanvas canvas;
    private DiffusionLimitedAggregationWorld particles;
    private final CyclicCellularAutomatonContext ctx;

    @Autowired
    public DiffusionLimitedAggregationFrame(CyclicCellularAutomatonContext ctx) {
        super(TITLE);
        this.ctx=ctx;
        init();
        setBounds(100, 100, getCanvasDimensions().getX(), getCanvasDimensions().getY() + 30);
        pack();
        setVisible(true);
        toFront();
        addWindowListener(this);
    }

    public void init() {

        this.setLayout(new BorderLayout());
        this.add(subtitle, BorderLayout.NORTH);
        LatticePoint worldDimensions = ctx.getWorldDimensions();
        particles = new DiffusionLimitedAggregationWorld(this.ctx);
        canvas = new DiffusionLimitedAggregationCanvas(worldDimensions,particles);
        this.add(canvas, BorderLayout.CENTER);
        controllerThread = new DiffusionLimitedAggregationControllerThread(canvas,particles);
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
