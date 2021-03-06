package org.woehlke.computer.kurzweil.tabs.dla;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.tabs.TabContext;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilContext;

import java.util.concurrent.ForkJoinTask;

import static java.lang.Thread.State.NEW;


@Log4j2
@Getter
@ToString(exclude={"canvas","controller","tab","ctx"})
@EqualsAndHashCode(exclude={"canvas","controller","tab","ctx"},callSuper = false)
public class DiffusionLimitedAggregationContext extends ForkJoinTask<Void> implements TabContext, DiffusionLimitedAggregation {

    private DiffusionLimitedAggregationController controller;

    private final DiffusionLimitedAggregationCanvas canvas;
    private final DiffusionLimitedAggregationModel tabModel;
    private final DiffusionLimitedAggregationTab tab;
    private final ComputerKurzweilContext ctx;

    public DiffusionLimitedAggregationContext(
        DiffusionLimitedAggregationTab tab
    ) {
        this.tab = tab;
        this.ctx = this.tab.getCtx();
        this.canvas = new DiffusionLimitedAggregationCanvas(this);
        this.tabModel = this.canvas.getTabModel();
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
    public Void getRawResult() {
        return null;
    }

    @Override
    protected void setRawResult(Void value) {

    }

    @Override
    protected boolean exec() {
        this.tab.repaint();
        return true;
    }
}
