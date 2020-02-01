package org.woehlke.computer.kurzweil.view.tabs.common;


import org.woehlke.computer.kurzweil.view.tabs.common.TabPanel;

import javax.swing.*;
import java.awt.*;

public class TabPanelLayout extends BoxLayout {

    /**
     * Creates a layout manager that will lay out components along the
     * given axis.
     *
     * @param target the container that needs to be laid out
     * @throws AWTError if the value of {@code axis} is invalid
     */
    public TabPanelLayout(TabPanel target) {
        super(target, BoxLayout.Y_AXIS);
    }
}
