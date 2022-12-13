package org.woehlke.computer.kurzweil.mandelbrot.config;

import static java.io.File.separator;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 * (C) 2006 - 2022 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see <a href="https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html">Blog Article</a>
 * @see <a href="https://github.com/Computer-Kurzweil/mandelbrot-julia">Github Repository</a>
 * @see <a href="https://java.woehlke.org/mandelbrot-julia/">Maven Project Repository</a>
 *
 * Created by tw on 16.12.2019.
 */
public interface ConfigProperties {

    String TITLE = "Mandelbrot Set";
    String SUBTITLE = "Mandelbrot Set drawn by a Turing Machine";
    String COPYRIGHT = "(c) 2022 Thomas Woehlke";
    String WIDTH="640";
    String HEIGHT="468";

    String APP_PROPERTIES_FILENAME ="src" + separator
        +"main" + separator + "resources" + separator  + "application.properties";

    String KEY = "org.woehlke.computer.kurzweil.mandelbrot.config.";

    String KEY_TITLE = KEY + "title";
    String KEY_SUBTITLE = KEY + "subtitle";
    String KEY_COPYRIGHT = KEY + "copyright";
    String KEY_WIDTH = KEY + "width";
    String KEY_HEIGHT = KEY + "height";

}
