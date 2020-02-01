package org.woehlke.computer.kurzweil.view.apps;

import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.control.events.UserSignal;
import org.woehlke.computer.kurzweil.view.widgets.TabAppPanel;
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

    @Override
    public void handleUserSignal(UserSignal userSignal) {
        log.info("handleUserSignal: "+userSignal.name());
    }
}
