package org.woehlke.simulation.all.view;

import org.springframework.stereotype.Component;
import org.woehlke.simulation.all.model.ComputerKurzweilApplicationContext;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.Serializable;

@Component
public class ComputerKurzweilApplicationFrame extends JFrame implements MenuContainer, ImageObserver, Serializable, Accessible {

    private final Bounds bounds;

    private final ComputerKurzweilApplicationContext ctx;

    public ComputerKurzweilApplicationFrame(
        ComputerKurzweilApplicationContext ctx
    ) throws HeadlessException {
        super(ctx.getProperties().getView().getTitle());
        this.ctx = ctx;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(this.ctx.getSimulatedEvolutionFrame());
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
        this.setBounds(bounds.getMystartX(), bounds.getMystartY(), bounds.getMywidth(), bounds.getMyheight());
        rootPane.setVisible(true);
        this.setVisible(true);
        toFront();
        repaint();
    }

    public void exit() {
        this.dispose();
    }

    public void start() {
        ctx.start();
    }

    public void repaint(){
        super.repaint();
        this.ctx.getSimulatedEvolutionFrame().repaint();
    }
}
