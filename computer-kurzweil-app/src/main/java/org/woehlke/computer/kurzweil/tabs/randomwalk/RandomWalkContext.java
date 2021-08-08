package org.woehlke.computer.kurzweil.tabs.randomwalk;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilContext;
import org.woehlke.computer.kurzweil.commons.tabs.TabContext;

import java.util.concurrent.ForkJoinTask;

import static java.lang.Thread.State.NEW;

@Log4j2
@Getter
@ToString(exclude = {"tab"})
@EqualsAndHashCode(exclude = {"tab"},callSuper = false)
public class RandomWalkContext extends ForkJoinTask<Void> implements TabContext, RandomWalk {

    private static final long serialVersionUID = 7526471155622776147L;

    private final ComputerKurzweilContext ctx;
    private final RandomWalkCanvas canvas;
    private final RandomWalkModel tabModel;
    private final RandomWalkTab tab;
    private RandomWalkController controller;

    public RandomWalkContext(
        RandomWalkTab tab
    ) {
        this.tab = tab;
        this.ctx = tab.getCtx();
        this.canvas = new RandomWalkCanvas( this);
        this.tabModel = this.canvas.getTabModel();
        this.controller = new RandomWalkController(this);
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
