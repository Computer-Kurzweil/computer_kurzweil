package org.woehlke.simulation.dla.control;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.allinone.config.ComputerKurzweilApplicationContext;
import org.woehlke.simulation.dla.model.DiffusionLimitedAggregationWorld;
import org.woehlke.simulation.dla.view.DiffusionLimitedAggregationCanvas;

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
@Component
public class DiffusionLimitedAggregationControllerThread extends Thread
        implements Runnable {

    private Boolean goOn;

    private final ComputerKurzweilApplicationContext ctx;
    private final DiffusionLimitedAggregationWorld particles;
    private final DiffusionLimitedAggregationCanvas canvas;

    @Autowired
    public DiffusionLimitedAggregationControllerThread(
        DiffusionLimitedAggregationCanvas canvas,
        ComputerKurzweilApplicationContext ctx) {
        this.ctx = ctx;
        goOn = Boolean.TRUE;
        this.canvas=canvas;
        this.particles=canvas.getWorld();
    }

    public void run() {
        boolean doIt;
        do {
            synchronized (goOn) {
                doIt = goOn.booleanValue();
            }
            particles.move();
            canvas.repaint();
            try { sleep(ctx.getProperties().getDla().getControl().getThreadSleepTime()); }
            catch (InterruptedException e) { e.printStackTrace(); }
        }
        while (doIt);
    }

    public void exit() {
        synchronized (goOn) {
            goOn = Boolean.FALSE;
        }
    }


}
