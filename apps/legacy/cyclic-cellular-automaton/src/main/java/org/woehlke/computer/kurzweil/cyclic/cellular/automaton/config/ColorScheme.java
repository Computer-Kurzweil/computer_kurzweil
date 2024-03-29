package org.woehlke.computer.kurzweil.cyclic.cellular.automaton.config;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * Cyclic Cellular Automaton.
 * <p>
 * (C) 2006 - 2022 Thomas Woehlke.
 * @see <a href="https://java.woehlke.org/cyclic-cellular-automaton">Maven Project Page</a>
 *
 * @author Thomas Woehlke
 */
public class ColorScheme {

    private Color[] stateColor;

    public ColorScheme() {
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
        stateColorList.add(new Color(54, 12, 88));
        stateColorList.add(new Color(154, 112, 38));
        stateColorList.add(new Color(234, 123, 254));
        stateColor = new Color[stateColorList.toArray().length];
        for (int i = 0; i < stateColorList.toArray().length; i++) {
            stateColor[i] = stateColorList.get(i);
        }
    }

    public int getMaxState() {
        return stateColor.length;
    }

    public Color getColorForState(int state) {
        return stateColor[state];
    }
}
