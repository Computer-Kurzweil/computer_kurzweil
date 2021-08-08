package org.woehlke.computer.kurzweil.tabs.simulatedevolution;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.Updateable;
import org.woehlke.computer.kurzweil.tabs.ComputerKurzweilTabbedPane;
import org.woehlke.computer.kurzweil.tabs.TabPanel;
import org.woehlke.computer.kurzweil.tabs.Tab;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.population.SimulatedEvolutionPopulation;

import java.awt.event.ActionEvent;


@Log
@Getter
@ToString(callSuper = true, exclude = {"tabCtx"})
@EqualsAndHashCode(callSuper=true, exclude = {"tabCtx"})
public class SimulatedEvolutionTab extends TabPanel implements Tab, SimulatedEvolution, Updateable {

    private static final long serialVersionUID = 7526471155622776147L;

    private final SimulatedEvolutionCanvas canvas;
    private final SimulatedEvolutionContext tabCtx;
    private final SimulatedEvolutionModel tabModel;
    private final SimulatedEvolutionTabPane tabPane;

    private SimulatedEvolutionPopulation population;

    public SimulatedEvolutionTab(ComputerKurzweilTabbedPane tabbedPane) {
        super(tabbedPane, TAB_TYPE);
        this.tabCtx = new SimulatedEvolutionContext(this, this.getCtx());
        this.canvas =  this.tabCtx.getCanvas();
        this.tabModel = this.canvas.getTabModel();
        this.tabPane = new SimulatedEvolutionTabPane(this);
        this.add(this.panelSubtitle);
        this.add(this.canvas);
        this.add(this.tabPane);
        this.tabModel.stop();
        this.tabPane.stop();
        this.ctx.getFrame().pack();
    }

    @Override
    public void start() {
        log.info("start");
        this.tabPane.start();
        this.tabModel.start();
        this.getTabCtx().startController();
        this.getTabCtx().getController().start();
        this.ctx.getFrame().pack();
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        log.info("start with canvas x="+x+" y="+y);
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stop");
        this.tabModel.stop();
        this.tabPane.stop();
        this.getTabCtx().stopController();
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        log.info("stop with canvas x="+x+" y="+y);
        log.info("stopped");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.tabPane.getFoodPerDayPanel().getFoodPerDayIncreaseButton()) {
            log.info("actionPerformed: increaseFoodPerDay");
            this.tabModel.increaseFoodPerDay();
            this.update();
        } else if (ae.getSource() == this.tabPane.getFoodPerDayPanel().getFoodPerDayDecreaseButton()) {
            log.info("actionPerformed: decreaseFoodPerDay");
            this.tabModel.decreaseFoodPerDay();
            this.update();
        } else if (ae.getSource() == this.tabPane.getGardenOfEdenPanel().getButtonToggleGardenOfEden()) {
            log.info("actionPerformed: toggleGardenOfEden");
            this.tabModel.toggleGardenOfEden();
            this.update();
        }
        if(ae.getSource() == this.tabPane.getStartStopButtonsPanel().getStartButton()){
            super.ctx.getFrame().start();
        }
        if(ae.getSource() == this.tabPane.getStartStopButtonsPanel().getStopButton()){
            super.ctx.getFrame().stop();
        }
    }

    public void update(){
        if(this.population!= null){
            this.tabPane.update();
        }
    }

    public void update(SimulatedEvolutionPopulation population) {
        this.population = population;
        this.tabPane.update();
    }
}
