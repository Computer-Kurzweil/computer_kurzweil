package org.woehlke.simulation.cca.view;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.allinone.model.ComputerKurzweilApplicationContext;
import org.woehlke.simulation.cca.model.CyclicCellularAutomatonLattice;

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
@Log
@ToString
@EqualsAndHashCode(callSuper=true)
@Component
public class CyclicCellularAutomatonCanvas extends JComponent implements Serializable {

    private static final long serialVersionUID = -3057254130516052936L;

    private ComputerKurzweilApplicationContext ctx;
    @Getter
    private final CyclicCellularAutomatonLattice lattice;

    @Autowired
    public CyclicCellularAutomatonCanvas(ComputerKurzweilApplicationContext ctx) {
        this.ctx = ctx;
        this.lattice = new CyclicCellularAutomatonLattice(   this.ctx);
        Dimension preferredSize = new Dimension(
            (int) ctx.getLatticeDimensions().getX(),
            (int) ctx.getLatticeDimensions().getY()
        );
        this.setPreferredSize(preferredSize);
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        for(int y = 0; y < ctx.getLatticeDimensions().getY(); y++){
            for(int x = 0; x < ctx.getLatticeDimensions().getX(); x++){
                int state =  this.lattice.getCellStatusFor(x,y);
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
