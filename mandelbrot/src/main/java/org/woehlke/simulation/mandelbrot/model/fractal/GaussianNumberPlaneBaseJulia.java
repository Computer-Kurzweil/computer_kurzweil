package org.woehlke.simulation.mandelbrot.model.fractal;

import org.woehlke.simulation.mandelbrot.model.fractal.common.GaussianNumberPlaneBase;
import org.woehlke.simulation.mandelbrot.model.numbers.LatticePoint;

public interface GaussianNumberPlaneBaseJulia extends GaussianNumberPlaneBase {



    void computeTheSet(LatticePoint latticePoint);
    void computeTheZoomedSet(LatticePoint latticePoint);

    boolean isInZooomed(LatticePoint turingPosition);

}
