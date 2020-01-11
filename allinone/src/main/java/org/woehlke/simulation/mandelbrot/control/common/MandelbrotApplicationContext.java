package org.woehlke.simulation.mandelbrot.control.common;

import org.woehlke.simulation.mandelbrot.control.common.EventDispatcher;
import org.woehlke.simulation.mandelbrot.control.common.ObjectRegistry;

public interface MandelbrotApplicationContext extends ObjectRegistry, EventDispatcher {

    void start();
    void showMe();
    void setModeSwitch();
    void setModeZoom();
    void zoomOut();

    String getZoomLevel();
}
