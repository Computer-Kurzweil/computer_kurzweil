package org.woehlke.computer.kurzweil.tabs.cca;

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
@ToString(callSuper = true, exclude = {"tab"})
@EqualsAndHashCode(exclude = {"tab"},callSuper = false)
public class CyclicCellularAutomatonContext extends ForkJoinTask<Void> implements TabContext, CyclicCellularAutomaton {

    private final ComputerKurzweilContext ctx;
    private final CyclicCellularAutomatonCanvas canvas;
    private final CyclicCellularAutomatonTab tab;
    private final CyclicCellularAutomatonModel tabModel;
    private CyclicCellularAutomatonController controller;

    public CyclicCellularAutomatonContext(
        CyclicCellularAutomatonTab tab
    ) {
        this.tab = tab;
        this.ctx = tab.getCtx();
        this.canvas = new CyclicCellularAutomatonCanvas( this);
        this.tabModel = this.canvas.getCyclicCellularAutomatonModel();
        this.controller = new CyclicCellularAutomatonController(this);
    }

    @Override
    public void stopController() {
        this.controller.exit();
        this.controller = new CyclicCellularAutomatonController(this);
    }

    @Override
    public void startController() {
        if(this.controller == null){
            this.controller = new CyclicCellularAutomatonController(this);
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
