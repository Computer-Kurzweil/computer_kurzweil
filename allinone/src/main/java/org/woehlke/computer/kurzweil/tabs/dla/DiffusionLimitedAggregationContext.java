package org.woehlke.computer.kurzweil.tabs.dla;

import lombok.Getter;
import lombok.Setter;
import org.woehlke.computer.kurzweil.tabs.TabType;
import org.woehlke.computer.kurzweil.commons.tabs.TabContext;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;

import static org.woehlke.computer.kurzweil.tabs.TabType.DIFFUSION_LIMITED_AGGREGATION;

@Getter
public class DiffusionLimitedAggregationContext implements TabContext {

    private final TabType tabType = DIFFUSION_LIMITED_AGGREGATION;

    @Setter private DiffusionLimitedAggregationController controller;

    private final DiffusionLimitedAggregation stepper;
    private final DiffusionLimitedAggregationCanvas canvas;
    private final DiffusionLimitedAggregationTab tab;
    private final ComputerKurzweilApplicationContext ctx;

    public DiffusionLimitedAggregationContext(
        DiffusionLimitedAggregationTab tab
    ) {
        this.tab = tab;
        this.ctx = this.tab.getCtx();
        this.canvas = new DiffusionLimitedAggregationCanvas(this.ctx);
        this.stepper = this.canvas.getStepper();
        startController();
    }

    public void stopController() {
        this.controller.exit();
        this.controller = new DiffusionLimitedAggregationController(this);
    }

    public void startController() {
        if(this.controller == null){
            this.controller = new DiffusionLimitedAggregationController(this);
        } else {
            Thread.State controllerState = this.controller.getState();
            switch (controllerState){
                case NEW:
                case RUNNABLE:
                    break;
                default:
                    this.stopController();
                    break;
            }
        }
    }

}
