package org.woehlke.computer.kurzweil.tabs;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.apps.dla.DiffusionLimitedAggregationCanvas;
import org.woehlke.computer.kurzweil.apps.dla.DiffusionLimitedAggregationContext;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.trashcan.signals.UserSignal;
import org.woehlke.computer.kurzweil.tabs.common.Tab;
import org.woehlke.computer.kurzweil.widgets.PanelSubtitle;
import org.woehlke.computer.kurzweil.widgets.StartStopButtonsPanel;
import org.woehlke.computer.kurzweil.tabs.common.TabPanel;

@Log
@Getter
public class DiffusionLimitedAggregationTab extends Tab implements TabPanel {

    private final ComputerKurzweilApplicationContext ctx;
    private final DiffusionLimitedAggregationContext appCtx;
    private final DiffusionLimitedAggregationCanvas canvas;
    private final StartStopButtonsPanel startStopButtonsPanel;
    private final PanelSubtitle panelSubtitle;

    public DiffusionLimitedAggregationTab(ComputerKurzweilApplicationContext ctx) {
        this.ctx = ctx;
        String subtitle = ctx.getProperties().getDla().getView().getSubtitle();
        this.appCtx = new DiffusionLimitedAggregationContext(this );
        this.startStopButtonsPanel = new StartStopButtonsPanel( this );
        this.panelSubtitle = new PanelSubtitle(subtitle);
        this.canvas = this.appCtx.getCanvas();
        this.add(this.panelSubtitle);
        this.add(this.canvas);
        this.add(this.startStopButtonsPanel);
    }

    @Override
    public void start() {
        log.info("start");
        this.appCtx.start();
        showMe();
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stop");
        this.appCtx.stop();
        log.info("stopped");
    }

    @Override
    public void update() {
        log.info("update");
    }

    @Override
    public void showMe() {
        log.info("showMe");
    }

    @Override
    public void hideMe() {
        log.info("hideMe");
    }

    @Override
    public void handleUserSignal(UserSignal userSignal) {
        log.info("handleUserSignal: "+userSignal.name());
    }

    @Override
    public String getTitle() {
        return ctx.getProperties().getDla().getView().getTitle();
    }

    @Override
    public String getSubTitle() {
        return ctx.getProperties().getDla().getView().getSubtitle();
    }
}
