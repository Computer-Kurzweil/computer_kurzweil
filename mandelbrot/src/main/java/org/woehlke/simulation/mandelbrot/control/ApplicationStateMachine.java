package org.woehlke.simulation.mandelbrot.control;

import org.woehlke.simulation.mandelbrot.control.state.ApplicationState;

public interface ApplicationStateMachine {

    ApplicationState getState();

    void click();

    void setModeSwitch();

    void setModeZoom();

}
