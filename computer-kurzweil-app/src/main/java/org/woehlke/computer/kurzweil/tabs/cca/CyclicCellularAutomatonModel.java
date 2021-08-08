package org.woehlke.computer.kurzweil.tabs.cca;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.model.LatticeNeighbourhoodType;
import org.woehlke.computer.kurzweil.commons.model.LatticePointNeighbourhoodPosition;
import org.woehlke.computer.kurzweil.commons.tabs.TabModel;
import org.woehlke.computer.kurzweil.tabs.cca.canvas.CyclicCellularAutomatonColorScheme;

import java.util.Random;
import java.util.concurrent.ForkJoinTask;

import static org.woehlke.computer.kurzweil.commons.model.LatticeNeighbourhoodType.*;

@Log4j2
@Getter
public class CyclicCellularAutomatonModel extends ForkJoinTask<Void> implements TabModel {

    private static final long serialVersionUID = 7526471155622776147L;

    private final CyclicCellularAutomatonContext tabCtx;

    private volatile int[][][] lattice;
    private volatile int source;
    private volatile int target;
    private volatile LatticeNeighbourhoodType neighbourhoodType;
    private Boolean running;

    private final CyclicCellularAutomatonColorScheme colorScheme;
    private final int versions;
    private final static int startX = 0;
    private final static int startY = 0;
    private final int worldX;
    private final int worldY;

    public CyclicCellularAutomatonModel(CyclicCellularAutomatonContext tabCtx) {
        this.tabCtx = tabCtx;
        this.worldX = this.tabCtx.getCtx().getWorldDimensions().getX();
        this.worldY = this.tabCtx.getCtx().getWorldDimensions().getY();
        this.colorScheme = new CyclicCellularAutomatonColorScheme();
        this.versions = 2;
        this.startWithNeighbourhoodVonNeumann();
        this.resetLattice();
        this.running = Boolean.FALSE;
    }

    @Override
    protected boolean exec() {
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
            int y;
            int x;
            int i;
            for (y = 0; y < worldY; y++) {
                for (x = 0; x < worldX; x++) {
                    lattice[target][x][y] = lattice[source][x][y];
                    nextState = (lattice[source][x][y] + 1) % maxState;
                    LatticePointNeighbourhoodPosition[] pos = LatticePointNeighbourhoodPosition.getNeighbourhoodFor(neighbourhoodType);
                    for (i = 0; i < pos.length; i++) {
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
            //log.info("stepped");
        }
        return true;
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
        int y;
        int x;
        for (y = 0; y < worldY; y++) {
            for (x = 0; x < worldX; x++) {
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
        //log.info("startWithNeighbourhoodVonNeumann: "+neighbourhoodType.name());
        this.neighbourhoodType=WOEHLKE_NEIGHBORHOOD;
        resetLattice();
        log.info("startWithNeighbourhoodVonNeumann started: "+neighbourhoodType.name());
    }


    @Override
    public Void getRawResult() {
        return null;
    }

    @Override
    protected void setRawResult(Void value) {}


    public void start() {
        log.info("start");
        synchronized (running) {
            running = Boolean.TRUE;
        }
        log.info("started "+this.toString());
    }

    public void stop() {
        log.info("stop");
        synchronized (running) {
            running = Boolean.FALSE;
        }
        log.info("stopped "+this.toString());
    }

    public int getState(int x, int y) {
        return lattice[source][x][y];
    }
}
