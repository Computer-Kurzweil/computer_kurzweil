package org.woehlke.computer.kurzweil.control.ctx;

import org.woehlke.computer.kurzweil.apps.AppType;
import org.woehlke.computer.kurzweil.view.tabs.common.TabPanel;

public interface AppContext extends Stepper {

    ControllerThread getControllerThread();
    TabPanel getTabPanel();
    AppType getAppType();
    Stepper getStepper();

}
