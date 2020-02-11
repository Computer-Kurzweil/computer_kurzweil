package org.woehlke.computer.kurzweil.apps.dla;

import lombok.Getter;
import lombok.Setter;
import org.woehlke.computer.kurzweil.apps.TabType;
import org.woehlke.computer.kurzweil.commons.tabs.TabContext;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.woehlke.computer.kurzweil.apps.TabType.DIFFUSION_LIMITED_AGGREGATION;

@Getter
public class DiffusionLimitedAggregationContext implements TabContext, Startable, ActionListener {

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

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == this.getTab().getStartStopButtonsPanel().getStartButton()){
            this.getTab().getStartStopButtonsPanel().getStartButton().setEnabled(false);
            this.getTab().getStartStopButtonsPanel().setEnabled(true);
            this.start();
        }
        if(ae.getSource() == this.getTab().getStartStopButtonsPanel().getStopButton()){
            this.getTab().getStartStopButtonsPanel().getStartButton().setEnabled(true);
            this.getTab().getStartStopButtonsPanel().getStopButton().setEnabled(false);
            this.stop();
        }
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

}
