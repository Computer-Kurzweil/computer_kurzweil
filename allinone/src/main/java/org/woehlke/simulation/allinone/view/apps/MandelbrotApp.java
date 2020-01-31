package org.woehlke.simulation.allinone.view.apps;

import lombok.extern.java.Log;
import org.woehlke.simulation.allinone.view.apps.parts.AppTabPanel;
import org.woehlke.simulation.allinone.view.tabs.parts.TabPanel;

@Log
public class MandelbrotApp extends AppTabPanel {

    public MandelbrotApp(TabPanel tab) {
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
