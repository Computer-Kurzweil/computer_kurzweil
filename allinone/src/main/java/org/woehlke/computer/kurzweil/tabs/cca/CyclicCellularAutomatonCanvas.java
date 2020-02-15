package org.woehlke.computer.kurzweil.tabs.cca;


import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.tabs.TabCanvas;
import org.woehlke.computer.kurzweil.commons.tabs.TabModel;
import org.woehlke.computer.kurzweil.model.LatticeNeighbourhoodType;
import org.woehlke.computer.kurzweil.model.LatticePointNeighbourhoodPosition;
import org.woehlke.computer.kurzweil.widgets.layouts.CanvasLayout;
import org.woehlke.computer.kurzweil.widgets.PanelSubtitle;
import org.woehlke.computer.kurzweil.widgets.StartStopButtonsPanel;

import javax.swing.*;
import java.awt.*;
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
    Serializable, TabCanvas, TabModel {

    private static final long serialVersionUID = -3057254130516052936L;

    @Getter private volatile LatticeNeighbourhoodType neighbourhoodType;

    private volatile int[][][] lattice;
    private volatile int source;
    private volatile int target;

    private final int versions;
    private Boolean running;

    private final static int startX = 0;
    private final static int startY = 0;
    private final int worldX;
    private final int worldY;

    @Getter
    private CyclicCellularAutomatonContext tabCtx;

    @Getter private final CyclicCellularAutomatonColorScheme colorScheme;

    public CyclicCellularAutomatonCanvas(CyclicCellularAutomatonContext tabCtx) {
        this.tabCtx = tabCtx;
        this.versions = 2;
        this.worldX = this.tabCtx.getCtx().getWorldDimensions().getX();
        this.worldY = this.tabCtx.getCtx().getWorldDimensions().getY();
        this.setLayout(new CanvasLayout(this));
        this.colorScheme = new CyclicCellularAutomatonColorScheme();
        Dimension preferredSize = new Dimension( this.worldX, this.worldY);
        this.setPreferredSize(preferredSize);
        this.setVisible(true);
        startWithNeighbourhoodVonNeumann();
        this.resetLattice();
        running = Boolean.FALSE;
        showMe();
    }

    public void paint(Graphics g) {
        if (lattice != null) {
            for (int y = 0; y < worldY; y++) {
                for (int x = 0; x < worldX; x++) {
                    int state = this.lattice[source][x][y];
                    Color stateColor = this.colorScheme.getColorForState(state);
                    g.setColor(stateColor);
                    g.drawLine(x, y, x, y);
                }
            }
        }
        super.paintComponent(g);
    }

    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void showMe() {
        //this.setVisible(true);
        //repaint();
    }

    public void start() {
        log.info("start");
        showMe();
        synchronized (running) {
            running = Boolean.TRUE;
        }
        log.info("started");
    }

    public void stop() {
        log.info("stop");
        synchronized (running) {
            running = Boolean.FALSE;
        }
        log.info("stopped");
    }

    public void update(){
        showMe();
    }

    public void step(){
        boolean doIt = false;
        synchronized (running) {
            doIt = running.booleanValue();
        }
        if(doIt){
           // log.info("step");
            int maxState = colorScheme.getMaxState();
            int xx;
            int yy;
            int nextState;
            for (int y = 0; y < worldY; y++) {
                for (int x = 0; x < worldX; x++) {
                    lattice[target][x][y] = lattice[source][x][y];
                    nextState = (lattice[source][x][y] + 1) % maxState;
                    LatticePointNeighbourhoodPosition[] pos = LatticePointNeighbourhoodPosition.getForfNeighbourhood(neighbourhoodType);
                    for (int i = 0; i < pos.length; i++) {
                        xx = ((x + pos[i].getX() + worldX) % worldX);
                        yy = ((y + pos[i].getY() + worldY) % worldY);
                        if (nextState == lattice[source][xx][yy]) {
                            lattice[target][x][y] = nextState;
                            i = pos.length;
                        }
                    }
                }
            }
            this.source = (this.source + 1) % 2;
            this.target = (this.target + 1) % 2;
           // log.info("stepped");
        }
    }

    private void initCreateLattice(){
        log.info("initCreateLattice start: "+neighbourhoodType.name());
        lattice = new int[versions][worldX][worldY];
        source = 0;
        target = 1;
        log.info("initCreateLattice finished: "+neighbourhoodType.name());
    }

    private void initFillLattice(){
        log.info("initCreateLattice start: "+neighbourhoodType.name());
        Random random = this.tabCtx.getCtx().getRandom();
        int maxState = this.colorScheme.getMaxState();
        for (int y = 0; y < worldY; y++) {
            for (int x = 0; x < worldX; x++) {
                lattice[source][x][y] = random.nextInt(maxState);
            }
        }
        log.info("initCreateLattice finished: "+neighbourhoodType.name());
    }

    public void resetLattice(){
        initCreateLattice();
        initFillLattice();
    }

    public void startWithNeighbourhoodVonNeumann() {
        if( this.neighbourhoodType == null) {
            log.info("startWithNeighbourhoodVonNeumann");
        } else {
            log.info("startWithNeighbourhoodVonNeumann: " + neighbourhoodType.name());
        }
        this.neighbourhoodType=VON_NEUMANN_NEIGHBORHOOD;
        resetLattice();
        log.info("startWithNeighbourhoodVonNeumann started: "+neighbourhoodType.name());
    }

    public void startWithNeighbourhoodMoore() {
        log.info("startWithNeighbourhoodVonNeumann: "+neighbourhoodType.name());
        this.neighbourhoodType=MOORE_NEIGHBORHOOD;
        resetLattice();
        log.info("startWithNeighbourhoodVonNeumann started: "+neighbourhoodType.name());
    }

    public void startWithNeighbourhoodWoehlke() {
        log.info("startWithNeighbourhoodVonNeumann: "+neighbourhoodType.name());
        this.neighbourhoodType=WOEHLKE_NEIGHBORHOOD;
        resetLattice();
        log.info("startWithNeighbourhoodVonNeumann started: "+neighbourhoodType.name());
    }


}
