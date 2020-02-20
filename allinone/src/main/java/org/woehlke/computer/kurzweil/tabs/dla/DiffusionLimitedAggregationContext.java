package org.woehlke.computer.kurzweil.tabs.dla;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.tabs.TabModel;
import org.woehlke.computer.kurzweil.tabs.TabType;
import org.woehlke.computer.kurzweil.commons.tabs.TabContext;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;

import static java.lang.Thread.State.NEW;
import static org.woehlke.computer.kurzweil.tabs.TabType.DIFFUSION_LIMITED_AGGREGATION;


@Log4j2
@Getter
@ToString(exclude={"canvas","controller","tab","ctx"})
@EqualsAndHashCode(exclude={"canvas","controller","tab","ctx"})
public class DiffusionLimitedAggregationContext implements TabContext {

    private final TabType tabType = DIFFUSION_LIMITED_AGGREGATION;

    private DiffusionLimitedAggregationController controller;

    private final DiffusionLimitedAggregationCanvas canvas;
    private final DiffusionLimitedAggregationTab tab;
    private final ComputerKurzweilApplicationContext ctx;

    public DiffusionLimitedAggregationContext(
        DiffusionLimitedAggregationTab tab
    ) {
        this.tab = tab;
        this.ctx = this.tab.getCtx();
        this.canvas = new DiffusionLimitedAggregationCanvas(this);
        startController();
    }

    @Override
    public void stopController() {
        this.controller.exit();
        this.controller = new DiffusionLimitedAggregationController(this);
    }

    @Override
    public void startController() {
        if(this.controller == null){
            this.controller = new DiffusionLimitedAggregationController(this);
        } else {
            if(this.controller.getState() != NEW){
                this.stopController();
            }
        }
    }

    @Override
    public TabModel getStepper() {
        return canvas;
    }
}
