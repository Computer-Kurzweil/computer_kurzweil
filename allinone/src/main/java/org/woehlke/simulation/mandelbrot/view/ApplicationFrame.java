package org.woehlke.simulation.mandelbrot.view;

import org.springframework.stereotype.Component;
import org.woehlke.simulation.mandelbrot.config.Config;
import org.woehlke.simulation.mandelbrot.control.ApplicationContext;
import org.woehlke.simulation.mandelbrot.control.impl.ApplicationContextImpl;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * (C) 2006 - 2013 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 * Date: 04.02.2006
 * Time: 18:47:46
 */
@Component
public class ApplicationFrame extends JFrame implements
        ImageObserver,
        MenuContainer,
        Accessible {

    private final ApplicationContext ctx;

    public ApplicationFrame(Config config) {
        super(config.getTitle());
        this.ctx = new ApplicationContextImpl(config, this);
        PanelButtons panelButtons = new PanelButtons( this.ctx );
        this.ctx.setPanelButtons(panelButtons);
        rootPane.setLayout(new BoxLayout(rootPane, BoxLayout.PAGE_AXIS));
        rootPane.add(this.ctx.getPanelSubtitle());
        rootPane.add(this.ctx.getCanvas());
        rootPane.add(this.ctx.getPanelButtons());
        showMeInit();
        this.ctx.start();
    }

    public void showMeInit() {
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = this.rootPane.getWidth();
        double height = this.ctx.getCanvas().getCanvasHeight() + 180;
        double startX = (screenSize.getWidth() - width) / 2d;
        double startY = (screenSize.getHeight() - height) / 2d;
        int myheight = Double.valueOf(height).intValue();
        int mywidth = Double.valueOf(width).intValue();
        int mystartX = Double.valueOf(startX).intValue();
        int mystartY = Double.valueOf(startY).intValue();
        this.ctx.setRectangleBounds(new Rectangle(mystartX, mystartY, mywidth, myheight));
        this.ctx.setDimensionSize(new Dimension(mywidth, myheight));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        showMe();
    }

    /**
     * TODO write doc.
     */
    public void showMe() {
        this.pack();
        this.setBounds(this.ctx.getRectangleBounds());
        this.setSize(this.ctx.getDimensionSize());
        this.setPreferredSize(this.ctx.getDimensionSize());
        this.setVisible(true);
        this.toFront();
    }

}
