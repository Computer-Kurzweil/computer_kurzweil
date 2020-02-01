package org.woehlke.computer.kurzweil.control.controller;

public interface ControllerThread extends Runnable {

    void start();
    void exit();
}
