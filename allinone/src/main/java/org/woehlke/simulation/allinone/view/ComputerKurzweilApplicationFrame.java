package org.woehlke.simulation.allinone.view;

import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.allinone.model.ComputerKurzweilApplicationContext;
import org.woehlke.simulation.evolution.view.SimulatedEvolutionFrame;
import org.woehlke.simulation.mandelbrot.view.MandelbrotFrame;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.Serializable;

@Log
@Component
public class ComputerKurzweilApplicationFrame extends JFrame implements MenuContainer, ImageObserver, Serializable, Accessible {

    private final Bounds bounds;

    @Getter
    private final ComputerKurzweilApplicationContext ctx;

    @Autowired
    public ComputerKurzweilApplicationFrame(
        ComputerKurzweilApplicationContext ctx,
        SimulatedEvolutionFrame simulatedEvolutionFrame,
        MandelbrotFrame mandelbrotFrame
    ) throws HeadlessException {
        super(ctx.getProperties().getView().getTitle());
        this.ctx = ctx;
        this.ctx.setMandelbrotFrame(mandelbrotFrame);
        this.ctx.setSimulatedEvolutionFrame(simulatedEvolutionFrame);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(simulatedEvolutionFrame);
        pack();
        double height = rootPane.getHeight();
        double width = rootPane.getWidth();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        bounds = new Bounds(height,width,screenSize);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
        this.ctx.getSimulatedEvolutionFrame().start();
    }

    public void repaint(){
        super.repaint();
        this.ctx.getSimulatedEvolutionFrame().repaint();
    }
}
