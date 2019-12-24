package org.woehlke.simulation.mandelbrot.model;

public interface MandelbrotTuringMachine {
    void step();
    void start();

    boolean isFinished();

}
