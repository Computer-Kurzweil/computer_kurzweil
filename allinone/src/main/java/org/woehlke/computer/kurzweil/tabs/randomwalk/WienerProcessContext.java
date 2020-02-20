package org.woehlke.computer.kurzweil.tabs.randomwalk;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.commons.tabs.TabContext;
import org.woehlke.computer.kurzweil.tabs.TabType;

import static org.woehlke.computer.kurzweil.tabs.TabType.CYCLIC_CELLULAR_AUTOMATON;

@Log
@Getter
@ToString(callSuper = true, exclude = {"tab"})
@EqualsAndHashCode(exclude = {"tab"})
public class WienerProcessContext implements TabContext {

    private final TabType tabType = CYCLIC_CELLULAR_AUTOMATON;

    private final ComputerKurzweilApplicationContext ctx;
    private final WienerProcessCanvas canvas;
    private final WienerProcessTab tab;
    private WienerProcessController controller;

    public WienerProcessContext(
        WienerProcessTab tab
    ) {
        this.tab = tab;
        this.ctx = tab.getCtx();
        this.canvas = new WienerProcessCanvas( this);
        this.controller = new WienerProcessController(this);
    }

    @Override
    public WienerProcessCanvas getStepper() {
        return this.canvas;
    }

    public void stopController() {
        this.controller.exit();
        this.controller = new WienerProcessController(this);
    }

    public void startController() {
        if(this.controller == null){
            this.controller = new WienerProcessController(this);
        } else {
            Thread.State controllerState = this.controller.getState();
            switch (controllerState){
                case NEW:
                //case RUNNABLE:
                    break;
                default:
                    this.stopController();
                    break;
            }
        }
    }

}
