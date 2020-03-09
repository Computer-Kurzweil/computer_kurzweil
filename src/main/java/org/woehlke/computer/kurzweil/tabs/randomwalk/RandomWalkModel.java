package org.woehlke.computer.kurzweil.tabs.randomwalk;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.model.LatticePoint;
import org.woehlke.computer.kurzweil.commons.tabs.TabModel;
import org.woehlke.computer.kurzweil.tabs.randomwalk.canvas.ParticleOrientation;
import org.woehlke.computer.kurzweil.tabs.randomwalk.canvas.RandomWalkColorScheme;

import java.awt.*;
import java.util.concurrent.ForkJoinTask;

@Log4j2
@Getter
@EqualsAndHashCode(callSuper=false,exclude = {"tabCtx"})
public class RandomWalkModel extends ForkJoinTask<Void> implements TabModel, RandomWalk {

    private final RandomWalkContext tabCtx;
    private final Dimension preferredSize;
    private final RandomWalkColorScheme colorScheme;
    private volatile long[][] lattice;
    private Boolean running;
    private LatticePoint particlePosition;

    private final static int startX = 0;
    private final static int startY = 0;
    private final int worldX;
    private final int worldY;


    public RandomWalkModel(RandomWalkContext tabCtx) {
        this.tabCtx = tabCtx;
        this.worldX = this.tabCtx.getCtx().getWorldDimensions().getX();
        this.worldY = this.tabCtx.getCtx().getWorldDimensions().getY();
        this.preferredSize = new Dimension(worldX,worldY);
        this.colorScheme = new RandomWalkColorScheme();
        this.particlePosition = new LatticePoint(worldX/2,worldY/2);
        this.running = Boolean.FALSE;
    }

    @Override
    protected boolean exec() {
        boolean doIt = false;
        synchronized (running) {
            doIt = running.booleanValue();
        }
        if(doIt){
            //log.info("step");
            int x = particlePosition.getX();
            int y = particlePosition.getY();
            int randomOrientation = this.tabCtx.getCtx().getRandom().nextInt(ParticleOrientation.values().length);
            LatticePoint move = ParticleOrientation.values()[randomOrientation].getMove();
            x = (x + move.getX() + worldX ) % worldX;
            y = (y + move.getY() + worldY ) % worldY;
            particlePosition.setX(x);
            particlePosition.setY(y);
            lattice[x][y] = (lattice[x][y] + 10) * 2;
            //log.info("stepped ("+x+","+y+" = "+ lattice[x][y]+") "+ParticleOrientation.values()[randomOrientation].name());
        }
        return doIt;
    }

    public void resetLattice(){
        lattice = new long[worldX][worldY];
        int x;
        int y;
        for(y = 0; y <worldY; y++){
            for(x=0; x < worldX; x++){
                lattice[x][y]=0;
            }
        }
    }

    public void start() {
        log.info("start");
        synchronized (running) {
            running = Boolean.TRUE;
        }
        //log.info("started "+this.toString());
    }

    public void stop() {
        log.info("stop");
        synchronized (running) {
            running = Boolean.FALSE;
        }
        //log.info("stopped "+this.toString());
    }

    @Override
    public Void getRawResult() {
        return null;
    }

    @Override
    protected void setRawResult(Void value) {

    }

}
