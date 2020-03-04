package org.woehlke.computer.kurzweil.tabs.samegame;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilContext;
import org.woehlke.computer.kurzweil.commons.tabs.TabContext;
import org.woehlke.computer.kurzweil.tabs.TabType;

import static java.lang.Thread.State.NEW;
import static org.woehlke.computer.kurzweil.tabs.TabType.SAME_GAME;

@Log4j2
@Getter
@ToString(callSuper = true, exclude = {"tab"})
@EqualsAndHashCode(exclude = {"tab"})
public class SameGameContext implements TabContext {

    private final TabType tabType = SAME_GAME;

    private final ComputerKurzweilContext ctx;
    private final SameGameCanvas canvas;
    private final SameGameTab tab;
    private SameGameController controller;

    public SameGameContext(
        SameGameTab tab
    ) {
        this.tab = tab;
        this.ctx = tab.getCtx();
        this.canvas = new SameGameCanvas( this);
        this.controller = new SameGameController(this);
    }

    @Override
    public SameGameCanvas getTabModel() {
        return this.canvas;
    }

    @Override
    public void stopController() {
        this.controller.exit();
        this.controller = new SameGameController(this);
    }

    @Override
    public void startController() {
        if(this.controller == null){
            this.controller = new SameGameController(this);
        } else {
            if(this.controller.getState() != NEW){
                this.stopController();
            }
        }
    }
}
