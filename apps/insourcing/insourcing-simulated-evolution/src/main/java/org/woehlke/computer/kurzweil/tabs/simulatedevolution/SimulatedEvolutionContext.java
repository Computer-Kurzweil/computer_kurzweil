package org.woehlke.computer.kurzweil.tabs.simulatedevolution;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilContext;
import org.woehlke.computer.kurzweil.commons.tabs.TabContext;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.WorldPoint;

import java.io.Serializable;
import java.util.concurrent.ForkJoinTask;

import static java.lang.Thread.State.NEW;

@Log
@Getter
@ToString(callSuper = false, exclude={"ctx","controller","tab"})
@EqualsAndHashCode(callSuper = false, exclude={"ctx","controller","tab"})
public class SimulatedEvolutionContext extends ForkJoinTask<Void> implements TabContext, SimulatedEvolution, Serializable {

    private static final long serialVersionUID = 7526471155622776147L;

    private final ComputerKurzweilContext ctx;
    private final SimulatedEvolutionTab tab;
    private final SimulatedEvolutionCanvas canvas;
    private final SimulatedEvolutionModel tabModel;

    @Setter
    private SimulatedEvolutionController controller;

    public SimulatedEvolutionContext(
        SimulatedEvolutionTab tab,
        ComputerKurzweilContext ctx
    ) {
       this.tab = tab;
       this.ctx = ctx;
        int scale = 2;
        int width = 320 * scale;
        int height = 234 * scale;
        WorldPoint worldDimensions = new WorldPoint(width,height);
       this.canvas = new SimulatedEvolutionCanvas(  worldDimensions );
       this.tabModel = this.canvas.getTabModel();
       this.controller = new SimulatedEvolutionController();
    }

    @Override
    public void stopController() {
        this.controller.exit();
        this.controller = null;
        this.controller = new SimulatedEvolutionController();
    }

    @Override
    public void startController() {
        if(this.controller == null){
            this.controller = new SimulatedEvolutionController();
        } else {
            if(this.controller.getState() != NEW){
                this.stopController();
            }
        }
    }

    @Override
    public Void getRawResult() {
        return null;
    }

    @Override
    protected void setRawResult(Void value) {

    }

    @Override
    protected boolean exec() {
        this.tab.update();
        this.tab.repaint();
        return true;
    }
}
