package org.woehlke.computer.kurzweil.control.commons;

import org.woehlke.computer.kurzweil.control.signals.UserSlot;

public interface Startable extends UserSlot {

    void start();
    void stop();

}
