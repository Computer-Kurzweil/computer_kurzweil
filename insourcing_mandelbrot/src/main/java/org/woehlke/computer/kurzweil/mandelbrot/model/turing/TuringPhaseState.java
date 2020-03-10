package org.woehlke.computer.kurzweil.mandelbrot.model.turing;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 16.12.2019.
 */
public class TuringPhaseState {

    private volatile TuringPhase turingTuringPhase;

    public TuringPhaseState() {
        start();
    }

    public void start(){
        this.turingTuringPhase = TuringPhase.SEARCH_THE_SET;
    }

    public void finishSearchTheSet(){
        turingTuringPhase = TuringPhase.WALK_AROUND_THE_SET;
    }

    public void finishWalkAround() {
        turingTuringPhase = TuringPhase.FILL_THE_OUTSIDE_WITH_COLOR;
    }

    public void finishFillTheOutsideWithColors() {
        turingTuringPhase = TuringPhase.FINISHED;
    }

    public TuringPhase getTuringTuringPhase() {
        return turingTuringPhase;
    }
}
