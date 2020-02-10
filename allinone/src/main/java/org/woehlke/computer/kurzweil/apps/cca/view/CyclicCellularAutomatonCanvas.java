package org.woehlke.computer.kurzweil.apps.cca.view;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.apps.cca.ctx.CyclicCellularAutomatonContext;
import org.woehlke.computer.kurzweil.apps.cca.model.CyclicCellularAutomatonColorScheme;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.commons.AppGuiComponent;
import org.woehlke.computer.kurzweil.commons.Stepper;
import org.woehlke.computer.kurzweil.trashcan.signals.UserSignal;
import org.woehlke.computer.kurzweil.model.LatticeNeighbourhoodType;
import org.woehlke.computer.kurzweil.model.LatticePointNeighbourhoodPosition;
import org.woehlke.computer.kurzweil.view.widgets.PanelSubtitle;
import org.woehlke.computer.kurzweil.view.widgets.StartStopButtonsPanel;

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

    @Getter private volatile LatticeNeighbourhoodType neighbourhoodType;

    private volatile int[][][] lattice;
    private volatile int source;
    private volatile int target;

    private final int versions;
    private final int latticeX;
    private final int latticeY;

    @Getter private final ComputerKurzweilApplicationContext ctx;

    @Getter @Setter
    private CyclicCellularAutomatonContext appCtx;

    @Getter private final CyclicCellularAutomatonColorScheme colorScheme;

    @Getter private final CyclicCellularAutomatonButtonsPanel neighbourhoodButtonsPanel;

    @Getter private final StartStopButtonsPanel startStopButtonsPanel;

    @Getter private final PanelSubtitle panelSubtitle;

    private Boolean running;

    public CyclicCellularAutomatonCanvas(ComputerKurzweilApplicationContext ctx) {
        this.ctx = ctx;
        this.colorScheme = new CyclicCellularAutomatonColorScheme();
        this.panelSubtitle = new PanelSubtitle(ctx.getProperties().getCca().getView().getSubtitle());
        this.neighbourhoodButtonsPanel = new CyclicCellularAutomatonButtonsPanel(this);
        this.versions = 2;
        this.latticeX = this.ctx.getWorldDimensions().getX();
        this.latticeY = this.ctx.getWorldDimensions().getY();
        Dimension preferredSize = new Dimension( this.latticeX, this.latticeY);
        this.setPreferredSize(preferredSize);
        this.setVisible(true);
        this.startStopButtonsPanel = new StartStopButtonsPanel( this );
        startWithNeighbourhoodVonNeumann();
        this.resetLattice();
        running = Boolean.FALSE;
    }

    public void paint(Graphics g) {
        if (lattice != null) {
            for (int y = 0; y < ctx.getLatticeDimensions().getY(); y++) {
                for (int x = 0; x < ctx.getLatticeDimensions().getX(); x++) {
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
        this.setVisible(true);
    }

    @Override
    public void hideMe() {
        this.setVisible(false);
    }

    public void start() {
        log.info("start");
        this.startStopButtonsPanel.getStartButton().setEnabled(false);
        this.startStopButtonsPanel.getStopButton().setEnabled(true);
        //showMe();
        synchronized (running) {
            running = Boolean.TRUE;
        }
        log.info("started");
    }

    public void stop() {
        log.info("stop");
        this.startStopButtonsPanel.getStartButton().setEnabled(true);
        this.startStopButtonsPanel.getStopButton().setEnabled(false);
        //hideMe();
        synchronized (running) {
            running = Boolean.FALSE;
        }
        log.info("stopped");
    }

    public void update(){
        this.neighbourhoodButtonsPanel.update();
        this.panelSubtitle.update();
        this.startStopButtonsPanel.update();
        repaint();
    }

    public synchronized void step(){
        boolean doIt = false;
        synchronized (running) {
            doIt = running.booleanValue();
        }
        if(doIt){
            //log.info("step");
            int maxState = colorScheme.getMaxState();
            int xx;
            int yy;
            int nextState;
            for (int y = 0; y < latticeY; y++) {
                for (int x = 0; x < latticeX; x++) {
                    lattice[target][x][y] = lattice[source][x][y];
                    nextState = (lattice[source][x][y] + 1) % maxState;
                    LatticePointNeighbourhoodPosition[] pos = LatticePointNeighbourhoodPosition.getForfNeighbourhood(neighbourhoodType);
                    for (int i = 0; i < pos.length; i++) {
                        xx = ((x + pos[i].getX() + latticeX) % latticeX);
                        yy = ((y + pos[i].getY() + latticeY) % latticeY);
                        if (nextState == lattice[source][xx][yy]) {
                            lattice[target][x][y] = nextState;
                            i = pos.length;
                        }
                    }
                }
            }
            this.source = (this.source + 1) % 2;
            this.target = (this.target + 1) % 2;
            //log.info("stepped");
        }
    }

    /*
    @Override
    public void handleUserSignal(UserSignal userSignal) {
        switch (userSignal){
            case START: start(); break;
            case STOP: stop(); break;
            case UPDATE: update(); break;
            case STEP: step(); break;
            default: break;
        }
    }*/

    private void initCreateLattice(){
        log.info("initCreateLattice start: "+neighbourhoodType.name());
            lattice = new int[versions][latticeX][latticeY];
            source = 0;
            target = 1;
        log.info("initCreateLattice finished: "+neighbourhoodType.name());
    }

    private void initFillLattice(){
        log.info("initCreateLattice start: "+neighbourhoodType.name());
        Random random = this.ctx.getRandom();
        int maxState = this.colorScheme.getMaxState();
        for (int y = 0; y < latticeY; y++) {
            for (int x = 0; x < latticeX; x++) {
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
            this.getAppCtx().start();
        } else if (ae.getSource() == this.neighbourhoodButtonsPanel.getButtonMoore()) {
            this.startWithNeighbourhoodMoore();
            this.getAppCtx().start();
        } else if (ae.getSource() == this.neighbourhoodButtonsPanel.getButtonWoehlke()) {
            this.startWithNeighbourhoodWoehlke();
            this.getAppCtx().start();
        }
        if(ae.getSource() == this.startStopButtonsPanel.getStartButton()){
            this.getAppCtx().start();
        }
        if(ae.getSource() == this.startStopButtonsPanel.getStopButton()){
            this.getAppCtx().stop();
        }
    }

    @Deprecated
    @Override
    public void handleUserSignal(UserSignal userSignal) {
        log.info("handleUserSignal: "+userSignal.name());
    }
}
