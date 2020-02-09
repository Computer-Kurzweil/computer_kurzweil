package org.woehlke.computer.kurzweil.apps.evolution.ctx;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.apps.AppType;
import org.woehlke.computer.kurzweil.apps.cca.control.CyclicCellularAutomatonControllerThread;
import org.woehlke.computer.kurzweil.apps.evolution.control.SimulatedEvolutionControllerThread;
import org.woehlke.computer.kurzweil.apps.evolution.model.SimulatedEvolutionState;
import org.woehlke.computer.kurzweil.apps.evolution.model.world.SimulatedEvolutionWorld;
import org.woehlke.computer.kurzweil.apps.evolution.view.SimulatedEvolutionCanvas;
import org.woehlke.computer.kurzweil.commons.AppContext;
import org.woehlke.computer.kurzweil.commons.ControllerThread;
import org.woehlke.computer.kurzweil.commons.Stepper;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.trashcan.signals.SignalSlotDispatcher;
import org.woehlke.computer.kurzweil.trashcan.signals.SignalSlotDispatcherImpl;
import org.woehlke.computer.kurzweil.view.tabs.SimulatedEvolutionTab;
import org.woehlke.computer.kurzweil.view.tabs.common.TabPanel;

@Log
@Getter
public class SimulatedEvolutionStateService implements AppContext {

    private SimulatedEvolutionControllerThread controller;
    private final SimulatedEvolutionState simulatedEvolutionState;
    private final ComputerKurzweilApplicationContext ctx;
    private final SimulatedEvolutionTab tab;
    private final SimulatedEvolutionWorld world;
    private final SignalSlotDispatcher signalSlotDispatcher;
    private final SimulatedEvolutionCanvas canvas;

    public SimulatedEvolutionStateService(
        SimulatedEvolutionTab tab,
        SimulatedEvolutionCanvas canvas
    ) {
        this.tab = tab;
        this.canvas = canvas;
        this.ctx = this.canvas.getCtx();
        this.controller = new SimulatedEvolutionControllerThread(this);
        this.signalSlotDispatcher = new SignalSlotDispatcherImpl();
        this.simulatedEvolutionState = new SimulatedEvolutionState();
        this.world = new SimulatedEvolutionWorld(this.ctx);
        createNewState();
    }

    private void createNewState(){
        int foodPerDay = this.ctx.getProperties().getEvolution().getFood().getFoodPerDay();
        int foodPerDayGardenOfEden = this.ctx.getProperties().getEvolution().getGardenOfEden().getFoodPerDay();
        boolean gardenOfEdenEnabled = this.ctx.getProperties().getEvolution().getGardenOfEden().getGardenOfEdenEnabled();
        this.simulatedEvolutionState.setFoodPerDay(foodPerDay);
        this.simulatedEvolutionState.setFoodPerDayGardenOfEden(foodPerDayGardenOfEden);
        this.simulatedEvolutionState.setGardenOfEdenEnabled(gardenOfEdenEnabled);
    }

    public void increaseFoodPerDay() {
        simulatedEvolutionState.increaseFoodPerDay();
    }

    public void decreaseFoodPerDay(){
        simulatedEvolutionState.decreaseFoodPerDay();
    }

    public void toggleGardenOfEden() {
        simulatedEvolutionState.toggleGardenOfEden();
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
        return AppType.SIMULATED_EVOLUTION;
    }

    @Override
    public Stepper getStepper() {
        return world;
    }

    @Override
    public void step() {
        this.world.step();
    }

    @Override
    public void update() {
        this.world.update();
    }

    @Override
    public void start() {
        this.world.start();
        this.startController();
    }

    @Override
    public void stop() {
        this.stopController();
        this.world.stop();
    }

    public void stopController() {
        this.controller.exit();
        this.controller = null;
        this.controller = new SimulatedEvolutionControllerThread(this);
    }

    public void startController() {
        if(this.controller == null){
            this.controller = new SimulatedEvolutionControllerThread(this);
        }
        switch (this.controller.getState()){
                case NEW:
                case RUNNABLE:
                    break;
                default:
                    this.stopController();
                    break;
        }
    }
}
