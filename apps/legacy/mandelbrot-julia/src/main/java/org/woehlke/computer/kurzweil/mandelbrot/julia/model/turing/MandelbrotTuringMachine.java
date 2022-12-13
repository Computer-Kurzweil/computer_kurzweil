package org.woehlke.computer.kurzweil.mandelbrot.julia.model.turing;

import org.woehlke.computer.kurzweil.mandelbrot.julia.model.ApplicationModel;
import org.woehlke.computer.kurzweil.mandelbrot.julia.model.fractal.GaussianNumberPlane;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 * (C) 2006 - 2022 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see <a href="https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html">Blog Article</a>
 * @see <a href="https://github.com/Computer-Kurzweil/mandelbrot-julia">Github Repository</a>
 * @see <a href="https://java.woehlke.org/mandelbrot-julia/">Maven Project Repository</a>
 *
 * @see GaussianNumberPlane
 * @see TuringPositions
 * @see TuringPhaseState
 *
 * Date: 28.08.13
 * Time: 12:39
 */
public class MandelbrotTuringMachine {

    private volatile GaussianNumberPlane gaussianNumberPlane;
    private volatile TuringPositions turingPositions;
    private volatile TuringPhaseState turingPhaseState;

    public MandelbrotTuringMachine(ApplicationModel model) {
        this.gaussianNumberPlane = model.getGaussianNumberPlane();
        this.turingPhaseState = new TuringPhaseState();
        this.turingPositions = new TuringPositions(model.getWorldDimensions());
        start();
    }

    public void start() {
        this.turingPhaseState.start();
        this.gaussianNumberPlane.start();
        this.turingPositions.start();
    }

    public synchronized boolean step() {
        boolean repaint=true;
        switch(turingPhaseState.getTuringTuringPhase()){
            case SEARCH_THE_SET:
                stepGoToSet();
                repaint=false;
                break;
            case WALK_AROUND_THE_SET:
                stepWalkAround();
                break;
            case FILL_THE_OUTSIDE_WITH_COLOR:
                fillTheOutsideWithColors();
                break;
            case FINISHED:
            default:
                repaint=false;
                break;
        }
        return repaint;
    }

    private void stepGoToSet(){
        if(this.gaussianNumberPlane.isInMandelbrotSet(this.turingPositions.getTuringPosition())){
            this.turingPositions.markFirstSetPosition();
            this.turingPhaseState.finishSearchTheSet();
        } else {
            this.turingPositions.goForward();
        }
    }

    private void stepWalkAround(){
        if(gaussianNumberPlane.isInMandelbrotSet(this.turingPositions.getTuringPosition())){
            this.turingPositions.turnRight();
        } else {
            this.turingPositions.turnLeft();
        }
        this.turingPositions.goForward();
        if(this.turingPositions.isFinishedWalkAround()){
            this.turingPhaseState.finishWalkAround();
        }
    }

    private void fillTheOutsideWithColors(){
        this.gaussianNumberPlane.fillTheOutsideWithColors();
        this.turingPhaseState.finishFillTheOutsideWithColors();
    }
}
