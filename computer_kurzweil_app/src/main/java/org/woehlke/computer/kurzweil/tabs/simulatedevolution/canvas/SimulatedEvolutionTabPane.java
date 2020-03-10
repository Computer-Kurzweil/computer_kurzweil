package org.woehlke.computer.kurzweil.tabs.simulatedevolution.canvas;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.widgets.PanelBottomButtons;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolutionTab;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.canvas.food.FoodPerDayPanel;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.canvas.garden.GardenOfEdenPanelRow;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.canvas.population.PopulationStatisticsElementsPanel;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.canvas.population.PopulationStatisticsElementsPanel2;

import javax.swing.*;

@Log4j2
@Getter
public class SimulatedEvolutionTabPane extends JTabbedPane {

    private final PopulationStatisticsElementsPanel statisticsPanel;
    private final PopulationStatisticsElementsPanel2 statisticsPanel2;
    private final FoodPerDayPanel foodPerDayPanel;
    private final GardenOfEdenPanelRow gardenOfEdenPanel;
    private final PanelBottomButtons bottomButtonsPanel;

    public SimulatedEvolutionTabPane(SimulatedEvolutionTab tab) {
        this.statisticsPanel = new PopulationStatisticsElementsPanel(tab.getTabCtx());
        this.statisticsPanel2 = new PopulationStatisticsElementsPanel2(tab.getTabCtx());
        this.foodPerDayPanel = new FoodPerDayPanel(tab.getTabCtx());
        this.gardenOfEdenPanel = new GardenOfEdenPanelRow(tab.getTabCtx());
        this.bottomButtonsPanel = new PanelBottomButtons( tab );
        this.addTab( "startstop",this.bottomButtonsPanel);
        this.addTab( "statistics",this.statisticsPanel);
        this.addTab( "statistics2",this.statisticsPanel2);
        this.addTab( "foodPerDay",this.foodPerDayPanel);
        this.addTab( "gardenOfEdenPanel",this.gardenOfEdenPanel);
        this.foodPerDayPanel.addActionListener(tab);
        this.gardenOfEdenPanel.addActionListener(tab);
        this.bottomButtonsPanel.addActionListener(tab);
    }

    public void update() {
        this.statisticsPanel.update();
        this.foodPerDayPanel.update();
        this.gardenOfEdenPanel.update();
    }
}
