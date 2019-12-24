package org.woehlke.simulation.mandelbrot.model;

import org.woehlke.simulation.mandelbrot.control.Startable;

public interface MandelbrotTuringMachine extends Startable {
    void step();
}
