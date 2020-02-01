package org.woehlke.computer.kurzweil.apps.cca.control;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.apps.cca.view.CyclicCellularAutomatonButtonsPanel;
import org.woehlke.computer.kurzweil.apps.cca.view.CyclicCellularAutomatonCanvas;
import org.woehlke.computer.kurzweil.config.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.control.ControllerThread;

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
public class CyclicCellularAutomatonControllerThread extends Thread
        implements Serializable, ActionListener, ControllerThread {

    private static final int THREAD_SLEEP_TIME = 300;
    private static final long serialVersionUID = 3642865135701767557L;

    private Boolean goOn;

    @Getter private final ComputerKurzweilApplicationContext ctx;
    @Getter private final CyclicCellularAutomatonCanvas canvas;
    @Getter private final CyclicCellularAutomatonButtonsPanel panelButtons;

    public CyclicCellularAutomatonControllerThread(
        ComputerKurzweilApplicationContext ctx,
        CyclicCellularAutomatonCanvas canvas,
        CyclicCellularAutomatonButtonsPanel panelButtons
    ) {
        this.ctx = ctx;
        this.canvas = canvas;
        this.panelButtons = panelButtons;
        goOn = Boolean.TRUE;
        panelButtons.getButtonVonNeumann().addActionListener(  this);
        panelButtons.getButtonMoore().addActionListener(  this);
        panelButtons.getButtonWoehlke().addActionListener(  this);
    }

    public void run() {
        canvas.start();
        log.info("run() - started");
        boolean doIt;
        do {
            synchronized (goOn) {
                doIt = goOn.booleanValue();
            }
            this.canvas.step();
            this.canvas.repaint();
            this.ctx.getFrame().repaint();
            try { sleep(THREAD_SLEEP_TIME); }
            catch (InterruptedException e) { log.info(e.getMessage()); }
        }
        while (doIt);
        log.info("run() - finished");
        canvas.stop();
    }

    public void exit() {
        log.info("exit");
        try {
            synchronized (goOn) {
                goOn = Boolean.FALSE;
            }
            join();
        } catch (InterruptedException e){
            log.info(e.getMessage());
        }
        log.info("exited");
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
