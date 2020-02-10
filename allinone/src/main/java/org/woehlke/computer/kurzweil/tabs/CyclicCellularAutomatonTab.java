package org.woehlke.computer.kurzweil.tabs;


import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.apps.cca.CyclicCellularAutomatonControllerThread;
import org.woehlke.computer.kurzweil.apps.cca.CyclicCellularAutomatonContext;
import org.woehlke.computer.kurzweil.apps.cca.CyclicCellularAutomatonCanvas;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.apps.AppType;
import org.woehlke.computer.kurzweil.trashcan.signals.UserSignal;
import org.woehlke.computer.kurzweil.borders.PanelBorder;
import org.woehlke.computer.kurzweil.tabs.common.Tab;
import org.woehlke.computer.kurzweil.tabs.common.TabLayout;
import org.woehlke.computer.kurzweil.tabs.common.TabPanel;

@Log
@Getter
public class CyclicCellularAutomatonTab extends Tab implements TabPanel {

    private final CyclicCellularAutomatonCanvas canvas;
    private final CyclicCellularAutomatonContext appCtx;
    private final ComputerKurzweilApplicationContext ctx;
    private CyclicCellularAutomatonControllerThread controller;

    private final static AppType appType = AppType.CYCLIC_CELLULAR_AUTOMATON;

    public CyclicCellularAutomatonTab(ComputerKurzweilApplicationContext ctx) {
        this.ctx = ctx;
        this.setBorder(PanelBorder.getBorder());
        this.setLayout(new TabLayout(this));
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
        this.showMe();
        this.getAppCtx().start();
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stop");
        this.getAppCtx().stop();
        log.info("stopped");
    }

    @Override
    public void update() {
        log.info("update");
        this.getAppCtx().update();
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

    @Override
    public String getTitle() {
        return ctx.getProperties().getCca().getView().getTitle();
    }

    @Override
    public String getSubTitle() {
        return ctx.getProperties().getCca().getView().getSubtitle();
    }
}
