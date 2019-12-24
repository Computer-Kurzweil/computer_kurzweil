package org.woehlke.simulation.mandelbrot.model.fractal;

import org.woehlke.simulation.mandelbrot.model.numbers.CellStatus;
import org.woehlke.simulation.mandelbrot.model.numbers.LatticePoint;

public interface GaussianNumberPlaneBaseJuliaIF {

    CellStatus getCellStatusFor(int x, int y);

    void setModeSwitch();

    void setModeZoom();

    void zoomOutOfTheJuliaSet();

    String getZoomLevel();

    void computeTheJuliaSetFor(LatticePoint c);

    void zoomIntoTheJuliaSetFor(LatticePoint c);
}
