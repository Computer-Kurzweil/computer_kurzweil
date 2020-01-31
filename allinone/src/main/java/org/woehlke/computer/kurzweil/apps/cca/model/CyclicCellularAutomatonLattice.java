package org.woehlke.computer.kurzweil.apps.cca.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.config.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.control.Stepper;
import org.woehlke.computer.kurzweil.model.LatticeNeighbourhoodType;
import org.woehlke.computer.kurzweil.model.LatticePoint;
import org.woehlke.computer.kurzweil.model.Startable;

import java.awt.*;
import java.io.Serializable;

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

    public CyclicCellularAutomatonLattice(ComputerKurzweilApplicationContext ctx) {
        this.ctx = ctx;
    }

    private void initCreateLattice(){
        log.info("initCreateLattice: "+neighbourhoodType.name());
        lattice = new int[2]
            [this.ctx.getWorldDimensions().getX()]
            [this.ctx.getWorldDimensions().getY()];
        source = 0;
        target = 1;
        for(int y = 0; y<this.ctx.getWorldDimensions().getY(); y++){
            for(int x = 0; x<this.ctx.getWorldDimensions().getX(); x++){
                lattice[source][x][y] = this.ctx.getRandom().nextInt(
                    ctx.getColorScheme().getMaxState()
                );
            }
        }
    }

    public void step(){
        log.info("step");
        LatticePoint dim = this.ctx.getWorldDimensions();
        for(int y = 0; y < dim.getY(); y++){
            for(int x = 0; x < dim.getX(); x++){
                lattice[target][x][y] = lattice[source][x][y];
                int nextState = (lattice[source][x][y] + 1) % ctx.getColorScheme().getMaxState();
                int west = ((x-1+dim.getX())%dim.getX());
                int north = ((y-1+dim.getY())%dim.getY());
                int east =  ((x+1+dim.getX())%dim.getX());
                int south = ((y+1+dim.getY())%dim.getY());
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
        log.info("stepped");
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
