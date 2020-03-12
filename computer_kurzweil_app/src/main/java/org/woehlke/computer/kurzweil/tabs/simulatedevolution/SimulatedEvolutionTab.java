package org.woehlke.computer.kurzweil.tabs.simulatedevolution;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.Updateable;
import org.woehlke.computer.kurzweil.tabs.ComputerKurzweilTabbedPane;
import org.woehlke.computer.kurzweil.tabs.TabPanel;
import org.woehlke.computer.kurzweil.tabs.Tab;

import java.awt.event.ActionEvent;


@Log4j2
@Getter
@ToString(callSuper = true, exclude = {"tabCtx"})
@EqualsAndHashCode(callSuper=true, exclude = {"tabCtx"})
public class SimulatedEvolutionTab extends TabPanel implements Tab, SimulatedEvolution, Updateable {

    private final SimulatedEvolutionCanvas canvas;
    private final SimulatedEvolutionContext tabCtx;
    private final SimulatedEvolutionModel tabModel;
    private final SimulatedEvolutionTabPane tabPane;

    public SimulatedEvolutionTab(ComputerKurzweilTabbedPane tabbedPane) {
        super(tabbedPane, TAB_TYPE);
        this.tabCtx = new SimulatedEvolutionContext(this, this.getCtx());
        this.canvas =  this.tabCtx.getCanvas();
        this.tabModel = this.canvas.getTabModel();
        this.tabPane = new SimulatedEvolutionTabPane(this);
        this.tabPane.addActionListener(this);
        this.add(this.panelSubtitle);
        this.add(this.canvas);
        this.add(this.tabPane);
        this.ctx.getFrame().pack();
    }

    @Override
    public void start() {
        log.info("start");
        this.tabPane.getStartStopButtonsPanel().start();
        this.canvas.getTabModel().start();
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
        this.tabPane.getStartStopButtonsPanel().stop();
        this.tabModel.stop();
        this.getTabCtx().stopController();
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        log.info("stop with canvas x="+x+" y="+y);
        log.info("stopped");
    }

    @Override
    public void showMe() {
        log.info("showMe");
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        log.info("this: "+this.toString());
        log.info("showMe with canvas x="+x+" y="+y);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        boolean updateUi = false;
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

    public void update() {
        this.tabPane.update();
    }
}
