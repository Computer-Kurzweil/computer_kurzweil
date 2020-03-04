package org.woehlke.computer.kurzweil.tabs.randomwalk;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.tabs.TabCanvas;
import org.woehlke.computer.kurzweil.commons.tabs.TabModel;
import org.woehlke.computer.kurzweil.commons.model.LatticePoint;
import org.woehlke.computer.kurzweil.commons.layouts.LayoutCanvas;
import org.woehlke.computer.kurzweil.tabs.randomwalk.canvas.ParticleOrientation;
import org.woehlke.computer.kurzweil.tabs.randomwalk.canvas.RandomWalkColorScheme;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.Serializable;

/**
 * Cyclic Cellular Automaton.
 *
 * (C) 2006 - 2013 Thomas Woehlke.
 * http://thomas-woehlke.de/p/cyclic-cellular-automaton/
 * @author Thomas Woehlke
 *
 * Date: 05.02.2006
 * Time: 00:51:51
 */
@Log4j2
@Getter
@ToString(callSuper = true, exclude = {"tabCtx","border","preferredSize","layout","colorScheme","lattice"})
@EqualsAndHashCode(callSuper=true, exclude = {"tabCtx","border","preferredSize","layout","colorScheme","lattice"})
public class RandomWalkCanvas extends JComponent implements
    Serializable, TabCanvas, TabModel {

    private static final long serialVersionUID = -3057254130516052936L;

    private final RandomWalkContext tabCtx;
    private final Border border;
    private final Dimension preferredSize;
    private final LayoutCanvas layout;
    private final RandomWalkColorScheme colorScheme;
    private volatile long[][] lattice;
    private Boolean running;
    private LatticePoint particlePosition;

    private final static int startX = 0;
    private final static int startY = 0;
    private final int worldX;
    private final int worldY;

    public RandomWalkCanvas(RandomWalkContext tabCtx) {
        this.tabCtx = tabCtx;
        this.border = this.tabCtx.getCtx().getCanvasBorder();
        this.worldX = this.tabCtx.getCtx().getWorldDimensions().getX();
        this.worldY = this.tabCtx.getCtx().getWorldDimensions().getY();
        this.layout = new LayoutCanvas(this);
        this.preferredSize = new Dimension(worldX,worldY);
        this.colorScheme = new RandomWalkColorScheme();
        this.setLayout(layout);
        this.setPreferredSize(preferredSize);
        this.setMinimumSize(preferredSize);
        this.setMaximumSize(preferredSize);
        this.setSize(this.worldX,this.worldY);
        this.resetLattice();
        this.particlePosition = new LatticePoint(worldX/2,worldY/2);
        this.running = Boolean.FALSE;
        showMe();
    }

    public void paint(Graphics g) {
        //log.info("paint START (Graphics g)");
        int x;
        int y;
        long age;
        int red;
        int green;
        int blue;
        long myage;
        long mybyte;
        long limit = 256 * 256 * 256;
        if (lattice == null) {
            this.resetLattice();
        }
        for (y = 0; y < worldY; y++) {
            for (x = 0; x < worldX; x++) {
                age = this.lattice[x][y];
                if(age == 0){
                    red = 0;
                    green = 0;
                    blue = 0;
                } else {
                    myage = limit - (age % limit);
                    mybyte = myage % 256;
                    red = (int)(mybyte);
                    myage /= 256;
                    mybyte = myage % 256;
                    green = (int)(mybyte);
                    myage /= 256;
                    mybyte = myage % 256;
                    blue = (int)(mybyte);
                }
                Color colorForAge = new Color(red, green, blue);
                g.setColor(colorForAge);
                g.drawLine(x, y, x, y);
            }
        }
        super.paintComponent(g);
        //log.info("paint DONE (Graphics g)");
    }

    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void showMe() {
        log.info("showMe "+this.toString());
    }

    public void start() {
        log.info("start");
        showMe();
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

    public void update(){
        //log.info("update");
    }

    public void step(){
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

}
