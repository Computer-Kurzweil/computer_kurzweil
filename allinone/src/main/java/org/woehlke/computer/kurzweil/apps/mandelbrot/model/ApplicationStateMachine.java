package org.woehlke.computer.kurzweil.apps.mandelbrot.model;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.apps.mandelbrot.model.state.ApplicationState;

import static org.woehlke.computer.kurzweil.apps.mandelbrot.model.state.ApplicationState.*;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 16.12.2019.
 */
@Log
public class ApplicationStateMachine {

    @Getter
    private ApplicationState applicationState;

    @Getter
    private final ComputerKurzweilApplicationContext ctx;

    public ApplicationStateMachine(ComputerKurzweilApplicationContext ctx) {
        this.applicationState = MANDELBROT_SWITCH;
        this.ctx = ctx;
    }

    public void start() {
        this.applicationState = ApplicationState.start();
    }

    public void click(){
        ApplicationState nextApplicationState = null;
        switch (applicationState){
            case MANDELBROT_SWITCH:
                nextApplicationState = JULIA_SET_SWITCH;
                break;
            case JULIA_SET_SWITCH:
                nextApplicationState = MANDELBROT_SWITCH;
                break;
            case MANDELBROT_ZOOM:
                nextApplicationState = MANDELBROT_ZOOM;
                break;
            case JULIA_SET_ZOOM:
                nextApplicationState = JULIA_SET_ZOOM;
                break;
        }
        log.info("click: "+ applicationState + " -> "+ nextApplicationState);
        this.setApplicationState(nextApplicationState);
    }

    public void setModeSwitch() {
        ApplicationState nextApplicationState  = null;
        switch (applicationState){
            case MANDELBROT_SWITCH:
            case MANDELBROT_ZOOM:
                nextApplicationState = MANDELBROT_SWITCH;
                break;
            case JULIA_SET_SWITCH:
            case JULIA_SET_ZOOM:
                nextApplicationState = JULIA_SET_SWITCH;
                break;
        }
        String msg = "setModeZoom: "+ applicationState + " -> "+ nextApplicationState;
        log.info(msg);
        this.setApplicationState(nextApplicationState);
    }

    public void setModeZoom() {
        ApplicationState nextApplicationState = null;
        switch (applicationState){
            case MANDELBROT_SWITCH:
            case MANDELBROT_ZOOM:
                nextApplicationState = MANDELBROT_ZOOM;
                break;
            case JULIA_SET_SWITCH:
            case JULIA_SET_ZOOM:
                nextApplicationState = JULIA_SET_ZOOM;
                break;
        }
        String msg = "setModeZoom: "+ applicationState + " -> "+ nextApplicationState;
        log.info(msg);
        this.setApplicationState(nextApplicationState);
    }

    public ApplicationState getState() {
        return applicationState;
    }

    public void setApplicationState(ApplicationState applicationState) {
        this.applicationState = applicationState;
    }

}
