package org.woehlke.computer.kurzweil.tabs.evolution;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.tabs.TabType;
import org.woehlke.computer.kurzweil.tabs.evolution.model.SimulatedEvolution;
import org.woehlke.computer.kurzweil.tabs.evolution.model.SimulatedEvolutionModel;
import org.woehlke.computer.kurzweil.commons.tabs.TabContext;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.woehlke.computer.kurzweil.tabs.TabType.SIMULATED_EVOLUTION;

@Log
@Getter
public class SimulatedEvolutionContext implements TabContext, ActionListener {

    private final TabType tabType = SIMULATED_EVOLUTION;

    private SimulatedEvolutionController controller;

    private final SimulatedEvolution simulatedEvolution;
    private final ComputerKurzweilApplicationContext ctx;
    private final SimulatedEvolutionTab tab;
    private final SimulatedEvolutionCanvas canvas;
    private final SimulatedEvolutionModel stepper;

    public SimulatedEvolutionContext(
        SimulatedEvolutionTab tab
    ) {
        this.tab = tab;
        this.ctx = this.tab.getCtx();
        this.canvas = new SimulatedEvolutionCanvas(this);
        this.stepper = this.canvas.getWorld();
        this.controller = new SimulatedEvolutionController(this);
        this.simulatedEvolution = new SimulatedEvolution();
        createNewState();
    }

    private void createNewState(){
        int foodPerDay = this.ctx.getProperties().getEvolution().getFood().getFoodPerDay();
        int foodPerDayGardenOfEden = this.ctx.getProperties().getEvolution().getGardenOfEden().getFoodPerDay();
        boolean gardenOfEdenEnabled = this.ctx.getProperties().getEvolution().getGardenOfEden().getGardenOfEdenEnabled();
        this.simulatedEvolution.setFoodPerDay(foodPerDay);
        this.simulatedEvolution.setFoodPerDayGardenOfEden(foodPerDayGardenOfEden);
        this.simulatedEvolution.setGardenOfEdenEnabled(gardenOfEdenEnabled);
    }

    public void increaseFoodPerDay() {
        simulatedEvolution.increaseFoodPerDay();
    }

    public void decreaseFoodPerDay(){
        simulatedEvolution.decreaseFoodPerDay();
    }

    public void toggleGardenOfEden() {
        simulatedEvolution.toggleGardenOfEden();
    }

    @Override
    public void start() {
        this.startController();
    }

    @Override
    public void stop() {
        this.stopController();
    }

    public void stopController() {
        this.controller.exit();
        this.controller = null;
        this.controller = new SimulatedEvolutionController(this);
    }

    public void startController() {
        if(this.controller == null){
            this.controller = new SimulatedEvolutionController(this);
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
