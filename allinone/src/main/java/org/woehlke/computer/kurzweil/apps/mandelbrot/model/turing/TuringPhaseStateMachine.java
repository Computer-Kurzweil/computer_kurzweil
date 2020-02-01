package org.woehlke.computer.kurzweil.apps.mandelbrot.model.turing;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.apps.mandelbrot.model.state.TuringPhase;
import org.woehlke.computer.kurzweil.control.startables.Startable;

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
public class TuringPhaseStateMachine  implements Startable {

    @Getter
    private TuringPhase turingTuringPhase;

    public TuringPhaseStateMachine() {
        turingTuringPhase = TuringPhase.start();
    }

    @Override
    public void start(){
        turingTuringPhase = TuringPhase.start();
    }

    @Override
    public void stop() {
        turingTuringPhase = TuringPhase.FINISHED;
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

    public boolean isFinished() {
        return turingTuringPhase == TuringPhase.FINISHED;
    }

}
