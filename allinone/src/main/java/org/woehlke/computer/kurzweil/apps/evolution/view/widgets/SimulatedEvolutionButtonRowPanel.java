package org.woehlke.computer.kurzweil.apps.evolution.view.widgets;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;

import javax.swing.*;

@Log
@Getter
public class SimulatedEvolutionButtonRowPanel extends JPanel {

    private final ComputerKurzweilApplicationContext ctx;
    private final FoodPanel foodPanel;
    private final GardenOfEdenPanel gardenOfEdenPanel;
    private final SimulatedEvolutionButtonRowPanelLayout layout;

    public SimulatedEvolutionButtonRowPanel(
      ComputerKurzweilApplicationContext ctx
  ) {
      this.ctx = ctx;
      this.layout = new SimulatedEvolutionButtonRowPanelLayout();
      this.foodPanel = new FoodPanel(this.ctx);
      this.gardenOfEdenPanel = new GardenOfEdenPanel(this.ctx);
      this.setLayout(layout);
      this.add(foodPanel);
      this.add(gardenOfEdenPanel);
  }

}
