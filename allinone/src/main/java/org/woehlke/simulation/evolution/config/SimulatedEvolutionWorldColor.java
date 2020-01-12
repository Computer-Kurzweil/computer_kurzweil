package org.woehlke.simulation.evolution.config;

import java.awt.*;

public enum SimulatedEvolutionWorldColor {

    COLOR_WATER(java.awt.Color.BLACK),
    COLOR_FOOD(java.awt.Color.GREEN);

    private Color color;
    public Color getColor() {
        return color;
    }

    SimulatedEvolutionWorldColor(java.awt.Color color){
        this.color=color;
    }
}
