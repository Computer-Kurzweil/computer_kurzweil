package org.woehlke.computer.kurzweil.view.tabs;


import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.apps.cca.control.CyclicCellularAutomatonControllerThread;
import org.woehlke.computer.kurzweil.apps.cca.ctx.CyclicCellularAutomatonContext;
import org.woehlke.computer.kurzweil.apps.cca.view.CyclicCellularAutomatonButtonsPanel;
import org.woehlke.computer.kurzweil.apps.cca.view.CyclicCellularAutomatonCanvas;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.apps.AppType;
import org.woehlke.computer.kurzweil.control.signals.UserSignal;
import org.woehlke.computer.kurzweil.control.signals.UserSlot;
import org.woehlke.computer.kurzweil.control.commons.Startable;
import org.woehlke.computer.kurzweil.control.commons.AppGuiComponent;
import org.woehlke.computer.kurzweil.view.common.BoxLayoutVertical;
import org.woehlke.computer.kurzweil.view.tabs.common.TabPanel;

import javax.accessibility.Accessible;
import java.awt.image.ImageObserver;
import java.io.Serializable;

@Log
public class CyclicCellularAutomatonTab extends TabPanel implements ImageObserver,
    Serializable,
    Accessible, Startable, AppGuiComponent {

    @Getter private CyclicCellularAutomatonCanvas canvas;
    @Getter private CyclicCellularAutomatonButtonsPanel panelButtons;
    @Getter private CyclicCellularAutomatonControllerThread controller;
    @Getter private final CyclicCellularAutomatonContext appCtx;

    @Getter
    private final static AppType appType = AppType.CYCLIC_CELLULAR_AUTOMATON;

    public CyclicCellularAutomatonTab(ComputerKurzweilApplicationContext ctx) {
        super(ctx,ctx.getProperties().getCca().getView().getSubtitle());
        this.setLayout(new BoxLayoutVertical(this));
        this.setBounds(ctx.getFrameBounds());
        this.canvas = new CyclicCellularAutomatonCanvas( this.ctx);
        this.panelButtons = new CyclicCellularAutomatonButtonsPanel( this.ctx);
        this.controller = new CyclicCellularAutomatonControllerThread(this.ctx);
        this.add(this.panelSubtitle);
        this.add(this.canvas);
        this.add(this.panelButtons);
        this.add(this.startStopButtonsPanel);
        this.appCtx = new CyclicCellularAutomatonContext(this, this.controller, this.canvas);
        this.controller.setAppCtx(  this.appCtx );
        UserSlot[] slotsModel = {this.canvas.getLattice()};
        UserSlot[] slotsGui = {this.canvas, this.panelButtons, this};
        UserSlot[] slotsControllers = {this.controller};
        this.appCtx.registerSignalsAndSlots(UserSignal.getModels(), slotsModel);
        this.appCtx.registerSignalsAndSlots(UserSignal.getGui(), slotsGui);
        this.appCtx.registerSignalsAndSlots(UserSignal.getControllerThreads(), slotsControllers);
    }

    @Override
    public void start() {
        log.info("start");
        this.showMe();
        this.controller.start();
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stop");
        this.controller = this.appCtx.stopController(this.getCtx());
        this.hideMe();
        log.info("stopped");
    }

    @Override
    public void update() {
        log.info("update");
        super.update();
        log.info("updated");
    }

    @Override
    public void handleUserSignal(UserSignal userSignal) {
        log.info("handleUserSignal: "+userSignal.name());
    }
}
