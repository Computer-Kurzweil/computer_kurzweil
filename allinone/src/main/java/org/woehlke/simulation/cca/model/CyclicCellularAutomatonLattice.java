package org.woehlke.simulation.cca.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.simulation.allinone.config.ComputerKurzweilApplicationContext;
import org.woehlke.simulation.allinone.model.lattice.LatticeNeighbourhoodType;

import java.awt.*;
import java.io.Serializable;

import static org.woehlke.simulation.allinone.model.lattice.LatticeNeighbourhoodType.*;

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

    private int[][][] lattice;
    private int source;
    private int target;
    private LatticeNeighbourhoodType neighbourhoodType;

    private final ComputerKurzweilApplicationContext ctx;

    public CyclicCellularAutomatonLattice(ComputerKurzweilApplicationContext ctx) {
        this.ctx = ctx;
        startWithNeighbourhoodVonNeumann();
    }

    private void initCreateLattice(){
        lattice = new int[2]
            [(int) this.ctx.getLatticeDimensions().getX()]
            [(int) this.ctx.getLatticeDimensions().getY()];
        source = 0;
        target = 1;
        initFillLatticeByRandom();
    }

    private void initFillLatticeByRandom(){
        for(int y = 0; y<this.ctx.getLatticeDimensions().getY(); y++){
            for(int x = 0; x<this.ctx.getLatticeDimensions().getX(); x++){
                lattice[source][x][y] = this.ctx.getRandom().nextInt(ctx.getColorScheme().getMaxState());
            }
        }
    }

    public void step(){
        //System.out.print(".");
        Point worldDimensions = this.ctx.getLatticeDimensions();
        for(int y = 0; y < worldDimensions.getY(); y++){
            for(int x = 0; x < worldDimensions.getX(); x++){
                lattice[target][x][y] = lattice[source][x][y];
                int nextState = (lattice[source][x][y] + 1) % ctx.getColorScheme().getMaxState();
                int west = (int) ((x-1+worldDimensions.getX())%worldDimensions.getX());
                int north = (int) ((y-1+worldDimensions.getY())%worldDimensions.getY());
                int east = (int) ((x+1+worldDimensions.getX())%worldDimensions.getX());
                int south = (int) ((y+1+worldDimensions.getY())%worldDimensions.getY());
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
}
