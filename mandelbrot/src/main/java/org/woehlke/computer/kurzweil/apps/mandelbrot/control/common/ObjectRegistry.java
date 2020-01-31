package org.woehlke.computer.kurzweil.apps.mandelbrot.control.common;

import org.woehlke.computer.kurzweil.apps.mandelbrot.config.Config;
import org.woehlke.computer.kurzweil.apps.mandelbrot.control.state.ApplicationStateMachine;
import org.woehlke.computer.kurzweil.apps.mandelbrot.model.MandelbrotTuringMachine;
import org.woehlke.computer.kurzweil.apps.mandelbrot.model.fractal.GaussianNumberPlaneBaseJulia;
import org.woehlke.computer.kurzweil.apps.mandelbrot.model.fractal.GaussianNumberPlaneMandelbrot;
import org.woehlke.computer.kurzweil.apps.mandelbrot.model.numbers.CellStatus;
import org.woehlke.computer.kurzweil.apps.mandelbrot.model.numbers.LatticePoint;
import org.woehlke.computer.kurzweil.apps.mandelbrot.view.ApplicationCanvas;
import org.woehlke.computer.kurzweil.apps.mandelbrot.view.ApplicationFrame;
import org.woehlke.computer.kurzweil.apps.mandelbrot.view.PanelButtons;
import org.woehlke.computer.kurzweil.apps.mandelbrot.view.PanelSubtitle;

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
    GaussianNumberPlaneMandelbrot getGaussianNumberPlaneMandelbrot();
    MandelbrotTuringMachine getMandelbrotTuringMachine();
    ApplicationStateMachine getApplicationStateMachine();

    LatticePoint getWorldDimensions();
    Rectangle getRectangleBounds();
    Dimension getDimensionSize();
    PanelSubtitle getPanelSubtitle();
}
