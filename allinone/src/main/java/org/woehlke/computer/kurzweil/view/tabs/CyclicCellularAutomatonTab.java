package org.woehlke.computer.kurzweil.view.tabs;


import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.apps.cca.control.CyclicCellularAutomatonControllerThread;
import org.woehlke.computer.kurzweil.apps.cca.view.CyclicCellularAutomatonButtonsPanel;
import org.woehlke.computer.kurzweil.apps.cca.view.CyclicCellularAutomatonCanvas;
import org.woehlke.computer.kurzweil.config.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.model.Startable;
import org.woehlke.computer.kurzweil.view.common.BoxLayoutVertical;
import org.woehlke.computer.kurzweil.view.tabs.parts.TabPanel;

import javax.accessibility.Accessible;
import java.awt.image.ImageObserver;
import java.io.Serializable;

@Log
public class CyclicCellularAutomatonTab extends TabPanel implements ImageObserver,
    Serializable,
    Accessible, Startable {

    @Getter private CyclicCellularAutomatonCanvas canvas;
    @Getter private CyclicCellularAutomatonButtonsPanel panelButtons;
    @Getter private CyclicCellularAutomatonControllerThread controller;

    public CyclicCellularAutomatonTab(ComputerKurzweilApplicationContext ctx) {
        super(ctx,ctx.getProperties().getCca().getView().getSubtitle());
        this.setLayout(new BoxLayoutVertical(this));
        this.setBounds(ctx.getFrameBounds());
        this.canvas = new CyclicCellularAutomatonCanvas( this.ctx);
        this.panelButtons = new CyclicCellularAutomatonButtonsPanel( this.ctx);
        this.controller = new CyclicCellularAutomatonControllerThread(ctx, this.canvas, this.panelButtons);
        this.add(this.panelSubtitle);
        this.add(this.canvas);
        this.add(this.panelButtons);
        this.add(this.startStopButtonsPanel);
    }

    @Override
    public void start() {
        log.info("start");
        this.panelButtons.start();
        this.canvas.start();
        this.showMe();
        this.controller.start();
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stop");
        this.controller.exit();
        this.canvas.stop();
        this.panelButtons.stop();
        this.canvas=null;
        this.panelButtons=null;
        hideMe();
        log.info("stopped");
    }

    public void showMe() {
        log.info("showMe");
        this.setVisible(true);
    }

    public void hideMe() {
        log.info("hideMe");
        this.setVisible(false);
    }
}
