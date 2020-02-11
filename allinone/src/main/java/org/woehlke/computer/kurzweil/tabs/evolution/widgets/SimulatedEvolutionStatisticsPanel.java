package org.woehlke.computer.kurzweil.tabs.evolution.widgets;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilProperties;
import org.woehlke.computer.kurzweil.tabs.evolution.SimulatedEvolutionContext;
import org.woehlke.computer.kurzweil.tabs.evolution.model.SimulatedEvolutionPopulationCensus;
import org.woehlke.computer.kurzweil.tabs.evolution.model.SimulatedEvolutionStatistics;

import javax.swing.*;
import java.awt.*;

import static org.woehlke.computer.kurzweil.tabs.evolution.model.CellLifeCycleStatus.*;

@Log
@Getter
public class SimulatedEvolutionStatisticsPanel extends JPanel {

    private final JTextField youngCellsStatistics;
    private final JTextField youngAndFatCellsStatistics;
    private final JTextField fullAgeCellsStatistics;
    private final JTextField hungryCellsStatistics;
    private final JTextField oldCellsStatistics;
    private final JTextField populationStatistics;

    private final JLabel youngCellsLabel;
    private final JLabel youngAndFatCellsLabel;
    private final JLabel fullAgeCellsLabel;
    private final JLabel hungryCellsLabel;
    private final JLabel oldCellsLabel;
    private final JLabel populationLabel;

    private final SimulatedEvolutionStatistics statistics;

    private final SimulatedEvolutionContext tabCtx;

  public SimulatedEvolutionStatisticsPanel(
      SimulatedEvolutionContext tabCtx
  ) {
      this.tabCtx = tabCtx;
      int initialPopulation = this.tabCtx.getCtx().getProperties().getEvolution().getPopulation().getInitialPopulation();
      //setLabels();
      ComputerKurzweilProperties.Evolution cfg = this.tabCtx.getCtx().getProperties().getEvolution();
      youngCellsLabel = new JLabel(cfg.getPopulation().getYoungCellsLabel());
      youngAndFatCellsLabel = new JLabel(cfg.getPopulation().getYoungAndFatCellsLabel());
      fullAgeCellsLabel = new JLabel(cfg.getPopulation().getFullAgeCellsLabel());
      hungryCellsLabel = new JLabel(cfg.getPopulation().getHungryCellsLabel());
      oldCellsLabel = new JLabel(cfg.getPopulation().getOldCellsLabel());
      populationLabel = new JLabel(cfg.getPopulation().getPopulationLabel());
      //setTextFields(initialPopulation);
      int cols = 3;
      youngCellsStatistics = new JTextField("0",cols);
      youngAndFatCellsStatistics = new JTextField("0",cols);
      fullAgeCellsStatistics = new JTextField("0",cols);
      hungryCellsStatistics = new JTextField("0",cols);
      oldCellsStatistics = new JTextField("0",cols);
      populationStatistics = new JTextField(""+ initialPopulation, cols);
      setColors();
    FlowLayout layout = new FlowLayout();
    this.setLayout(layout);
    SimulatedEvolutionStatisticsPanelLayout layoutSubPanel = new SimulatedEvolutionStatisticsPanelLayout();
    JPanel subPanel = new JPanel(layoutSubPanel);
    String borderLabel = this.tabCtx.getCtx().getProperties().getEvolution().getPopulation().getPanelPopulationStatistics();
    subPanel.setBorder( this.tabCtx.getCtx().getBorder(borderLabel));
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
      this.statistics = new SimulatedEvolutionStatistics(  this.tabCtx );
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
      youngAndFatCellsStatistics.setBackground(YOUNG_AND_FAT.getColorBackground());
      fullAgeCellsStatistics.setBackground(FULL_AGE.getColorBackground());
      hungryCellsStatistics.setBackground(HUNGRY.getColorBackground());
      oldCellsStatistics.setBackground(OLD.getColorBackground());
  }

}
