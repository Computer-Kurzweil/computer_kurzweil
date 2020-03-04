package org.woehlke.computer.kurzweil.tabs.todo.wator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilContext;
import org.woehlke.computer.kurzweil.commons.tabs.TabContext;

import static java.lang.Thread.State.NEW;

@Log4j2
@Getter
@ToString(callSuper = true, exclude = {"tab"})
@EqualsAndHashCode(exclude = {"tab"})
public class WaTorContext implements TabContext, WaTor {

    private final ComputerKurzweilContext ctx;
    private final WaTorCanvas canvas;
    private final WaTorTab tab;
    private WaTorController controller;

    public WaTorContext(
        WaTorTab tab
    ) {
        this.tab = tab;
        this.ctx = tab.getCtx();
        this.canvas = new WaTorCanvas( this);
        this.controller = new WaTorController(this);
    }

    @Override
    public WaTorCanvas getTabModel() {
        return this.canvas;
    }

    @Override
    public void stopController() {
        this.controller.exit();
        this.controller = new WaTorController(this);
    }

    @Override
    public void startController() {
        if(this.controller == null){
            this.controller = new WaTorController(this);
        } else {
            if(this.controller.getState() != NEW){
                this.stopController();
            }
        }
    }
}
