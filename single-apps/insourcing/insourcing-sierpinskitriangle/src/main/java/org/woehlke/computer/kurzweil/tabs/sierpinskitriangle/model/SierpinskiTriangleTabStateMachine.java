package org.woehlke.computer.kurzweil.tabs.sierpinskitriangle.model;


import org.woehlke.computer.kurzweil.tabs.sierpinskitriangle.model.SierpinskiTriangleTabState;

import static org.woehlke.computer.kurzweil.tabs.sierpinskitriangle.model.SierpinskiTriangleTabState.*;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 16.12.2019.
 */
public class SierpinskiTriangleTabStateMachine {

    private volatile SierpinskiTriangleTabState applicationState;

    public SierpinskiTriangleTabStateMachine() {
        this.applicationState = SierpinskiTriangleTabState.MANDELBROT;
    }

    public void click(){
        SierpinskiTriangleTabState nextApplicationState = null;
        switch (applicationState){
            case MANDELBROT:
                nextApplicationState = MANDELBROT_ZOOM;
                break;
            case MANDELBROT_ZOOM:
                nextApplicationState = MANDELBROT;
                break;
        }
        this.setApplicationState(nextApplicationState);
    }

    public void setModeSwitch() {
        SierpinskiTriangleTabState nextApplicationState = this.applicationState;
        /*
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
        */
        this.setApplicationState(nextApplicationState);
    }

    public void setModeZoom() {
        SierpinskiTriangleTabState nextApplicationState = this.applicationState;
        switch (applicationState){
            case MANDELBROT:
                nextApplicationState = MANDELBROT_ZOOM;
                break;
            //case JULIA_SET:
              //  nextApplicationState = JULIA_SET_ZOOM;
                //break;
            case MANDELBROT_ZOOM:
            //case JULIA_SET_ZOOM:
                break;
        }
        this.setApplicationState(nextApplicationState);
    }

    public SierpinskiTriangleTabState getApplicationState() {
        return applicationState;
    }

    public void setApplicationState(SierpinskiTriangleTabState applicationState) {
        this.applicationState = applicationState;
    }

}
