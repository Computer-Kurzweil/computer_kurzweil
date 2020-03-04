package org.woehlke.computer.kurzweil.tabs.turmite;

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
public class TurmiteContext implements TabContext, Turmite {

    private final ComputerKurzweilContext ctx;
    private final TurmiteCanvas canvas;
    private final TurmiteTab tab;
    private TurmiteController controller;

    public TurmiteContext(
        TurmiteTab tab
    ) {
        this.tab = tab;
        this.ctx = tab.getCtx();
        this.canvas = new TurmiteCanvas( this);
        this.controller = new TurmiteController(this);
    }

    @Override
    public TurmiteCanvas getTabModel() {
        return this.canvas;
    }

    @Override
    public void stopController() {
        this.controller.exit();
        this.controller = new TurmiteController(this);
    }

    @Override
    public void startController() {
        if(this.controller == null){
            this.controller = new TurmiteController(this);
        } else {
            if(this.controller.getState() != NEW){
                this.stopController();
            }
        }
    }
}
