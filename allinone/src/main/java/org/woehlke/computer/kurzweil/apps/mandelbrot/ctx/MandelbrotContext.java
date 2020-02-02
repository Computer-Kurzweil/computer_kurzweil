package org.woehlke.computer.kurzweil.apps.mandelbrot.ctx;

import lombok.Getter;
import lombok.Setter;
import org.woehlke.computer.kurzweil.apps.AppType;
import org.woehlke.computer.kurzweil.apps.mandelbrot.control.ComputeMandelbrotSetControllerThread;
import org.woehlke.computer.kurzweil.apps.mandelbrot.model.turing.MandelbrotTuringMachine;
import org.woehlke.computer.kurzweil.control.ctx.AppContext;
import org.woehlke.computer.kurzweil.control.ctx.ControllerThread;
import org.woehlke.computer.kurzweil.control.ctx.Stepper;
import org.woehlke.computer.kurzweil.control.signals.SignalSlotDispatcher;
import org.woehlke.computer.kurzweil.view.tabs.MandelbrotTab;
import org.woehlke.computer.kurzweil.view.tabs.common.TabPanel;

public class MandelbrotContext implements AppContext {

    @Getter @Setter
    private ComputeMandelbrotSetControllerThread computeMandelbrotSetControllerThread;

    @Getter @Setter
    private MandelbrotTab mandelbrotTab;

    @Getter @Setter
    private MandelbrotTuringMachine mandelbrotTuringMachine;

    @Getter
    private final SignalSlotDispatcher signalSlotDispatcher;

    public MandelbrotContext(SignalSlotDispatcher signalSlotDispatcher) {
        this.signalSlotDispatcher = signalSlotDispatcher;
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
    public AppType gaetAppType() {
        return AppType.MANDELBROT_SET;
    }

    @Override
    public Stepper getStepper() {
        return mandelbrotTuringMachine;
    }
}
