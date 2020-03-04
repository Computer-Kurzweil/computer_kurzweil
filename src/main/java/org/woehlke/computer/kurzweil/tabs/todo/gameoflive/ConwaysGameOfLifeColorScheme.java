package org.woehlke.computer.kurzweil.tabs.todo.gameoflive;

import lombok.Getter;

import java.awt.*;

public class ConwaysGameOfLifeColorScheme {

    @Getter
    private final Color backgroundColor;

    @Getter
    private final Color foregroundColor;

    @Getter
    private final int backgroundColorState;

    @Getter
    private final int foregroundColorState;

    @Getter
    private final int maxState;

    public ConwaysGameOfLifeColorScheme(){
        backgroundColor = Color.BLACK;
        foregroundColor = Color.WHITE;
        backgroundColorState = 0;
        foregroundColorState = 1;
        maxState = 2;
    }

    public Color getColorForState(int state){
        switch (state){
            case 0: return backgroundColor;
            case 1: return foregroundColor;
            default: throw new IllegalArgumentException();
        }
    }
}
