package org.woehlke.computer.kurzweil.tabs.simulatedevolution.views;

import lombok.Getter;
import lombok.experimental.Delegate;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.commons.Updateable;
import org.woehlke.computer.kurzweil.commons.widgets.PanelStartStopButtons;
import org.woehlke.computer.kurzweil.commons.widgets.SubTabImpl;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolutionTab;

import javax.swing.*;

@Log
@Getter
public class SimulatedEvolutionTabPane extends JTabbedPane implements Startable, Updateable {

    private static final long serialVersionUID = 7526471155622776147L;

    private final GetPopulationLifeCyclePanel statisticsPanelPanelLifeCycle;
    private final GetPopulationStatisticsPanel statisticsPanelCounted;
    private final SetFoodPerDayPanel setFoodPerDayPanel;
    private final SetGardenOfEdenPanel gardenOfEdenPanel;
    @Delegate(excludes={SubTabImpl.class,JPanel.class,Updateable.class})
    private final PanelStartStopButtons startStopButtonsPanel;

    public SimulatedEvolutionTabPane(SimulatedEvolutionTab tab) {
        this.statisticsPanelPanelLifeCycle = new GetPopulationLifeCyclePanel( tab.getTabCtx() );
        this.statisticsPanelCounted = new GetPopulationStatisticsPanel( tab.getTabCtx() );
        this.setFoodPerDayPanel = new SetFoodPerDayPanel( tab.getTabCtx() );
        this.gardenOfEdenPanel = new SetGardenOfEdenPanel( tab.getTabCtx() );
        this.startStopButtonsPanel = new PanelStartStopButtons( tab );
        SubTabImpl[] allSubTabPanels = {
            this.startStopButtonsPanel,
            this.statisticsPanelPanelLifeCycle,
            this.statisticsPanelCounted,
            this.setFoodPerDayPanel,
            this.gardenOfEdenPanel
        };
        for(SubTabImpl t:allSubTabPanels){
            this.addTab( t.getTitle(),t);
        }
        this.setFoodPerDayPanel.addActionListener(tab);
        this.gardenOfEdenPanel.addActionListener(tab);
        this.getStartStopButtonsPanel().stop();
    }

    public void update() {
        log.info("update");
        this.statisticsPanelPanelLifeCycle.update();
        this.statisticsPanelCounted.update();
        this.setFoodPerDayPanel.update();
        this.gardenOfEdenPanel.update();
    }
    public void updateStep() {
        log.info("updateStep");
        this.statisticsPanelPanelLifeCycle.update();
        this.statisticsPanelCounted.update();
    }
}
