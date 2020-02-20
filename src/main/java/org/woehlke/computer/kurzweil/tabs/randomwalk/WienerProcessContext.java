package org.woehlke.computer.kurzweil.tabs.randomwalk;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.commons.tabs.TabContext;
import org.woehlke.computer.kurzweil.tabs.TabType;

import static java.lang.Thread.State.NEW;
import static org.woehlke.computer.kurzweil.tabs.TabType.RANDOM_WALK_WIENER_PROCESS;

@Log4j2
@Getter
@ToString(callSuper = true, exclude = {"tab"})
@EqualsAndHashCode(exclude = {"tab"})
public class WienerProcessContext implements TabContext {

    private final TabType tabType = RANDOM_WALK_WIENER_PROCESS;

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

    @Override
    public void stopController() {
        this.controller.exit();
        this.controller = new WienerProcessController(this);
    }

    @Override
    public void startController() {
        if(this.controller == null){
            this.controller = new WienerProcessController(this);
        } else {
            if(this.controller.getState() != NEW){
                this.stopController();
            }
        }
    }

}
