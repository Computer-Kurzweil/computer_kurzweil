package org.woehlke.computer.kurzweil.apps.mandelbrot.control.state;

public enum FractalSetType {
    MANDELBROT_SET,
    JULIA_SET;

    public static FractalSetType start(){
        return MANDELBROT_SET;
    }
}
