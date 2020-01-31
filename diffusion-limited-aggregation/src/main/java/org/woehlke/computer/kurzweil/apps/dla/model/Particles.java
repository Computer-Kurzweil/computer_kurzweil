package org.woehlke.computer.kurzweil.apps.dla.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.woehlke.computer.kurzweil.apps.dla.config.ConfigProperties.NUMBER_OF_PARTICLES;

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
public class Particles {

    private LatticePoint worldDimensions;

    private List<LatticePoint> particles = new ArrayList<LatticePoint>();

    private Random random;

    private Dendrite dendrite;

    public Particles(LatticePoint worldDimensions) {
        this.worldDimensions=worldDimensions;
        random = new Random(new Date().getTime());
        for(int i=0; i<NUMBER_OF_PARTICLES;i++){
            int x = random.nextInt(worldDimensions.getX());
            int y = random.nextInt(worldDimensions.getY());
            particles.add(new LatticePoint(x>=0?x:-x,y>=0?y:-y));
        }
        this.dendrite = new Dendrite(worldDimensions);
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
