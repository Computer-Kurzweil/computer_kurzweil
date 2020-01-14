package org.woehlke.simulation.allinone.view;

import javax.swing.*;
import javax.swing.border.CompoundBorder;

public class PanelBorder {

    private static int BORDER_PADDING = 5;

    public static CompoundBorder getBorder(String label){
        int top = BORDER_PADDING;
        int left = BORDER_PADDING;
        int bottom = BORDER_PADDING;
        int right = BORDER_PADDING;
        return BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(label),
            BorderFactory.createEmptyBorder(top,left,bottom,right)
        );
    }
}
