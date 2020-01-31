package org.woehlke.computer.kurzweil.apps.dla.model;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.computer.kurzweil.config.ComputerKurzweilApplicationContext;
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
@Component
public class DiffusionLimitedAggregationWorld {

    private LatticePoint worldDimensions;

    private List<LatticePoint> particles = new ArrayList<>();

    private DiffusionLimitedAggregationWorldLattice dendrite;

    private final ComputerKurzweilApplicationContext ctx;

    @Autowired
    public DiffusionLimitedAggregationWorld(ComputerKurzweilApplicationContext ctx) {
        this.ctx=ctx;
        this.worldDimensions=ctx.getWorldDimensions();
        for(int i=0; i<ctx.getProperties().getDla().getControl().getNumberOfParticles();i++){
            int x = ctx.getRandom().nextInt(worldDimensions.getX());
            int y = ctx.getRandom().nextInt(worldDimensions.getY());
            particles.add(new LatticePoint(x>=0?x:-x,y>=0?y:-y));
        }
        this.dendrite = new DiffusionLimitedAggregationWorldLattice(this.ctx);
    }

    public List<LatticePoint> getParticles() {
        return particles;
    }

    public void move() {
        List<LatticePoint> newParticles = new ArrayList<LatticePoint>();
        for(LatticePoint particle:particles){
            int x = particle.getX()+worldDimensions.getX();
            int y = particle.getY()+worldDimensions.getY();
            //Todo: make Enum
            int direction = ctx.getRandom().nextInt(4);
            switch (direction>=0?direction:-direction){
                case 0: y--; break;
                case 1: x++; break;
                case 2: y++; break;
                case 3: x--; break;
            }
            x %= worldDimensions.getX();
            y %= worldDimensions.getY();
            particle.setX(x);
            particle.setY(y);
            if(!dendrite.hasDendriteNeighbour(particle)){
                newParticles.add(particle);
            }
        }
        particles=newParticles;
    }

    public int getDendriteColor(int x, int y) {
        return dendrite.getAgeForPixel(x,y);
    }
}
