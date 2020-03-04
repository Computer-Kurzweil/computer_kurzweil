package org.woehlke.computer.kurzweil.tabs.todo.tetris.canvas;

import org.woehlke.computer.kurzweil.tabs.todo.tetris.Tetris;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TetrisColorScheme implements Tetris {

    private Color[] stateColor;

    public TetrisColorScheme(){
        List<Color> stateColorList = new ArrayList<>();
        stateColorList.add(Color.BLACK);
        stateColorList.add(Color.DARK_GRAY);
        stateColorList.add(Color.GRAY);
        stateColorList.add(Color.LIGHT_GRAY);
        stateColorList.add(Color.WHITE);
        stateColorList.add(Color.MAGENTA);
        stateColorList.add(Color.RED);
        stateColorList.add(Color.ORANGE);
        stateColorList.add(Color.YELLOW);
        stateColorList.add(Color.PINK);
        stateColorList.add(Color.BLUE);
        stateColorList.add(Color.CYAN);
        stateColorList.add(Color.GREEN);
        stateColorList.add(new Color(54,12,88));
        stateColorList.add(new Color(154,112,38));
        stateColorList.add(new Color(234,123,254));
        stateColor = new Color[stateColorList.toArray().length];
        for(int i=0; i < stateColorList.toArray().length; i++){
            stateColor[i] = (Color) stateColorList.get(i);
        }
    }

    public int getMaxState(){
        return stateColor.length;
    }

    public Color getColorForState(int state){
        return stateColor[state];
    }
}
