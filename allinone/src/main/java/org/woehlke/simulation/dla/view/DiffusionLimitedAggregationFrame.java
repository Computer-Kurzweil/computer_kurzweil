package org.woehlke.simulation.dla.view;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.allinone.model.LatticePoint;
import org.woehlke.simulation.allinone.view.PanelSubtitle;
import org.woehlke.simulation.cca.model.CyclicCellularAutomatonContext;
import org.woehlke.simulation.dla.control.DiffusionLimitedAggregationControllerThread;
import org.woehlke.simulation.dla.model.DiffusionLimitedAggregatioContext;
import org.woehlke.simulation.dla.model.DiffusionLimitedAggregationWorld;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.ImageObserver;
import java.io.Serializable;

import static org.woehlke.simulation.dla.config.DiffusionLimitedAggregationProperties.SUBTITLE;
import static org.woehlke.simulation.dla.config.DiffusionLimitedAggregationProperties.TITLE;

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

    private final PanelSubtitle subtitle = new PanelSubtitle(SUBTITLE);
    private final DiffusionLimitedAggregationControllerThread controllerThread;
    private final DiffusionLimitedAggregationCanvas canvas;
    private final DiffusionLimitedAggregatioContext ctx;

    @Autowired
    public DiffusionLimitedAggregationFrame(DiffusionLimitedAggregatioContext ctx) {
        super(TITLE);
        this.ctx=ctx;
        this.setLayout(new BorderLayout());
        this.add(subtitle, BorderLayout.NORTH);
        canvas = new DiffusionLimitedAggregationCanvas(this.ctx);
        this.add(canvas, BorderLayout.CENTER);
        controllerThread = new DiffusionLimitedAggregationControllerThread(canvas);
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
