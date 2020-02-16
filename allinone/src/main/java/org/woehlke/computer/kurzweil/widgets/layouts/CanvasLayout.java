package org.woehlke.computer.kurzweil.widgets.layouts;

import javax.swing.*;
import java.awt.*;

public class CanvasLayout extends BoxLayoutVertical {

    /**
     * Creates a layout manager that will lay out components along the
     * given axis.
     *
     * @param target the container that needs to be laid out
     * @throws AWTError if the value of {@code axis} is invalid
     */
    public CanvasLayout(JComponent target) {
        super(target);
    }
}
