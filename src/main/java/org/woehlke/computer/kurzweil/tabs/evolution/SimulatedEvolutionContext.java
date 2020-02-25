package org.woehlke.computer.kurzweil.tabs.evolution;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.tabs.TabType;
import org.woehlke.computer.kurzweil.commons.tabs.TabContext;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilContext;
import org.woehlke.computer.kurzweil.tabs.evolution.canvas.SimulatedEvolutionCanvas;
import org.woehlke.computer.kurzweil.tabs.evolution.model.SimulatedEvolution;

import static java.lang.Thread.State.NEW;
import static org.woehlke.computer.kurzweil.tabs.TabType.SIMULATED_EVOLUTION;

@Log4j2
@Getter
@ToString(callSuper = true, exclude={"ctx","controller","tab"})
@EqualsAndHashCode(exclude={"ctx","controller","tab"})
public class SimulatedEvolutionContext implements TabContext {

    private final TabType tabType = SIMULATED_EVOLUTION;
    private final ComputerKurzweilContext ctx;
    private SimulatedEvolutionController controller;
    private final SimulatedEvolutionTab tab;
    private final SimulatedEvolution simulatedEvolution;
    @Setter
    private SimulatedEvolutionCanvas canvas;
    @Setter
    private SimulatedEvolutionWorld stepper;

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

    @Override
    public void stopController() {
        this.controller.exit();
        this.controller = null;
        this.controller = new SimulatedEvolutionController(this);
    }

    @Override
    public void startController() {
        if(this.controller == null){
            this.controller = new SimulatedEvolutionController(this);
        } else {
            if(this.controller.getState() != NEW){
                this.stopController();
            }
        }
    }
}
