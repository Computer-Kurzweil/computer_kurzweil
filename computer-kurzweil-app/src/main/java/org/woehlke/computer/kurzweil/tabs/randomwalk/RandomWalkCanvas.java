package org.woehlke.computer.kurzweil.tabs.randomwalk;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.model.LatticePoint;
import org.woehlke.computer.kurzweil.commons.layouts.LayoutCanvas;
import org.woehlke.computer.kurzweil.commons.tabs.TabCanvas;
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
@Log
@Getter
@ToString(callSuper = true, exclude = {"tabCtx","border","preferredSize","layout","colorScheme","tabModel"})
@EqualsAndHashCode(callSuper=true, exclude = {"tabCtx","border","preferredSize","layout","colorScheme","tabModel"})
public class RandomWalkCanvas extends JComponent implements
    Serializable, TabCanvas, RandomWalk {

    private static final long serialVersionUID = 7526471155622776147L;

    private final RandomWalkContext tabCtx;
    private final RandomWalkModel tabModel;
    private final Border border;
    private final Dimension preferredSize;
    private final LayoutCanvas layout;
    private final RandomWalkColorScheme colorScheme;
    private Boolean running;
    private LatticePoint particlePosition;

    private final static int startX = 0;
    private final static int startY = 0;
    private final int worldX;
    private final int worldY;

    public RandomWalkCanvas(RandomWalkContext tabCtx) {
        this.tabCtx = tabCtx;
        this.tabModel = new RandomWalkModel( this.tabCtx );
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
        this.tabModel.resetLattice();
        this.particlePosition = new LatticePoint(worldX/2,worldY/2);
        this.running = Boolean.FALSE;
    }

    @Override
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
        if ( this.tabModel.getLattice() == null) {
            this.tabModel.resetLattice();
        }
        for (y = 0; y < worldY; y++) {
            for (x = 0; x < worldX; x++) {
                age = this.tabModel.getLattice() [x][y];
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

    @Override
    public void update(Graphics g) {
        paint(g);
    }


    @Override
    public void showMe() {

    }
}
