package org.woehlke.simulation.mandelbrot.control.common;

public interface MandelbrotApplicationContext extends ObjectRegistry, EventDispatcher {

    void start();
    void showMe();
    void setModeSwitch();
    void setModeZoom();
    void zoomOut();

    String getZoomLevel();
}
