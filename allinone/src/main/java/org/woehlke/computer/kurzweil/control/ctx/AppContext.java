package org.woehlke.computer.kurzweil.control.ctx;

import org.woehlke.computer.kurzweil.apps.AppType;
import org.woehlke.computer.kurzweil.control.signals.SignalSlotDispatcher;
import org.woehlke.computer.kurzweil.view.tabs.common.TabPanel;

public interface AppContext extends SignalSlotDispatcher {

    ControllerThread getControllerThread();
    TabPanel getTabPanel();
    AppType getAppType();
    Stepper getStepper();

}
