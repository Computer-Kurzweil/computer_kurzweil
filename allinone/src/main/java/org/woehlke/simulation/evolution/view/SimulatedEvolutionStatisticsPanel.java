package org.woehlke.simulation.evolution.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.evolution.model.SimulatedEvolutionContext;
import org.woehlke.simulation.evolution.model.statistics.SimulatedEvolutionPopulationCensus;
import org.woehlke.simulation.evolution.model.statistics.SimulatedEvolutionStatistics;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

import static org.woehlke.simulation.evolution.model.cell.CellLifeCycleStatus.*;


/**
 * TODO write doc.
 */
@Component
public class SimulatedEvolutionStatisticsPanel extends JPanel {

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

  private final SimulatedEvolutionStatistics simulatedEvolutionWorldStatisticsContainer;
  private final SimulatedEvolutionContext ctx;

  @Autowired
  public SimulatedEvolutionStatisticsPanel(
      SimulatedEvolutionStatistics simulatedEvolutionWorldStatisticsContainer,
      SimulatedEvolutionContext ctx
  ) {
    this.simulatedEvolutionWorldStatisticsContainer = simulatedEvolutionWorldStatisticsContainer;
    this.ctx = ctx;
    setLabels();
    setTextFields();
    setColors();
    FlowLayout layout = new FlowLayout();
    this.setLayout(layout);
    FlowLayout layoutSubPanel = new FlowLayout();
    JPanel subPanel = new JPanel(layoutSubPanel);
    subPanel.setBorder(getBorder(ctx.getProperties().getCellPopulation().getPanelPopulationStatistics()));
      subPanel.add(youngCellsLabel);
      subPanel.add(youngCellsStatistics);
      subPanel.add(youngAndFatCellsLabel);
      subPanel.add(youngAndFatCellsStatistics);
      subPanel.add(fullAgeCellsLabel);
      subPanel.add(fullAgeCellsStatistics);
      subPanel.add(hungryCellsLabel);
      subPanel.add(hungryCellsStatistics);
      subPanel.add(oldCellsLabel);
      subPanel.add(oldCellsStatistics);
      subPanel.add(populationLabel);
      subPanel.add(populationStatistics);
      this.add(subPanel);
  }


    private CompoundBorder getBorder(String label){
        int top = this.ctx.getProperties().getView().getBorderPadding();
        int left = this.ctx.getProperties().getView().getBorderPadding();
        int bottom = this.ctx.getProperties().getView().getBorderPadding();
        int right = this.ctx.getProperties().getView().getBorderPadding();
        return BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(label),
            BorderFactory.createEmptyBorder(top,left,bottom,right)
        );
    }

    private void setLabels(){
        youngCellsLabel = new JLabel(ctx.getProperties().getCellPopulation().getYoungCellsLabel());
        youngAndFatCellsLabel = new JLabel(ctx.getProperties().getCellPopulation().getYoungAndFatCellsLabel());
        fullAgeCellsLabel = new JLabel(ctx.getProperties().getCellPopulation().getFullAgeCellsLabel());
        hungryCellsLabel = new JLabel(ctx.getProperties().getCellPopulation().getHungryCellsLabel());
        oldCellsLabel = new JLabel(ctx.getProperties().getCellPopulation().getOldCellsLabel());
        populationLabel = new JLabel(ctx.getProperties().getCellPopulation().getPopulationLabel());
    }

    private void setTextFields(){
        int cols = 3;
        SimulatedEvolutionPopulationCensus simulatedEvolutionWorldStatistics = simulatedEvolutionWorldStatisticsContainer.getSimulatedEvolutionWorldStatistics();
        youngCellsStatistics = new JTextField(""+ simulatedEvolutionWorldStatistics.getDeadCells(),cols);
        youngAndFatCellsStatistics = new JTextField(""+ simulatedEvolutionWorldStatistics.getYoungAndFatCells(),cols);
        fullAgeCellsStatistics = new JTextField(""+ simulatedEvolutionWorldStatistics.getFullAgeCells(),cols);
        hungryCellsStatistics = new JTextField(""+ simulatedEvolutionWorldStatistics.getHungryCells(),cols);
        oldCellsStatistics = new JTextField(""+ simulatedEvolutionWorldStatistics.getOldCells(),cols);
        populationStatistics = new JTextField(""+ simulatedEvolutionWorldStatistics.getPopulation(),cols);
    }

    public void updateTextFields() {
        SimulatedEvolutionPopulationCensus simulatedEvolutionWorldStatistics = simulatedEvolutionWorldStatisticsContainer.getSimulatedEvolutionWorldStatistics();
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
