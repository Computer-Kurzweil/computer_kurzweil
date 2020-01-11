package org.woehlke.simulation.evolution.model.world;

import java.awt.*;

public enum SimulatedEvolutionWorldColor {

    COLOR_WATER(java.awt.Color.BLACK),
    COLOR_FOOD(java.awt.Color.GREEN),
    COLOR_YOUNG_FOREGROUND(java.awt.Color.WHITE),
    COLOR_OLD_FOREGROUND(java.awt.Color.WHITE);

    private Color color;
    public Color getColor() {
        return color;
    }

    SimulatedEvolutionWorldColor(java.awt.Color color){
        this.color=color;
    }
}
