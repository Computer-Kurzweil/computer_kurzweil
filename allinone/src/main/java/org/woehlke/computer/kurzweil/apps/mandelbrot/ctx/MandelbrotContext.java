package org.woehlke.computer.kurzweil.apps.mandelbrot.ctx;

import lombok.Delegate;
import lombok.Getter;
import lombok.Setter;
import org.woehlke.computer.kurzweil.apps.AppType;
import org.woehlke.computer.kurzweil.apps.mandelbrot.control.ComputeMandelbrotSetControllerThread;
import org.woehlke.computer.kurzweil.apps.mandelbrot.model.turing.MandelbrotTuringMachine;
import org.woehlke.computer.kurzweil.control.ctx.AppContext;
import org.woehlke.computer.kurzweil.control.ctx.ControllerThread;
import org.woehlke.computer.kurzweil.control.ctx.Stepper;
import org.woehlke.computer.kurzweil.control.signals.SignalSlotDispatcher;
import org.woehlke.computer.kurzweil.control.signals.SignalSlotDispatcherImpl;
import org.woehlke.computer.kurzweil.view.tabs.MandelbrotTab;
import org.woehlke.computer.kurzweil.view.tabs.common.TabPanel;

public class MandelbrotContext implements AppContext {

    @Getter @Setter
    private ComputeMandelbrotSetControllerThread computeMandelbrotSetControllerThread;

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
        return computeMandelbrotSetControllerThread;
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
