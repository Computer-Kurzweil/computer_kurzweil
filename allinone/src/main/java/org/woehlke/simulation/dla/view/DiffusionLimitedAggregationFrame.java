package org.woehlke.simulation.dla.view;

import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.allinone.view.parts.PanelSubtitle;
import org.woehlke.simulation.dla.control.DiffusionLimitedAggregationControllerThread;
import org.woehlke.simulation.dla.model.DiffusionLimitedAggregatioContext;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.Serializable;

/**
 * Diffusion Limited Aggregation.
 *
 * (C) 2006 - 2013 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/diffusion-limited-aggregation.html
 * @author Thomas Woehlke
 *
 * Date: 04.02.2006
 * Time: 18:47:46
 */
@Log
@Component
public class DiffusionLimitedAggregationFrame extends JFrame implements ImageObserver,
        MenuContainer,
        Serializable,
        Accessible {

    private final PanelSubtitle subtitle;
    private final DiffusionLimitedAggregationControllerThread controllerThread;
    private final DiffusionLimitedAggregationCanvas canvas;
    @Getter
    private final DiffusionLimitedAggregatioContext ctx;

    @Autowired
    public DiffusionLimitedAggregationFrame(DiffusionLimitedAggregatioContext ctx) {
        super(ctx.getProperties().getTitle());
        this.ctx=ctx;
        this.subtitle = new PanelSubtitle(ctx.getProperties().getSubtitle());
        this.setLayout(new BorderLayout());
        this.add(subtitle, BorderLayout.NORTH);
        canvas = new DiffusionLimitedAggregationCanvas(this.ctx);
        this.add(canvas, BorderLayout.CENTER);
        controllerThread = new DiffusionLimitedAggregationControllerThread(canvas, ctx);
        pack();
        setBounds(100, 100, canvas.getWorldDimensions().getX(), canvas.getWorldDimensions().getY() + 30);
    }

    public void start(){
        setVisible(true);
        toFront();
        controllerThread.start();
    }

    public void stop(){

    }

}
