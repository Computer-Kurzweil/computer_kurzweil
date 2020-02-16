package org.woehlke.computer.kurzweil.tabs.evolution.widgets;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.tabs.evolution.SimulatedEvolutionContext;

import javax.swing.*;

@Log
@Getter
@ToString(callSuper = true)
public class SimulatedEvolutionButtonRowPanel extends JPanel {

    @ToString.Exclude
    private final SimulatedEvolutionContext tabCtx;
    private final FoodPanel foodPanel;
    private final GardenOfEdenPanel gardenOfEdenPanel;
    private final SimulatedEvolutionButtonRowPanelLayout layout;

    public SimulatedEvolutionButtonRowPanel(
        SimulatedEvolutionContext tabCtx
  ) {
      this.tabCtx = tabCtx;
      this.layout = new SimulatedEvolutionButtonRowPanelLayout();
      this.foodPanel = new FoodPanel(this.tabCtx);
      this.gardenOfEdenPanel = new GardenOfEdenPanel(this.tabCtx);
      this.setLayout(layout);
      this.add(foodPanel);
      this.add(gardenOfEdenPanel);
  }

}
