package org.woehlke.computer.kurzweil.apps.dla.view;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.trashcan.signals.UserSignal;
import org.woehlke.computer.kurzweil.view.widgets.PanelSubtitle;
import org.woehlke.computer.kurzweil.apps.dla.control.DiffusionLimitedAggregationControllerThread;

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
@Deprecated
@Log
public class DiffusionLimitedAggregationFrame extends JPanel implements ImageObserver,
        MenuContainer,
        Serializable,
        Accessible, Startable {

    private final PanelSubtitle subtitle;
    private final DiffusionLimitedAggregationControllerThread controllerThread;
    private final DiffusionLimitedAggregationCanvas canvas;

    @Getter
    private final ComputerKurzweilApplicationContext ctx;

    public DiffusionLimitedAggregationFrame(ComputerKurzweilApplicationContext ctx) {
        //super(ctx.getProperties().getDla().getView().getTitle());
        this.ctx=ctx;
        this.subtitle = PanelSubtitle.getPanelSubtitleForDla(this.ctx);
        this.setLayout(new BorderLayout());
        this.add(subtitle, BorderLayout.NORTH);
        canvas = new DiffusionLimitedAggregationCanvas(this.ctx);
        this.add(canvas, BorderLayout.CENTER);
        controllerThread = new DiffusionLimitedAggregationControllerThread(canvas, this.ctx);
        //pack();
        //setBounds(100, 100, canvas.getWorldDimensions().getX(), canvas.getWorldDimensions().getY() + 30);
    }

    public void start(){
        setVisible(true);
        //toFront();
        controllerThread.start();
    }

    public void stop(){
        controllerThread.exit();
        setVisible(false);
    }

    //@Override
    public void handleUserSignal(UserSignal userSignal) {

    }
}
