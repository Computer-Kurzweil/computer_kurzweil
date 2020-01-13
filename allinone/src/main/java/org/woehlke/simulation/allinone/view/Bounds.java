package org.woehlke.simulation.allinone.view;

import lombok.Getter;

import java.awt.*;

public class Bounds {

    @Getter
    int myheight;
    @Getter
    int mywidth;
    @Getter
    int mystartX;
    @Getter
    int mystartY;

    public Bounds(double height, double width, Dimension screenSize){
        double startX = (screenSize.getWidth() - width) / 2d;
        double startY = (screenSize.getHeight() - height) / 2d;
        myheight = Double.valueOf(height).intValue();
        mywidth = Double.valueOf(width).intValue();
        mystartX = Double.valueOf(startX).intValue();
        mystartY = Double.valueOf(startY).intValue();
    }
}
