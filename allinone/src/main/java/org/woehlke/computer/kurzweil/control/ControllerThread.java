package org.woehlke.computer.kurzweil.control;

public interface ControllerThread extends Runnable {

    void start();
    void exit();
}
