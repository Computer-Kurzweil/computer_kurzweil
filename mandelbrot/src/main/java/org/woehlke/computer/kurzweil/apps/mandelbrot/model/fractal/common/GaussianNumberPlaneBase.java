package org.woehlke.computer.kurzweil.apps.mandelbrot.model.fractal.common;

import org.woehlke.computer.kurzweil.apps.mandelbrot.model.numbers.CellStatus;
import org.woehlke.computer.kurzweil.apps.mandelbrot.model.numbers.LatticePoint;

public interface GaussianNumberPlaneBase {

    CellStatus getCellStatusFor(int x, int y);
    CellStatus getCellStatusFor(final LatticePoint turingPosition);

    String getZoomLevel();
    void zoomInto(LatticePoint latticePoint);

    void start();
    void setModeSwitch();
    void setModeZoom();
    void zoomOut();

    boolean isModeSwitch();
    boolean isModeZoom();
}
