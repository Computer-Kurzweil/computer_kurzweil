package org.woehlke.computer.kurzweil.control.commons;

import org.woehlke.computer.kurzweil.control.commons.AppGuiComponent;

import java.awt.*;

public interface AppCanvas extends AppGuiComponent {

    void paint(Graphics g);
    void update(Graphics g);

}
