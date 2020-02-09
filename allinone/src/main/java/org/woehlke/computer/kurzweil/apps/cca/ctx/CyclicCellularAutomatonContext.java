package org.woehlke.computer.kurzweil.apps.cca.ctx;

import lombok.Getter;
import org.woehlke.computer.kurzweil.apps.AppType;
import org.woehlke.computer.kurzweil.apps.cca.control.CyclicCellularAutomatonControllerThread;
import org.woehlke.computer.kurzweil.apps.cca.view.CyclicCellularAutomatonCanvas;
import org.woehlke.computer.kurzweil.commons.ControllerThread;
import org.woehlke.computer.kurzweil.commons.Stepper;
import org.woehlke.computer.kurzweil.commons.AppContext;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.view.tabs.CyclicCellularAutomatonTab;
import org.woehlke.computer.kurzweil.view.tabs.common.TabPanel;

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

    public CyclicCellularAutomatonControllerThread stopController(ComputerKurzweilApplicationContext ctx) {
        this.controller.exit();
        this.controller = new CyclicCellularAutomatonControllerThread(ctx);
        this.controller.setAppCtx(this);
        return this.controller;
    }

    public CyclicCellularAutomatonControllerThread startController(ComputerKurzweilApplicationContext ctx) {
        if(this.controller == null){
            this.controller = new CyclicCellularAutomatonControllerThread(ctx);
            this.controller.setAppCtx(this);
        } else {
            Thread.State controllerState = this.controller.getState();
            switch (controllerState){
                case NEW:
                case RUNNABLE:
                    break;
                default:
                    this.controller = this.stopController(ctx);
                    break;
            }
        }
        return this.controller;
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
        this.controller = this.startController(this.getTab().getCtx());
        this.canvas.start();
        this.controller.start();
    }

    @Override
    public void stop() {
        this.canvas.stop();
        this.controller = this.stopController(this.getTab().getCtx());
        this.controller.start();
    }
}
