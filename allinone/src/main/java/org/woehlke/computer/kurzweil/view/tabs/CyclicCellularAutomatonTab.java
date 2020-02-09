package org.woehlke.computer.kurzweil.view.tabs;


import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.apps.cca.control.CyclicCellularAutomatonControllerThread;
import org.woehlke.computer.kurzweil.apps.cca.ctx.CyclicCellularAutomatonContext;
import org.woehlke.computer.kurzweil.apps.cca.view.CyclicCellularAutomatonCanvas;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.apps.AppType;
import org.woehlke.computer.kurzweil.control.signals.UserSignal;
import org.woehlke.computer.kurzweil.view.common.PanelBorder;
import org.woehlke.computer.kurzweil.view.tabs.common.TabAppLayout;
import org.woehlke.computer.kurzweil.view.tabs.common.TabPanel;

import javax.swing.*;

@Log
public class CyclicCellularAutomatonTab extends JPanel implements TabPanel {

    @Getter private CyclicCellularAutomatonCanvas canvas;
    @Getter private CyclicCellularAutomatonControllerThread controller;
    @Getter private final CyclicCellularAutomatonContext appCtx;
    @Getter private final ComputerKurzweilApplicationContext ctx;

    @Getter
    private final static AppType appType = AppType.CYCLIC_CELLULAR_AUTOMATON;

    public CyclicCellularAutomatonTab(ComputerKurzweilApplicationContext ctx) {
        this.ctx = ctx;
        this.setBorder(PanelBorder.getBorder());
        this.setLayout(new TabAppLayout(this));
        this.setBounds(ctx.getFrameBounds());
        this.canvas = new CyclicCellularAutomatonCanvas( this.ctx);
        this.controller = new CyclicCellularAutomatonControllerThread(this.ctx);
        this.add(this.canvas.getPanelSubtitle());
        this.add(this.canvas);
        this.add(this.canvas.getNeighbourhoodButtonsPanel());
        this.add(this.canvas.getStartStopButtonsPanel());
        this.appCtx = new CyclicCellularAutomatonContext(this, this.controller, this.canvas);
        this.controller.setAppCtx(  this.appCtx );
        this.canvas.setAppCtx(  this.appCtx );
    }

    @Override
    public void start() {
        log.info("start");
        //this.showMe();
        this.getAppCtx().start();
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stop");
        this.getAppCtx().stop();
        //this.update();
        //this.showMe();
        log.info("stopped");
    }

    @Override
    public void update() {
        log.info("update");
        this.getAppCtx().update();
        //this.canvas.update();
        //this.repaint();
        log.info("updated");
    }

    @Override
    public void showMe() {
        log.info("showMe");
        this.canvas.getStartStopButtonsPanel().setVisible(true);
        this.canvas.getNeighbourhoodButtonsPanel().setVisible(true);
        this.canvas.getPanelSubtitle().setVisible(true);
        this.canvas.setVisible(true);
        this.setVisible(true);
    }

    @Override
    public void hideMe() {
        log.info("hideMe");
        this.canvas.getStartStopButtonsPanel().setVisible(false);
        this.canvas.getNeighbourhoodButtonsPanel().setVisible(false);
        this.canvas.getPanelSubtitle().setVisible(false);
        this.canvas.setVisible(false);
        this.setVisible(false);
    }

    @Override
    public void handleUserSignal(UserSignal userSignal) {
        log.info("handleUserSignal: "+userSignal.name());
    }

}
