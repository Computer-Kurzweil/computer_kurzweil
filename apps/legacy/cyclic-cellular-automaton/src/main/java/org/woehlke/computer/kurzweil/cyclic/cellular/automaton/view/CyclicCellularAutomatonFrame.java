package org.woehlke.computer.kurzweil.cyclic.cellular.automaton.view;

import org.woehlke.computer.kurzweil.cyclic.cellular.automaton.config.ObjectRegistry;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.ImageObserver;
import java.io.Serializable;

/**
 * Cyclic Cellular Automaton.
 * <p>
 * (C) 2006 - 2022 Thomas Woehlke.
 * @see <a href="https://java.woehlke.org/cyclic-cellular-automaton">Maven Project Page</a>
 *
 * @author Thomas Woehlke
 * <p>
 * Date: 04.02.2006
 * Time: 18:47:46
 */
public class CyclicCellularAutomatonFrame extends JFrame implements ImageObserver,
    MenuContainer,
    Serializable,
    Accessible,
    WindowListener {

    private static final long serialVersionUID = 4357793241219932594L;

    private ObjectRegistry ctx;

    public CyclicCellularAutomatonFrame(ObjectRegistry ctx) {
        super(ctx.getConfig().getTitle());
        this.ctx = ctx;
        ctx.setFrame(this);
        Container pane = this.getContentPane();
        pane.add(ctx.getSubtitle(), BorderLayout.PAGE_START);
        pane.add(ctx.getCanvas(), BorderLayout.CENTER);
        pane.add(ctx.getPanelButtons(), BorderLayout.PAGE_END);
        addWindowListener(this);
        ctx.getController().start();
        showMe();
    }

    public void showMe() {
        pack();
        this.setBounds(ctx.getConfig().getFrameBounds());
        setVisible(true);
        toFront();
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

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
        showMe();
    }

    public void windowActivated(WindowEvent e) {
        toFront();
    }

    public void windowDeactivated(WindowEvent e) {
    }
}
