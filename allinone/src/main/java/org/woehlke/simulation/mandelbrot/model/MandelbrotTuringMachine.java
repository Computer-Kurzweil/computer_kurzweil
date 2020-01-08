package org.woehlke.simulation.mandelbrot.model;

public interface MandelbrotTuringMachine {

    void start();
    void step();
    boolean isFinished();

}
