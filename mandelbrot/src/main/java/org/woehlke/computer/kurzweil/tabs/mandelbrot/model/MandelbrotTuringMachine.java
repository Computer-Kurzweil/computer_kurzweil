package org.woehlke.computer.kurzweil.tabs.mandelbrot.model;

public interface MandelbrotTuringMachine {

    void start();
    void step();
    boolean isFinished();

}
