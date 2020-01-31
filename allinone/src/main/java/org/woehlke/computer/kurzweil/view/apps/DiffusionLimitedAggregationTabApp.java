package org.woehlke.computer.kurzweil.view.apps;

import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.view.apps.parts.TabAppPanel;
import org.woehlke.computer.kurzweil.view.tabs.parts.TabPanel;


@Log
public class DiffusionLimitedAggregationTabApp extends TabAppPanel {

    public DiffusionLimitedAggregationTabApp(TabPanel tab) {
        super(tab);
    }

    @Override
    public void start() {
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stopped");
    }
}
