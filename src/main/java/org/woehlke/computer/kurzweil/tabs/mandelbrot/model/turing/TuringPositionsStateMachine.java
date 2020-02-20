package org.woehlke.computer.kurzweil.tabs.mandelbrot.model.turing;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.model.LatticePoint;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.MandelbrotContext;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.state.TuringDirection;
import org.woehlke.computer.kurzweil.commons.Startable;

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
public class TuringPositionsStateMachine implements Startable {

    @ToString.Exclude
    private final MandelbrotContext tabCtx;

    private int steps;
    private LatticePoint turingPosition;
    private LatticePoint firstSetPosition;
    private TuringDirection turingDirection;

    private final LatticePoint wWorldDimensions;

    public TuringPositionsStateMachine(MandelbrotContext tabCtx) {
        this.tabCtx=tabCtx;
        this.wWorldDimensions = this.tabCtx.getCtx().getWorldDimensions();
        start();
    }

    public void start() {
        this.steps = 0;
        this.turingPosition = LatticePoint.start(this.wWorldDimensions);
        this.firstSetPosition = null;
        this.turingDirection = TuringDirection.start();
    }

    @Override
    public void stop() {

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

}
