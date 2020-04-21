package org.woehlke.computer.kurzweil.tabs.dla.control;

import org.woehlke.computer.kurzweil.tabs.dla.DiffusionLimitedAggregation;
import org.woehlke.computer.kurzweil.tabs.dla.model.Particles;
import org.woehlke.computer.kurzweil.tabs.dla.view.WorldCanvas;

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
public class ControllerThread extends Thread
        implements Runnable, DiffusionLimitedAggregation {

    static final long serialVersionUID = mySerialVersionUID;


    private Particles particles;
    private WorldCanvas canvas;

    private Boolean goOn;

    public ControllerThread(WorldCanvas canvas, Particles particles) {
        goOn = Boolean.TRUE;
        this.canvas=canvas;
        this.particles=particles;
    }

    public void run() {
        boolean doIt;
        do {
            synchronized (goOn) {
                doIt = goOn.booleanValue();
            }
            particles.move();
            canvas.repaint();
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


}
