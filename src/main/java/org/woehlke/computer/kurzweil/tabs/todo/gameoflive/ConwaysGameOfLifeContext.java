package org.woehlke.computer.kurzweil.tabs.todo.gameoflive;

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
public class ConwaysGameOfLifeContext implements TabContext, ConwaysGameOfLife {

    private final ComputerKurzweilContext ctx;
    private final ConwaysGameOfLifeCanvas canvas;
    private final ConwaysGameOfLifeTab tab;
    private ConwaysGameOfLifeController controller;

    public ConwaysGameOfLifeContext(
        ConwaysGameOfLifeTab tab
    ) {
        this.tab = tab;
        this.ctx = tab.getCtx();
        this.canvas = new ConwaysGameOfLifeCanvas( this);
        this.controller = new ConwaysGameOfLifeController(this);
    }

    @Override
    public ConwaysGameOfLifeCanvas getTabModel() {
        return this.canvas;
    }

    @Override
    public void stopController() {
        this.controller.exit();
        this.controller = new ConwaysGameOfLifeController(this);
    }

    @Override
    public void startController() {
        if(this.controller == null){
            this.controller = new ConwaysGameOfLifeController(this);
        } else {
            if(this.controller.getState() != NEW){
                this.stopController();
            }
        }
    }
}
