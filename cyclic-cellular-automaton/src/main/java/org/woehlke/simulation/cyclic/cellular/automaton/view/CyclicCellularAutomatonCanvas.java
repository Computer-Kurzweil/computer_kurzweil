package org.woehlke.simulation.cyclic.cellular.automaton.view;

import org.woehlke.simulation.cyclic.cellular.automaton.config.ObjectRegistry;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * Cyclic Cellular Automaton.
 *
 * (C) 2006 - 2013 Thomas Woehlke.
 * http://thomas-woehlke.de/p/cyclic-cellular-automaton/
 * @author Thomas Woehlke
 *
 * Date: 05.02.2006
 * Time: 00:51:51
 */
public class CyclicCellularAutomatonCanvas extends JComponent implements Serializable {

    private static final long serialVersionUID = -3057254130516052936L;

    private ObjectRegistry ctx;

    public CyclicCellularAutomatonCanvas(ObjectRegistry ctx) {
        this.ctx = ctx;
        Dimension preferredSize = new Dimension(
            (int) ctx.getConfig().getLatticeDimensions().getX(),
            (int) ctx.getConfig().getLatticeDimensions().getY()
        );
        this.setPreferredSize(preferredSize);
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        for(int y = 0; y < ctx.getConfig().getLatticeDimensions().getY(); y++){
            for(int x = 0; x < ctx.getConfig().getLatticeDimensions().getX(); x++){
                int state = this.ctx.getLattice().getCellStatusFor(x,y);
                Color stateColor = this.ctx.getColorScheme().getColorForState(state);
                g.setColor(stateColor);
                g.drawLine(x,y,x,y);
            }
        }
    }

    public void update(Graphics g) {
        paint(g);
    }

}
