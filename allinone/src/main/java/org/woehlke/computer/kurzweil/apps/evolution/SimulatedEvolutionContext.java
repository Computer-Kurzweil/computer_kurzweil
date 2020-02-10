package org.woehlke.computer.kurzweil.apps.evolution;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.apps.AppType;
import org.woehlke.computer.kurzweil.apps.evolution.model.SimulatedEvolutionState;
import org.woehlke.computer.kurzweil.apps.evolution.model.SimulatedEvolutionWorld;
import org.woehlke.computer.kurzweil.commons.AppContext;
import org.woehlke.computer.kurzweil.commons.ControllerThread;
import org.woehlke.computer.kurzweil.commons.Stepper;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.trashcan.signals.SignalSlotDispatcher;
import org.woehlke.computer.kurzweil.trashcan.signals.SignalSlotDispatcherImpl;
import org.woehlke.computer.kurzweil.tabs.SimulatedEvolutionTab;
import org.woehlke.computer.kurzweil.tabs.common.TabPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.woehlke.computer.kurzweil.apps.AppType.SIMULATED_EVOLUTION;

@Log
@Getter
public class SimulatedEvolutionContext implements AppContext, ActionListener {

    private final AppType appType = SIMULATED_EVOLUTION;

    private SimulatedEvolutionControllerThread controller;
    private final SimulatedEvolutionState simulatedEvolutionState;
    private final ComputerKurzweilApplicationContext ctx;
    private final SimulatedEvolutionTab tab;
    private final SimulatedEvolutionWorld world;
    private final SimulatedEvolutionCanvas canvas;

    public SimulatedEvolutionContext(
        SimulatedEvolutionTab tab,
        SimulatedEvolutionCanvas canvas
    ) {
        this.tab = tab;
        this.canvas = canvas;
        this.ctx = this.canvas.getCtx();
        this.controller = new SimulatedEvolutionControllerThread(this);
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
        this.canvas.update();
        this.canvas.repaint();
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

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == this.tab.getStartStopButtonsPanel().getStartButton()){
            this.tab.getStartStopButtonsPanel().getStartButton().setEnabled(false);
            this.tab.getStartStopButtonsPanel().getStopButton().setEnabled(true);
            this.start();
        }
        if(ae.getSource() == this.tab.getStartStopButtonsPanel().getStopButton()){
            this.tab.getStartStopButtonsPanel().getStartButton().setEnabled(true);
            this.tab.getStartStopButtonsPanel().getStopButton().setEnabled(false);
            this.stop();
        }
    }
}
