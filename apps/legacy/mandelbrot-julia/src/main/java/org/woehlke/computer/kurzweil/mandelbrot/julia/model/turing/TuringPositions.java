package org.woehlke.computer.kurzweil.mandelbrot.julia.model.turing;

import org.woehlke.computer.kurzweil.mandelbrot.julia.model.common.Point;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 * (C) 2006 - 2022 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see <a href="https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html">Blog Article</a>
 * @see <a href="https://github.com/Computer-Kurzweil/mandelbrot-julia">Github Repository</a>
 * @see <a href="https://java.woehlke.org/mandelbrot-julia/">Maven Project Repository</a>
 *
 * @see Point
 * @see TuringDirection
 *
 * Created by tw on 16.12.2019.
 */
public class TuringPositions {

    private volatile Point turingPosition;
    private volatile Point worldDimensions;
    private volatile Point firstSetPosition;

    private volatile TuringDirection turingDirection;

    private volatile int steps;

    public TuringPositions(Point worldDimensions) {
        this.worldDimensions = worldDimensions;
        start();
    }

    public void start() {
        this.steps = 0;
        this.turingPosition = new Point((worldDimensions.getX()-2),(worldDimensions.getY()/2+11));
        this.turingDirection = TuringDirection.LEFT;
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

    public synchronized void turnLeft() {
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

    public synchronized boolean isFinishedWalkAround() {
        return (this.turingPosition.equals(this.firstSetPosition)) && (this.steps>100);
    }

}
