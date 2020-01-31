package org.woehlke.simulation.allinone.view.apps.parts;

import javax.swing.*;
import java.awt.*;

public class AppTabLayout extends BoxLayout {

    /**
     * Creates a layout manager that will lay out components along the
     * given axis.
     *
     * @param target the container that needs to be laid out
     * @throws AWTError if the value of {@code axis} is invalid
     */
    public AppTabLayout(Container target) {
        super(target, BoxLayout.Y_AXIS);
    }
}
