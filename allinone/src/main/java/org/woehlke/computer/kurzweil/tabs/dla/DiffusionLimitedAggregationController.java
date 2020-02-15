package org.woehlke.computer.kurzweil.tabs.dla;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.tabs.TabController;

/**
 * Diffusion Limited Aggregation.
 *
 * (C) 2006 - 2013 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/diffusion-limited-aggregation.html
 * @author Thomas Woehlke
 *
 * Date: 05.02.2006
 * Time: 00:36:20
 */
@Log
@Getter
public class DiffusionLimitedAggregationController extends Thread
        implements TabController {

    private final DiffusionLimitedAggregationContext appCtx;
    private final int sleepTime;
    private Boolean goOn;

    public DiffusionLimitedAggregationController(
        DiffusionLimitedAggregationContext appCtx
    ) {
        super("DLA-Controller");
        this.appCtx = appCtx;
        this.goOn = Boolean.TRUE;
        this.sleepTime =  this.appCtx.getCtx().getProperties().getDla().getControl().getThreadSleepTime();
    }

    public void run() {
        log.info("run() started");
        boolean doIt;
        do {
            synchronized (goOn) {
                doIt = goOn.booleanValue();
            }
            synchronized ( this.appCtx){
                this.appCtx.getStepper().step();
                this.appCtx.getCanvas().update();
                this.appCtx.getCanvas().repaint();
            }
            try { sleep( this.sleepTime ); }
            catch (InterruptedException e) { e.printStackTrace(); }
        }
        while (doIt);
        log.info("run() finished");
    }

    public void exit() {
        log.info("exit");
        try {
            synchronized (goOn) {
                goOn = Boolean.FALSE;
            }
            join();
            log.info("exited");
        } catch (InterruptedException e){
            log.info(e.getMessage());
        }
    }

}
