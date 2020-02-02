package org.woehlke.computer.kurzweil.apps.cca.control;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.apps.cca.ctx.CyclicCellularAutomatonContext;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.control.ctx.ControllerThread;
import org.woehlke.computer.kurzweil.control.signals.UserSignal;

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
        implements ControllerThread {

    private static final int THREAD_SLEEP_TIME = 100;
    private static final long serialVersionUID = 3642865135701767557L;

    private Boolean goOn;

    @Getter private final ComputerKurzweilApplicationContext ctx;

    @Getter @Setter private CyclicCellularAutomatonContext appCtx;

    public CyclicCellularAutomatonControllerThread(
        ComputerKurzweilApplicationContext ctx
    ) {
        super("CCA-Controller");
        this.ctx = ctx;
        goOn = Boolean.TRUE;
    }

    public void run() {
        log.info("run() - started");
        boolean doIt;
        do {
            synchronized (goOn) {
                doIt = goOn.booleanValue();
            }
            this.appCtx.getStepper().step();
            this.ctx.getFrame().update();
            try { super.sleep(THREAD_SLEEP_TIME); }
            catch (InterruptedException e) { log.info(e.getMessage()); }
        }
        while (doIt);
        log.info("run() - finished");
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
    public void handleUserSignal(UserSignal userSignal) {

    }
}
