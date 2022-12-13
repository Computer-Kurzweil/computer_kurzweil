package org.woehlke.computer.kurzweil.mandelbrot.zoom;

import org.woehlke.computer.kurzweil.mandelbrot.zoom.config.Config;
import org.woehlke.computer.kurzweil.mandelbrot.zoom.view.ApplicationFrame;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 * (C) 2006 - 2022 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see <a href="https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html">Blog Article</a>
 * @see <a href="https://github.com/Computer-Kurzweil/mandelbrot-zoom">Github Repository</a>
 * @see <a href="https://java.woehlke.org/mandelbrot-zoom/">Maven Project Repository</a>
 *
 */
public class MandelbrotSetApplication {

    private MandelbrotSetApplication() {
        Config config = new Config();
        ApplicationFrame frame = new ApplicationFrame(config);
    }

    /**
     * Starting the Application.
     * @param args CLI Parameter
     */
    public static void main(String[] args) {
        MandelbrotSetApplication application = new MandelbrotSetApplication();
    }
}
