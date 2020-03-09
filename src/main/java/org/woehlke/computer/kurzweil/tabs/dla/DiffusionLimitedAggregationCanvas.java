package org.woehlke.computer.kurzweil.tabs.dla;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.model.LatticeNeighbourhood;
import org.woehlke.computer.kurzweil.commons.model.LatticePoint;
import org.woehlke.computer.kurzweil.commons.layouts.LayoutCanvas;

import javax.swing.*;
import javax.swing.border.Border;
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
@Log4j2
@Getter
@ToString(callSuper = true, exclude = {"tabCtx","border","preferredSize","layout","particles"})
@EqualsAndHashCode(callSuper=true, exclude = {"tabCtx","border","preferredSize","layout","particles"})
public class DiffusionLimitedAggregationCanvas extends JComponent implements
    Serializable, DiffusionLimitedAggregation {

    private final DiffusionLimitedAggregationContext tabCtx;
    private final DiffusionLimitedAggregationModel tabModel;
    private final Border border;
    private final Dimension preferredSize;
    private final LayoutCanvas layout;

    private final Color MEDIUM = Color.BLACK;
    private final Color PARTICLES = Color.BLUE;
    private final int directionsFirst = 0;
    private final static int startX = 0;
    private final static int startY = 0;
    private final int worldX;
    private final int worldY;

    public DiffusionLimitedAggregationCanvas(
        DiffusionLimitedAggregationContext tabCtx
    ) {
        this.tabCtx = tabCtx;
        this.tabModel = new DiffusionLimitedAggregationModel( this.tabCtx );
        this.border = this.tabCtx.getCtx().getCanvasBorder();
        worldX = this.tabCtx.getCtx().getWorldDimensions().getX();
        worldY = this.tabCtx.getCtx().getWorldDimensions().getY();
        this.layout = new LayoutCanvas(this);
        this.preferredSize = new Dimension(worldX,worldY);

        this.setBackground(MEDIUM);
        this.setLayout(layout);
        this.setPreferredSize(preferredSize);
        this.setMinimumSize(preferredSize);
        this.setMaximumSize(preferredSize);
        this.setSize(worldX,worldY);

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
        for(LatticePoint particle :  this.tabModel.getParticles()){
            x = particle.getX();
            y = particle.getY();
            g.drawLine(x,y,x,y);
        }
        int myAge = 0;
        //paint dendrite Particles
        for(y=0; y < worldY; y++){
            for(x=0; x < worldX; x++){
                myAge =   this.tabModel.getAgeFor(x,y);
                //myAge = worldMap[x][y];
                //if is part of dendrite
                if(myAge > 0) {
                    // color from age
                    myAge /= 25;
                    int blue = (myAge / 256) % (256 * 256);
                    int green = (myAge % 256);
                    int red = 255;
                    ageColor = new Color(red, green, blue);
                    g.setColor(ageColor);
                    g.drawLine(x,y,x,y);
                    //log.info("paint: age "+myAge+" x="+x+",y="+y+" with color: red="+red+", green="+green+", blue="+blue+" ");
                }
            }
        }
    }

    public void update(Graphics g) {
        //log.info("update(Graphics g)");
        paint(g);
    }

}
