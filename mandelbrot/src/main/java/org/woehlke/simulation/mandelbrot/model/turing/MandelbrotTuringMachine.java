package org.woehlke.simulation.mandelbrot.model.turing;

import org.woehlke.simulation.mandelbrot.model.ApplicationModel;
import org.woehlke.simulation.mandelbrot.model.fractal.GaussianNumberPlane;

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
public class MandelbrotTuringMachine {

    private volatile GaussianNumberPlane gaussianNumberPlane;
    private volatile TuringPositionsStateMachine turingPositionsStateMachine;
    private volatile TuringPhaseStateMachine turingPhaseStateMachine;

    public MandelbrotTuringMachine(ApplicationModel model) {
        this.gaussianNumberPlane = model.getGaussianNumberPlane();
        this.turingPhaseStateMachine = new TuringPhaseStateMachine();
        this.turingPositionsStateMachine = new TuringPositionsStateMachine(model.getWorldDimensions());
        start();
    }

    public synchronized void start() {
        this.turingPhaseStateMachine.start();
        this.gaussianNumberPlane.start();
        this.turingPositionsStateMachine.start();
    }

    public synchronized void step() {
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

    private static Logger log = Logger.getLogger(MandelbrotTuringMachine.class.getName());
}
