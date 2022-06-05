package org.woehlke.computer.kurzweil.tabs.dla.model;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.model.LatticeNeighbourhood;
import org.woehlke.computer.kurzweil.commons.model.LatticePoint;
import org.woehlke.computer.kurzweil.commons.tabs.TabModel;
import org.woehlke.computer.kurzweil.tabs.dla.config.DiffusionLimitedAggregationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;

@Log
@Getter
public class DiffusionLimitedAggregationModel extends ForkJoinTask<Void> implements TabModel {

    private static final long serialVersionUID = 7526471155622776147L;

    private final DiffusionLimitedAggregationContext tabCtx;
    private final int initialNumberOfParticles;
    private final int worldX;
    private final int worldY;

    private int[][] worldMap;
    private int age = 1;
    private long steps;
    private Boolean running;
    private List<LatticePoint> particles = new ArrayList<>();

    private final int directions = 4;
    private final int directionsFirst = 0;
    private final static int startX = 0;
    private final static int startY = 0;

    public DiffusionLimitedAggregationModel(
        DiffusionLimitedAggregationContext tabCtx
    ) {
        this.tabCtx = tabCtx;
        running = Boolean.FALSE;
        worldX = this.tabCtx.getCtx().getWorldDimensions().getX();
        worldY = this.tabCtx.getCtx().getWorldDimensions().getY();
        this.initialNumberOfParticles = this.tabCtx.getCtx().getProperties().getDla().getControl().getNumberOfParticles();
        this.worldMap = new int[this.worldX][this.worldY];
        int x;
        int y;
        //create moving Particles
        for(int i=0; i < this.initialNumberOfParticles; i++){
            x = this.tabCtx.getCtx().getRandom().nextInt(this.worldX);
            y = this.tabCtx.getCtx().getRandom().nextInt(this.worldY);
            x = x >= 0 ? x : -x;
            y = y >= 0 ? y : -y;
            particles.add(new LatticePoint(x , y));
        }
        //place first dendrite Particle
        x = this.worldX / 2;
        y = this.worldY / 2;
        worldMap[x][y]=age;
        age++;
    }

    public void start() {
        log.info("start");
        synchronized (running) {
            running = Boolean.TRUE;
        }
        log.info("started "+this.toString());
    }

    public void stop() {
        log.info("stop");
        synchronized (running) {
            running = Boolean.FALSE;
        }
        log.info("stopped "+this.toString());
    }

    public void step() { }

    public int getAgeFor(int x, int y) {
        return this.worldMap[x][y];
    }

    @Override
    public boolean exec() {
        boolean doIt = false;
        synchronized (running) {
            doIt = running.booleanValue();
        }
        if(doIt) {
            steps++;
            log.info("step "+steps);
            List<LatticePoint> newParticles = new ArrayList<LatticePoint>();
            int x;
            int y;
            for (LatticePoint particle : particles) {
                x = particle.getX() + this.worldX;
                y = particle.getY() + this.worldY;
                //Todo: make Enum
                int newDirection = this.tabCtx.getCtx().getRandom().nextInt(directions);
                switch (newDirection >= directionsFirst ? newDirection : -newDirection) {
                    case 0:
                        y--;
                        break;
                    case 1:
                        x++;
                        break;
                    case 2:
                        y++;
                        break;
                    case 3:
                        x--;
                        break;
                }
                x %= this.worldX;
                y %= this.worldY;
                particle.setX(x);
                particle.setY(y);
                if (!this.hasDendriteNeighbour(x, y)) {
                    newParticles.add(particle);
                }
            }
            particles = newParticles;
        }
        log.info("stepped");
        return doIt;
    }

    public boolean hasDendriteNeighbour( int myX , int myY){
        log.info("hasDendriteNeighbour. age="+age);
        if(worldMap[myX][myY]==0){
            LatticePoint[] neighbours = LatticeNeighbourhood.get(worldX,worldY, myX, myY);
            for(LatticePoint neighbour:neighbours){
                if(worldMap[neighbour.getX()][neighbour.getY()]>0){
                    worldMap[myX][myY]=age;
                    age++;
                    return true;
                }
            }
            return false;
        } else {
            return true;
        }
    }


    @Override
    public Void getRawResult() {
        return null;
    }

    @Override
    protected void setRawResult(Void value) {

    }

}
