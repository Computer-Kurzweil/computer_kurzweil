package org.woehlke.simulation.mandelbrot.model.turing;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.woehlke.simulation.mandelbrot.model.MandelbrotContext;


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
@Log
@Service
public class MandelbrotTuringMachine {

    private final MandelbrotContext ctx;

    @Autowired
    public MandelbrotTuringMachine(MandelbrotContext ctx) {
        this.ctx=ctx;
        start();
    }

    public void start() {
        this.ctx.getTuringPhaseStateMachine().start();
        this.ctx.getGaussianNumberPlaneMandelbrot().start();
        this.ctx.getTuringPositionsStateMachine().start();
    }

    public boolean isFinished() {
        return  this.ctx.getTuringPhaseStateMachine().isFinished();
    }

    public void step() {
        switch( this.ctx.getTuringPhaseStateMachine().getTuringTuringPhase()){
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
        if(this.ctx.getGaussianNumberPlaneMandelbrot().isInSet(
            this.ctx.getTuringPositionsStateMachine().getTuringPosition()
        )){
            this.ctx.getTuringPositionsStateMachine().markFirstSetPosition();
            this.ctx.getTuringPhaseStateMachine().finishSearchTheSet();
        } else {
            this.ctx.getTuringPositionsStateMachine().goForward();
        }
    }

    private void stepWalkAround(){
        if(this.ctx.getGaussianNumberPlaneMandelbrot().isInSet(this.ctx.getTuringPositionsStateMachine().getTuringPosition())){
            this.ctx.getTuringPositionsStateMachine().turnRight();
        } else {
            this.ctx.getTuringPositionsStateMachine().turnLeft();
        }
        this.ctx.getTuringPositionsStateMachine().goForward();
        if(this.ctx.getTuringPositionsStateMachine().isFinishedWalkAround()){
            this.ctx.getTuringPhaseStateMachine().finishWalkAround();
        }
    }

    private void stepFillTheOutsideWithColors(){
        this.ctx.getGaussianNumberPlaneMandelbrot().fillTheOutsideWithColors();
        this.ctx.getTuringPhaseStateMachine().finishFillTheOutsideWithColors();
    }

}
