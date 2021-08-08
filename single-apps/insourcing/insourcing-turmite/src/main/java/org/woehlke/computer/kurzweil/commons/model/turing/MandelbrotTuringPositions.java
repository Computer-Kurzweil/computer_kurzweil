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
public class MandelbrotTuringPositions implements Serializable {

    private static final long serialVersionUID = 7526471155622776147L;

    private volatile Point turingPosition;
    private volatile Point worldDimensions;
    private volatile Point firstSetPosition;

    private volatile MandelbrotTuringDirection turingDirection;

    private volatile int steps;

    public MandelbrotTuringPositions(Point worldDimensions) {
        this.worldDimensions = worldDimensions;
        start();
    }

    public void start() {
        this.steps = 0;
        this.turingPosition = new Point((worldDimensions.getX()-2),(worldDimensions.getY()/2+11));
        this.turingDirection = MandelbrotTuringDirection.LEFT;
    }

    public synchronized void markFirstSetPosition(){
        this.firstSetPosition = turingPosition;
        this.steps = 0;
    }

    public synchronized Point getTuringPosition() {
        return turingPosition;
    }

    public synchronized void goForward() {
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

    public synchronized void turnRight() {
        MandelbrotTuringDirection newTuringDirection;
        switch (this.turingDirection){
            case UP: newTuringDirection = MandelbrotTuringDirection.RIGHT; break;
            case RIGHT: newTuringDirection = MandelbrotTuringDirection.DOWN; break;
            case DOWN: newTuringDirection = MandelbrotTuringDirection.LEFT; break;
            case LEFT: newTuringDirection = MandelbrotTuringDirection.UP; break;
            default: newTuringDirection = this.turingDirection; break;
        }
        this.turingDirection = newTuringDirection;
    }

    public synchronized void turnLeft() {
        MandelbrotTuringDirection newTuringDirection;
        switch (this.turingDirection){
            case UP: newTuringDirection = MandelbrotTuringDirection.LEFT; break;
            case RIGHT: newTuringDirection = MandelbrotTuringDirection.UP; break;
            case DOWN: newTuringDirection = MandelbrotTuringDirection.RIGHT; break;
            case LEFT: newTuringDirection = MandelbrotTuringDirection.DOWN; break;
            default: newTuringDirection = this.turingDirection; break;
        }
        this.turingDirection = newTuringDirection;
    }

    public synchronized boolean isFinishedWalkAround() {
        return (this.turingPosition.equals(this.firstSetPosition)) && (this.steps>100);
    }

}
