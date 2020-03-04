package org.woehlke.computer.kurzweil.tabs.evolution;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.tabs.ComputerKurzweilTabbedPane;
import org.woehlke.computer.kurzweil.tabs.TabPanel;
import org.woehlke.computer.kurzweil.tabs.TabType;
import org.woehlke.computer.kurzweil.tabs.evolution.canvas.SimulatedEvolutionCanvas;
import org.woehlke.computer.kurzweil.tabs.evolution.food.FoodPerDayPanel;
import org.woehlke.computer.kurzweil.tabs.evolution.garden.GardenOfEdenPanelRow;
import org.woehlke.computer.kurzweil.tabs.evolution.population.PopulationStatisticsPanel;
import org.woehlke.computer.kurzweil.commons.widgets.PanelBottomButtons;
import org.woehlke.computer.kurzweil.tabs.Tab;
import java.awt.event.ActionEvent;

import static org.woehlke.computer.kurzweil.tabs.TabType.SIMULATED_EVOLUTION;


@Log4j2
@Getter
@ToString(callSuper = true, exclude = {"tabCtx"})
@EqualsAndHashCode(callSuper=true, exclude = {"tabCtx"})
public class SimulatedEvolutionTab extends TabPanel implements Tab {

    private final static TabType tabType = SIMULATED_EVOLUTION;

    private final SimulatedEvolutionCanvas canvas;
    private final SimulatedEvolutionContext tabCtx;
    private final PanelBottomButtons bottomButtonsPanel;
    private final PopulationStatisticsPanel statisticsPanel;
    private final FoodPerDayPanel foodPerDayPanel;
    private final GardenOfEdenPanelRow gardenOfEdenPanel;

    public SimulatedEvolutionTab(ComputerKurzweilTabbedPane tabbedPane) {
        super(tabbedPane, tabType);
        this.tabCtx = new SimulatedEvolutionContext(this);
        this.canvas = new SimulatedEvolutionCanvas( this);
        this.tabCtx.setCanvas(this.canvas);
        this.tabCtx.setTabModel(this.canvas.getWorld());
        this.statisticsPanel = this.canvas.getStatisticsPanel();
        this.foodPerDayPanel = new FoodPerDayPanel(this.tabCtx);
        this.gardenOfEdenPanel = new GardenOfEdenPanelRow(this.tabCtx);
        this.bottomButtonsPanel = new PanelBottomButtons( this );
        this.bottomButtonsPanel.add(foodPerDayPanel);
        this.bottomButtonsPanel.add(gardenOfEdenPanel);
        this.add(this.panelSubtitle);
        this.add(this.canvas);
        this.add(this.statisticsPanel);
        this.add(this.bottomButtonsPanel);
        this.foodPerDayPanel.getFoodPerDayIncreaseButton().addActionListener(this);
        this.foodPerDayPanel.getFoodPerDayDecreaseButton().addActionListener(this);
        this.gardenOfEdenPanel.getButtonToggleGardenOfEden().addActionListener(this);
        this.bottomButtonsPanel.getStartStopButtonsPanel().stop();
        this.ctx.getFrame().pack();
    }

    @Override
    public void start() {
        log.info("start");
        this.bottomButtonsPanel.getStartStopButtonsPanel().start();
        this.canvas.getWorld().start();
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
        return this.tabCtx.getCtx().getProperties().getEvolution().getView().getTitle();
    }

    @Override
    public String getSubTitle() {
        return this.tabCtx.getCtx().getProperties().getEvolution().getView().getSubtitle();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.foodPerDayPanel.getFoodPerDayIncreaseButton()) {
            log.info("actionPerformed: increaseFoodPerDay");
            tabCtx.increaseFoodPerDay();
            this.canvas.update();
        } else if (ae.getSource() == this.foodPerDayPanel.getFoodPerDayDecreaseButton()) {
            log.info("actionPerformed: decreaseFoodPerDay");
            tabCtx.decreaseFoodPerDay();
            this.canvas.update();
        } else if (ae.getSource() == this.gardenOfEdenPanel.getButtonToggleGardenOfEden()) {
            log.info("actionPerformed: toggleGardenOfEden");
            tabCtx.toggleGardenOfEden();
            this.canvas.update();
        }
        if(ae.getSource() ==  this.bottomButtonsPanel.getStartButton()){
            super.ctx.getFrame().start();
        }
        if(ae.getSource() ==  this.bottomButtonsPanel.getStopButton()){
            super.ctx.getFrame().stop();
        }
    }
}
