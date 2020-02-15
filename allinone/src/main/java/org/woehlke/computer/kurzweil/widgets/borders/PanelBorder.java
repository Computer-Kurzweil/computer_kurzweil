package org.woehlke.computer.kurzweil.widgets.borders;

import javax.swing.*;
import javax.swing.border.CompoundBorder;


public class PanelBorder {

    public static int BORDER_PADDING = 5;

    public static CompoundBorder getBorder(String label){
        int top = BORDER_PADDING;
        int left = BORDER_PADDING;
        int bottom = BORDER_PADDING;
        int right = BORDER_PADDING;
        return BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(top,left,bottom,right),
            BorderFactory.createTitledBorder(label)
        );
    }

    public static CompoundBorder getBorder(){
        int top = BORDER_PADDING;
        int left = BORDER_PADDING;
        int bottom = BORDER_PADDING;
        int right = BORDER_PADDING;
        return BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(top,left,bottom,right),
            BorderFactory.createEmptyBorder(top,left,bottom,right)
        );
    }
}
