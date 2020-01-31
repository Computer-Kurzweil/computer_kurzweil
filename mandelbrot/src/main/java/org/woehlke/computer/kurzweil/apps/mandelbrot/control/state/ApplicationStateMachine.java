package org.woehlke.computer.kurzweil.apps.mandelbrot.control.state;


public interface ApplicationStateMachine {

    void start();

    ApplicationState getState();

    void click();

    void setModeSwitch();

    void setModeZoom();

}
