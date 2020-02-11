package org.woehlke.computer.kurzweil.tabs.mandelbrot.model.fractal;

import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.fractal.common.GaussianNumberPlaneBase;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.numbers.LatticePoint;

public interface GaussianNumberPlaneBaseJulia extends GaussianNumberPlaneBase {

    void computeTheSet(LatticePoint latticePoint);

}
