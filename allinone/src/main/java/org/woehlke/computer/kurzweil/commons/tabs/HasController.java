package org.woehlke.computer.kurzweil.commons.tabs;

public interface HasController {

    TabController getController();
    void startController();
    void stopController();
}
