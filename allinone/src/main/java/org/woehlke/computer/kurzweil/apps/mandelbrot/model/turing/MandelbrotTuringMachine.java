package org.woehlke.computer.kurzweil.apps.mandelbrot.model.turing;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.apps.mandelbrot.model.fractal.GaussianNumberPlaneMandelbrot;
import org.woehlke.computer.kurzweil.control.controller.Stepper;
import org.woehlke.computer.kurzweil.control.startables.Startable;


/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Date: 28.08.13
 * Time: 12:39
 */
@Log
public class MandelbrotTuringMachine  implements Startable, Stepper {

    @Getter private final TuringPositionsStateMachine turingPositionsStateMachine;
    @Getter private final TuringPhaseStateMachine turingPhaseStateMachine;
    @Getter private final GaussianNumberPlaneMandelbrot gaussianNumberPlaneMandelbrot;

    public MandelbrotTuringMachine(GaussianNumberPlaneMandelbrot gaussianNumberPlaneMandelbrot) {
        this.gaussianNumberPlaneMandelbrot = gaussianNumberPlaneMandelbrot;
        turingPhaseStateMachine = new TuringPhaseStateMachine();
        turingPositionsStateMachine = new TuringPositionsStateMachine(this.gaussianNumberPlaneMandelbrot.getCtx());
        start();
    }

    @Override
    public void start() {
        this.getTuringPhaseStateMachine().start();
        this.getGaussianNumberPlaneMandelbrot().start();
        this.getTuringPositionsStateMachine().start();
    }

    @Override
    public void stop() {

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
