package org.woehlke.simulation.mandelbrot.model.fractal;

import org.woehlke.simulation.mandelbrot.model.numbers.LatticePoint;

public interface GaussianNumberPlaneBaseMandelbrot extends GaussianNumberPlaneBase {

    void zoomOutOfTheMandelbrotSet();

    void zoomIntoTheMandelbrotSet(LatticePoint c);

    boolean isInMandelbrotSet(LatticePoint turingPosition);

    void fillTheOutsideWithColors();
}
