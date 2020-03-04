package org.woehlke.computer.kurzweil.tabs.simulatedevolution.population;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilProperties;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolutionContext;
import org.woehlke.computer.kurzweil.commons.layouts.FlowLayoutCenter;

import javax.swing.*;
import javax.swing.border.CompoundBorder;

import java.awt.*;

import static org.woehlke.computer.kurzweil.tabs.simulatedevolution.cell.CellLifeCycleStatus.*;

@Log4j2
@Getter
@ToString(callSuper = true,exclude = {"tabCtx","border","layout","layoutSubPanel"})
@EqualsAndHashCode(callSuper=true,exclude = {"tabCtx","border","layout","layoutSubPanel"})
public class PopulationStatisticsElementsPanel extends JPanel {

    private final PopulationStatisticsElement youngCellsStatistics;
    private final PopulationStatisticsElement youngAndFatCellsStatistics;
    private final PopulationStatisticsElement fullAgeCellsStatistics;
    private final PopulationStatisticsElement hungryCellsStatistics;
    private final PopulationStatisticsElement oldCellsStatistics;
    private final PopulationStatisticsElement populationStatistics;

    private final SimulatedEvolutionPopulationContainer statistics;
    private final String borderLabel;
    private final int initialPopulation;

    private final SimulatedEvolutionContext tabCtx;
    private final CompoundBorder border;
    private final FlowLayoutCenter layout;
    private final FlowLayout layoutSubPanel;

    public PopulationStatisticsElementsPanel(
      SimulatedEvolutionContext tabCtx
    ) {
        this.tabCtx = tabCtx;
        layoutSubPanel = new FlowLayout();
        this.setLayout(layoutSubPanel);
        borderLabel = this.tabCtx.getCtx().getProperties().getEvolution().getPopulation().getPanelPopulationStatistics();
        layout = new FlowLayoutCenter();
        border = tabCtx.getCtx().getBottomButtonsPanelBorder(borderLabel);
        this.setLayout(layout);
        this.setBorder(border);
        initialPopulation = this.tabCtx.getCtx().getProperties().getEvolution().getPopulation().getInitialPopulation();
        ComputerKurzweilProperties.SimulatedEvolution.Population cfg = this.tabCtx.getCtx().getProperties().getEvolution().getPopulation();
        youngCellsStatistics = new PopulationStatisticsElement(cfg.getYoungCellsLabel(),YOUNG);
        youngAndFatCellsStatistics = new PopulationStatisticsElement(cfg.getYoungAndFatCellsLabel(),YOUNG_AND_FAT);
        fullAgeCellsStatistics = new PopulationStatisticsElement(cfg.getFullAgeCellsLabel(),FULL_AGE);
        hungryCellsStatistics = new PopulationStatisticsElement(cfg.getHungryCellsLabel(),HUNGRY);
        oldCellsStatistics = new PopulationStatisticsElement(cfg.getOldCellsLabel(),OLD);
        populationStatistics = new PopulationStatisticsElement(cfg.getPopulationLabel(),POPULATION);
        populationStatistics.setText(initialPopulation);
        PopulationStatisticsElement[] statistics = {
            youngCellsStatistics,
            youngAndFatCellsStatistics,
            fullAgeCellsStatistics,
            hungryCellsStatistics,
            oldCellsStatistics,
            populationStatistics
        };
        for(PopulationStatisticsElement ps :statistics){
            this.add(ps);
        }
        this.statistics = new SimulatedEvolutionPopulationContainer(  this.tabCtx );
    }

    public void update() {
        SimulatedEvolutionPopulation simulatedEvolutionWorldStatistics = statistics.peek();;
        youngCellsStatistics.setText(simulatedEvolutionWorldStatistics.getYoungCells());
        youngAndFatCellsStatistics.setText(simulatedEvolutionWorldStatistics.getYoungAndFatCells());
        fullAgeCellsStatistics.setText(simulatedEvolutionWorldStatistics.getFullAgeCells());
        hungryCellsStatistics.setText(simulatedEvolutionWorldStatistics.getHungryCells());
        oldCellsStatistics.setText(simulatedEvolutionWorldStatistics.getOldCells());
        populationStatistics.setText(simulatedEvolutionWorldStatistics.getPopulation());
    }
}
