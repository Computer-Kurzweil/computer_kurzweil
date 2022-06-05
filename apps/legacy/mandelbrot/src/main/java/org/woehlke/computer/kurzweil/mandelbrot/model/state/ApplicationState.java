package org.woehlke.computer.kurzweil.mandelbrot.model.state;

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
    MANDELBROT,
    JULIA_SET,
    MANDELBROT_ZOOM,
    JULIA_SET_ZOOM
}
