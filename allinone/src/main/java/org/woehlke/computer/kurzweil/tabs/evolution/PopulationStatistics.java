package org.woehlke.computer.kurzweil.tabs.evolution;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilProperties;
import org.woehlke.computer.kurzweil.tabs.evolution.model.SimulatedEvolutionPopulationCensus;
import org.woehlke.computer.kurzweil.tabs.evolution.model.SimulatedEvolutionStatistics;
import org.woehlke.computer.kurzweil.tabs.evolution.widgets.PopulationStatisticsElemenr;

import javax.swing.*;
import javax.swing.border.CompoundBorder;

import java.awt.*;

import static org.woehlke.computer.kurzweil.tabs.evolution.model.CellLifeCycleStatus.*;

@Log
@Getter
@ToString(callSuper = true)
public class PopulationStatistics extends JPanel {

    private final PopulationStatisticsElemenr youngCellsStatistics;
    private final PopulationStatisticsElemenr youngAndFatCellsStatistics;
    private final PopulationStatisticsElemenr fullAgeCellsStatistics;
    private final PopulationStatisticsElemenr hungryCellsStatistics;
    private final PopulationStatisticsElemenr oldCellsStatistics;
    private final PopulationStatisticsElemenr populationStatistics;

    private final SimulatedEvolutionStatistics statistics;
    private final String borderLabel;
    private final int initialPopulation;

    @ToString.Exclude
    private final SimulatedEvolutionContext tabCtx;
    @ToString.Exclude
    private final CompoundBorder border;
    @ToString.Exclude
    private final FlowLayout layoutSubPanel;
    @ToString.Exclude
    private final JPanel subPanel;

    public PopulationStatistics(
      SimulatedEvolutionContext tabCtx
    ) {
        this.tabCtx = tabCtx;
        layoutSubPanel = new FlowLayout();
        this.setLayout(layoutSubPanel);
        borderLabel = this.tabCtx.getCtx().getProperties().getEvolution().getPopulation().getPanelPopulationStatistics();
        border = this.tabCtx.getCtx().getBorder(borderLabel);
        subPanel = new JPanel(layoutSubPanel);
        subPanel.setBorder(border);
        initialPopulation = this.tabCtx.getCtx().getProperties().getEvolution().getPopulation().getInitialPopulation();
        ComputerKurzweilProperties.Evolution.Population cfg = this.tabCtx.getCtx().getProperties().getEvolution().getPopulation();
        youngCellsStatistics = new PopulationStatisticsElemenr(cfg.getYoungCellsLabel(),YOUNG);
        youngAndFatCellsStatistics = new PopulationStatisticsElemenr(cfg.getYoungAndFatCellsLabel(),YOUNG_AND_FAT);
        fullAgeCellsStatistics = new PopulationStatisticsElemenr(cfg.getFullAgeCellsLabel(),FULL_AGE);
        hungryCellsStatistics = new PopulationStatisticsElemenr(cfg.getHungryCellsLabel(),HUNGRY);
        oldCellsStatistics = new PopulationStatisticsElemenr(cfg.getOldCellsLabel(),OLD);
        populationStatistics = new PopulationStatisticsElemenr(cfg.getPopulationLabel(),POPULATION);
        populationStatistics.setText(initialPopulation);
        PopulationStatisticsElemenr[] statistics = {
            youngCellsStatistics,
            youngAndFatCellsStatistics,
            fullAgeCellsStatistics,
            hungryCellsStatistics,
            oldCellsStatistics,
            populationStatistics
        };
        for(PopulationStatisticsElemenr ps :statistics){
            subPanel.add(ps);
        }
        this.add(subPanel);
        this.statistics = new SimulatedEvolutionStatistics(  this.tabCtx );
    }

    public void update() {
        SimulatedEvolutionPopulationCensus simulatedEvolutionWorldStatistics = statistics.peek();;
        youngCellsStatistics.setText(simulatedEvolutionWorldStatistics.getYoungCells());
        youngAndFatCellsStatistics.setText(simulatedEvolutionWorldStatistics.getYoungAndFatCells());
        fullAgeCellsStatistics.setText(simulatedEvolutionWorldStatistics.getFullAgeCells());
        hungryCellsStatistics.setText(simulatedEvolutionWorldStatistics.getHungryCells());
        oldCellsStatistics.setText(simulatedEvolutionWorldStatistics.getOldCells());
        populationStatistics.setText(simulatedEvolutionWorldStatistics.getPopulation());
    }
}
