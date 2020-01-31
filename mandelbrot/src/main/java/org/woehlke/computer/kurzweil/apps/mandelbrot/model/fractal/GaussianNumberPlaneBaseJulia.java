package org.woehlke.computer.kurzweil.apps.mandelbrot.model.fractal;

import org.woehlke.computer.kurzweil.apps.mandelbrot.model.fractal.common.GaussianNumberPlaneBase;
import org.woehlke.computer.kurzweil.apps.mandelbrot.model.numbers.LatticePoint;

public interface GaussianNumberPlaneBaseJulia extends GaussianNumberPlaneBase {

    void computeTheSet(LatticePoint latticePoint);

}
