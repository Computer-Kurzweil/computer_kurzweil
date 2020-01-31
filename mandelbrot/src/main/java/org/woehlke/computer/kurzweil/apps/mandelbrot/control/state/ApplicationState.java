package org.woehlke.computer.kurzweil.apps.mandelbrot.control.state;

import static org.woehlke.computer.kurzweil.apps.mandelbrot.control.state.ClickBehaviour.SWITCH_BETWEEN_MANDELBROT_AND_JULIA_SET;
import static org.woehlke.computer.kurzweil.apps.mandelbrot.control.state.ClickBehaviour.ZOOM_IN;
import static org.woehlke.computer.kurzweil.apps.mandelbrot.control.state.FractalSetType.JULIA_SET;
import static org.woehlke.computer.kurzweil.apps.mandelbrot.control.state.FractalSetType.MANDELBROT_SET;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 23.08.15.
 */
public enum ApplicationState {
    MANDELBROT_SWITCH(MANDELBROT_SET, SWITCH_BETWEEN_MANDELBROT_AND_JULIA_SET),
    JULIA_SET_SWITCH(JULIA_SET, SWITCH_BETWEEN_MANDELBROT_AND_JULIA_SET),
    MANDELBROT_ZOOM(MANDELBROT_SET, ZOOM_IN),
    JULIA_SET_ZOOM(JULIA_SET, ZOOM_IN);

    private FractalSetType fractalSetType;
    private ClickBehaviour clickBehaviour;

    ApplicationState(FractalSetType fractalSetType, ClickBehaviour clickBehaviour) {
        this.fractalSetType = fractalSetType;
        this.clickBehaviour = clickBehaviour;
    }

    public FractalSetType getFractalSetType() {
        return fractalSetType;
    }

    public ClickBehaviour getClickBehaviour() {
        return clickBehaviour;
    }

    public static ApplicationState start(){
        return MANDELBROT_SWITCH;
    }

    public static ApplicationState resolve(FractalSetType fractalSetType, ClickBehaviour clickBehaviour){
        ApplicationState result;
        switch (clickBehaviour){
            case ZOOM_IN:
                switch (fractalSetType){
                    case JULIA_SET:
                        result = ApplicationState.JULIA_SET_ZOOM;
                        break;
                    case MANDELBROT_SET:
                    default:
                        result = ApplicationState.MANDELBROT_ZOOM;
                        break;
                }
                break;
            case SWITCH_BETWEEN_MANDELBROT_AND_JULIA_SET:
            default:
                switch (fractalSetType){
                    case JULIA_SET:
                        result = ApplicationState.JULIA_SET_SWITCH;
                        break;
                    case MANDELBROT_SET:
                    default:
                        result = ApplicationState.MANDELBROT_SWITCH;
                        break;
                }
                break;
        }
        return result;
    }
}
