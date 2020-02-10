package org.woehlke.computer.kurzweil.apps.dla;

import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.model.LatticePoint;

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
    private int age=1;

    private final ComputerKurzweilApplicationContext ctx;
    private final int worldX;
    private final int worldY;

    public DiffusionLimitedAggregationWorldLattice(ComputerKurzweilApplicationContext ctx) {
        this.ctx=ctx;
        this.worldX = this.ctx.getWorldDimensions().getX();
        this.worldY = this.ctx.getWorldDimensions().getY();
        this.worldMap = new int[this.worldX][this.worldY];
        int x = this.worldY / 2;
        int y = this.worldY / 2;
        worldMap[x][y]=age;
        age++;
    }

    public boolean hasDendriteNeighbour(LatticePoint pixel){
        if(worldMap[pixel.getX()][pixel.getY()]==0){
            LatticePoint[] neighbours = pixel.getNeighbourhood(this.ctx);
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
