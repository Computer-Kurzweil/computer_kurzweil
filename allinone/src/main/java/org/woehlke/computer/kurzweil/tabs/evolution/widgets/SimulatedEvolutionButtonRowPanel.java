package org.woehlke.computer.kurzweil.tabs.evolution.widgets;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.tabs.evolution.SimulatedEvolutionContext;
import org.woehlke.computer.kurzweil.tabs.evolution.widgets.layouts.SimulatedEvolutionButtonRowPanelLayout;

import javax.swing.*;

@Log
@Getter
@ToString(callSuper = true)
public class SimulatedEvolutionButtonRowPanel extends JPanel {

    @ToString.Exclude
    private final SimulatedEvolutionContext tabCtx;
    private final FoodPerDayPanel foodPanel;
    private final GardenOfEdenPanelRow gardenOfEdenPanel;
    private final SimulatedEvolutionButtonRowPanelLayout layout;

    public SimulatedEvolutionButtonRowPanel(
        SimulatedEvolutionContext tabCtx
  ) {
      this.tabCtx = tabCtx;
      this.layout = new SimulatedEvolutionButtonRowPanelLayout();
      this.foodPanel = new FoodPerDayPanel(this.tabCtx);
      this.gardenOfEdenPanel = new GardenOfEdenPanelRow(this.tabCtx);
      this.setLayout(layout);
      this.add(foodPanel);
      this.add(gardenOfEdenPanel);
  }

}
