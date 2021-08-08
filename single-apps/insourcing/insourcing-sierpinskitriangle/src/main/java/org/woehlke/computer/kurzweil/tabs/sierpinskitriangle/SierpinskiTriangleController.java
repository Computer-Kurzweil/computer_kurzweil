package org.woehlke.computer.kurzweil.tabs.sierpinskitriangle;

import org.woehlke.computer.kurzweil.tabs.sierpinskitriangle.SierpinskiTriangleModel;
import org.woehlke.computer.kurzweil.tabs.sierpinskitriangle.SierpinskiTriangleTab;

import java.io.Serializable;

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
public class SierpinskiTriangleController extends Thread implements Runnable, Serializable {

    private static final long serialVersionUID = 7526471155622776147L;

    private final int THREAD_SLEEP_TIME = 1;
    private volatile SierpinskiTriangleModel mandelbrotModel;
    private volatile SierpinskiTriangleTab frame;
    private volatile Boolean goOn;

    public SierpinskiTriangleController(SierpinskiTriangleModel model, SierpinskiTriangleTab frame) {
        this.frame = frame;
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
                frame.getCanvas().repaint();
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
