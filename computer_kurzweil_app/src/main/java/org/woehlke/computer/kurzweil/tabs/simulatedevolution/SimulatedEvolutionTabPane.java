package org.woehlke.computer.kurzweil.tabs.simulatedevolution;

import lombok.Getter;
import lombok.experimental.Delegate;
import lombok.extern.log4j.Log4j2;
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
public class SimulatedEvolutionTabPane extends JTabbedPane implements Updateable {

    //@Delegate(excludes={SubTabImpl.class,JPanel.class,Updateable.class})
    private final PopulationStatisticsElementsPanelLifeCycle statisticsPanelPanelLifeCycle;
    //@Delegate(excludes={SubTabImpl.class,JPanel.class,Updateable.class})
    private final PopulationStatisticsElementsPanelCounted statisticsPanelCounted;
    //@Delegate(excludes={SubTabImpl.class,JPanel.class,Updateable.class})
    private final FoodPerDayPanel foodPerDayPanel;
    //@Delegate(excludes={SubTabImpl.class,JPanel.class,Updateable.class})
    private final GardenOfEdenPanelRow gardenOfEdenPanel;
    @Delegate(excludes={SubTabImpl.class,JPanel.class,Updateable.class})
    private final PanelStartStopButtons startStopButtonsPanel;

    public SimulatedEvolutionTabPane(SimulatedEvolutionTab tab) {
        this.statisticsPanelPanelLifeCycle = new PopulationStatisticsElementsPanelLifeCycle(tab.getTabCtx());
        this.statisticsPanelCounted = new PopulationStatisticsElementsPanelCounted(tab.getTabCtx());
        this.foodPerDayPanel = new FoodPerDayPanel(tab.getTabCtx());
        this.gardenOfEdenPanel = new GardenOfEdenPanelRow(tab.getTabCtx());
        this.startStopButtonsPanel = new PanelStartStopButtons( tab );
        this.addTab( this.startStopButtonsPanel.getTitle(),this.startStopButtonsPanel);
        this.addTab( this.statisticsPanelPanelLifeCycle.getTitle(),this.statisticsPanelPanelLifeCycle);
        this.addTab( this.statisticsPanelCounted.getTitle(),this.statisticsPanelCounted);
        this.addTab( this.foodPerDayPanel.getTitle(),this.foodPerDayPanel);
        this.addTab( this.gardenOfEdenPanel.getTitle(),this.gardenOfEdenPanel);
        this.foodPerDayPanel.addActionListener(tab);
        this.gardenOfEdenPanel.addActionListener(tab);
        this.startStopButtonsPanel.addActionListener(tab);
    }

    public void update() {
        this.statisticsPanelPanelLifeCycle.update();
        this.statisticsPanelCounted.update();
        this.foodPerDayPanel.update();
        this.gardenOfEdenPanel.update();
    }
}
