package org.woehlke.simulation.mandelbrot.model.turing;

import org.woehlke.simulation.mandelbrot.model.turing.state.TuringPhase;

public interface TuringPhaseStateMachine {

     void start();
     void finishSearchTheSet();
     void finishWalkAround();
     void finishFillTheOutsideWithColors();
     boolean isFinished();

     TuringPhase getTuringTuringPhase();
}
