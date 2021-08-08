package org.woehlke.computer.kurzweil.commons.model.turing;

import java.io.Serializable;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 16.12.2019.
 */
public class MandelbrotTuringPhaseState implements Serializable {

    private static final long serialVersionUID = 7526471155622776147L;

    private volatile MandelbrotTuringPhase turingTuringPhase;

    public MandelbrotTuringPhaseState() {
        start();
    }

    public void start(){
        this.turingTuringPhase = MandelbrotTuringPhase.SEARCH_THE_SET;
    }

    public void finishSearchTheSet(){
        turingTuringPhase = MandelbrotTuringPhase.WALK_AROUND_THE_SET;
    }

    public void finishWalkAround() {
        turingTuringPhase = MandelbrotTuringPhase.FILL_THE_OUTSIDE_WITH_COLOR;
    }

    public void finishFillTheOutsideWithColors() {
        turingTuringPhase = MandelbrotTuringPhase.FINISHED;
    }

    public MandelbrotTuringPhase getTuringTuringPhase() {
        return turingTuringPhase;
    }
}
