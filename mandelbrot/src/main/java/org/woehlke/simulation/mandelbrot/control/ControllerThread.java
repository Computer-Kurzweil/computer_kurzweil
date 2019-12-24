package org.woehlke.simulation.mandelbrot.control;

public interface ControllerThread extends Runnable, Startable {
    void exit();
}
