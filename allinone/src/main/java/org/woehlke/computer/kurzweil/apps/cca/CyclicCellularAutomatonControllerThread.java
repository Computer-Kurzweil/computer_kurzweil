package org.woehlke.computer.kurzweil.apps.cca;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.apps.cca.CyclicCellularAutomatonContext;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.commons.ControllerThread;
import org.woehlke.computer.kurzweil.trashcan.signals.UserSignal;

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

    private final int time2wait;

    public CyclicCellularAutomatonControllerThread(
        ComputerKurzweilApplicationContext ctx
    ) {
        super("CCA-Controller");
        this.ctx = ctx;
        this.goOn = Boolean.TRUE;
        this.time2wait = THREAD_SLEEP_TIME;
    }

    public void run() {
        log.info("run() - begin");
        boolean doIt;
        do {
            synchronized (this.goOn) {
                doIt = goOn.booleanValue();
            }
            synchronized (this.appCtx) {
                this.appCtx.step();
            }
            synchronized (this.appCtx) {
                this.appCtx.update();
            }
            try { super.sleep( this.time2wait ); }
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
