package org.woehlke.simulation.mandelbrot.control.common;


import org.woehlke.simulation.mandelbrot.control.state.ApplicationState;

public interface ApplicationStateMachine {

    void start();

    ApplicationState getState();

    void click();

    void setModeSwitch();

    void setModeZoom();

}
