package org.woehlke.simulation.dla.model;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.allinone.model.LatticePoint;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

    private Random random;

    private DiffusionLimitedAggregationWorldLattice dendrite;

    private final DiffusionLimitedAggregatioContext ctx;

    @Autowired
    public DiffusionLimitedAggregationWorld(DiffusionLimitedAggregatioContext ctx) {
        this.ctx=ctx;
        this.worldDimensions=ctx.getWorldDimensions();
        random = new Random(new Date().getTime());
        for(int i=0; i<ctx.getProperties().getNumberOfParticles();i++){
            int x = random.nextInt(worldDimensions.getX());
            int y = random.nextInt(worldDimensions.getY());
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
            int direction = random.nextInt(4);
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
