package org.woehlke.computer.kurzweil.apps.mandelbrot;

import lombok.Getter;
import lombok.Setter;
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
@Getter
public class ApplicationStateMachine {

    @Setter
    private ApplicationState state;
    private final ComputerKurzweilApplicationContext ctx;

    public ApplicationStateMachine(ComputerKurzweilApplicationContext ctx) {
        this.state = MANDELBROT_SWITCH;
        this.ctx = ctx;
    }

    public void start() {
        this.state = ApplicationState.start();
    }

    public void click(){
        ApplicationState nextApplicationState = null;
        switch (this.state){
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
        String msg = "click: "+ this.state + " -> "+ nextApplicationState.name();
        log.info(msg);
        this.setState(nextApplicationState);
    }

    public void setModeSwitch() {
        ApplicationState nextApplicationState  = null;
        switch (this.state){
            case MANDELBROT_SWITCH:
            case MANDELBROT_ZOOM:
                nextApplicationState = MANDELBROT_SWITCH;
                break;
            case JULIA_SET_SWITCH:
            case JULIA_SET_ZOOM:
                nextApplicationState = JULIA_SET_SWITCH;
                break;
        }
        String msg = "setModeSwitch: "+ this.state + " -> "+ nextApplicationState.name();
        log.info(msg);
        this.setState(nextApplicationState);
    }

    public void setModeZoom() {
        ApplicationState nextApplicationState = null;
        switch (this.state){
            case MANDELBROT_SWITCH:
            case MANDELBROT_ZOOM:
                nextApplicationState = MANDELBROT_ZOOM;
                break;
            case JULIA_SET_SWITCH:
            case JULIA_SET_ZOOM:
                nextApplicationState = JULIA_SET_ZOOM;
                break;
        }
        String msg = "setModeZoom: "+ this.state + " -> "+ nextApplicationState.name();
        log.info(msg);
        this.setState(nextApplicationState);
    }

}
