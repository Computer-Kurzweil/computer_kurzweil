package org.woehlke.simulation.mandelbrot.model.turing;

import org.woehlke.simulation.all.model.LatticePointMandelbrot;

public interface TuringPositionsStateMachine {

    void start();
    void markFirstSetPosition();
    void goForward();
    void turnRight();
    void turnLeft();
    boolean isFinishedWalkAround();

    LatticePointMandelbrot getTuringPosition();
}
