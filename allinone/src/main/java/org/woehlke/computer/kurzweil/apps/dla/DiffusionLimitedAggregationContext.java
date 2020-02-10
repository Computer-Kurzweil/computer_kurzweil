package org.woehlke.computer.kurzweil.apps.dla;

import lombok.Getter;
import lombok.Setter;
import org.woehlke.computer.kurzweil.apps.AppType;
import org.woehlke.computer.kurzweil.commons.AppContext;
import org.woehlke.computer.kurzweil.commons.ControllerThread;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.commons.Stepper;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.tabs.DiffusionLimitedAggregationTab;
import org.woehlke.computer.kurzweil.tabs.common.TabPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.woehlke.computer.kurzweil.apps.AppType.DIFFUSION_LIMITED_AGGREGATION;

@Getter
public class DiffusionLimitedAggregationContext implements AppContext, Startable, ActionListener {

    private final AppType appType = DIFFUSION_LIMITED_AGGREGATION;

    @Setter private DiffusionLimitedAggregationControllerThread controller;

    private final DiffusionLimitedAggregationCanvas canvas;
    private final DiffusionLimitedAggregationWorld world;
    private final DiffusionLimitedAggregationTab tab;
    private final ComputerKurzweilApplicationContext ctx;

    public DiffusionLimitedAggregationContext(
        DiffusionLimitedAggregationTab tab
    ) {
        this.tab = tab;
        this.ctx = this.tab.getCtx();
        this.world = new DiffusionLimitedAggregationWorld(this.ctx);
        this.canvas = new DiffusionLimitedAggregationCanvas(this.ctx);
        startController();
    }

    @Override
    public ControllerThread getControllerThread() {
        return controller;
    }

    @Override
    public TabPanel getTabPanel() {
        return tab;
    }

    @Override
    public Stepper getStepper() {
        return world;
    }

    public void stopController() {
        this.controller.exit();
        this.controller = new DiffusionLimitedAggregationControllerThread(this);
    }

    public void startController() {
        if(this.controller == null){
            this.controller = new DiffusionLimitedAggregationControllerThread(this);
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
    public void step() {

    }

    @Override
    public void update() {

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
