package org.woehlke.simulation.allinone.view.tabs;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.simulation.allinone.model.ComputerKurzweilApplicationContext;
import org.woehlke.simulation.allinone.view.apps.DiffusionLimitedAggregationApp;
import org.woehlke.simulation.allinone.view.tabs.parts.TabPanel;

@Log
public class DiffusionLimitedAggregationTab extends TabPanel {

    @Getter
    private final DiffusionLimitedAggregationApp app;

    public DiffusionLimitedAggregationTab(ComputerKurzweilApplicationContext ctx) {
        super(ctx,ctx.getProperties().getDla().getView().getSubtitle());
        this.app = new DiffusionLimitedAggregationApp(this);
        this.add(this.panelSubtitle);
        this.add(this.app);
        this.add(this.startStopButtonsPanel);
    }

    @Override
    public void start() {
        this.app.start();
    }

    @Override
    public void stop() {
        this.app.stop();
    }

}
