package org.woehlke.simulation.cca.control;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.cca.model.CyclicCellularAutomatonContext;

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
        implements Runnable, Serializable {

    private static final int THREAD_SLEEP_TIME = 100;

    private static final long serialVersionUID = 3642865135701767557L;

    private Boolean goOn;

    private final CyclicCellularAutomatonContext ctx;

    @Autowired
    public CyclicCellularAutomatonController(CyclicCellularAutomatonContext ctx) {
        this.ctx = ctx;
        goOn = Boolean.TRUE;
    }

    public void run() {
        boolean doIt;
        do {
            synchronized (goOn) {
                doIt = goOn.booleanValue();
            }
            ctx.getLattice().step();
            ctx.getCanvas().repaint();
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


    public void pushButtonVonNeumann() {
        ctx.getLattice().startVonNeumann();
    }

    public void pushButtonMoore() {
        ctx.getLattice().startMoore();
    }

    public void pushButtonWoehlke() {
        ctx.getLattice().startWoehlke();
    }
}
