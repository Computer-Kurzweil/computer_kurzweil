package org.woehlke.simulation.mandelbrot.model.state;

import org.woehlke.simulation.mandelbrot.model.ApplicationModel;

import java.util.logging.Logger;

import static org.woehlke.simulation.mandelbrot.model.state.ApplicationState.*;
import static org.woehlke.simulation.mandelbrot.model.state.ApplicationState.JULIA_SET_ZOOM;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 16.12.2019.
 */
public class ApplicationStateMachine {

    private volatile ApplicationState applicationState;

    private volatile ApplicationModel model;

    public ApplicationStateMachine(ApplicationModel model) {
        this.applicationState = ApplicationState.MANDELBROT;
        this.model = model;
    }

    public void click(){
        ApplicationState nextApplicationState = null;
        switch (applicationState){
            case MANDELBROT:
                nextApplicationState = JULIA_SET;
                break;
            case JULIA_SET:
                nextApplicationState = MANDELBROT;
                break;
            case MANDELBROT_ZOOM:
            case JULIA_SET_ZOOM:
                nextApplicationState = applicationState;
                break;
        }
        if(model.getConfig().getLogDebug()){
            log.info("click: "+ applicationState + " -> "+ nextApplicationState);
        }
        this.setApplicationState(nextApplicationState);
    }

    public boolean setModeSwitch() {
        boolean repaint = true;
        ApplicationState nextApplicationState = ApplicationState.getDefault();
        switch (applicationState){
            case MANDELBROT:
            case JULIA_SET:
                nextApplicationState = this.applicationState;
                break;
            case MANDELBROT_ZOOM:
                nextApplicationState = MANDELBROT;
                break;
            case JULIA_SET_ZOOM:
                nextApplicationState = JULIA_SET;
                break;
        }
        if(model.getConfig().getLogDebug()){
            log.info("setModeSwitch: "+ applicationState + " -> "+ nextApplicationState);
        }
        this.setApplicationState(nextApplicationState);
        return repaint;
    }

    public boolean setModeZoom() {
        boolean repaint = true;
        ApplicationState nextApplicationState = ApplicationState.getDefault();
        switch (applicationState){
            case MANDELBROT:
                nextApplicationState = MANDELBROT_ZOOM;
                break;
            case JULIA_SET:
                nextApplicationState = JULIA_SET_ZOOM;
                break;
            case MANDELBROT_ZOOM:
            case JULIA_SET_ZOOM:
                nextApplicationState = this.applicationState;
                break;
        }
        if(model.getConfig().getLogDebug()){
            log.info("setModeZoom: "+ applicationState + " -> "+ nextApplicationState);
        }
        this.setApplicationState(nextApplicationState);
        return repaint;
    }

    public ApplicationState getApplicationState() {
        return applicationState;
    }

    public void setApplicationState(ApplicationState applicationState) {
        this.applicationState = applicationState;
    }

    public static Logger log = Logger.getLogger(ApplicationStateMachine.class.getName());
}
