package org.woehlke.computer.kurzweil.mandelbrot.config;

import static java.io.File.separator;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 16.12.2019.
 */
public interface ConfigProperties {

    String TITLE = "Mandelbrot Set";
    String SUBTITLE = "Mandelbrot Set drawn by a Turing Machine";
    String COPYRIGHT = "(c) 2019 Thomas Woehlke";
    String WIDTH="640";
    String HEIGHT="468";

    String BUTTONS_LABEL = "Choose Mode";
    String BUTTONS_SWITCH = "Mandelbrot Set <-> Julia Set";
    String BUTTONS_ZOOM = "Zoom";
    String BUTTONS_ZOOMOUT = "Zoom out";

    String APP_PROPERTIES_FILENAME ="src" + separator
        +"main" + separator + "resources" + separator  + "application.properties";

    String KEY = "org.woehlke.computer.kurzweil.mandelbrot.config.";

    String KEY_TITLE = KEY + "title";
    String KEY_SUBTITLE = KEY + "subtitle";
    String KEY_COPYRIGHT = KEY + "copyright";
    String KEY_WIDTH = KEY + "width";
    String KEY_HEIGHT = KEY + "height";

    String KEY_BUTTONS_LABEL = KEY + "buttons.label";
    String KEY_BUTTONS_SWITCH = KEY + "buttons.switch";
    String KEY_BUTTONS_ZOOM = KEY + "buttons.zoom";
    String KEY_BUTTONS_ZOOMOUT = KEY + "buttons.zoomout";
}
