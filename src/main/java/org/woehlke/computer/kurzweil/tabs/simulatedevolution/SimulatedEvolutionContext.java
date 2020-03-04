package org.woehlke.computer.kurzweil.tabs.simulatedevolution;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.tabs.TabContext;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilContext;

import static java.lang.Thread.State.NEW;

@Log4j2
@Getter
@ToString(callSuper = true, exclude={"ctx","controller","tab"})
@EqualsAndHashCode(exclude={"ctx","controller","tab"})
public class SimulatedEvolutionContext implements TabContext, SimulatedEvolution {

    private final ComputerKurzweilContext ctx;
    private final SimulatedEvolutionTab tab;
    private final SimulatedEvolutionCanvas canvas;
    private final SimulatedEvolutionModel tabModel;

    private SimulatedEvolutionController controller;

    public SimulatedEvolutionContext(
        SimulatedEvolutionTab tab,
        ComputerKurzweilContext ctx
    ) {
       this.tab = tab;
       this.ctx = ctx;
       this.canvas = new SimulatedEvolutionCanvas(   this );
       this.tabModel = this.canvas.getTabModel();
       this.controller = new SimulatedEvolutionController(this);
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
