package org.woehlke.simulation.evolution.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.evolution.config.SimulatedEvolutionProperties;
import org.woehlke.simulation.evolution.model.statistics.SimulatedEvolutionWorldStatistics;
import org.woehlke.simulation.evolution.model.statistics.SimulatedEvolutionWorldStatisticsContainer;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

import static org.woehlke.simulation.evolution.model.cell.LifeCycleStatus.*;
import static org.woehlke.simulation.evolution.model.world.SimulatedEvolutionWorldColor.*;


/**
 * TODO write doc.
 */
@Component
public class SimulatedEvolutionPanelStatistics extends JPanel {

    private JTextField youngCellsStatistics;
    private JTextField youngAndFatCellsStatistics;
    private JTextField fullAgeCellsStatistics;
    private JTextField hungryCellsStatistics;
    private JTextField oldCellsStatistics;
    private JTextField populationStatistics;

    private JLabel youngCellsLabel;
    private JLabel youngAndFatCellsLabel;
    private JLabel fullAgeCellsLabel;
    private JLabel hungryCellsLabel;
    private JLabel oldCellsLabel;
    private JLabel populationLabel;

  private final SimulatedEvolutionWorldStatisticsContainer simulatedEvolutionWorldStatisticsContainer;
  private final SimulatedEvolutionProperties simulatedEvolutionProperties;

  @Autowired
  public SimulatedEvolutionPanelStatistics(
      SimulatedEvolutionWorldStatisticsContainer simulatedEvolutionWorldStatisticsContainer,
      SimulatedEvolutionProperties simulatedEvolutionProperties
  ) {
    this.simulatedEvolutionWorldStatisticsContainer = simulatedEvolutionWorldStatisticsContainer;
    this.simulatedEvolutionProperties = simulatedEvolutionProperties;
    setLabels();
    setTextFields();
    setColors();
    FlowLayout layout = new FlowLayout();
    this.setLayout(layout);
    this.setBorder(getBorder(this.simulatedEvolutionProperties.getPanelPopulationStatistics()));
      this.add(youngCellsLabel);
      this.add(youngCellsStatistics);
      this.add(youngAndFatCellsLabel);
      this.add(youngAndFatCellsStatistics);
      this.add(fullAgeCellsLabel);
      this.add(fullAgeCellsStatistics);
      this.add(hungryCellsLabel);
      this.add(hungryCellsStatistics);
      this.add(oldCellsLabel);
      this.add(oldCellsStatistics);
      this.add(populationLabel);
      this.add(populationStatistics);
  }


    private CompoundBorder getBorder(String label){
        int top = this.simulatedEvolutionProperties.getBorderPadding();
        int left = this.simulatedEvolutionProperties.getBorderPadding();
        int bottom = this.simulatedEvolutionProperties.getBorderPadding();
        int right = this.simulatedEvolutionProperties.getBorderPadding();
        return BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(label),
            BorderFactory.createEmptyBorder(top,left,bottom,right)
        );
    }

    private void setLabels(){
        youngCellsLabel = new JLabel(simulatedEvolutionProperties.getYoungCellsLabel());
        youngAndFatCellsLabel = new JLabel(simulatedEvolutionProperties.getYoungAndFatCellsLabel());
        fullAgeCellsLabel = new JLabel(simulatedEvolutionProperties.getFullAgeCellsLabel());
        hungryCellsLabel = new JLabel(simulatedEvolutionProperties.getHungryCellsLabel());
        oldCellsLabel = new JLabel(simulatedEvolutionProperties.getOldCellsLabel());
        populationLabel = new JLabel(simulatedEvolutionProperties.getPopulationLabel());
    }

    private void setTextFields(){
        int cols = 3;
        SimulatedEvolutionWorldStatistics simulatedEvolutionWorldStatistics = simulatedEvolutionWorldStatisticsContainer.getSimulatedEvolutionWorldStatistics();
        youngCellsStatistics = new JTextField(""+ simulatedEvolutionWorldStatistics.getDeadCells(),cols);
        youngAndFatCellsStatistics = new JTextField(""+ simulatedEvolutionWorldStatistics.getYoungAndFatCells(),cols);
        fullAgeCellsStatistics = new JTextField(""+ simulatedEvolutionWorldStatistics.getFullAgeCells(),cols);
        hungryCellsStatistics = new JTextField(""+ simulatedEvolutionWorldStatistics.getHungryCells(),cols);
        oldCellsStatistics = new JTextField(""+ simulatedEvolutionWorldStatistics.getOldCells(),cols);
        populationStatistics = new JTextField(""+ simulatedEvolutionWorldStatistics.getPopulation(),cols);
    }

    public void updateTextFields() {
        SimulatedEvolutionWorldStatistics simulatedEvolutionWorldStatistics = simulatedEvolutionWorldStatisticsContainer.getSimulatedEvolutionWorldStatistics();
        youngCellsStatistics.setText(""+ simulatedEvolutionWorldStatistics.getYoungCells());
        youngAndFatCellsStatistics.setText(""+ simulatedEvolutionWorldStatistics.getYoungAndFatCells());
        fullAgeCellsStatistics.setText(""+ simulatedEvolutionWorldStatistics.getFullAgeCells());
        hungryCellsStatistics.setText(""+ simulatedEvolutionWorldStatistics.getHungryCells());
        oldCellsStatistics.setText(""+ simulatedEvolutionWorldStatistics.getOldCells());
        populationStatistics.setText(""+ simulatedEvolutionWorldStatistics.getPopulation());
    }

  private void setColors(){
      youngCellsStatistics.setBackground(YOUNG.getColorBackground());
      youngCellsStatistics.setForeground(YOUNG.getColorForeground());
      youngAndFatCellsStatistics.setBackground(YOUNG_AND_FAT.getColorBackground());
      youngAndFatCellsStatistics.setForeground(YOUNG_AND_FAT.getColorForeground());
      fullAgeCellsStatistics.setBackground(FULL_AGE.getColorBackground());
      fullAgeCellsStatistics.setForeground(FULL_AGE.getColorForeground());
      hungryCellsStatistics.setBackground(HUNGRY.getColorBackground());
      hungryCellsStatistics.setForeground(HUNGRY.getColorForeground());
      oldCellsStatistics.setBackground(OLD.getColorBackground());
      oldCellsStatistics.setForeground(OLD.getColorForeground());
  }

}
