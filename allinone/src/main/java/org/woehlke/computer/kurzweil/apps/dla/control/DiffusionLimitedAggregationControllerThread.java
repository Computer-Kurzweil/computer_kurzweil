package org.woehlke.computer.kurzweil.apps.dla.control;

import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.apps.dla.model.DiffusionLimitedAggregationWorld;
import org.woehlke.computer.kurzweil.apps.dla.view.DiffusionLimitedAggregationCanvas;
import org.woehlke.computer.kurzweil.control.ctx.ControllerThread;
import org.woehlke.computer.kurzweil.control.signals.UserSignal;

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
public class DiffusionLimitedAggregationControllerThread extends Thread
        implements ControllerThread {

    private Boolean goOn;

    private final ComputerKurzweilApplicationContext ctx;
    private final DiffusionLimitedAggregationWorld particles;
    private final DiffusionLimitedAggregationCanvas canvas;

    public DiffusionLimitedAggregationControllerThread(
        DiffusionLimitedAggregationCanvas canvas,
        ComputerKurzweilApplicationContext ctx
    ) {
        this.ctx = ctx;
        goOn = Boolean.TRUE;
        this.canvas = canvas;
        this.particles = canvas.getWorld();
    }

    public void run() {
        log.info("run() started");
        boolean doIt;
        do {
            synchronized (goOn) {
                doIt = goOn.booleanValue();
            }
            particles.step();
            canvas.repaint();
            try { sleep(ctx.getProperties().getDla().getControl().getThreadSleepTime()); }
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

    @Override
    public void handleUserSignal(UserSignal userSignal) {

    }
}
