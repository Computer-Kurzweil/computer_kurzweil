package org.woehlke.simulation.mandelbrot.control;

import org.woehlke.simulation.mandelbrot.control.common.EventDispatcher;
import org.woehlke.simulation.mandelbrot.control.common.ObjectRegistry;

public interface ApplicationContext extends ObjectRegistry, EventDispatcher {

    void start();
    void showMe();
    void setModeSwitch();
    void setModeZoom();
    void zoomOut();

    String getZoomLevel();
}
