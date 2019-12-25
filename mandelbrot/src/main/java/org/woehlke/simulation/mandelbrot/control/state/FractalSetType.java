package org.woehlke.simulation.mandelbrot.control.state;

public enum FractalSetType {
    MANDELBROT_SET,
    JULIA_SET;

    public static FractalSetType start(){
        return MANDELBROT_SET;
    }
}
