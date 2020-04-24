package org.woehlke.computer.kurzweil;

import org.woehlke.computer.kurzweil.application.Config;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.MandelbrotTab;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 */
public class MandelbrotApplication {

    private MandelbrotApplication() {
        Config config = new Config();
        MandelbrotTab frame = new MandelbrotTab(config);
    }

    /**
     * Starting the Application.
     * @param args CLI Parameter
     */
    public static void main(String[] args) {
        MandelbrotApplication application = new MandelbrotApplication();
    }
}
