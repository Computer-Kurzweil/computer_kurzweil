package org.woehlke.simulation.evolution.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.evolution.config.SimulatedEvolutionProperties;
import org.woehlke.simulation.evolution.model.LifeCycleCount;
import org.woehlke.simulation.evolution.model.LifeCycleCountContainer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;

import static org.woehlke.simulation.evolution.config.GuiConfigColors.*;

/**
 * TODO write doc.
 */

@Component
public class PanelLifeCycleStatus extends JPanel {

  private JTextField youngCells;
  private JTextField youngAndFatCells;
  private JTextField fullAgeCells;
  private JTextField hungryCells;
  private JTextField oldCells;
  private JTextField population;

  private final LifeCycleCountContainer lifeCycleCountContainer;
  private final SimulatedEvolutionProperties simulatedEvolutionProperties;

  @Autowired
  public PanelLifeCycleStatus(LifeCycleCountContainer lifeCycleCountContainer, SimulatedEvolutionProperties simulatedEvolutionProperties) {
      this.lifeCycleCountContainer = lifeCycleCountContainer;
      this.simulatedEvolutionProperties = simulatedEvolutionProperties;
      LifeCycleCount lifeCycleCount = lifeCycleCountContainer.getLifeCycleCount();
    int cols = 3;
    JLabel youngCellsLabel = new JLabel(simulatedEvolutionProperties.getYoungCellsLabel());
    youngCells = new JTextField(""+lifeCycleCount.getDeadCells(),cols);
    JLabel youngAndFatCellsLabel = new JLabel(simulatedEvolutionProperties.getYoungAndFatCellsLabel());
    youngAndFatCells = new JTextField(""+lifeCycleCount.getYoungAndFatCells(),cols);
    JLabel fullAgeCellsLabel = new JLabel(simulatedEvolutionProperties.getFullAgeCellsLabel());
    fullAgeCells = new JTextField(""+lifeCycleCount.getFullAgeCells(),cols);
    JLabel hungryCellsLabel = new JLabel(simulatedEvolutionProperties.getHungryCellsLabel());
    hungryCells = new JTextField(""+lifeCycleCount.getHungryCells(),cols);
    JLabel oldCellsLabel = new JLabel(simulatedEvolutionProperties.getOldCellsLabel());
    oldCells = new JTextField(""+lifeCycleCount.getOldCells(),cols);
    JLabel populationLabel = new JLabel(simulatedEvolutionProperties.getPopulationLabel());
    population = new JTextField(""+lifeCycleCount.getPopulation(),cols);
    youngCells.setForeground(COLOR_YOUNG_FOREGROUND);
    youngCells.setBackground(COLOR_YOUNG);
    youngAndFatCells.setBackground(COLOR_YOUNG_AND_FAT);
    fullAgeCells.setBackground(COLOR_FULL_AGE);
    hungryCells.setBackground(COLOR_HUNGRY);
    oldCells.setBackground(COLOR_OLD);
    oldCells.setForeground(COLOR_OLD_FOREGROUND);
    FlowLayout layout = new FlowLayout();
    this.setLayout(layout);
    this.add(youngCellsLabel);
    this.add(youngCells);
    this.add(youngAndFatCellsLabel);
    this.add(youngAndFatCells);
    this.add(fullAgeCellsLabel);
    this.add(fullAgeCells);
    this.add(hungryCellsLabel);
    this.add(hungryCells);
    this.add(oldCellsLabel);
    this.add(oldCells);
    this.add(populationLabel);
    this.add(population);
  }

  /**
   * TODO write doc.
   */
  public void updateLifeCycleCount() {
    LifeCycleCount lifeCycleCount = lifeCycleCountContainer.getLifeCycleCount();
    youngCells.setText(""+lifeCycleCount.getYoungCells());
    youngAndFatCells.setText(""+lifeCycleCount.getYoungAndFatCells());
    fullAgeCells.setText(""+lifeCycleCount.getFullAgeCells());
    hungryCells.setText(""+lifeCycleCount.getHungryCells());
    oldCells.setText(""+lifeCycleCount.getOldCells());
    population.setText(""+lifeCycleCount.getPopulation());
  }

}
