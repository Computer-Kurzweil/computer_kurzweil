package org.woehlke.computer.kurzweil.tabs.simulatedevolution;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.tabs.TabContext;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilContext;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.world.SimulatedEvolutionParameter;

import static java.lang.Thread.State.NEW;

@Log4j2
@Getter
@ToString(callSuper = true, exclude={"ctx","controller","tab"})
@EqualsAndHashCode(exclude={"ctx","controller","tab"})
public class SimulatedEvolutionContext implements TabContext, SimulatedEvolution {

    private final ComputerKurzweilContext ctx;
    private SimulatedEvolutionController controller;
    private final SimulatedEvolutionTab tab;
    private final SimulatedEvolutionParameter simulatedEvolutionParameter;
    @Setter
    private SimulatedEvolutionCanvas canvas;
    @Setter
    private SimulatedEvolutionModel tabModel;

    public SimulatedEvolutionContext(
        SimulatedEvolutionTab tab
    ) {
       this.tab = tab;
       this.ctx = this.tab.getCtx();
        this.controller = new SimulatedEvolutionController(this);
        this.simulatedEvolutionParameter = new SimulatedEvolutionParameter();
        createNewState();
    }

    private void createNewState(){
        int foodPerDay = this.ctx.getProperties().getSimulatedevolution().getFood().getFoodPerDay();
        int foodPerDayGardenOfEden = this.ctx.getProperties().getSimulatedevolution().getGardenOfEden().getFoodPerDay();
        boolean gardenOfEdenEnabled = this.ctx.getProperties().getSimulatedevolution().getGardenOfEden().getGardenOfEdenEnabled();
        this.simulatedEvolutionParameter.setFoodPerDay(foodPerDay);
        this.simulatedEvolutionParameter.setFoodPerDayGardenOfEden(foodPerDayGardenOfEden);
        this.simulatedEvolutionParameter.setGardenOfEdenEnabled(gardenOfEdenEnabled);
    }

    public void increaseFoodPerDay() {
        simulatedEvolutionParameter.increaseFoodPerDay();
    }

    public void decreaseFoodPerDay(){
        simulatedEvolutionParameter.decreaseFoodPerDay();
    }

    public void toggleGardenOfEden() {
        this.simulatedEvolutionParameter.toggleGardenOfEden();
        this.tabModel.toggleGardenOfEden();
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
