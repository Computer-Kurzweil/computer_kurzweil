package org.woehlke.computer.kurzweil.control.ctx;

import org.woehlke.computer.kurzweil.control.commons.Startable;

public interface Stepper extends Startable {

    void step();
    void update();
}
