package org.woehlke.computer.kurzweil.apps.cca.ctx;

import lombok.Delegate;
import lombok.Getter;
import lombok.Setter;
import org.woehlke.computer.kurzweil.apps.AppType;
import org.woehlke.computer.kurzweil.apps.cca.control.CyclicCellularAutomatonControllerThread;
import org.woehlke.computer.kurzweil.apps.cca.model.CyclicCellularAutomatonColorScheme;
import org.woehlke.computer.kurzweil.apps.cca.view.CyclicCellularAutomatonCanvas;
import org.woehlke.computer.kurzweil.control.ctx.ControllerThread;
import org.woehlke.computer.kurzweil.control.ctx.Stepper;
import org.woehlke.computer.kurzweil.control.ctx.AppContext;
import org.woehlke.computer.kurzweil.control.signals.SignalSlotDispatcher;
import org.woehlke.computer.kurzweil.control.signals.SignalSlotDispatcherImpl;
import org.woehlke.computer.kurzweil.view.tabs.CyclicCellularAutomatonTab;
import org.woehlke.computer.kurzweil.view.tabs.common.TabPanel;

public class CyclicCellularAutomatonContext implements AppContext {

    @Getter @Setter
    private CyclicCellularAutomatonControllerThread cyclicCellularAutomatonControllerThread;

    @Getter @Setter
    private CyclicCellularAutomatonTab tabCyclicCellularAutomaton;

    @Getter @Setter
    private CyclicCellularAutomatonCanvas stepperCyclicCellularAutomatonCanvas;

    @Delegate
    @Getter
    private final SignalSlotDispatcher signalSlotDispatcher;

    @Getter private final CyclicCellularAutomatonColorScheme colorScheme;

    public CyclicCellularAutomatonContext() {
        this.colorScheme = new CyclicCellularAutomatonColorScheme();
        this.signalSlotDispatcher = new SignalSlotDispatcherImpl();
    }

    @Override
    public ControllerThread getControllerThread() {
        return cyclicCellularAutomatonControllerThread;
    }

    @Override
    public TabPanel getTabPanel() {
        return tabCyclicCellularAutomaton;
    }

    @Override
    public AppType gaetAppType() {
        return AppType.CYCLIC_CELLULAR_AUTOMATON;
    }

    @Override
    public Stepper getStepper() {
        return stepperCyclicCellularAutomatonCanvas;
    }
}
