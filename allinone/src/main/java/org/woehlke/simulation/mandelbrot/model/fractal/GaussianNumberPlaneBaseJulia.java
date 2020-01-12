package org.woehlke.simulation.mandelbrot.model.fractal;

import org.woehlke.simulation.mandelbrot.model.fractal.common.GaussianNumberPlaneBase;
import org.woehlke.simulation.all.model.LatticePointMandelbrot;

public interface GaussianNumberPlaneBaseJulia extends GaussianNumberPlaneBase {

    void computeTheSet(LatticePointMandelbrot latticePoint);

}
