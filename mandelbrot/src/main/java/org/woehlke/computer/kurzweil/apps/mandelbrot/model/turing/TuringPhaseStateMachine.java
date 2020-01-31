package org.woehlke.computer.kurzweil.apps.mandelbrot.model.turing;

import org.woehlke.computer.kurzweil.apps.mandelbrot.model.state.TuringPhase;

public interface TuringPhaseStateMachine {

     void start();
     void finishSearchTheSet();
     void finishWalkAround();
     void finishFillTheOutsideWithColors();
     boolean isFinished();

     TuringPhase getTuringTuringPhase();
}
