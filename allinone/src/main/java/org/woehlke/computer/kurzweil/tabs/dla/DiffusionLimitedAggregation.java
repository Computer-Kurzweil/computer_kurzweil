package org.woehlke.computer.kurzweil.tabs.dla;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.commons.tabs.TabModel;
import org.woehlke.computer.kurzweil.model.LatticePoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Diffusion Limited Aggregation.
 *
 * (C) 2006 - 2013 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/diffusion-limited-aggregation.html
 * @author Thomas Woehlke
 *
 * Date: 27.08.13
 * Time: 14:57
 */
@Log
@Getter
public class DiffusionLimitedAggregation implements TabModel {

    private List<LatticePoint> particles = new ArrayList<>();
    private int worldMap[][];
    private int age=1;

    private final int worldX;
    private final int worldY;
    private final int initialNumberOfParticles;
    private final ComputerKurzweilApplicationContext ctx;

    public DiffusionLimitedAggregation(ComputerKurzweilApplicationContext ctx) {
        this.ctx=ctx;
        this.worldX = this.ctx.getWorldDimensions().getX();
        this.worldY = this.ctx.getWorldDimensions().getY();
        this.initialNumberOfParticles = ctx.getProperties().getDla().getControl().getNumberOfParticles();
        for(int i=0; i< this.initialNumberOfParticles; i++){
            int x = ctx.getRandom().nextInt(this.worldX);
            int y = ctx.getRandom().nextInt(this.worldY);
            particles.add(new LatticePoint(x>=0?x:-x,y>=0?y:-y));
        }
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

    public void step() {
        log.info("stop");
        List<LatticePoint> newParticles = new ArrayList<LatticePoint>();
        for(LatticePoint particle:particles){
            int x = particle.getX()+this.worldX;
            int y = particle.getY()+this.worldY;
            //Todo: make Enum
            int direction = ctx.getRandom().nextInt(4);
            switch (direction>=0?direction:-direction){
                case 0: y--; break;
                case 1: x++; break;
                case 2: y++; break;
                case 3: x--; break;
            }
            x %= this.worldX;
            y %= this.worldY;
            particle.setX(x);
            particle.setY(y);
            if(!this.hasDendriteNeighbour(particle)){
                newParticles.add(particle);
            }
        }
        particles=newParticles;
    }

    public int getDendriteColor(int x, int y) {
        return worldMap[x][y];
    }

}
