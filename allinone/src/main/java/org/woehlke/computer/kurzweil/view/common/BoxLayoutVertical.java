package org.woehlke.computer.kurzweil.view.common;

import javax.swing.*;
import java.awt.*;

public class BoxLayoutVertical extends BoxLayout {

    /**
     * Creates a layout manager that will lay out components along the
     * given axis.
     *
     * @param target the container that needs to be laid out
     * @param axis   the axis to lay out components along. Can be one of:
     *               {@code BoxLayout.X_AXIS, BoxLayout.Y_AXIS,
     *               BoxLayout.LINE_AXIS} or {@code BoxLayout.PAGE_AXIS}
     * @throws AWTError if the value of {@code axis} is invalid
     */
    public BoxLayoutVertical(Container target) {
        super(target, BoxLayout.PAGE_AXIS);
    }
}
