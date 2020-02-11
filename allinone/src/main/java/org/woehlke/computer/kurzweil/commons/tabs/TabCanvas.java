package org.woehlke.computer.kurzweil.commons.tabs;

import org.woehlke.computer.kurzweil.commons.GuiComponentTab;
import org.woehlke.computer.kurzweil.commons.Updateable;

import java.awt.*;

public interface TabCanvas extends GuiComponentTab , Updateable {

    void paint(Graphics g);
    void update(Graphics g);

}
