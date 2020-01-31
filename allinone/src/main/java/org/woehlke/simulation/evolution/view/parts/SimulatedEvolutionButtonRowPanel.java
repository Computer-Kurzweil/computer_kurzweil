package org.woehlke.simulation.evolution.view.parts;

import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.allinone.config.ComputerKurzweilProperties;
import org.woehlke.simulation.evolution.model.SimulatedEvolutionStateService;

import javax.swing.*;
import java.awt.FlowLayout;

@Log
@Component
public class SimulatedEvolutionButtonRowPanel extends JPanel {

    @Getter
    private final SimulatedEvolutionStateService stateService;

    @Getter
    private final FoodPanel foodPanel;

    @Getter
    private final GardenOfEdenPanel gardenOfEdenPanel;

  @Autowired
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
