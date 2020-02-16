package org.woehlke.computer.kurzweil.tabs.evolution.widgets;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilProperties;
import org.woehlke.computer.kurzweil.tabs.evolution.SimulatedEvolutionContext;
import org.woehlke.computer.kurzweil.tabs.evolution.model.SimulatedEvolutionPopulationCensus;
import org.woehlke.computer.kurzweil.tabs.evolution.model.SimulatedEvolutionStatistics;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

import static org.woehlke.computer.kurzweil.tabs.evolution.model.CellLifeCycleStatus.*;

@Log
@Getter
@ToString(callSuper = true)
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

    private final String borderLabel;

    private final SimulatedEvolutionStatistics statistics;

    private final int cols = 3;
    private final int initialPopulation;
    private final String defaultTextField = "0";

    @ToString.Exclude
    private final SimulatedEvolutionContext tabCtx;
    @ToString.Exclude
    private final CompoundBorder border;
    @ToString.Exclude
    private final SimulatedEvolutionStatisticsPanelLayout layoutSubPanel;
    @ToString.Exclude
    private final JPanel subPanel;
    @ToString.Exclude
    private final FlowLayout layout;

    public SimulatedEvolutionStatisticsPanel(
      SimulatedEvolutionContext tabCtx
    ) {
        this.tabCtx = tabCtx;
        initialPopulation = this.tabCtx.getCtx().getProperties().getEvolution().getPopulation().getInitialPopulation();
        //setLabels();
        ComputerKurzweilProperties.Evolution.Population cfg = this.tabCtx.getCtx().getProperties().getEvolution().getPopulation();
        youngCellsLabel = new JLabel(cfg.getYoungCellsLabel());
        youngAndFatCellsLabel = new JLabel(cfg.getYoungAndFatCellsLabel());
        fullAgeCellsLabel = new JLabel(cfg.getFullAgeCellsLabel());
        hungryCellsLabel = new JLabel(cfg.getHungryCellsLabel());
        oldCellsLabel = new JLabel(cfg.getOldCellsLabel());
        populationLabel = new JLabel(cfg.getPopulationLabel());
        //setTextFields(initialPopulation);
        youngCellsStatistics = new JTextField(defaultTextField,cols);
        youngAndFatCellsStatistics = new JTextField(defaultTextField,cols);
        fullAgeCellsStatistics = new JTextField(defaultTextField,cols);
        hungryCellsStatistics = new JTextField(defaultTextField,cols);
        oldCellsStatistics = new JTextField(defaultTextField,cols);
        populationStatistics = new JTextField("" + initialPopulation, cols);
        this.setColors();
        layout = new FlowLayout();
        this.setLayout(layout);
        layoutSubPanel = new SimulatedEvolutionStatisticsPanelLayout();
        subPanel = new JPanel(layoutSubPanel);
        borderLabel = this.tabCtx.getCtx().getProperties().getEvolution().getPopulation().getPanelPopulationStatistics();
        border = this.tabCtx.getCtx().getBorder(borderLabel);
        subPanel.setBorder(border);
        JLabel[] labels ={
            youngCellsLabel,
            youngAndFatCellsLabel,
            fullAgeCellsLabel,
            hungryCellsLabel,
            oldCellsLabel,
            populationLabel
        };
        JTextField[] testFields = {
            youngCellsStatistics,
            youngAndFatCellsStatistics,
            fullAgeCellsStatistics,
            hungryCellsStatistics,
            oldCellsStatistics,
            populationStatistics
        };
        for(int i=0;  i < labels.length; i++){
            subPanel.add(labels[i]);
            subPanel.add(testFields[i]);
        }
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
