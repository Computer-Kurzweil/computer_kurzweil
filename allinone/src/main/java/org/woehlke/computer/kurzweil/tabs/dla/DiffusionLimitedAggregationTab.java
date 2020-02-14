package org.woehlke.computer.kurzweil.tabs.dla;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.commons.tabs.TabPanel;
import org.woehlke.computer.kurzweil.widgets.PanelSubtitle;
import org.woehlke.computer.kurzweil.widgets.StartStopButtonsPanel;
import org.woehlke.computer.kurzweil.commons.tabs.Tab;

@Log
@Getter
public class DiffusionLimitedAggregationTab extends TabPanel implements Tab {

    private final ComputerKurzweilApplicationContext ctx;
    private final DiffusionLimitedAggregationContext tabCtx;
    private final DiffusionLimitedAggregationCanvas canvas;

    private final StartStopButtonsPanel startStopButtonsPanel;
    private final PanelSubtitle panelSubtitle;

    public DiffusionLimitedAggregationTab(ComputerKurzweilApplicationContext ctx) {
        this.ctx = ctx;
        String subtitle = ctx.getProperties().getDla().getView().getSubtitle();
        this.tabCtx = new DiffusionLimitedAggregationContext(this );
        this.startStopButtonsPanel = new StartStopButtonsPanel( this );
        this.panelSubtitle = new PanelSubtitle(subtitle);
        this.canvas = this.tabCtx.getCanvas();
        this.add(this.panelSubtitle);
        this.add(this.canvas);
        this.add(this.startStopButtonsPanel);
    }

    @Override
    public void start() {
        log.info("start");
        this.tabCtx.start();
        showMe();
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stop");
        this.tabCtx.stop();
        log.info("stopped");
    }

    @Override
    public void showMe() {
        log.info("showMe");
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
