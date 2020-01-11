package org.woehlke.simulation.evolution.view;

import java.awt.*;

public enum GuiConfigColors {

    COLOR_WATER(java.awt.Color.BLACK),
    COLOR_FOOD(java.awt.Color.GREEN),
    COLOR_YOUNG(java.awt.Color.BLUE),
    COLOR_YOUNG_FOREGROUND(java.awt.Color.WHITE),
    COLOR_YOUNG_AND_FAT(java.awt.Color.YELLOW),
    COLOR_FULL_AGE(java.awt.Color.RED),
    COLOR_HUNGRY(java.awt.Color.LIGHT_GRAY),
    COLOR_OLD(java.awt.Color.DARK_GRAY),
    COLOR_OLD_FOREGROUND(java.awt.Color.WHITE),
    COLOR_DEAD(java.awt.Color.BLACK);

    private java.awt.Color color;

    GuiConfigColors(java.awt.Color color){
        this.color=color;
    }

    public Color getColor() {
        return color;
    }
}
