package org.woehlke.simulation.mandelbrot.control.common;

import org.woehlke.simulation.mandelbrot.config.Config;
import org.woehlke.simulation.mandelbrot.control.state.ApplicationStateMachine;
import org.woehlke.simulation.mandelbrot.model.MandelbrotTuringMachine;
import org.woehlke.simulation.mandelbrot.model.fractal.GaussianNumberPlaneBaseJulia;
import org.woehlke.simulation.mandelbrot.model.fractal.GaussianNumberPlaneBaseMandelbrot;
import org.woehlke.simulation.mandelbrot.model.numbers.CellStatus;
import org.woehlke.simulation.mandelbrot.model.numbers.LatticePoint;
import org.woehlke.simulation.mandelbrot.view.ApplicationCanvas;
import org.woehlke.simulation.mandelbrot.view.ApplicationFrame;
import org.woehlke.simulation.mandelbrot.view.PanelButtons;
import org.woehlke.simulation.mandelbrot.view.PanelSubtitle;

import java.awt.*;

public interface ObjectRegistry {

    void setPanelButtons(PanelButtons panelButtons);
    void setRectangleBounds(Rectangle rectangleBounds);
    void setDimensionSize(Dimension dimensionSize);

    CellStatus getCellStatusFor(int x, int y);

    Config getConfig();
    ApplicationFrame getFrame();
    ApplicationCanvas getCanvas();
    PanelButtons getPanelButtons();
    GaussianNumberPlaneBaseJulia getGaussianNumberPlaneBaseJulia();
    GaussianNumberPlaneBaseMandelbrot getGaussianNumberPlaneBaseMandelbrot();
    MandelbrotTuringMachine getMandelbrotTuringMachine();
    ApplicationStateMachine getApplicationStateMachine();

    LatticePoint getWorldDimensions();
    Rectangle getRectangleBounds();
    Dimension getDimensionSize();
    PanelSubtitle getPanelSubtitle();
}
