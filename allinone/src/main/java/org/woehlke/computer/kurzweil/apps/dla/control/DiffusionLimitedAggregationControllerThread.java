package org.woehlke.computer.kurzweil.apps.dla.control;

import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.config.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.apps.dla.model.DiffusionLimitedAggregationWorld;
import org.woehlke.computer.kurzweil.apps.dla.view.DiffusionLimitedAggregationCanvas;
import org.woehlke.computer.kurzweil.control.ControllerThread;

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
        implements Runnable, ControllerThread {

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
    }

    public void exit() {
        try {
            synchronized (goOn) {
                goOn = Boolean.FALSE;
            }
            join();
        } catch (InterruptedException e){
            log.info(e.getMessage());
        }
    }

}
