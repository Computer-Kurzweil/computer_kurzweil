package org.woehlke.computer.kurzweil.apps.mandelbrot.model.state;

public enum ClickBehaviour {

    SWITCH_BETWEEN_MANDELBROT_AND_JULIA_SET,
    ZOOM_IN;

    public static ClickBehaviour start(){
        return SWITCH_BETWEEN_MANDELBROT_AND_JULIA_SET;
    }

}
