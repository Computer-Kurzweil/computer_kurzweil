package org.woehlke.computer.kurzweil.tabs.mandelbrot.model.turing;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.state.TuringPhase;
import org.woehlke.computer.kurzweil.commons.application.Startable;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 16.12.2019.
 */
@Log4j2
@Getter
@ToString(callSuper = true)
public class TuringPhaseStateMachine implements Startable {

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
        turingTuringPhase = TuringPhase.stop();
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
