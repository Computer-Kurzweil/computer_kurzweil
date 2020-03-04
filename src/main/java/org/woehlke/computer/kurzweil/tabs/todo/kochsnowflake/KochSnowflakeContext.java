package org.woehlke.computer.kurzweil.tabs.todo.kochsnowflake;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilContext;
import org.woehlke.computer.kurzweil.commons.tabs.TabContext;
import org.woehlke.computer.kurzweil.tabs.TabType;

import static java.lang.Thread.State.NEW;
import static org.woehlke.computer.kurzweil.tabs.TabType.CYCLIC_CELLULAR_AUTOMATON;

@Log4j2
@Getter
@ToString(callSuper = true, exclude = {"tab"})
@EqualsAndHashCode(exclude = {"tab"})
public class KochSnowflakeContext implements TabContext {

    private final TabType tabType = CYCLIC_CELLULAR_AUTOMATON;

    private final ComputerKurzweilContext ctx;
    private final KochSnowflakeCanvas canvas;
    private final KochSnowflakeTab tab;
    private KochSnowflakeController controller;

    public KochSnowflakeContext(
        KochSnowflakeTab tab
    ) {
        this.tab = tab;
        this.ctx = tab.getCtx();
        this.canvas = new KochSnowflakeCanvas( this);
        this.controller = new KochSnowflakeController(this);
    }

    @Override
    public KochSnowflakeCanvas getTabModel() {
        return this.canvas;
    }

    @Override
    public void stopController() {
        this.controller.exit();
        this.controller = new KochSnowflakeController(this);
    }

    @Override
    public void startController() {
        if(this.controller == null){
            this.controller = new KochSnowflakeController(this);
        } else {
            if(this.controller.getState() != NEW){
                this.stopController();
            }
        }
    }
}
