package org.woehlke.computer.kurzweil.commons;

import org.woehlke.computer.kurzweil.trashcan.signals.UserSlot;

public interface ControllerThread extends Runnable, UserSlot {

    void start();
    void exit();
}
