package org.woehlke.computer.kurzweil.tabs.simulatedevolution.views;

import lombok.Getter;
import lombok.experimental.Delegate;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.commons.Updateable;
import org.woehlke.computer.kurzweil.commons.widgets.PanelStartStopButtons;
import org.woehlke.computer.kurzweil.commons.widgets.SubTabImpl;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolutionTab;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.views.garden.GardenOfEdenPanelRow;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.views.population.generation.PopulationStatisticsPanel;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.views.population.lifecycle.PopulationStatisticsElementsLifeCyclePanel;

import javax.swing.*;

@Log
@Getter
public class SimulatedEvolutionTabPane extends JTabbedPane implements Startable, Updateable {

    private static final long serialVersionUID = 7526471155622776147L;

    private final PopulationStatisticsElementsLifeCyclePanel statisticsPanelPanelLifeCycle;
    private final PopulationStatisticsPanel statisticsPanelCounted;
    private final FoodPerDayPanel foodPerDayPanel;
    private final GardenOfEdenPanelRow gardenOfEdenPanel;
    @Delegate(excludes={SubTabImpl.class,JPanel.class,Updateable.class})
    private final PanelStartStopButtons startStopButtonsPanel;

    public SimulatedEvolutionTabPane(SimulatedEvolutionTab tab) {
        this.statisticsPanelPanelLifeCycle = new PopulationStatisticsElementsLifeCyclePanel( tab.getTabCtx() );
        this.statisticsPanelCounted = new PopulationStatisticsPanel( tab.getTabCtx() );
        this.foodPerDayPanel = new FoodPerDayPanel( tab.getTabCtx() );
        this.gardenOfEdenPanel = new GardenOfEdenPanelRow( tab.getTabCtx() );
        this.startStopButtonsPanel = new PanelStartStopButtons( tab );
        SubTabImpl[] allSubTabPanels = {
            this.startStopButtonsPanel,
            this.statisticsPanelPanelLifeCycle,
            this.statisticsPanelCounted,
            this.foodPerDayPanel,
            this.gardenOfEdenPanel
        };
        for(SubTabImpl t:allSubTabPanels){
            this.addTab( t.getTitle(),t);
        }
        this.foodPerDayPanel.addActionListener(tab);
        this.gardenOfEdenPanel.addActionListener(tab);
        this.getStartStopButtonsPanel().stop();
    }

    public void update() {
        log.info("update");
        this.statisticsPanelPanelLifeCycle.update();
        this.statisticsPanelCounted.update();
        /*
        for(Updateable t:tabPanelsUpdateable){
           t.update();
        }
        */
    }

}
