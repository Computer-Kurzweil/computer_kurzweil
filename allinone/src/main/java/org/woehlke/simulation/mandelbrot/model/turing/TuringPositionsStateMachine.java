package org.woehlke.simulation.mandelbrot.model.turing;

import org.woehlke.simulation.allinone.model.LatticePoint;

public interface TuringPositionsStateMachine {

    void start();
    void markFirstSetPosition();
    void goForward();
    void turnRight();
    void turnLeft();
    boolean isFinishedWalkAround();

    LatticePoint getTuringPosition();
}
