package org.woehlke.computer.kurzweil.apps.evolution.view.widgets;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;

import javax.swing.*;
import java.awt.FlowLayout;

@Log
@Getter
public class SimulatedEvolutionButtonRowPanel extends JPanel {

    private final ComputerKurzweilApplicationContext ctx;
    private final FoodPanel foodPanel;
    private final GardenOfEdenPanel gardenOfEdenPanel;

  public SimulatedEvolutionButtonRowPanel(
      ComputerKurzweilApplicationContext ctx
  ) {
      this.ctx = ctx;
      this.foodPanel = new FoodPanel(this.ctx);
      this.gardenOfEdenPanel = new GardenOfEdenPanel(this.ctx);
      FlowLayout flowLayout = new FlowLayout();
      this.setLayout(flowLayout);
      this.add(foodPanel);
      this.add(gardenOfEdenPanel);
  }

}
