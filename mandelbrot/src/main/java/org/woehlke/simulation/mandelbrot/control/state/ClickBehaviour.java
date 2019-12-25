package org.woehlke.simulation.mandelbrot.control.state;

public enum ClickBehaviour {
    SWITCH_BETWEEN_MANDELBROT_AND_JULIA_SET,
    ZOOM_IN;

    public static ClickBehaviour start(){
        return SWITCH_BETWEEN_MANDELBROT_AND_JULIA_SET;
    }

}
