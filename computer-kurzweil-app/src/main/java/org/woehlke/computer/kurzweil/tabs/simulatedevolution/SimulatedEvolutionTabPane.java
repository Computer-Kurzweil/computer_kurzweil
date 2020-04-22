package org.woehlke.computer.kurzweil.tabs.simulatedevolution;

import lombok.Getter;
import lombok.experimental.Delegate;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.commons.Updateable;
import org.woehlke.computer.kurzweil.commons.widgets.PanelStartStopButtons;
import org.woehlke.computer.kurzweil.commons.widgets.SubTabImpl;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.canvas.food.FoodPerDayPanel;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.canvas.garden.GardenOfEdenPanelRow;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.canvas.population.PopulationStatisticsElementsPanelCounted;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.canvas.population.PopulationStatisticsElementsPanelLifeCycle;

import javax.swing.*;

@Log4j2
@Getter
public class SimulatedEvolutionTabPane extends JTabbedPane implements Updateable, Startable {

    private final PopulationStatisticsElementsPanelLifeCycle statisticsPanelPanelLifeCycle;
    private final PopulationStatisticsElementsPanelCounted statisticsPanelCounted;
    private final FoodPerDayPanel foodPerDayPanel;
    private final GardenOfEdenPanelRow gardenOfEdenPanel;
    @Delegate(excludes={SubTabImpl.class,JPanel.class,Updateable.class})
    private final PanelStartStopButtons startStopButtonsPanel;

    private final Updateable[] tabPanelsUpdateable;

    public SimulatedEvolutionTabPane(SimulatedEvolutionTab tab) {
        this.statisticsPanelPanelLifeCycle = new PopulationStatisticsElementsPanelLifeCycle(tab.getTabCtx());
        this.statisticsPanelCounted = new PopulationStatisticsElementsPanelCounted(tab.getTabCtx());
        this.foodPerDayPanel = new FoodPerDayPanel(tab.getTabCtx());
        this.gardenOfEdenPanel = new GardenOfEdenPanelRow(tab.getTabCtx());
        this.startStopButtonsPanel = new PanelStartStopButtons( tab );
        tabPanelsUpdateable =new Updateable[]{
            this.statisticsPanelPanelLifeCycle,
            this.statisticsPanelCounted,
            this.foodPerDayPanel,
            this.gardenOfEdenPanel
        };
        SubTabImpl[] allSubTabPanels = {
            this.startStopButtonsPanel,
            this.foodPerDayPanel,
            this.gardenOfEdenPanel,
            this.statisticsPanelPanelLifeCycle,
            this.statisticsPanelCounted
        };
        for(SubTabImpl t:allSubTabPanels){
            this.addTab( t.getTitle(),t);
        }
        this.foodPerDayPanel.addActionListener(tab);
        this.gardenOfEdenPanel.addActionListener(tab);
        this.getStartStopButtonsPanel().stop();
    }

    public void update() {
        for(Updateable t:tabPanelsUpdateable){
           t.update();
        }
    }

}
