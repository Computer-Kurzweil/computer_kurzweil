package org.woehlke.computer.kurzweil.commons;

import org.woehlke.computer.kurzweil.apps.AppType;
import org.woehlke.computer.kurzweil.tabs.common.TabPanel;

public interface AppContext extends Stepper {

    ControllerThread getControllerThread();
    TabPanel getTabPanel();
    AppType getAppType();
    Stepper getStepper();

}
