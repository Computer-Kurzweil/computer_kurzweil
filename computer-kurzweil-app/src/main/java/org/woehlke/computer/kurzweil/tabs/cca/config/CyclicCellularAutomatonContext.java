package org.woehlke.computer.kurzweil.tabs.cca.config;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilContext;
import org.woehlke.computer.kurzweil.commons.tabs.TabContext;
import org.woehlke.computer.kurzweil.tabs.cca.CyclicCellularAutomaton;
import org.woehlke.computer.kurzweil.tabs.cca.CyclicCellularAutomatonTab;
import org.woehlke.computer.kurzweil.tabs.cca.canvas.CyclicCellularAutomatonCanvas;
import org.woehlke.computer.kurzweil.tabs.cca.control.CyclicCellularAutomatonController;
import org.woehlke.computer.kurzweil.tabs.cca.model.CyclicCellularAutomatonModel;

import java.util.concurrent.ForkJoinTask;

import static java.lang.Thread.State.NEW;

@Log
@Getter
@ToString(callSuper = true, exclude = {"tab"})
@EqualsAndHashCode(exclude = {"tab"},callSuper = false)
public class CyclicCellularAutomatonContext extends ForkJoinTask<Void> implements TabContext, CyclicCellularAutomaton {

    private static final long serialVersionUID = 7526471155622776147L;

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
    public boolean exec() {
        this.tab.repaint();
        return true;
    }
}
