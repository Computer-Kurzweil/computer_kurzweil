package org.woehlke.computer.kurzweil.tabs.mandelbrot.model.turing;

import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.numbers.LatticePoint;

public interface TuringPositionsStateMachine {

    void start();
    void markFirstSetPosition();
    void goForward();
    void turnRight();
    void turnLeft();
    boolean isFinishedWalkAround();

    LatticePoint getTuringPosition();
}
