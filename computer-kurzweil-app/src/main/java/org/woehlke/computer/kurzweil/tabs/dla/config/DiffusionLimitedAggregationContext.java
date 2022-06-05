package org.woehlke.computer.kurzweil.tabs.dla.config;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.tabs.TabContext;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilContext;
import org.woehlke.computer.kurzweil.tabs.dla.canvas.DiffusionLimitedAggregationCanvas;
import org.woehlke.computer.kurzweil.tabs.dla.model.DiffusionLimitedAggregationModel;
import org.woehlke.computer.kurzweil.tabs.dla.DiffusionLimitedAggregationTab;
import org.woehlke.computer.kurzweil.tabs.dla.control.DiffusionLimitedAggregationController;

import java.util.concurrent.ForkJoinTask;

import static java.lang.Thread.State.NEW;


@Log
@Getter
@ToString(exclude={"canvas","controller","tab","ctx"})
@EqualsAndHashCode(exclude={"canvas","controller","tab","ctx"},callSuper = false)
public class DiffusionLimitedAggregationContext extends ForkJoinTask<Void> implements TabContext, DiffusionLimitedAggregation {

    private static final long serialVersionUID = 7526471155622776147L;

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
        this.controller.start();
    }

    @Override
    public Void getRawResult() {
        return null;
    }

    @Override
    protected void setRawResult(Void value) {

    }

    @Override
    public boolean exec() {
        this.tab.repaint();
        return true;
    }
}
