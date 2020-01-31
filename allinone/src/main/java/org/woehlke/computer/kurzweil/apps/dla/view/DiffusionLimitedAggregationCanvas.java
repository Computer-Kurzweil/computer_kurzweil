package org.woehlke.computer.kurzweil.apps.dla.view;

import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import org.woehlke.computer.kurzweil.config.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.model.LatticePoint;
import org.woehlke.computer.kurzweil.apps.dla.model.DiffusionLimitedAggregationWorld;

import javax.swing.*;
import java.awt.*;


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
@Component
public class DiffusionLimitedAggregationCanvas extends JComponent {

    @Getter
    private final DiffusionLimitedAggregationWorld world;

    @Getter
    private final LatticePoint worldDimensions;

    private final Color MEDIUM = Color.BLACK;
    private final Color PARTICLES = Color.BLUE;

    private final ComputerKurzweilApplicationContext ctx;

    public DiffusionLimitedAggregationCanvas(
        ComputerKurzweilApplicationContext ctx
    ) {
        this.ctx = ctx;
        this.worldDimensions = ctx.getWorldDimensions();
        this.setBackground(MEDIUM);
        this.setSize(this.worldDimensions.getX(), this.worldDimensions.getY());
        this.world=new DiffusionLimitedAggregationWorld(this.ctx);
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        int width = worldDimensions.getX();
        int height = worldDimensions.getY();
        g.setColor(MEDIUM);
        g.fillRect(0,0,width,height);
        g.setColor(PARTICLES);
        for(LatticePoint pixel : world.getParticles()){
            g.drawLine(pixel.getX(),pixel.getY(),pixel.getX(),pixel.getY());
        }
        for(int y=0;y<worldDimensions.getY();y++){
            for(int x=0;x<worldDimensions.getX();x++){
                int age = world.getDendriteColor(x,y);
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
        paint(g);
    }

}