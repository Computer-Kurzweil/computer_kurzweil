package org.woehlke.simulation.all.view;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.all.model.ComputerKurzweilApplicationContext;
import org.woehlke.simulation.evolution.view.SimulatedEvolutionFrame;
import org.woehlke.simulation.mandelbrot.view.MandelbrotFrame;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.Serializable;

@Component
public class ComputerKurzweilApplicationFrame extends JFrame implements MenuContainer, ImageObserver, Serializable, Accessible {

    private final Bounds bounds;

    @Getter
    private final ComputerKurzweilApplicationContext ctx;

    @Getter
    private final SimulatedEvolutionFrame simulatedEvolutionFrame;

    @Getter
    private final MandelbrotFrame mandelbrotFrame;

    public ComputerKurzweilApplicationFrame(
        ComputerKurzweilApplicationContext ctx,
        SimulatedEvolutionFrame simulatedEvolutionFrame, MandelbrotFrame mandelbrotFrame) throws HeadlessException {
        super(ctx.getProperties().getView().getTitle());
        this.ctx = ctx;
        this.simulatedEvolutionFrame = simulatedEvolutionFrame;
        this.mandelbrotFrame = mandelbrotFrame;
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
        this.simulatedEvolutionFrame.start();
    }

    public void repaint(){
        super.repaint();
        this.simulatedEvolutionFrame.repaint();
    }
}
