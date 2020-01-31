package org.woehlke.simulation.cca.control;

import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.cca.view.CyclicCellularAutomatonButtonsPanel;
import org.woehlke.simulation.cca.view.CyclicCellularAutomatonCanvas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

/**
 * Cyclic Cellular Automaton.
 *
 * (C) 2006 - 2013 Thomas Woehlke.
 * http://thomas-woehlke.de/p/cyclic-cellular-automaton/
 * @author Thomas Woehlke
 *
 * Date: 05.02.2006
 * Time: 00:36:20
 */
@Log
@Component
public class CyclicCellularAutomatonController extends Thread
        implements Runnable, Serializable, ActionListener {

    private static final int THREAD_SLEEP_TIME = 100;
    private static final long serialVersionUID = 3642865135701767557L;

    private Boolean goOn;

    @Getter private final CyclicCellularAutomatonCanvas canvas;
    @Getter private final CyclicCellularAutomatonButtonsPanel panelButtons;

    @Autowired
    public CyclicCellularAutomatonController(
        CyclicCellularAutomatonCanvas canvas,
        CyclicCellularAutomatonButtonsPanel panelButtons
    ) {
        this.canvas = canvas;
        this.panelButtons = panelButtons;
        goOn = Boolean.TRUE;
        panelButtons.getButtonVonNeumann().addActionListener(  this);
        panelButtons.getButtonMoore().addActionListener(  this);
        panelButtons.getButtonWoehlke().addActionListener(  this);
    }

    public void run() {
        boolean doIt;
        do {
            synchronized (goOn) {
                doIt = goOn.booleanValue();
            }
            this.canvas.getLattice().step();
            this.canvas.repaint();
            try { sleep(THREAD_SLEEP_TIME); }
            catch (InterruptedException e) { e.printStackTrace(); }
        }
        while (doIt);
    }

    public void exit() {
        synchronized (goOn) {
            goOn = Boolean.FALSE;
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == panelButtons.getButtonVonNeumann()) {
            this.canvas.getLattice().startWithNeighbourhoodVonNeumann();
        } else if (ae.getSource() == panelButtons.getButtonMoore()) {
            this.canvas.getLattice().startWithNeighbourhoodMoore();
        } else if (ae.getSource() == panelButtons.getButtonWoehlke()) {
            this.canvas.getLattice().startWithNeighbourhoodWoehlke();
        }
    }
}
