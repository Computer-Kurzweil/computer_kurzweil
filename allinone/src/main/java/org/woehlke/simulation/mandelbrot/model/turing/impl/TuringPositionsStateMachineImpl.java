package org.woehlke.simulation.mandelbrot.model.turing.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.woehlke.simulation.mandelbrot.config.MandelbrotProperties;
import org.woehlke.simulation.mandelbrot.model.numbers.LatticePoint;
import org.woehlke.simulation.mandelbrot.model.turing.TuringPositionsStateMachine;
import org.woehlke.simulation.mandelbrot.model.turing.state.TuringDirection;

import java.util.logging.Logger;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 16.12.2019.
 */
@Service
public class TuringPositionsStateMachineImpl implements TuringPositionsStateMachine {

    private LatticePoint turingPosition;
    private LatticePoint worldDimensions;
    private LatticePoint firstSetPosition;

    private TuringDirection turingDirection;

    private int steps;

    @Autowired
    public TuringPositionsStateMachineImpl(MandelbrotProperties properties) {
        this.worldDimensions = properties.getWorldDimensions();
        start();
    }

    public void start() {
        this.steps = 0;
        this.turingPosition = LatticePoint.start(worldDimensions);
        this.firstSetPosition = null;
        this.turingDirection = TuringDirection.start();
    }

    public void markFirstSetPosition(){
        this.firstSetPosition = this.turingPosition.copy();
        this.steps = 0;
    }

    public LatticePoint getTuringPosition() {
        return turingPosition;
    }

    public void goForward() {
        this.steps++;
        switch (this.turingDirection){
            case UP:
                this.turingPosition.moveUp();
                break;
            case RIGHT:
                this.turingPosition.moveRight();
                break;
            case DOWN:
                this.turingPosition.moveDown();
                break;
            case LEFT:
                this.turingPosition.moveLeft();
                break;
            default:
                break;
        }
    }

    public void turnRight() {
        TuringDirection newTuringDirection;
        switch (this.turingDirection){
            case UP: newTuringDirection = TuringDirection.RIGHT; break;
            case RIGHT: newTuringDirection = TuringDirection.DOWN; break;
            case DOWN: newTuringDirection = TuringDirection.LEFT; break;
            case LEFT: newTuringDirection = TuringDirection.UP; break;
            default: newTuringDirection = this.turingDirection; break;
        }
        this.turingDirection = newTuringDirection;
    }

    public void turnLeft() {
        TuringDirection newTuringDirection;
        switch (this.turingDirection){
            case UP: newTuringDirection = TuringDirection.LEFT; break;
            case RIGHT: newTuringDirection = TuringDirection.UP; break;
            case DOWN: newTuringDirection = TuringDirection.RIGHT; break;
            case LEFT: newTuringDirection = TuringDirection.DOWN; break;
            default: newTuringDirection = this.turingDirection; break;
        }
        this.turingDirection = newTuringDirection;
    }

    public boolean isFinishedWalkAround() {
        return (this.turingPosition.equals(this.firstSetPosition)) && (this.steps>100);
    }

    public static Logger log = Logger.getLogger(TuringPositionsStateMachineImpl.class.getName());
}
