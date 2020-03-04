package org.woehlke.computer.kurzweil.tabs.simulatedevolution;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.Updateable;
import org.woehlke.computer.kurzweil.tabs.ComputerKurzweilTabbedPane;
import org.woehlke.computer.kurzweil.tabs.TabPanel;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.canvas.food.FoodPerDayPanel;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.canvas.garden.GardenOfEdenPanelRow;
import org.woehlke.computer.kurzweil.commons.widgets.PanelBottomButtons;
import org.woehlke.computer.kurzweil.tabs.Tab;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.canvas.population.PopulationStatisticsElementsPanel;

import java.awt.event.ActionEvent;


@Log4j2
@Getter
@ToString(callSuper = true, exclude = {"tabCtx"})
@EqualsAndHashCode(callSuper=true, exclude = {"tabCtx"})
public class SimulatedEvolutionTab extends TabPanel implements Tab, SimulatedEvolution, Updateable {

    private final SimulatedEvolutionCanvas canvas;
    private final SimulatedEvolutionContext tabCtx;
    private final SimulatedEvolutionModel tabModel;
    private final PanelBottomButtons bottomButtonsPanel;
    private final PopulationStatisticsElementsPanel statisticsPanel;
    private final FoodPerDayPanel foodPerDayPanel;
    private final GardenOfEdenPanelRow gardenOfEdenPanel;

    public SimulatedEvolutionTab(ComputerKurzweilTabbedPane tabbedPane) {
        super(tabbedPane, TAB_TYPE);
        this.tabCtx = new SimulatedEvolutionContext(this,this.getCtx());
        this.canvas =  this.tabCtx.getCanvas();
        this.tabModel = this.canvas.getTabModel();
        this.statisticsPanel = new PopulationStatisticsElementsPanel(this.tabCtx);
        this.foodPerDayPanel = new FoodPerDayPanel(this.tabCtx);
        this.gardenOfEdenPanel = new GardenOfEdenPanelRow(this.tabCtx);
        this.bottomButtonsPanel = new PanelBottomButtons( this );
        this.bottomButtonsPanel.add(foodPerDayPanel);
        this.bottomButtonsPanel.add(gardenOfEdenPanel);
        this.add(this.panelSubtitle);
        this.add(this.canvas);
        this.add(this.statisticsPanel);
        this.add(this.bottomButtonsPanel);
        this.foodPerDayPanel.addActionListener(this);
        this.gardenOfEdenPanel.addActionListener(this);
        this.bottomButtonsPanel.addActionListener(this);
        this.ctx.getFrame().pack();
    }

    @Override
    public void start() {
        log.info("start");
        this.bottomButtonsPanel.getStartStopButtonsPanel().start();
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
        this.bottomButtonsPanel.getStartStopButtonsPanel().stop();
        this.canvas.stop();
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
    public String getTitle() {
        return this.tabCtx.getCtx().getProperties().getSimulatedevolution().getView().getTitle();
    }

    @Override
    public String getSubTitle() {
        return this.tabCtx.getCtx().getProperties().getSimulatedevolution().getView().getSubtitle();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        boolean updateUi = false;
        if (ae.getSource() == this.foodPerDayPanel.getFoodPerDayIncreaseButton()) {
            log.info("actionPerformed: increaseFoodPerDay");
            this.tabModel.increaseFoodPerDay();
            this.update();
        } else if (ae.getSource() == this.foodPerDayPanel.getFoodPerDayDecreaseButton()) {
            log.info("actionPerformed: decreaseFoodPerDay");
            this.tabModel.decreaseFoodPerDay();
            this.update();
        } else if (ae.getSource() == this.gardenOfEdenPanel.getButtonToggleGardenOfEden()) {
            log.info("actionPerformed: toggleGardenOfEden");
            this.tabModel.toggleGardenOfEden();
            this.update();
        }
        if(ae.getSource() ==  this.bottomButtonsPanel.getStartButton()){
            super.ctx.getFrame().start();
        }
        if(ae.getSource() ==  this.bottomButtonsPanel.getStopButton()){
            super.ctx.getFrame().stop();
        }
    }

    @Override
    public void update() {
        this.statisticsPanel.update();
        this.foodPerDayPanel.update();
        this.gardenOfEdenPanel.update();
    }
}
