package org.woehlke.computer.kurzweil.tabs.randomwalk;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilContext;
import org.woehlke.computer.kurzweil.commons.tabs.TabContext;
import org.woehlke.computer.kurzweil.tabs.TabType;

import static java.lang.Thread.State.NEW;

@Log4j2
@Getter
@ToString(callSuper = true, exclude = {"tab"})
@EqualsAndHashCode(exclude = {"tab"})
public class RandomWalkContext implements TabContext, RandomWalk {

    private final ComputerKurzweilContext ctx;
    private final RandomWalkCanvas canvas;
    private final RandomWalkTab tab;
    private RandomWalkController controller;

    public RandomWalkContext(
        RandomWalkTab tab
    ) {
        this.tab = tab;
        this.ctx = tab.getCtx();
        this.canvas = new RandomWalkCanvas( this);
        this.controller = new RandomWalkController(this);
    }

    @Override
    public RandomWalkCanvas getTabModel() {
        return this.canvas;
    }

    @Override
    public void stopController() {
        this.controller.exit();
        this.controller = new RandomWalkController(this);
    }

    @Override
    public void startController() {
        if(this.controller == null){
            this.controller = new RandomWalkController(this);
        } else {
            if(this.controller.getState() != NEW){
                this.stopController();
            }
        }
    }

}
