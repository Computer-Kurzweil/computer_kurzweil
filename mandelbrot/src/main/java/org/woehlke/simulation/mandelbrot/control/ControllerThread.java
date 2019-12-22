package org.woehlke.simulation.mandelbrot.control;

import org.woehlke.simulation.mandelbrot.model.OnjectRegistry;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Date: 05.02.2006
 * Time: 00:36:20
 */
public class ControllerThread extends Thread implements Runnable {

    private final OnjectRegistry ctx;

    private final int THREAD_SLEEP_TIME = 1;

    private Boolean goOn;

    public ControllerThread(OnjectRegistry ctx) {
        this.ctx=ctx;
        goOn = Boolean.TRUE;

    }

    public void run() {
        boolean doIt;
        do {
            synchronized (goOn) {
                doIt = goOn.booleanValue();
            }
            this.ctx.step();
            try { sleep(THREAD_SLEEP_TIME); }
            catch (InterruptedException e) { }
        }
        while (doIt);
    }

    public void exit() {
        synchronized (goOn) {
            goOn = Boolean.FALSE;
        }
    }

}
