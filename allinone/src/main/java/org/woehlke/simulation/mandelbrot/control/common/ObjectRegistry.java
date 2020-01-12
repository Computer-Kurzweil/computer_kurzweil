package org.woehlke.simulation.mandelbrot.control.common;

import org.woehlke.simulation.mandelbrot.config.MandelbrotProperties;
import org.woehlke.simulation.mandelbrot.model.MandelbrotTuringMachine;
import org.woehlke.simulation.mandelbrot.model.fractal.GaussianNumberPlaneBaseJulia;
import org.woehlke.simulation.mandelbrot.model.fractal.GaussianNumberPlaneMandelbrot;
import org.woehlke.simulation.mandelbrot.model.numbers.CellStatus;
import org.woehlke.simulation.all.model.LatticePointMandelbrot;
import org.woehlke.simulation.mandelbrot.view.ApplicationCanvas;
import org.woehlke.simulation.mandelbrot.view.MandelbrotApplicationFrame;
import org.woehlke.simulation.mandelbrot.view.PanelButtons;
import org.woehlke.simulation.mandelbrot.view.PanelSubtitle;

import java.awt.*;

public interface ObjectRegistry {

    void setPanelButtons(PanelButtons panelButtons);
    void setRectangleBounds(Rectangle rectangleBounds);
    void setDimensionSize(Dimension dimensionSize);

    CellStatus getCellStatusFor(int x, int y);

    MandelbrotProperties getProperties();
    MandelbrotApplicationFrame getFrame();
    void setFrame(MandelbrotApplicationFrame frame);
    ApplicationCanvas getCanvas();
    PanelButtons getPanelButtons();
    GaussianNumberPlaneBaseJulia getGaussianNumberPlaneBaseJulia();
    GaussianNumberPlaneMandelbrot getGaussianNumberPlaneMandelbrot();
    MandelbrotTuringMachine getMandelbrotTuringMachine();
    ApplicationStateMachine getApplicationStateMachine();

    LatticePointMandelbrot getWorldDimensions();
    Rectangle getRectangleBounds();
    Dimension getDimensionSize();
    PanelSubtitle getPanelSubtitle();
}
