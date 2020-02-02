package org.woehlke.computer.kurzweil.apps.evolution.ctx;

import lombok.Delegate;
import lombok.Getter;
import lombok.Setter;
import org.woehlke.computer.kurzweil.apps.AppType;
import org.woehlke.computer.kurzweil.apps.evolution.control.SimulatedEvolutionControllerThread;
import org.woehlke.computer.kurzweil.apps.evolution.model.world.SimulatedEvolutionWorld;
import org.woehlke.computer.kurzweil.control.ctx.AppContext;
import org.woehlke.computer.kurzweil.control.ctx.ControllerThread;
import org.woehlke.computer.kurzweil.control.ctx.Stepper;
import org.woehlke.computer.kurzweil.control.signals.SignalSlotDispatcher;
import org.woehlke.computer.kurzweil.control.signals.SignalSlotDispatcherImpl;
import org.woehlke.computer.kurzweil.view.tabs.SimulatedEvolutionTab;
import org.woehlke.computer.kurzweil.view.tabs.common.TabPanel;

public class SimulatedEvolutionContext implements AppContext {

    @Getter @Setter
    private SimulatedEvolutionControllerThread simulatedEvolutionControllerThread;

    @Getter @Setter
    private SimulatedEvolutionTab simulatedEvolutionTab;

    @Getter @Setter
    private SimulatedEvolutionWorld simulatedEvolutionWorld;

    @Delegate
    @Getter
    private final SignalSlotDispatcher signalSlotDispatcher;

    public SimulatedEvolutionContext() {
        this.signalSlotDispatcher = new SignalSlotDispatcherImpl();
    }

    @Override
    public ControllerThread getControllerThread() {
        return simulatedEvolutionControllerThread;
    }

    @Override
    public TabPanel getTabPanel() {
        return simulatedEvolutionTab;
    }

    @Override
    public AppType gaetAppType() {
        return AppType.SIMULATED_EVOLUTION;
    }

    @Override
    public Stepper getStepper() {
        return simulatedEvolutionWorld;
    }
}
