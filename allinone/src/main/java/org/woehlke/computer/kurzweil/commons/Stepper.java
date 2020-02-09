package org.woehlke.computer.kurzweil.commons;

public interface Stepper extends Startable {

    void step();
    void update();
}
