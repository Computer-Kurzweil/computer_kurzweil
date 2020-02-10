package org.woehlke.computer.kurzweil.apps.cca;

import lombok.Getter;
import org.woehlke.computer.kurzweil.apps.AppType;
import org.woehlke.computer.kurzweil.commons.ControllerThread;
import org.woehlke.computer.kurzweil.commons.Stepper;
import org.woehlke.computer.kurzweil.commons.AppContext;
import org.woehlke.computer.kurzweil.tabs.CyclicCellularAutomatonTab;
import org.woehlke.computer.kurzweil.tabs.common.TabPanel;

@Getter
public class CyclicCellularAutomatonContext implements AppContext {

    private final CyclicCellularAutomatonCanvas canvas;
    private final CyclicCellularAutomatonTab tab;
    private CyclicCellularAutomatonControllerThread controller;

    public CyclicCellularAutomatonContext(
        CyclicCellularAutomatonTab tab,
        CyclicCellularAutomatonControllerThread controllerThread,
        CyclicCellularAutomatonCanvas canvas
    ) {
        this.tab = tab;
        this.controller = controllerThread;
        this.canvas = canvas;
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
    public AppType getAppType() {
        return AppType.CYCLIC_CELLULAR_AUTOMATON;
    }

    @Override
    public Stepper getStepper() {
        return canvas;
    }

    public void stopController() {
        this.controller.exit();
        this.controller = new CyclicCellularAutomatonControllerThread(this.getTab().getCtx());
        this.controller.setAppCtx(this);
    }

    public void startController() {
        if(this.controller == null){
            this.controller = new CyclicCellularAutomatonControllerThread(this.getTab().getCtx());
            this.controller.setAppCtx(this);
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
    public void step() {
        this.canvas.step();
    }

    @Override
    public void update() {
        this.canvas.update();
        this.canvas.repaint();
    }

    @Override
    public void start() {
        this.startController();
        this.canvas.start();
        this.controller.start();
    }

    @Override
    public void stop() {
        this.canvas.stop();
        this.stopController();
    }
}
