package org.woehlke.computer.kurzweil.view.apps;

import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.control.signals.UserSignal;
import org.woehlke.computer.kurzweil.control.commons.AppGuiComponent;
import org.woehlke.computer.kurzweil.view.tabs.common.TabAppPanel;
import org.woehlke.computer.kurzweil.view.tabs.common.TabPanel;


@Log
public class DiffusionLimitedAggregationTabApp extends TabAppPanel implements AppGuiComponent {

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

    @Override
    public void update() {

    }
}
