package org.woehlke.simulation.allinone.view.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.java.Log;

import java.awt.*;
import java.io.Serializable;


@Log
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class Bounds implements Serializable {

    @Getter int myheight;
    @Getter int mywidth;
    @Getter int mystartX;
    @Getter int mystartY;

    public Bounds(double height, double width, Dimension screenSize){
        double startX = (screenSize.getWidth() - width) / 2d;
        double startY = (screenSize.getHeight() - height) / 2d;
        myheight = Double.valueOf(height).intValue();
        mywidth = Double.valueOf(width).intValue();
        mystartX = Double.valueOf(startX).intValue();
        mystartY = Double.valueOf(startY).intValue();
    }
}
