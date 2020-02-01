package org.woehlke.computer.kurzweil.control.ctx;

import org.woehlke.computer.kurzweil.control.signals.UserSlot;

public interface ControllerThread extends Runnable, UserSlot {

    void start();
    void exit();
}
