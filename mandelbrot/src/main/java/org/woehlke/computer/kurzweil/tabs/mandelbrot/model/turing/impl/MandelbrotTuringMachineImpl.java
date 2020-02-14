package org.woehlke.computer.kurzweil.tabs.mandelbrot.model.turing.impl;

import org.woehlke.computer.kurzweil.tabs.mandelbrot.control.ApplicationContext;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.MandelbrotTuringMachine;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.fractal.GaussianNumberPlaneMandelbrot;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.turing.TuringPhaseStateMachine;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.turing.TuringPositionsStateMachine;

import java.util.logging.Logger;

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
public class MandelbrotTuringMachineImpl implements MandelbrotTuringMachine {

    private GaussianNumberPlaneMandelbrot gaussianNumberPlaneMandelbrot;
    private TuringPositionsStateMachine turingPositionsStateMachine;
    private TuringPhaseStateMachine turingPhaseStateMachine;

    public MandelbrotTuringMachineImpl(ApplicationContext model) {
        this.gaussianNumberPlaneMandelbrot = model.getGaussianNumberPlaneMandelbrot();
        this.turingPhaseStateMachine = new TuringPhaseStateMachineImpl();
        this.turingPositionsStateMachine = new TuringPositionsStateMachineImpl(model.getWorldDimensions());
        start();
    }

    @Override
    public void start() {
        this.turingPhaseStateMachine.start();
        this.gaussianNumberPlaneMandelbrot.start();
        this.turingPositionsStateMachine.start();
    }

    @Override
    public boolean isFinished() {
        return turingPhaseStateMachine.isFinished();
    }

    @Override
    public void step() {
        switch(turingPhaseStateMachine.getTuringTuringPhase()){
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
        if(this.gaussianNumberPlaneMandelbrot.isInSet(
            this.turingPositionsStateMachine.getTuringPosition())
        ){
            this.turingPositionsStateMachine.markFirstSetPosition();
            this.turingPhaseStateMachine.finishSearchTheSet();
        } else {
            this.turingPositionsStateMachine.goForward();
        }
    }

    private void stepWalkAround(){
        if(gaussianNumberPlaneMandelbrot.isInSet(this.turingPositionsStateMachine.getTuringPosition())){
            this.turingPositionsStateMachine.turnRight();
        } else {
            this.turingPositionsStateMachine.turnLeft();
        }
        this.turingPositionsStateMachine.goForward();
        if(this.turingPositionsStateMachine.isFinishedWalkAround()){
            this.turingPhaseStateMachine.finishWalkAround();
        }
    }

    private void stepFillTheOutsideWithColors(){
        this.gaussianNumberPlaneMandelbrot.fillTheOutsideWithColors();
        this.turingPhaseStateMachine.finishFillTheOutsideWithColors();
    }

    private static Logger log = Logger.getLogger(MandelbrotTuringMachineImpl.class.getName());
}
