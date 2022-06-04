package org.woehlke.computer.kurzweil.tabs.simulatedevolution.canvas;

import java.awt.*;

public enum WorldColor {

    COLOR_WATER(java.awt.Color.BLACK),
    COLOR_FOOD(java.awt.Color.GREEN);

    private Color color;
    public Color getColor() {
        return color;
    }

    WorldColor(java.awt.Color color){
        this.color=color;
    }
}
