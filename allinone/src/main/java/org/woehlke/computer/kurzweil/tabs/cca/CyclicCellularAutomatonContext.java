package org.woehlke.computer.kurzweil.tabs.cca;

import lombok.Getter;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.tabs.TabType;
import org.woehlke.computer.kurzweil.commons.tabs.TabContext;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.woehlke.computer.kurzweil.tabs.TabType.CYCLIC_CELLULAR_AUTOMATON;

@Getter
public class CyclicCellularAutomatonContext implements TabContext, ActionListener {

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

    public void stopController() {
        this.controller.exit();
        this.controller = new CyclicCellularAutomatonController(this);
    }

    public void startController() {
        if(this.controller == null){
            this.controller = new CyclicCellularAutomatonController(this);
        } else {
            Thread.State controllerState = this.controller.getState();
            switch (controllerState){
                case NEW:
                case RUNNABLE:
                    break;
                default:
                    this.stopController();
                    break;
            }
        }
        this.controller.start();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.canvas.getNeighbourhoodButtonsPanel().getButtonVonNeumann()) {
            this.canvas.startWithNeighbourhoodVonNeumann();
            this.start();
        } else if (ae.getSource() == this.canvas.getNeighbourhoodButtonsPanel().getButtonMoore()) {
            this.canvas.startWithNeighbourhoodMoore();
            this.start();
        } else if (ae.getSource() == this.canvas.getNeighbourhoodButtonsPanel().getButtonWoehlke()) {
            this.canvas.startWithNeighbourhoodWoehlke();
            this.start();
        }
        if(ae.getSource() == this.canvas.getStartStopButtonsPanel().getStartButton()){
            this.start();
        }
        if(ae.getSource() == this.canvas.getStartStopButtonsPanel().getStopButton()){
            this.stop();
        }
    }


    @Override
    public void start() {
        this.canvas.start();
        this.startController();
    }

    @Override
    public void stop() {
        this.canvas.stop();
        this.stopController();
    }
}
