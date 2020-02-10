package org.woehlke.computer.kurzweil.apps.mandelbrot;

import lombok.Delegate;
import lombok.Getter;
import lombok.Setter;
import org.woehlke.computer.kurzweil.apps.AppType;
import org.woehlke.computer.kurzweil.apps.mandelbrot.model.turing.MandelbrotTuringMachine;
import org.woehlke.computer.kurzweil.commons.AppContext;
import org.woehlke.computer.kurzweil.commons.ControllerThread;
import org.woehlke.computer.kurzweil.commons.Stepper;
import org.woehlke.computer.kurzweil.trashcan.signals.SignalSlotDispatcher;
import org.woehlke.computer.kurzweil.trashcan.signals.SignalSlotDispatcherImpl;
import org.woehlke.computer.kurzweil.tabs.MandelbrotTab;
import org.woehlke.computer.kurzweil.tabs.common.TabPanel;

public class MandelbrotContext implements AppContext {

    @Getter @Setter
    private MandelbrotControllerThread mandelbrotControllerThread;

    @Getter @Setter
    private MandelbrotTab mandelbrotTab;

    @Getter @Setter
    private MandelbrotTuringMachine mandelbrotTuringMachine;

    @Delegate
    @Getter
    private final SignalSlotDispatcher signalSlotDispatcher;

    public MandelbrotContext() {
        this.signalSlotDispatcher = new SignalSlotDispatcherImpl();
    }

    @Override
    public ControllerThread getControllerThread() {
        return mandelbrotControllerThread;
    }

    @Override
    public TabPanel getTabPanel() {
        return mandelbrotTab;
    }

    @Override
    public AppType getAppType() {
        return AppType.MANDELBROT_SET;
    }

    @Override
    public Stepper getStepper() {
        return mandelbrotTuringMachine;
    }


}
