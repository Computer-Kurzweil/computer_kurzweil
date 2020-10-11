package org.woehlke.computer.kurzweil.tabs.mandelbrotzoom.model.state;

import static org.woehlke.computer.kurzweil.tabs.mandelbrotzoom.model.state.TabState.*;
import static org.woehlke.computer.kurzweil.tabs.mandelbrotzoom.model.state.TabState.JULIA_SET_ZOOM;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 16.12.2019.
 */
public class TabStateMachine {

    private volatile TabState applicationState;

    public TabStateMachine() {
        this.applicationState = TabState.MANDELBROT;
    }

    public void click(){
        TabState nextApplicationState = null;
        switch (applicationState){
            case MANDELBROT:
                nextApplicationState = JULIA_SET;
                break;
            case JULIA_SET:
                nextApplicationState = MANDELBROT;
                break;
            case MANDELBROT_ZOOM:
                nextApplicationState = MANDELBROT_ZOOM;
                break;
            case JULIA_SET_ZOOM:
                nextApplicationState = JULIA_SET_ZOOM;
                break;
        }
        this.setApplicationState(nextApplicationState);
    }

    public void setModeSwitch() {
        TabState nextApplicationState = this.applicationState;
        switch (applicationState){
            case MANDELBROT:
            case JULIA_SET:
                break;
            case MANDELBROT_ZOOM:
                nextApplicationState = MANDELBROT;
                break;
            case JULIA_SET_ZOOM:
                nextApplicationState = JULIA_SET;
                break;
        }
        this.setApplicationState(nextApplicationState);
    }

    public void setModeZoom() {
        TabState nextApplicationState = this.applicationState;
        switch (applicationState){
            case MANDELBROT:
                nextApplicationState = MANDELBROT_ZOOM;
                break;
            case JULIA_SET:
                nextApplicationState = JULIA_SET_ZOOM;
                break;
            case MANDELBROT_ZOOM:
            case JULIA_SET_ZOOM:
                break;
        }
        this.setApplicationState(nextApplicationState);
    }

    public TabState getApplicationState() {
        return applicationState;
    }

    public void setApplicationState(TabState applicationState) {
        this.applicationState = applicationState;
    }

}
