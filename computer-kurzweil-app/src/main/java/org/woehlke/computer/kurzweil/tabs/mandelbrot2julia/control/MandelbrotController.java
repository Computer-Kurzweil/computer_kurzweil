package org.woehlke.computer.kurzweil.tabs.mandelbrot2julia.control;

import org.woehlke.computer.kurzweil.commons.tabs.TabController;
import org.woehlke.computer.kurzweil.tabs.mandelbrot2julia.model.MandelbrotModel;
import org.woehlke.computer.kurzweil.tabs.mandelbrot2julia.MandelbrotTab;
import org.woehlke.computer.kurzweil.tabs.mandelbrot2julia.config.Mandelbrot;

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
public class MandelbrotController extends Thread implements TabController, Mandelbrot {

    private static final long serialVersionUID = 7526471155622776147L;

    private volatile MandelbrotModel mandelbrotModel;
    private volatile MandelbrotTab tab;

    private final int THREAD_SLEEP_TIME = 1;

    private volatile Boolean goOn;

    public MandelbrotController(MandelbrotModel model, MandelbrotTab tab) {
        this.tab = tab;
        this.mandelbrotModel = model;
        goOn = Boolean.TRUE;
    }

    public void run() {
        boolean doIt;
        do {
            synchronized (goOn) {
                doIt = goOn.booleanValue();
            }
            if(this.mandelbrotModel.step()){
                this.tab.getCanvas().repaint();
            }
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
