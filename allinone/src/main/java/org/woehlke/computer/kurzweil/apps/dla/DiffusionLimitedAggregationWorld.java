package org.woehlke.computer.kurzweil.apps.dla;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.commons.Stepper;
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
public class DiffusionLimitedAggregationWorld implements Stepper {

    private List<LatticePoint> particles = new ArrayList<>();

    private final LatticePoint worldDimensions;
    private final DiffusionLimitedAggregationWorldLattice dendrite;
    private final ComputerKurzweilApplicationContext ctx;
    private final int initialNumberOfParticles;

    public DiffusionLimitedAggregationWorld(ComputerKurzweilApplicationContext ctx) {
        this.ctx=ctx;
        this.worldDimensions=ctx.getWorldDimensions();
        this.initialNumberOfParticles = ctx.getProperties().getDla().getControl().getNumberOfParticles();
        for(int i=0; i< this.initialNumberOfParticles; i++){
            int x = ctx.getRandom().nextInt(worldDimensions.getX());
            int y = ctx.getRandom().nextInt(worldDimensions.getY());
            particles.add(new LatticePoint(x>=0?x:-x,y>=0?y:-y));
        }
        this.dendrite = new DiffusionLimitedAggregationWorldLattice(this.ctx);
    }

    public List<LatticePoint> getParticles() {
        return particles;
    }

    public void step() {
        log.info("stop");
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

    @Override
    public void update() {
        log.info("update");
    }

    public int getDendriteColor(int x, int y) {
        return dendrite.getAgeForPixel(x,y);
    }

    @Override
    public void start() {
        log.info("start");
    }

    @Override
    public void stop() {
        log.info("stop");
    }
}
