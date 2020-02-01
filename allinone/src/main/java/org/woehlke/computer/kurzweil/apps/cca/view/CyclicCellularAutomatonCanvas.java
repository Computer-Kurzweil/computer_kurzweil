package org.woehlke.computer.kurzweil.apps.cca.view;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.config.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.apps.cca.model.CyclicCellularAutomatonLattice;
import org.woehlke.computer.kurzweil.control.commons.AppGuiComponent;
import org.woehlke.computer.kurzweil.control.ctx.Stepper;
import org.woehlke.computer.kurzweil.control.signals.UserSignal;
import org.woehlke.computer.kurzweil.control.signals.UserSlot;

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
    Serializable, Stepper, AppGuiComponent {

    private static final long serialVersionUID = -3057254130516052936L;

    private ComputerKurzweilApplicationContext ctx;

    @Getter
    private CyclicCellularAutomatonLattice lattice;

    private boolean ready;

    public CyclicCellularAutomatonCanvas(ComputerKurzweilApplicationContext ctx) {
        this.ctx = ctx;
        this.setPreferredSize(this.ctx.getLatticeDimension());
        ready = false;
        this.lattice = new CyclicCellularAutomatonLattice(this.ctx);
    }

    public void paint(Graphics g) {
        if(ready && (lattice!=null)) {
            for (int y = 0; y < ctx.getLatticeDimensions().getY(); y++) {
                for (int x = 0; x < ctx.getLatticeDimensions().getX(); x++) {
                    int state = this.lattice.getCellStatusFor(x, y);
                    Color stateColor = this.ctx.getCtxCyclicCellularAutomaton().getColorScheme().getColorForState(state);
                    g.setColor(stateColor);
                    g.drawLine(x, y, x, y);
                }
            }
            super.paintComponent(g);
        }
    }

    public void update(Graphics g) {
        paint(g);
    }

    public void update(){
        repaint();;
    }

    public void start() {
        log.info("start");
        ready=true;
        this.lattice.start();
        this.setVisible(true);
        this.repaint();
        log.info("started");
    }

    public void stop() {
        log.info("stop");
        ready=false;
        this.setVisible(false);
        this.lattice.stop();
        this.lattice = null;
        log.info("stopped");
    }

    public void step() {
        log.info("step");
        if(this.lattice == null){
            log.info("step: start");
            start();
        } else {
            if(ready){
                this.lattice.step();
            }
        }
        log.info("stepped");
    }

    @Override
    public void handleUserSignal(UserSignal userSignal) {
        switch (userSignal){
            case START: start(); break;
            case STOP: stop(); break;
            case UPDATE: update(); break;
            case STEP: step(); break;
            default: break;
        }
    }
}
