package org.woehlke.simulation.cyclic.cellular.automaton.model;

import org.woehlke.simulation.cyclic.cellular.automaton.config.ObjectRegistry;

import java.awt.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Random;

import static org.woehlke.simulation.cyclic.cellular.automaton.model.LatticeNeighbourhood.*;

/**
 * Cyclic Cellular Automaton.
 *
 * Created with IntelliJ IDEA.
 * Date: 28.08.13
 * Time: 12:39
 */
public class CyclicCellularAutomatonLattice implements Serializable {

    private static final long serialVersionUID = -594681595882016258L;

    private Random random;

    private volatile int[][][] lattice;
    private volatile int source;
    private volatile int target;

    private final ObjectRegistry ctx;

    private volatile LatticeNeighbourhood neighbourhood;

    public CyclicCellularAutomatonLattice(ObjectRegistry ctx) {
        this.ctx = ctx;
        random = new Random(new Date().getTime());
        startVonNeumann();
    }

    private void initCreateLattice(){
        lattice = new int[2]
            [(int) this.ctx.getConfig().getLatticeDimensions().getX()]
            [(int) this.ctx.getConfig().getLatticeDimensions().getY()];
        source = 0;
        target = 1;
    }

    private void initFillLatticeByRandom(){
        for(int y = 0; y<this.ctx.getConfig().getLatticeDimensions().getY(); y++){
            for(int x = 0; x<this.ctx.getConfig().getLatticeDimensions().getX(); x++){
                lattice[source][x][y] = random.nextInt(ctx.getColorScheme().getMaxState());
            }
        }
    }

    public synchronized void step(){
        //System.out.print(".");
        Point worldDimensions = this.ctx.getConfig().getLatticeDimensions();
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

    public synchronized void startVonNeumann() {
        initCreateLattice();
        initFillLatticeByRandom();
        this.neighbourhood=VON_NEUMANN_NEIGHBORHOOD;
    }

    public synchronized void startMoore() {
        initCreateLattice();
        initFillLatticeByRandom();
        this.neighbourhood=MOORE_NEIGHBORHOOD;
    }

    public synchronized void startWoehlke() {
        initCreateLattice();
        initFillLatticeByRandom();
        this.neighbourhood=WOEHLKE_NEIGHBORHOOD;
    }
}
