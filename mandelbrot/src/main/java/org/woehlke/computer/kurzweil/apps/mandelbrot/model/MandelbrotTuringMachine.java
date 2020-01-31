package org.woehlke.computer.kurzweil.apps.mandelbrot.model;

public interface MandelbrotTuringMachine {

    void start();
    void step();
    boolean isFinished();

}
