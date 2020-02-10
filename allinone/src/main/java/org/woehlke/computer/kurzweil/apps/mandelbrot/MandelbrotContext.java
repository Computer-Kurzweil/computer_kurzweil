package org.woehlke.computer.kurzweil.apps.mandelbrot;

import lombok.Delegate;
import lombok.Getter;
import lombok.Setter;
import org.woehlke.computer.kurzweil.apps.AppType;
import org.woehlke.computer.kurzweil.apps.mandelbrot.model.turing.MandelbrotTuringMachine;
import org.woehlke.computer.kurzweil.commons.AppContext;
import org.woehlke.computer.kurzweil.commons.ControllerThread;
import org.woehlke.computer.kurzweil.commons.Stepper;
import org.woehlke.computer.kurzweil.tabs.MandelbrotTab;
import org.woehlke.computer.kurzweil.tabs.common.TabPanel;

@Getter
public class MandelbrotContext implements AppContext {

    @Setter private MandelbrotControllerThread mandelbrotControllerThread;
    @Setter private MandelbrotTab mandelbrotTab;
    @Setter private MandelbrotTuringMachine mandelbrotTuringMachine;

    public MandelbrotContext() {}

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


    @Override
    public void step() {

    }

    @Override
    public void update() {

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
