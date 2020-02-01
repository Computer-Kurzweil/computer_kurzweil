package org.woehlke.computer.kurzweil.apps.cca.conf;

import org.woehlke.computer.kurzweil.apps.AppType;
import org.woehlke.computer.kurzweil.control.ctx.ControllerThread;
import org.woehlke.computer.kurzweil.control.ctx.Stepper;
import org.woehlke.computer.kurzweil.control.ctx.AppContext;
import org.woehlke.computer.kurzweil.view.tabs.common.TabPanel;

public class CyclicCellularAutomatonContext implements AppContext {

    @Override
    public ControllerThread getControllerThread() {
        return null;
    }

    @Override
    public TabPanel getTabPanel() {
        return null;
    }

    @Override
    public AppType gaetAppType() {
        return null;
    }

    @Override
    public Stepper getStepper() {
        return null;
    }
}
