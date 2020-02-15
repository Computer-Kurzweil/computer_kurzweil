package org.woehlke.computer.kurzweil.tabs.dla;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.commons.tabs.TabCanvas;
import org.woehlke.computer.kurzweil.commons.tabs.TabModel;
import org.woehlke.computer.kurzweil.model.LatticePoint;

import javax.swing.*;
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
@ToString
@EqualsAndHashCode(callSuper=true)
public class DiffusionLimitedAggregationCanvas extends JComponent implements
    Serializable, TabCanvas,TabModel {

    private final Color MEDIUM = Color.BLACK;
    private final Color PARTICLES = Color.BLUE;
    private final Dimension preferredSize;

    private final ComputerKurzweilApplicationContext ctx;

    private final static int startX = 0;
    private final static int startY = 0;
    private final int worldX;
    private final int worldY;

    private List<LatticePoint> particles = new ArrayList<>();
    private int worldMap[][];
    private int age=1;

    private final int initialNumberOfParticles;

    public DiffusionLimitedAggregationCanvas(
        ComputerKurzweilApplicationContext ctx
    ) {
        this.ctx = ctx;
        worldX = ctx.getWorldDimensions().getX();
        worldY = ctx.getWorldDimensions().getY();
        this.setBackground(MEDIUM);
        this.setSize(worldX, worldY);
        this.preferredSize = new Dimension(worldX,worldY);
        this.setPreferredSize(preferredSize);
        this.setSize(this.preferredSize);
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

    public void paint(Graphics g) {
        log.info("paint");
        super.paintComponent(g);
        g.setColor(MEDIUM);
        g.fillRect(startX,startY,worldX,worldY);
        g.setColor(PARTICLES);
        for(LatticePoint pixel : particles){
            g.drawLine(pixel.getX(),pixel.getY(),pixel.getX(),pixel.getY());
        }
        for(int y=0; y<startY; y++){
            for(int x=0; x<worldX; x++){
                Color ageColor;
                int myAge = worldMap[x][y];
                if(myAge>0) {
                    myAge /= 25;
                    int blue = (myAge / 256) % (256 * 256);
                    int green = (myAge % 256);
                    int red = 255;
                    ageColor =  new Color(red, green, blue);
                } else {
                    ageColor = MEDIUM;
                }
                g.setColor(ageColor);
                g.drawLine(x,y,x,y);
            }
        }
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

    public void update(Graphics g) {
        log.info("update(Graphics g)");
        paint(g);
    }

    @Override
    public void showMe() {
        log.info("showMe");
    }

    @Override
    public void update() {
        log.info("update");
        //repaint();
    }
}
