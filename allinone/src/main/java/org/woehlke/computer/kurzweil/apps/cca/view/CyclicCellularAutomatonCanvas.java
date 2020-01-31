package org.woehlke.computer.kurzweil.apps.cca.view;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.config.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.apps.cca.model.CyclicCellularAutomatonLattice;
import org.woehlke.computer.kurzweil.control.AppCanvas;
import org.woehlke.computer.kurzweil.control.Stepper;
import org.woehlke.computer.kurzweil.model.Startable;

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
public class CyclicCellularAutomatonCanvas extends JComponent implements
    Serializable, Startable, AppCanvas, Stepper {

    private static final long serialVersionUID = -3057254130516052936L;

    private ComputerKurzweilApplicationContext ctx;

    @Getter
    private CyclicCellularAutomatonLattice lattice;

    public CyclicCellularAutomatonCanvas(ComputerKurzweilApplicationContext ctx) {
        this.ctx = ctx;
        this.setPreferredSize(this.ctx.getLatticeDimension());
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        for(int y = 0; y < ctx.getLatticeDimensions().getY(); y++){
            for(int x = 0; x < ctx.getLatticeDimensions().getX(); x++){
                if(lattice!=null) {
                    int state = this.lattice.getCellStatusFor(x, y);
                    Color stateColor = this.ctx.getColorScheme().getColorForState(state);
                    g.setColor(stateColor);
                    g.drawLine(x, y, x, y);
                }
            }
        }
    }

    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void start() {
        log.info("start");
        this.lattice = new CyclicCellularAutomatonLattice(this.ctx);
        this.lattice.start();
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stop");
        this.lattice.stop();
        this.lattice = null;
        log.info("stopped");
    }

    @Override
    public void step() {
        log.info("step");
        if(this.lattice == null){
            start();
        }
        this.lattice.step();
        this.repaint();
        log.info("stepped");
    }
}
