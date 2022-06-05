package org.woehlke.computer.kurzweil.tabs.kochsnowflake.model;


import java.io.Serializable;

import static org.woehlke.computer.kurzweil.tabs.kochsnowflake.model.KochSnowflakeTabState.*;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 16.12.2019.
 */
public class KochSnowflakeTabStateMachine implements Serializable {

    private static final long serialVersionUID = 7526471155622776147L;

    private volatile KochSnowflakeTabState applicationState;

    public KochSnowflakeTabStateMachine() {
        this.applicationState = KochSnowflakeTabState.MANDELBROT;
    }

    public void click(){
        KochSnowflakeTabState nextApplicationState = null;
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
        KochSnowflakeTabState nextApplicationState = this.applicationState;
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
        KochSnowflakeTabState nextApplicationState = this.applicationState;
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

    public KochSnowflakeTabState getApplicationState() {
        return applicationState;
    }

    public void setApplicationState(KochSnowflakeTabState applicationState) {
        this.applicationState = applicationState;
    }

}
