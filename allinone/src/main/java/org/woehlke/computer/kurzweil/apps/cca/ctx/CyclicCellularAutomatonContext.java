package org.woehlke.computer.kurzweil.apps.cca.ctx;

import lombok.Delegate;
import lombok.Getter;
import org.woehlke.computer.kurzweil.apps.AppType;
import org.woehlke.computer.kurzweil.apps.cca.control.CyclicCellularAutomatonControllerThread;
import org.woehlke.computer.kurzweil.apps.cca.view.CyclicCellularAutomatonCanvas;
import org.woehlke.computer.kurzweil.control.ctx.ControllerThread;
import org.woehlke.computer.kurzweil.control.ctx.Stepper;
import org.woehlke.computer.kurzweil.control.ctx.AppContext;
import org.woehlke.computer.kurzweil.control.signals.SignalSlotDispatcher;
import org.woehlke.computer.kurzweil.control.signals.SignalSlotDispatcherImpl;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.view.tabs.CyclicCellularAutomatonTab;
import org.woehlke.computer.kurzweil.view.tabs.common.TabPanel;

public class CyclicCellularAutomatonContext implements AppContext {

    @Getter
    private final CyclicCellularAutomatonCanvas canvas;

    @Delegate
    @Getter
    private final SignalSlotDispatcher signalSlotDispatcher;

    @Getter
    private final CyclicCellularAutomatonTab tab;

    @Getter
    private CyclicCellularAutomatonControllerThread controller;

    public CyclicCellularAutomatonContext(
        CyclicCellularAutomatonTab tab,
        CyclicCellularAutomatonControllerThread controllerThread,
        CyclicCellularAutomatonCanvas canvas
    ) {
        this.tab = tab;
        this.controller = controllerThread;
        this.canvas = canvas;
        this.signalSlotDispatcher = new SignalSlotDispatcherImpl();
    }

    @Override
    public ControllerThread getControllerThread() {
        return controller;
    }

    @Override
    public TabPanel getTabPanel() {
        return tab;
    }

    @Override
    public AppType getAppType() {
        return AppType.CYCLIC_CELLULAR_AUTOMATON;
    }

    @Override
    public Stepper getStepper() {
        return canvas;
    }

    public CyclicCellularAutomatonControllerThread stopController(ComputerKurzweilApplicationContext ctx) {
        this.controller.exit();
        this.controller = new CyclicCellularAutomatonControllerThread(ctx);
        return this.controller;
    }
}
