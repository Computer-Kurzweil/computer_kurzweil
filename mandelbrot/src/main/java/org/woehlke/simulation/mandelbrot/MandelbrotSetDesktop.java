package org.woehlke.simulation.mandelbrot;

import org.woehlke.simulation.mandelbrot.view.desktop.MandelbrotSetFrame;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 */
public class MandelbrotSetDesktop {

    private MandelbrotSetDesktop() { }

    /**
     * Starting the App.
     * @param args CLI Parameter
     */
    public static void main(String[] args) {
        MandelbrotSetFrame mandelbrot = new MandelbrotSetFrame();
    }
}
