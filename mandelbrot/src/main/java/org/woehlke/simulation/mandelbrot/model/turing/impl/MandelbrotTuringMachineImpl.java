package org.woehlke.simulation.mandelbrot.model.turing.impl;

import org.woehlke.simulation.mandelbrot.control.ObjectRegistryAndEventDispatcher;
import org.woehlke.simulation.mandelbrot.model.MandelbrotTuringMachine;
import org.woehlke.simulation.mandelbrot.model.fractal.GaussianNumberPlaneBaseMandelbrot;

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

    private GaussianNumberPlaneBaseMandelbrot gaussianNumberPlane;
    private TuringPositionsStateMachineImpl turingPositionsStateMachine;
    private TuringPhaseStateMachineImpl turingPhaseStateMachine;

    public MandelbrotTuringMachineImpl(ObjectRegistryAndEventDispatcher model) {
        this.gaussianNumberPlane = model.getGaussianNumberPlaneBaseMandelbrot();
        this.turingPhaseStateMachine = new TuringPhaseStateMachineImpl();
        this.turingPositionsStateMachine = new TuringPositionsStateMachineImpl(model.getWorldDimensions());
        start();
    }

    public void start() {
        this.turingPhaseStateMachine.start();
        this.gaussianNumberPlane.start();
        this.turingPositionsStateMachine.start();
    }

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
        }
    }

    private void stepGoToSet(){
        if(this.gaussianNumberPlane.isInMandelbrotSet(this.turingPositionsStateMachine.getTuringPosition())){
            this.turingPositionsStateMachine.markFirstSetPosition();
            this.turingPhaseStateMachine.finishSearchTheSet();
        } else {
            this.turingPositionsStateMachine.goForward();
        }
    }

    private void stepWalkAround(){
        if(gaussianNumberPlane.isInMandelbrotSet(this.turingPositionsStateMachine.getTuringPosition())){
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
        this.gaussianNumberPlane.fillTheOutsideWithColors();
        this.turingPhaseStateMachine.finishFillTheOutsideWithColors();
    }

    private static Logger log = Logger.getLogger(MandelbrotTuringMachineImpl.class.getName());
}
