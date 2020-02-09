package org.woehlke.computer.kurzweil.commons;

import org.woehlke.computer.kurzweil.trashcan.signals.UserSlot;

public interface AppGuiComponent extends UserSlot {

    void update();
    void showMe();
    void hideMe();
}
