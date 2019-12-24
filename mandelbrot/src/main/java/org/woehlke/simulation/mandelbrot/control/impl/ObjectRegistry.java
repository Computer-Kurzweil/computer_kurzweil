package org.woehlke.simulation.mandelbrot.control.impl;

import org.woehlke.simulation.mandelbrot.config.Config;
import org.woehlke.simulation.mandelbrot.control.ApplicationStateMachine;
import org.woehlke.simulation.mandelbrot.model.MandelbrotTuringMachine;
import org.woehlke.simulation.mandelbrot.model.fractal.GaussianNumberPlaneBaseMandelbrot;
import org.woehlke.simulation.mandelbrot.model.fractal.impl.GaussianNumberPlaneBaseJuliaImpl;
import org.woehlke.simulation.mandelbrot.model.numbers.CellStatus;
import org.woehlke.simulation.mandelbrot.model.numbers.LatticePoint;
import org.woehlke.simulation.mandelbrot.view.ApplicationCanvas;
import org.woehlke.simulation.mandelbrot.view.ApplicationFrame;
import org.woehlke.simulation.mandelbrot.view.PanelButtons;
import org.woehlke.simulation.mandelbrot.view.impl.PanelSubtitle;

import java.awt.*;

public interface ObjectRegistry {

    CellStatus getCellStatusFor(int x, int y);

    LatticePoint getWorldDimensions();

    void setPanelButtons(PanelButtons panelButtons);

    GaussianNumberPlaneBaseJuliaImpl getGaussianNumberPlaneBaseJulia();

    GaussianNumberPlaneBaseMandelbrot getGaussianNumberPlaneBaseMandelbrot();

    ControllerThread getControllerThread();

    ApplicationCanvas getCanvas();

    PanelButtons getPanelButtons();

    Config getConfig();

    ApplicationFrame getFrame();

    Rectangle getRectangleBounds();

    void setRectangleBounds(Rectangle rectangleBounds);

    Dimension getDimensionSize();

    void setDimensionSize(Dimension dimensionSize);

    PanelSubtitle getPanelSubtitle();

    MandelbrotTuringMachine getMandelbrotTuringMachine();

    ApplicationStateMachine getApplicationStateMachine();
}
