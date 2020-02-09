package org.woehlke.computer.kurzweil.apps.evolution.view.widgets;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilProperties;
import org.woehlke.computer.kurzweil.apps.evolution.model.SimulatedEvolutionStateService;

import javax.swing.*;
import java.awt.FlowLayout;

@Log
public class SimulatedEvolutionButtonRowPanel extends JPanel {

    @Getter
    private final SimulatedEvolutionStateService stateService;

    @Getter
    private final FoodPanel foodPanel;

    @Getter
    private final GardenOfEdenPanel gardenOfEdenPanel;

  public SimulatedEvolutionButtonRowPanel(
      SimulatedEvolutionStateService stateService
  ) {
      this.stateService=stateService;
      ComputerKurzweilProperties.Evolution cnf = this.stateService.getCtx().getProperties().getEvolution();
      this.foodPanel = new FoodPanel(this.stateService);
      this.gardenOfEdenPanel = new GardenOfEdenPanel(this.stateService);
      FlowLayout flowLayout = new FlowLayout();
      this.setLayout(flowLayout);
      this.add(foodPanel);
      this.add(gardenOfEdenPanel);
  }

}
