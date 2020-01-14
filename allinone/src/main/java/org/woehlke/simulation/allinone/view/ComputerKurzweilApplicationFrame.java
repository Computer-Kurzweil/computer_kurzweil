package org.woehlke.simulation.allinone.view;

import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.allinone.model.ComputerKurzweilApplicationContext;
import org.woehlke.simulation.allinone.view.parts.Bounds;
import org.woehlke.simulation.cca.view.CyclicCellularAutomatonFrame;
import org.woehlke.simulation.dla.view.DiffusionLimitedAggregationFrame;
import org.woehlke.simulation.evolution.view.SimulatedEvolutionFrame;
import org.woehlke.simulation.mandelbrot.view.MandelbrotFrame;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.ImageObserver;
import java.io.Serializable;

@Log
@Component
public class ComputerKurzweilApplicationFrame extends JFrame implements
    MenuContainer,
    ImageObserver,
    Serializable,
    Accessible, WindowListener {

    private final Bounds bounds;

    @Getter
    private final ComputerKurzweilApplicationContext ctx;

    @Getter
    private final SimulatedEvolutionFrame simulatedEvolutionFrame;
    /*
    @Getter
    private final MandelbrotFrame mandelbrotFrame;
    @Getter
    private final CyclicCellularAutomatonFrame cyclicCellularAutomatonFrame;
    @Getter
    private final DiffusionLimitedAggregationFrame diffusionLimitedAggregationFrame;
*/
    @Autowired
    public ComputerKurzweilApplicationFrame(
        ComputerKurzweilApplicationContext ctx,
        SimulatedEvolutionFrame simulatedEvolutionFrame/*,
        MandelbrotFrame mandelbrotFrame,
        CyclicCellularAutomatonFrame cyclicCellularAutomatonFrame,
        DiffusionLimitedAggregationFrame diffusionLimitedAggregationFrame*/
    ) throws HeadlessException {
        super(ctx.getProperties().getView().getTitle());
        this.ctx = ctx;
        this.simulatedEvolutionFrame = simulatedEvolutionFrame;/*
        this.mandelbrotFrame = mandelbrotFrame;
        this.cyclicCellularAutomatonFrame = cyclicCellularAutomatonFrame;
        this.diffusionLimitedAggregationFrame = diffusionLimitedAggregationFrame;*/
        BoxLayout layout = new BoxLayout(rootPane, BoxLayout.Y_AXIS);
        rootPane.setLayout(layout);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add(
            this.simulatedEvolutionFrame.getCtx().getProperties().getView().getTitle(),
            this.simulatedEvolutionFrame
        );
        //tabbedPane.add(this.mandelbrotFrame.getCtx().getProperties().getTitle(), this.mandelbrotFrame);
        //tabbedPane.add(this.cyclicCellularAutomatonFrame.getCtx().getProperties().getTitle(), this.cyclicCellularAutomatonFrame);
        //tabbedPane.add(this.diffusionLimitedAggregationFrame.getCtx().getProperties().getTitle(), this.diffusionLimitedAggregationFrame);
        rootPane.add(tabbedPane);
        pack();
        double height = rootPane.getHeight();
        double width = rootPane.getWidth();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        bounds = new Bounds(height,width,screenSize);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(this);
        showMe();
    }

    /**
     * TODO write doc.
     */
    public void showMe() {
        pack();
        this.setBounds(
            bounds.getMystartX(),
            bounds.getMystartY(),
            bounds.getMywidth(),
            bounds.getMyheight()
        );
        rootPane.setVisible(true);
        this.setVisible(true);
        toFront();
        repaint();
    }

    public void exit() {
        this.dispose();
    }

    public void start() {
        this.getSimulatedEvolutionFrame().start();
    }

    public void repaint(){
        super.repaint();
        this.getSimulatedEvolutionFrame().repaint();
    }

    public void windowOpened(WindowEvent e) {
        showMe();
    }

    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    public void windowClosed(WindowEvent e) {
        System.exit(0);
    }

    public void windowIconified(WindowEvent e) { }

    public void windowDeiconified(WindowEvent e) {
        showMe();
    }

    public void windowActivated(WindowEvent e) {
        toFront();
    }

    public void windowDeactivated(WindowEvent e) {
    }
}
