package org.woehlke.computer.kurzweil.tabs.evolution;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.tabs.TabType;
import org.woehlke.computer.kurzweil.commons.tabs.TabContext;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;


import static org.woehlke.computer.kurzweil.tabs.TabType.SIMULATED_EVOLUTION;

@Log
@Getter
public class SimulatedEvolutionContext implements TabContext {

    private final TabType tabType = SIMULATED_EVOLUTION;
    private final SimulatedEvolution simulatedEvolution;
    private final ComputerKurzweilApplicationContext ctx;
    private final SimulatedEvolutionTab tab;

    @Setter
    private SimulatedEvolutionCanvas canvas;

    @Setter
    private SimulatedEvolutionWorld stepper;

    private SimulatedEvolutionController controller;

    public SimulatedEvolutionContext(
        SimulatedEvolutionTab tab
    ) {
       this.tab = tab;
       this.ctx = this.tab.getCtx();
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
        this.simulatedEvolution.toggleGardenOfEden();
        this.stepper.toggleGardenOfEden();
        this.canvas.toggleGardenOfEden();
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

}
