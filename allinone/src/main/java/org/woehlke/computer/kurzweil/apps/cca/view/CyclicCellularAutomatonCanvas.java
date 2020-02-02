package org.woehlke.computer.kurzweil.apps.cca.view;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.apps.cca.model.CyclicCellularAutomatonColorScheme;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.control.commons.AppGuiComponent;
import org.woehlke.computer.kurzweil.control.ctx.Stepper;
import org.woehlke.computer.kurzweil.control.signals.UserSignal;
import org.woehlke.computer.kurzweil.model.LatticeNeighbourhoodType;
import org.woehlke.computer.kurzweil.model.LatticePointNeighbourhoodPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Random;

import static org.woehlke.computer.kurzweil.model.LatticeNeighbourhoodType.*;
import static org.woehlke.computer.kurzweil.model.LatticeNeighbourhoodType.WOEHLKE_NEIGHBORHOOD;

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
public class CyclicCellularAutomatonCanvas extends JComponent implements
    Serializable, Stepper, AppGuiComponent, ActionListener {

    private static final long serialVersionUID = -3057254130516052936L;

    private int[][][] lattice;
    private int source;
    private int target;

    private final int versions;
    private final int latticeX;
    private final int latticeY;

    @Getter private LatticeNeighbourhoodType neighbourhoodType;

    @Getter private final ComputerKurzweilApplicationContext ctx;

    @Getter private final CyclicCellularAutomatonColorScheme colorScheme;

    @Getter private final CyclicCellularAutomatonButtonsPanel neighbourhoodButtonsPanel;

    private boolean ready;

    public CyclicCellularAutomatonCanvas(ComputerKurzweilApplicationContext ctx) {
        this.ctx = ctx;
        this.colorScheme = new CyclicCellularAutomatonColorScheme();
        this.neighbourhoodButtonsPanel = new CyclicCellularAutomatonButtonsPanel(this);
        this.setPreferredSize(this.ctx.getLatticeDimension());
        this.ready = false;
        this.versions = 2;
        this.latticeX = this.ctx.getWorldDimensions().getX();
        this.latticeY = this.ctx.getWorldDimensions().getY();
        this.setVisible(true);
        startWithNeighbourhoodVonNeumann();
    }

    public void paint(Graphics g) {
        if(ready && (lattice!=null)) {
            for (int y = 0; y < ctx.getLatticeDimensions().getY(); y++) {
                for (int x = 0; x < ctx.getLatticeDimensions().getX(); x++) {
                    int state =this.lattice[source][x][y];
                    Color stateColor = this.colorScheme.getColorForState(state);
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
        repaint();
    }

    public void start() {
        log.info("start");
        ready=true;
        this.setVisible(true);
        log.info("started");
    }

    public void stop() {
        log.info("stop");
        ready=false;
        this.setVisible(false);
        log.info("stopped");
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


    private void initCreateLattice(){
        log.info("initCreateLattice: "+neighbourhoodType.name());
        lattice = new int[versions][latticeX][latticeY];
        source = 0;
        target = 1;
        log.info("initCreateLattice finished: "+neighbourhoodType.name());
    }

    private void initFillLattice(){
        Random random = this.ctx.getRandom();
        int maxState = this.colorScheme.getMaxState();
        for(int y = 0; y<latticeY; y++){
            for(int x = 0; x<latticeX; x++){
                lattice[source][x][y] = random.nextInt(maxState);
            }
        }
        log.info("initCreateLattice finished: "+neighbourhoodType.name());
    }

    private void resetLattice(){
        initCreateLattice();
        initFillLattice();
        this.repaint();
    }

    public void step(){
        //log.info("step");
        int maxState = colorScheme.getMaxState();
        int xx;
        int yy;
        int nextState;
        for(int y = 0; y < latticeY; y++){
            for(int x = 0; x < latticeX; x++){
                lattice[target][x][y] = lattice[source][x][y];
                nextState = (lattice[source][x][y] + 1) % maxState;
                LatticePointNeighbourhoodPosition[] pos = LatticePointNeighbourhoodPosition.getForfNeighbourhood(neighbourhoodType);
                for(int i=0; i<pos.length; i++){
                    xx = ((x+pos[i].getX()+latticeX)%latticeX);
                    yy = ((y+pos[i].getY()+latticeY)%latticeY);
                    if (nextState == lattice[source][xx][yy]) {
                        lattice[target][x][y] = nextState;
                        i=pos.length;
                    }
                }
            }
        }
        this.source = (this.source + 1 ) % 2;
        this.target =  (this.target + 1 ) % 2;
        //log.info("stepped");
    }

    public void startWithNeighbourhoodVonNeumann() {
        this.neighbourhoodType=VON_NEUMANN_NEIGHBORHOOD;
        resetLattice();
    }

    public void startWithNeighbourhoodMoore() {
        this.neighbourhoodType=MOORE_NEIGHBORHOOD;
        resetLattice();
    }

    public void startWithNeighbourhoodWoehlke() {
        this.neighbourhoodType=WOEHLKE_NEIGHBORHOOD;
        resetLattice();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.neighbourhoodButtonsPanel.getButtonVonNeumann()) {
            this.startWithNeighbourhoodVonNeumann();
        } else if (ae.getSource() == this.neighbourhoodButtonsPanel.getButtonMoore()) {
            this.startWithNeighbourhoodMoore();
        } else if (ae.getSource() == this.neighbourhoodButtonsPanel.getButtonWoehlke()) {
            this.startWithNeighbourhoodWoehlke();
        }
    }
}
