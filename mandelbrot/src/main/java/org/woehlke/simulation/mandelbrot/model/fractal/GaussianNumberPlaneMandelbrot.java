package org.woehlke.simulation.mandelbrot.model.fractal;

import org.woehlke.simulation.mandelbrot.model.fractal.common.GaussianNumberPlaneBase;
import org.woehlke.simulation.mandelbrot.model.numbers.LatticePoint;

public interface GaussianNumberPlaneMandelbrot extends GaussianNumberPlaneBase {

    void fillTheOutsideWithColors();

    boolean isInSet(LatticePoint turingPosition);

}
