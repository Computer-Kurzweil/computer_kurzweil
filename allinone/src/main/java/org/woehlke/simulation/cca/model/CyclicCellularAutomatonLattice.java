package org.woehlke.simulation.cca.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.allinone.model.LatticeNeighbourhoodType;

import java.awt.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Random;

import static org.woehlke.simulation.allinone.model.LatticeNeighbourhoodType.*;

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
public class CyclicCellularAutomatonLattice implements Serializable {

    private static final long serialVersionUID = -594681595882016258L;

    private Random random;

    private volatile int[][][] lattice;
    private volatile int source;
    private volatile int target;

    private final CyclicCellularAutomatonContext ctx;

    private volatile LatticeNeighbourhoodType neighbourhood;

    public CyclicCellularAutomatonLattice(CyclicCellularAutomatonContext ctx) {
        this.ctx = ctx;
        random = new Random(new Date().getTime());
        startVonNeumann();
    }

    private void initCreateLattice(){
        lattice = new int[2]
            [(int) this.ctx.getProperties().getLatticeDimensions().getX()]
            [(int) this.ctx.getProperties().getLatticeDimensions().getY()];
        source = 0;
        target = 1;
    }

    private void initFillLatticeByRandom(){
        for(int y = 0; y<this.ctx.getProperties().getLatticeDimensions().getY(); y++){
            for(int x = 0; x<this.ctx.getProperties().getLatticeDimensions().getX(); x++){
                lattice[source][x][y] = random.nextInt(ctx.getColorScheme().getMaxState());
            }
        }
    }

    public synchronized void step(){
        //System.out.print(".");
        Point worldDimensions = this.ctx.getProperties().getLatticeDimensions();
        for(int y = 0; y < worldDimensions.getY(); y++){
            for(int x = 0; x < worldDimensions.getX(); x++){
                lattice[target][x][y] = lattice[source][x][y];
                int nextState = (lattice[source][x][y] + 1) % ctx.getColorScheme().getMaxState();
                int west = (int) ((x-1+worldDimensions.getX())%worldDimensions.getX());
                int north = (int) ((y-1+worldDimensions.getY())%worldDimensions.getY());
                int east = (int) ((x+1+worldDimensions.getX())%worldDimensions.getX());
                int south = (int) ((y+1+worldDimensions.getY())%worldDimensions.getY());
                if(neighbourhood == MOORE_NEIGHBORHOOD || neighbourhood == WOEHLKE_NEIGHBORHOOD) {
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
                    if(neighbourhood == MOORE_NEIGHBORHOOD) {
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
                if(neighbourhood == MOORE_NEIGHBORHOOD || neighbourhood == VON_NEUMANN_NEIGHBORHOOD) {
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
    }

    public int getCellStatusFor(int x,int y){
        return this.lattice[source][x][y];
    }

    public void startVonNeumann() {
        initCreateLattice();
        initFillLatticeByRandom();
        this.neighbourhood=VON_NEUMANN_NEIGHBORHOOD;
    }

    public void startMoore() {
        initCreateLattice();
        initFillLatticeByRandom();
        this.neighbourhood=MOORE_NEIGHBORHOOD;
    }

    public void startWoehlke() {
        initCreateLattice();
        initFillLatticeByRandom();
        this.neighbourhood=WOEHLKE_NEIGHBORHOOD;
    }
}
