package org.woehlke.computer.kurzweil.apps.cca;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.commons.tabs.TabController;

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
@Getter
public class CyclicCellularAutomatonController extends Thread
        implements TabController {

    private static final int THREAD_SLEEP_TIME = 100;
    private static final long serialVersionUID = 3642865135701767557L;
    private final ComputerKurzweilApplicationContext ctx;
    private final CyclicCellularAutomatonContext appCtx;
    private final int time2wait;

    private Boolean goOn;

    public CyclicCellularAutomatonController(
        CyclicCellularAutomatonContext appCtx
    ) {
        super("CCA-Controller");
        this.appCtx = appCtx;
        this.ctx = appCtx.getCtx();
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
                this.appCtx.getStepper().step();
            }
            synchronized (this.appCtx) {
                this.appCtx.getCanvas().update();
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

}
