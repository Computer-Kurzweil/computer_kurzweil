package org.woehlke.computer.kurzweil.apps.cca.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.config.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.control.Stepper;
import org.woehlke.computer.kurzweil.model.LatticeNeighbourhoodType;
import org.woehlke.computer.kurzweil.model.LatticePoint;
import org.woehlke.computer.kurzweil.model.Startable;

import java.awt.*;
import java.io.Serializable;
import java.util.Random;

import static org.woehlke.computer.kurzweil.model.LatticeNeighbourhoodType.*;

/**
 * Cyclic Cellular Automaton.
 *
 * Created with IntelliJ IDEA.
 * Date: 28.08.13
 * Time: 12:39
 */
@Log
@ToString
@EqualsAndHashCode
public class CyclicCellularAutomatonLattice implements Serializable, Startable, Stepper {

    private static final long serialVersionUID = -594681595882016258L;

    private int[][][] lattice;
    private int source;
    private int target;
    private LatticeNeighbourhoodType neighbourhoodType;

    private final ComputerKurzweilApplicationContext ctx;

    @Getter
    private final CyclicCellularAutomatonColorScheme colorScheme;

    public CyclicCellularAutomatonLattice(ComputerKurzweilApplicationContext ctx) {
        this.ctx = ctx;
        this.colorScheme = new CyclicCellularAutomatonColorScheme();
    }

    private void initCreateLattice(){
        log.info("initCreateLattice: "+neighbourhoodType.name());
        int versions = 2;
        int latticeX = this.ctx.getWorldDimensions().getX();
        int latticeY = this.ctx.getWorldDimensions().getY();
        lattice = new int[versions][latticeX][latticeY];
        source = 0;
        target = 1;
        Random random = this.ctx.getRandom();
        int maxState = this.colorScheme.getMaxState();
        for(int y = 0; y<latticeY; y++){
            for(int x = 0; x<latticeX; x++){
                lattice[source][x][y] = random.nextInt(maxState);
            }
        }
        log.info("initCreateLattice finished: "+neighbourhoodType.name());
    }

    public synchronized void step(){
        //log.info("step");
        int maxState = ctx.getColorScheme().getMaxState();
        int dimY = this.ctx.getWorldDimensions().getY();
        int dimX = this.ctx.getWorldDimensions().getX();
        for(int y = 0; y < dimY; y++){
            for(int x = 0; x < dimX; x++){
                lattice[target][x][y] = lattice[source][x][y];
                int nextState = (lattice[source][x][y] + 1) % maxState;
                int west = ((x-1+dimX)%dimX);
                int north = ((y-1+dimY)%dimY);
                int east =  ((x+1+dimX)%dimX);
                int south = ((y+1+dimY)%dimY);
                if(neighbourhoodType == MOORE_NEIGHBORHOOD || neighbourhoodType == WOEHLKE_NEIGHBORHOOD) {
                    //North-West
                    if (nextState == lattice[source][west][north]) {
                        lattice[target][x][y] = nextState;
                        continue;
                    }
                    //North-East
                    if (nextState == lattice[source][east][north]) {
                        lattice[target][x][y] = nextState;
                        continue;
                    }
                    if(neighbourhoodType == MOORE_NEIGHBORHOOD) {
                        //South-East
                        if (nextState == lattice[source][east][south]) {
                            lattice[target][x][y] = nextState;
                            continue;
                        }
                    }
                    //SouthWest
                    if (nextState == lattice[source][west][south]) {
                        lattice[target][x][y] = nextState;
                        continue;
                    }
                }
                //North
                if (nextState == lattice[source][x][north]
                ) {
                    lattice[target][x][y] = nextState;
                    continue;
                }
                //East
                if(nextState == lattice[source][east][y]){
                    lattice[target][x][y] = nextState;
                    continue;
                }
                if(neighbourhoodType == MOORE_NEIGHBORHOOD || neighbourhoodType == VON_NEUMANN_NEIGHBORHOOD) {
                    //South
                    if (nextState == lattice[source][x][south]) {
                        lattice[target][x][y] = nextState;
                        continue;
                    }
                }
                //West
                if(nextState == lattice[source][west][y]){
                    lattice[target][x][y] = nextState;
                }
            }
        }
        this.source = (this.source + 1 ) % 2;
        this.target =  (this.target + 1 ) % 2;
        //log.info("stepped");
    }

    public int getCellStatusFor(int x,int y){
        return this.lattice[source][x][y];
    }

    public void startWithNeighbourhoodVonNeumann() {
        this.neighbourhoodType=VON_NEUMANN_NEIGHBORHOOD;
        initCreateLattice();
    }

    public void startWithNeighbourhoodMoore() {
        this.neighbourhoodType=MOORE_NEIGHBORHOOD;
        initCreateLattice();
    }

    public void startWithNeighbourhoodWoehlke() {
        this.neighbourhoodType=WOEHLKE_NEIGHBORHOOD;
        initCreateLattice();
    }

    @Override
    public void start() {
        log.info("start");
        startWithNeighbourhoodVonNeumann();
    }

    @Override
    public void stop() {
        log.info("stop");
    }
}
