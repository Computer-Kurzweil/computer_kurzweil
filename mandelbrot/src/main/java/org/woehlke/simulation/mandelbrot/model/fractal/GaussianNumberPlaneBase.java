package org.woehlke.simulation.mandelbrot.model.fractal;

import org.woehlke.simulation.mandelbrot.model.numbers.CellStatus;

public interface GaussianNumberPlaneBase {

    void start();

    void setModeSwitch();

    void setModeZoom();

    CellStatus getCellStatusFor(int x, int y);

    String getZoomLevel();

}
