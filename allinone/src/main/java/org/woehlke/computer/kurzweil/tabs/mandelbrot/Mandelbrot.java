package org.woehlke.computer.kurzweil.tabs.mandelbrot;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.fractal.GaussianNumberPlaneBaseJulia;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.fractal.GaussianNumberPlaneMandelbrot;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.turing.TuringPhaseStateMachine;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.turing.TuringPositionsStateMachine;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.commons.tabs.TabModel;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.state.ApplicationState;

import static org.woehlke.computer.kurzweil.tabs.mandelbrot.model.state.ApplicationState.*;

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
public class Mandelbrot implements Startable, TabModel {

    @Setter
    private ApplicationState state;
    private ApplicationState nextApplicationState;
    private final MandelbrotContext tabCtx;
    private final TuringPositionsStateMachine turingPositionsStateMachine;
    private final TuringPhaseStateMachine turingPhaseStateMachine;
    private final GaussianNumberPlaneMandelbrot gaussianNumberPlaneMandelbrot;
    private final GaussianNumberPlaneBaseJulia gaussianNumberPlaneBaseJulia;

    public Mandelbrot(
        MandelbrotContext tabCtx
    ) {
        this.tabCtx = tabCtx;
        this.state = ApplicationState.start();
        this.gaussianNumberPlaneBaseJulia = new GaussianNumberPlaneBaseJulia(this.tabCtx);
        this.gaussianNumberPlaneMandelbrot = new GaussianNumberPlaneMandelbrot(this.tabCtx);
        this.turingPositionsStateMachine = new TuringPositionsStateMachine(this.tabCtx);
        this.turingPhaseStateMachine = new TuringPhaseStateMachine();
    }

    @Override
    public void start() {
        this.state = ApplicationState.start();
        this.getTuringPhaseStateMachine().start();
        this.getGaussianNumberPlaneMandelbrot().start();
        this.getTuringPositionsStateMachine().start();
    }

    @Override
    public void stop() {
    }

    public void click(){
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
        switch (this.getState()){
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

    public boolean isFinished() {
        return  this.getTuringPhaseStateMachine().isFinished();
    }

    @Override
    public void step() {
        switch( this.getTuringPhaseStateMachine().getTuringTuringPhase()){
            case SEARCH_THE_SET:
                stepGoToSet();
                break;
            case WALK_AROUND_THE_SET:
                stepWalkAround();
                break;
            case FILL_THE_OUTSIDE_WITH_COLOR:
                stepFillTheOutsideWithColors();
                break;
            case FINISHED:
                break;
        }
    }

    private void stepGoToSet(){
        if(this.getGaussianNumberPlaneMandelbrot().isInSet(
            this.getTuringPositionsStateMachine().getTuringPosition()
        )){
            this.getTuringPositionsStateMachine().markFirstSetPosition();
            this.getTuringPhaseStateMachine().finishSearchTheSet();
        } else {
            this.getTuringPositionsStateMachine().goForward();
        }
    }

    private void stepWalkAround(){
        if(this.getGaussianNumberPlaneMandelbrot().isInSet(
            this.getTuringPositionsStateMachine().getTuringPosition()
        )){
            this.getTuringPositionsStateMachine().turnRight();
        } else {
            this.getTuringPositionsStateMachine().turnLeft();
        }
        this.getTuringPositionsStateMachine().goForward();
        if(this.getTuringPositionsStateMachine().isFinishedWalkAround()){
            this.getTuringPhaseStateMachine().finishWalkAround();
        }
    }

    private void stepFillTheOutsideWithColors(){
        this.getGaussianNumberPlaneMandelbrot().fillTheOutsideWithColors();
        this.getTuringPhaseStateMachine().finishFillTheOutsideWithColors();
    }

}
