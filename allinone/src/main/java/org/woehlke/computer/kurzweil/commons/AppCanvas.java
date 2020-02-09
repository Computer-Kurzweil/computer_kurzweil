package org.woehlke.computer.kurzweil.commons;

import java.awt.*;

public interface AppCanvas extends AppGuiComponent {

    void paint(Graphics g);
    void update(Graphics g);

}
