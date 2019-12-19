package org.woehlke.simulation.mandelbrot.model.turing;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 18.08.15.
 */
public enum TuringPhase {
    SEARCH_THE_SET,
    WALK_AROUND_THE_SET,
    FILL_THE_OUTSIDE_WITH_COLOR,
    FINISHED
}
