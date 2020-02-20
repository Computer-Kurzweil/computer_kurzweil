package org.woehlke.computer.kurzweil.tabs.cca;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.tabs.TabType;
import org.woehlke.computer.kurzweil.commons.tabs.TabContext;

import static java.lang.Thread.State.NEW;
import static org.woehlke.computer.kurzweil.tabs.TabType.CYCLIC_CELLULAR_AUTOMATON;

@Log
@Getter
@ToString(callSuper = true, exclude = {"tab"})
@EqualsAndHashCode(exclude = {"tab"})
public class CyclicCellularAutomatonContext implements TabContext {

    private final TabType tabType = CYCLIC_CELLULAR_AUTOMATON;

    private final ComputerKurzweilApplicationContext ctx;
    private final CyclicCellularAutomatonCanvas canvas;
    private final CyclicCellularAutomatonTab tab;
    private CyclicCellularAutomatonController controller;

    public CyclicCellularAutomatonContext(
        CyclicCellularAutomatonTab tab
    ) {
        this.tab = tab;
        this.ctx = tab.getCtx();
        this.canvas = new CyclicCellularAutomatonCanvas( this);
        this.controller = new CyclicCellularAutomatonController(this);
    }

    @Override
    public CyclicCellularAutomatonCanvas getStepper() {
        return this.canvas;
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
}
