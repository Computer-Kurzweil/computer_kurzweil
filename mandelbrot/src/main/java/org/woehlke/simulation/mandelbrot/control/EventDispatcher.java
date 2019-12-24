package org.woehlke.simulation.mandelbrot.control;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;

public interface EventDispatcher extends WindowListener, MouseListener, ActionListener {

    void start();
    void showMe();
    void step();
    void setModeSwitch();
    void setModeZoom();
    void zoomOut();

    String getZoomLevel();
}
