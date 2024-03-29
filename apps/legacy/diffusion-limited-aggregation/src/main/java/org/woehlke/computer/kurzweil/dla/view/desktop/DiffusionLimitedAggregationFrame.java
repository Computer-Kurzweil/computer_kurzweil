package org.woehlke.computer.kurzweil.dla.view.desktop;

import org.woehlke.computer.kurzweil.dla.DiffusionLimitedAggregation;
import org.woehlke.computer.kurzweil.dla.view.applet.DiffusionLimitedAggregationApplet;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.ImageObserver;
import java.io.Serializable;

/**
 * Diffusion Limited Aggregation.
 *
 * (C) 2006 - 2022 Thomas Woehlke.
 * @see <a href="https://thomas-woehlke.blogspot.com/2016/01/diffusion-limited-aggregation.html">Blog Arrticle</a>
 * @see <a href="https://java.woehlke.org/diffusion-limited-aggregation">Maven Project Page</a>
 * @author Thomas Woehlke
 *
 * Date: 04.02.2006
 * Time: 18:47:46
 */
public class DiffusionLimitedAggregationFrame extends JFrame implements ImageObserver,
        MenuContainer,
        Serializable,
        Accessible,
        WindowListener, DiffusionLimitedAggregation {

    static final long serialVersionUID = mySerialVersionUID;


    private DiffusionLimitedAggregationApplet exe;

    public DiffusionLimitedAggregationFrame() {
        super(TITLE);
        exe = new DiffusionLimitedAggregationApplet();
        exe.init();
        add("Center", exe);
        setBounds(100, 100, exe.getCanvasDimensions().getX(), exe.getCanvasDimensions().getY() + 30);
        pack();
        setVisible(true);
        toFront();
        addWindowListener(this);
    }

    public void windowOpened(WindowEvent e) {
        setBounds(100, 100, exe.getCanvasDimensions().getX(), exe.getCanvasDimensions().getY() + 30);
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
        setBounds(100, 100, exe.getCanvasDimensions().getX(), exe.getCanvasDimensions().getY() + 30);
        setVisible(true);
        toFront();
    }

    public void windowActivated(WindowEvent e) {
        toFront();
    }

    public void windowDeactivated(WindowEvent e) {
    }
}
