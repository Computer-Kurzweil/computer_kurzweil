package org.woehlke.computer.kurzweil.apps.evolution.view.widgets;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.config.ComputerKurzweilProperties;
import org.woehlke.computer.kurzweil.apps.evolution.model.SimulatedEvolutionStateService;
import org.woehlke.computer.kurzweil.apps.evolution.model.statistics.SimulatedEvolutionPopulationCensus;
import org.woehlke.computer.kurzweil.apps.evolution.model.statistics.SimulatedEvolutionStatistics;

import javax.swing.*;
import java.awt.*;

import static org.woehlke.computer.kurzweil.apps.evolution.model.cell.CellLifeCycleStatus.*;

@Log
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

  @Getter
  private final SimulatedEvolutionStateService stateService;

    @Getter
    private final SimulatedEvolutionStatistics statistics;

  public SimulatedEvolutionStatisticsPanel(
      SimulatedEvolutionStateService stateService
  ) {
      this.stateService = stateService;
    setLabels();
    setTextFields(this.stateService.getCtx().getProperties().getEvolution().getPopulation().getInitialPopulation());
    setColors();
    FlowLayout layout = new FlowLayout();
    this.setLayout(layout);
    FlowLayout layoutSubPanel = new FlowLayout();
    JPanel subPanel = new JPanel(layoutSubPanel);
    String borderLabel = this.stateService.getCtx().getProperties().getEvolution().getPopulation().getPanelPopulationStatistics();
    subPanel.setBorder( this.stateService.getCtx().getBorder(borderLabel));
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
      this.statistics = new SimulatedEvolutionStatistics(  this.stateService.getCtx() );
  }

    private void setLabels(){
        ComputerKurzweilProperties.Evolution cfg = this.stateService.getCtx().getProperties().getEvolution();
        youngCellsLabel = new JLabel(cfg.getPopulation().getYoungCellsLabel());
        youngAndFatCellsLabel = new JLabel(cfg.getPopulation().getYoungAndFatCellsLabel());
        fullAgeCellsLabel = new JLabel(cfg.getPopulation().getFullAgeCellsLabel());
        hungryCellsLabel = new JLabel(cfg.getPopulation().getHungryCellsLabel());
        oldCellsLabel = new JLabel(cfg.getPopulation().getOldCellsLabel());
        populationLabel = new JLabel(cfg.getPopulation().getPopulationLabel());
    }

    private void setTextFields(int initialPopulation){
        int cols = 3;
        youngCellsStatistics = new JTextField("0",cols);
        youngAndFatCellsStatistics = new JTextField("0",cols);
        fullAgeCellsStatistics = new JTextField("0",cols);
        hungryCellsStatistics = new JTextField("0",cols);
        oldCellsStatistics = new JTextField("0",cols);
        populationStatistics = new JTextField(""+ initialPopulation, cols);
    }

    public void update() {
        SimulatedEvolutionPopulationCensus simulatedEvolutionWorldStatistics = statistics.peek();;
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
