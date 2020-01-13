package org.woehlke.simulation.mandelbrot.control.common;

import org.woehlke.simulation.all.model.LatticePoint;
import org.woehlke.simulation.mandelbrot.config.MandelbrotProperties;
import org.woehlke.simulation.mandelbrot.model.MandelbrotTuringMachine;
import org.woehlke.simulation.mandelbrot.model.fractal.GaussianNumberPlaneBaseJulia;
import org.woehlke.simulation.mandelbrot.model.fractal.GaussianNumberPlaneMandelbrot;
import org.woehlke.simulation.mandelbrot.model.numbers.CellStatus;
import org.woehlke.simulation.mandelbrot.view.MandelbrotCanvas;
import org.woehlke.simulation.mandelbrot.view.MandelbrotFrame;
import org.woehlke.simulation.mandelbrot.view.MandelbrotPanelButtons;
import org.woehlke.simulation.mandelbrot.view.MandelbrotPanelSubtitle;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public interface MandelbrotApplicationContext extends MouseListener, ActionListener {

    void start();
    void showMe();
    void setModeSwitch();
    void setModeZoom();
    void zoomOut();

    String getZoomLevel();

    void setPanelButtons(MandelbrotPanelButtons panelButtons);

    CellStatus getCellStatusFor(int x, int y);

    MandelbrotProperties getProperties();
    MandelbrotFrame getFrame();
    void setFrame(MandelbrotFrame frame);
    MandelbrotCanvas getCanvas();
    MandelbrotPanelButtons getPanelButtons();
    GaussianNumberPlaneBaseJulia getGaussianNumberPlaneBaseJulia();
    GaussianNumberPlaneMandelbrot getGaussianNumberPlaneMandelbrot();
    MandelbrotTuringMachine getMandelbrotTuringMachine();
    ApplicationStateMachine getApplicationStateMachine();

    LatticePoint getWorldDimensions();
    MandelbrotPanelSubtitle getPanelSubtitle();
}
