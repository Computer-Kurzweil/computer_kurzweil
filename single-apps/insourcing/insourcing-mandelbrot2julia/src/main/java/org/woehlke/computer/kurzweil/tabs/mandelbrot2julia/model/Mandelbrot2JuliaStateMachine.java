package org.woehlke.computer.kurzweil.tabs.mandelbrot2julia.model;

import static org.woehlke.computer.kurzweil.tabs.mandelbrot2julia.model.Mandelbrot2JuliaState.*;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 16.12.2019.
 */
public class Mandelbrot2JuliaStateMachine {

    private volatile Mandelbrot2JuliaState applicationState;

    public Mandelbrot2JuliaStateMachine() {
        this.applicationState = Mandelbrot2JuliaState.MANDELBROT;
    }

    public void click(){
        Mandelbrot2JuliaState nextApplicationState = null;
        switch (applicationState){
            case MANDELBROT:
                nextApplicationState = JULIA_SET;
                break;
            case JULIA_SET:
                nextApplicationState = MANDELBROT;
                break;
        }
        this.setApplicationState(nextApplicationState);
    }

    /*
    public void setModeSwitch() {
        ApplicationState nextApplicationState = this.applicationState;
        switch (applicationState){
            case MANDELBROT:
            case JULIA_SET:
                break;
        }
        this.setApplicationState(nextApplicationState);
    }
     */

    public Mandelbrot2JuliaState getApplicationState() {
        return applicationState;
    }

    public void setApplicationState(Mandelbrot2JuliaState applicationState) {
        this.applicationState = applicationState;
    }

}
