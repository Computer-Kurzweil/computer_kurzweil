package org.woehlke.computer.kurzweil.commons.tabs;

import org.woehlke.computer.kurzweil.commons.Startable;

import java.awt.event.ActionListener;

public interface TabContext extends ActionListener,
    Startable,
    HasController,
    HasModel,
    HasCanvas,
    HasTab {

}
