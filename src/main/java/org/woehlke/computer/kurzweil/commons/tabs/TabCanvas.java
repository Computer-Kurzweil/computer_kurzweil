package org.woehlke.computer.kurzweil.commons.tabs;

import org.woehlke.computer.kurzweil.commons.application.Updateable;
import org.woehlke.computer.kurzweil.commons.gui.GuiComponent;

import java.awt.*;

public interface TabCanvas extends GuiComponent, Updateable {

    void paint(Graphics g);
    void update(Graphics g);

}
