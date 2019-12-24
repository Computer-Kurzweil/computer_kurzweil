package org.woehlke.simulation.mandelbrot.model.fractal.common;

import org.woehlke.simulation.mandelbrot.model.numbers.CellStatus;
import org.woehlke.simulation.mandelbrot.model.numbers.LatticePoint;

public interface GaussianNumberPlaneBase {

    CellStatus getCellStatusFor(int x, int y);
    String getZoomLevel();
    void zoomInto(LatticePoint latticePoint);
    boolean isInSet(LatticePoint turingPosition);

    void start();
    void setModeSwitch();
    void setModeZoom();
    void zoomOut();
}
