package org.woehlke.computer.kurzweil.tabs.dla;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.tabs.HasModel;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.commons.tabs.TabCanvas;
import org.woehlke.computer.kurzweil.model.LatticePoint;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;


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
    Serializable, TabCanvas, HasModel {

    private final DiffusionLimitedAggregation stepper;
    private final Color MEDIUM = Color.BLACK;
    private final Color PARTICLES = Color.BLUE;
    private final Dimension preferredSize;

    private final ComputerKurzweilApplicationContext ctx;

    private final static int startX = 0;
    private final static int startY = 0;
    private final int worldX;
    private final int worldY;

    public DiffusionLimitedAggregationCanvas(
        ComputerKurzweilApplicationContext ctx
    ) {
        this.ctx = ctx;
        worldX = ctx.getWorldDimensions().getX();
        worldY = ctx.getWorldDimensions().getY();
        this.stepper = new DiffusionLimitedAggregation(this.ctx);
        this.setBackground(MEDIUM);
        this.setSize(worldX, worldY);
        this.preferredSize = new Dimension(worldX,worldY);
        this.setPreferredSize(preferredSize);
        this.setSize(this.preferredSize);
    }

    public void paint(Graphics g) {
        log.info("paint");
        super.paintComponent(g);
        g.setColor(MEDIUM);
        g.fillRect(startX,startY,worldX,worldY);
        g.setColor(PARTICLES);
        for(LatticePoint pixel : stepper.getParticles()){
            g.drawLine(pixel.getX(),pixel.getY(),pixel.getX(),pixel.getY());
        }
        for(int y=0; y<startY; y++){
            for(int x=0; x<worldX; x++){
                int age = stepper.getDendriteColor(x,y);
                if(age>0){
                    age /= 25;
                    int blue = (age / 256) % (256*256);
                    int green = (age % 256);
                    int red = 255;
                    Color ageColor = new Color(red,green,blue);
                    g.setColor(ageColor);
                    g.drawLine(x,y,x,y);
                }
            }
        }
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
