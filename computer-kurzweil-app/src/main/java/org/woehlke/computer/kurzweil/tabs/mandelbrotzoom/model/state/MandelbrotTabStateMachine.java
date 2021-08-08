package org.woehlke.computer.kurzweil.tabs.mandelbrotzoom.model.state;

import java.io.Serializable;

import static org.woehlke.computer.kurzweil.tabs.mandelbrotzoom.model.state.MandelbrotTabState.*;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 16.12.2019.
 */
public class MandelbrotTabStateMachine implements Serializable {

    private static final long serialVersionUID = 7526471155622776147L;

    private volatile MandelbrotTabState mandelbrotTabState;

    public MandelbrotTabStateMachine() {
        this.mandelbrotTabState = MandelbrotTabState.MANDELBROT;
    }

    public void click(){
        MandelbrotTabState nextMandelbrotTabState = null;
        switch (mandelbrotTabState){
            case MANDELBROT:
                nextMandelbrotTabState = JULIA_SET;
                break;
            case JULIA_SET:
                nextMandelbrotTabState = MANDELBROT;
                break;
            case MANDELBROT_ZOOM:
                nextMandelbrotTabState = MANDELBROT_ZOOM;
                break;
            case JULIA_SET_ZOOM:
                nextMandelbrotTabState = JULIA_SET_ZOOM;
                break;
        }
        this.setMandelbrotTabState(nextMandelbrotTabState);
    }

    public void setModeSwitch() {
        MandelbrotTabState nextMandelbrotTabState = this.mandelbrotTabState;
        switch (mandelbrotTabState){
            case MANDELBROT:
            case JULIA_SET:
                break;
            case MANDELBROT_ZOOM:
                nextMandelbrotTabState = MANDELBROT;
                break;
            case JULIA_SET_ZOOM:
                nextMandelbrotTabState = JULIA_SET;
                break;
        }
        this.setMandelbrotTabState(nextMandelbrotTabState);
    }

    public void setModeZoom() {
        MandelbrotTabState nextMandelbrotTabState = this.mandelbrotTabState;
        switch (mandelbrotTabState){
            case MANDELBROT:
                nextMandelbrotTabState = MANDELBROT_ZOOM;
                break;
            case JULIA_SET:
                nextMandelbrotTabState = JULIA_SET_ZOOM;
                break;
            case MANDELBROT_ZOOM:
            case JULIA_SET_ZOOM:
                break;
        }
        this.setMandelbrotTabState(nextMandelbrotTabState);
    }

    public MandelbrotTabState getMandelbrotTabState() {
        return mandelbrotTabState;
    }

    public void setMandelbrotTabState(MandelbrotTabState mandelbrotTabState) {
        this.mandelbrotTabState = mandelbrotTabState;
    }

}
