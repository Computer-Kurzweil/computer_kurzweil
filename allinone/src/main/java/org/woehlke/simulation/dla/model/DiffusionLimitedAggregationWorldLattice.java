package org.woehlke.simulation.dla.model;

import lombok.extern.java.Log;
import org.woehlke.simulation.allinone.model.LatticePoint;

/**
 * Diffusion Limited Aggregation.
 *
 * (C) 2006 - 2013 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/diffusion-limited-aggregation.html
 * @author Thomas Woehlke
 *
 * Date: 27.08.13
 * Time: 16:56
 */
@Log
public class DiffusionLimitedAggregationWorldLattice {

    private int worldMap[][];
    private final DiffusionLimitedAggregatioContext ctx;
    private int age=1;

    public DiffusionLimitedAggregationWorldLattice(DiffusionLimitedAggregatioContext ctx) {
        this.ctx=ctx;
        worldMap = new int[this.ctx.getWorldDimensions().getX()][this.ctx.getWorldDimensions().getY()];
        int x = this.ctx.getWorldDimensions().getX() / 2;
        int y = this.ctx.getWorldDimensions().getY() / 2;
        worldMap[x][y]=age;
        age++;
    }

    public boolean hasDendriteNeighbour(LatticePoint pixel){
        if(worldMap[pixel.getX()][pixel.getY()]==0){
            LatticePoint[] neighbours=pixel.getNeighbourhood(this.ctx);
            for(LatticePoint neighbour:neighbours){
               if(worldMap[neighbour.getX()][neighbour.getY()]>0){
                   worldMap[pixel.getX()][pixel.getY()]=age;
                   age++;
                   return true;
               }
            }
            return false;
        } else {
           return true;
        }
    }

    public int getAgeForPixel(int x,int y){
        return worldMap[x][y];
    }
}
