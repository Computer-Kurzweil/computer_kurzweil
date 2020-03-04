package org.woehlke.computer.kurzweil.tabs.todo.tetris;

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
public class TetrisContext implements TabContext, Tetris {

    private final ComputerKurzweilContext ctx;
    private final TetrisCanvas canvas;
    private final TetrisTab tab;
    private TetrisController controller;

    public TetrisContext(
        TetrisTab tab
    ) {
        this.tab = tab;
        this.ctx = tab.getCtx();
        this.canvas = new TetrisCanvas( this);
        this.controller = new TetrisController(this);
    }

    @Override
    public TetrisCanvas getTabModel() {
        return this.canvas;
    }

    @Override
    public void stopController() {
        this.controller.exit();
        this.controller = new TetrisController(this);
    }

    @Override
    public void startController() {
        if(this.controller == null){
            this.controller = new TetrisController(this);
        } else {
            if(this.controller.getState() != NEW){
                this.stopController();
            }
        }
    }
}
