package org.woehlke.computer.kurzweil.tabs.evolution;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.tabs.evolution.widgets.FoodPerDayPanel;
import org.woehlke.computer.kurzweil.tabs.evolution.widgets.GardenOfEdenPanelRow;

import javax.swing.*;
import java.awt.*;

@Log
@Getter
@ToString(callSuper = true)
public class FoodButtonRow extends JPanel {

    @ToString.Exclude
    private final SimulatedEvolutionContext tabCtx;
    private final FoodPerDayPanel foodPerDayPanel;
    private final GardenOfEdenPanelRow gardenOfEdenPanel;
    private final FlowLayout layout;

    public FoodButtonRow(
        SimulatedEvolutionContext tabCtx
    ) {
        this.tabCtx = tabCtx;
        this.layout = new FlowLayout();
        this.foodPerDayPanel = new FoodPerDayPanel(this.tabCtx);
        this.gardenOfEdenPanel = new GardenOfEdenPanelRow(this.tabCtx);
        this.setLayout(layout);
        this.add(foodPerDayPanel);
        this.add(gardenOfEdenPanel);
    }

}
