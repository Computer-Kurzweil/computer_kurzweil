package org.woehlke.simulation.cca.view;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.allinone.view.parts.PanelSubtitle;
import org.woehlke.simulation.cca.control.CyclicCellularAutomatonController;
import org.woehlke.simulation.cca.model.CyclicCellularAutomatonContext;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.ImageObserver;
import java.io.Serializable;

/**
 * Cyclic Cellular Automaton.
 *
 * (C) 2006 - 2013 Thomas Woehlke.
 * http://thomas-woehlke.de/p/cyclic-cellular-automaton/
 * @author Thomas Woehlke
 * Date: 04.02.2006
 * Time: 18:47:46
 */
@Log
@ToString
@EqualsAndHashCode(callSuper=true)
@Component
public class CyclicCellularAutomatonFrame extends JFrame implements ImageObserver,
        MenuContainer,
        Serializable,
        Accessible,
        WindowListener {

    private static final long serialVersionUID = 4357793241219932594L;

    @Getter private final CyclicCellularAutomatonContext ctx;
    @Getter private final CyclicCellularAutomatonController controller;
    @Getter private final CyclicCellularAutomatonCanvas canvas;
    @Getter private final CyclicCellularAutomatonButtonsPanel panelButtons;
    @Getter private final PanelSubtitle subtitle;

    @Autowired
    public CyclicCellularAutomatonFrame(CyclicCellularAutomatonContext ctx) {
        super(ctx.getProperties().getTitle());
        this.ctx=ctx;
        BoxLayout layout = new BoxLayout(rootPane, BoxLayout.PAGE_AXIS);
        this.canvas = new CyclicCellularAutomatonCanvas(   this.ctx);
        this.panelButtons = new CyclicCellularAutomatonButtonsPanel( this.ctx);
        this.controller = new CyclicCellularAutomatonController( this.canvas, this.panelButtons);
        this.subtitle = new PanelSubtitle(   this.ctx);
        rootPane.setLayout(layout);
        rootPane.add(this.subtitle);
        rootPane.add(this.canvas);
        rootPane.add(this.panelButtons);
        addWindowListener(this);
    }

    public void start() {
        this.controller.start();
        showMe();
    }

    public void showMe() {
        pack();
        this.setBounds(ctx.getProperties().getFrameBounds());
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
