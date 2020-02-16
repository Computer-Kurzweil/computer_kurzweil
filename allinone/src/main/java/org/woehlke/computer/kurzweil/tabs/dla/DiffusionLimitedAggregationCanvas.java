package org.woehlke.computer.kurzweil.tabs.dla;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.tabs.TabCanvas;
import org.woehlke.computer.kurzweil.commons.tabs.TabModel;
import org.woehlke.computer.kurzweil.model.LatticeNeighbourhood;
import org.woehlke.computer.kurzweil.model.LatticePoint;
import org.woehlke.computer.kurzweil.widgets.layouts.CanvasLayout;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Diffusion Limited Aggregation.
 *
 * (C) 2006 - 2013 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/diffusion-limited-aggregation.html
 * @author Thomas Woehlke
 *
 * Date: 05.02.2006
 * Time: 00:51:51
 */
@Log
@Getter
@ToString(callSuper = true)
//@ToString(exclude={"border","preferredSize","layout","particles","worldMap"})
@EqualsAndHashCode(callSuper=true)
public class DiffusionLimitedAggregationCanvas extends JComponent implements
    Serializable, TabCanvas,TabModel {

    @ToString.Exclude
    private final DiffusionLimitedAggregationContext tabCtx;
    @ToString.Exclude
    private final CompoundBorder border;
    @ToString.Exclude
    private final Dimension preferredSize;
    @ToString.Exclude
    private final CanvasLayout layout;

    @ToString.Exclude
    private volatile List<LatticePoint> particles = new ArrayList<>();
    @ToString.Exclude
    private volatile int[][] worldMap;
    private volatile int age=1;
    private volatile long steps;

    private final Color MEDIUM = Color.BLACK;
    private final Color PARTICLES = Color.BLUE;
    private final int initialNumberOfParticles;
    private final int directions = 4;
    private final int directionsFirst = 0;
    private final static int startX = 0;
    private final static int startY = 0;
    private final int worldX;
    private final int worldY;

    public DiffusionLimitedAggregationCanvas(
        DiffusionLimitedAggregationContext tabCtx
    ) {
        this.tabCtx = tabCtx;
        worldX = this.tabCtx.getCtx().getWorldDimensions().getX();
        worldY = this.tabCtx.getCtx().getWorldDimensions().getY();
        border = this.tabCtx.getCtx().getBorder();
        this.layout = new CanvasLayout(this);
        this.preferredSize = new Dimension(worldX,worldY);
        this.initialNumberOfParticles = this.tabCtx.getCtx().getProperties().getDla().getControl().getNumberOfParticles();
        this.worldMap = new int[this.worldX][this.worldY];
        this.setBorder(border);
        this.setBackground(MEDIUM);
        this.setLayout(layout);
        this.setPreferredSize(preferredSize);
        this.setSize(preferredSize);
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

    public void paint(Graphics g) {
        //log.info("paint");
        super.paintComponent(g);
        g.setColor(MEDIUM);
        g.fillRect(startX, startY, worldX, worldY);
        g.setColor(PARTICLES);
        Color ageColor;
        int x;
        int y;
        //paint moving Particles
        for(LatticePoint particle : particles){
            x = particle.getX();
            y = particle.getY();
            g.drawLine(x,y,x,y);
        }
        //paint dendrite Particles
        for(y=0; y < worldY; y++){
            for(x=0; x < worldX; x++){
                int myAge = worldMap[x][y];
                //if is part of dendrite
                if(myAge > 0) {
                    // color from age
                    myAge /= 25;
                    int blue = (myAge / 256) % (256 * 256);
                    int green = (myAge % 256);
                    int red = 255;
                    ageColor =  new Color(red, green, blue);
                    g.setColor(ageColor);
                    g.drawLine(x,y,x,y);
                    //log.info("paint: age "+myAge+" x="+x+",y="+y+" with color: red="+red+", green="+green+", blue="+blue+" ");
                }
            }
        }
    }

    public boolean hasDendriteNeighbour( int myX , int myY){
        //log.info("hasDendriteNeighbour. age="+age);
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

    public void step() {
        steps++;
        //log.info("step "+steps);
        List<LatticePoint> newParticles = new ArrayList<LatticePoint>();
        int x;
        int y;
        for(LatticePoint particle:particles){
            x = particle.getX() + this.worldX;
            y = particle.getY() + this.worldY;
            //Todo: make Enum
            int newDirection = this.tabCtx.getCtx().getRandom().nextInt(directions);
            switch (newDirection >= directionsFirst ? newDirection : - newDirection){
                case 0: y--; break;
                case 1: x++; break;
                case 2: y++; break;
                case 3: x--; break;
            }
            x %= this.worldX;
            y %= this.worldY;
            particle.setX(x);
            particle.setY(y);
            if(!this.hasDendriteNeighbour(x,y)){
                newParticles.add(particle);
            }
        }
        particles=newParticles;
        //log.info("stepped");
    }

    public void update(Graphics g) {
        //log.info("update(Graphics g)");
        paint(g);
    }

    @Override
    public void showMe() {
        log.info("showMe");
        log.info("this: "+this.toString());
    }

    @Override
    public void update() {
        //log.info("update");
    }
}
