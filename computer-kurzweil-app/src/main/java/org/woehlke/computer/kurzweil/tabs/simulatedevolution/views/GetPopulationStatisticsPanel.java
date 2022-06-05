package org.woehlke.computer.kurzweil.tabs.simulatedevolution.views;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilProperties;
import org.woehlke.computer.kurzweil.commons.layouts.FlowLayoutCenter;
import org.woehlke.computer.kurzweil.commons.widgets.SubTab;
import org.woehlke.computer.kurzweil.commons.widgets.SubTabImpl;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.config.SimulatedEvolution;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.config.SimulatedEvolutionContext;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.population.CellPopulationRecord;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.views.population.PopulationStatisticsElement;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

import static org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.cell.CellLifeCycleStatus.POPULATION;

@Log
@Getter
@ToString(callSuper = true,exclude = {"tabCtx","border","layout","layoutSubPanel"})
@EqualsAndHashCode(callSuper=true,exclude = {"tabCtx","border","layout","layoutSubPanel"})
public class GetPopulationStatisticsPanel extends SubTabImpl implements SimulatedEvolution, SubTab {

    private static final long serialVersionUID = 7526471155622776147L;

    private final SimulatedEvolutionContext tabCtx;

    private final JTextField populationElement;
    private final JTextField generationOldestElement;
    private final JTextField generationYoungestElement;
    private final String borderLabel;

    public GetPopulationStatisticsPanel(
        SimulatedEvolutionContext tabCtx
    ) {
        super(
            tabCtx.getCtx().getProperties().getSimulatedevolution().getPopulation().getPanelPopulationStatistics(),
            tabCtx.getCtx().getProperties()
        );
        this.tabCtx = tabCtx;
        FlowLayout layoutSubPanel = new FlowLayout();
        this.setLayout(layoutSubPanel);
        borderLabel = this.tabCtx.getCtx().getProperties().getSimulatedevolution().getPopulation().getPanelPopulationStatistics();
        FlowLayoutCenter layout = new FlowLayoutCenter();
        CompoundBorder border = tabCtx.getCtx().getBottomButtonsPanelBorder(borderLabel);
        this.setLayout(layout);
        this.setBorder(border);
        ComputerKurzweilProperties.SimulatedEvolution.Population cfg = this.tabCtx.getCtx().getProperties().getSimulatedevolution().getPopulation();
        JLabel populationLabel = new JLabel(cfg.getPopulationLabel());
        JLabel generationOldestLabel =  new JLabel(cfg.getGenerationOldestLabel());
        JLabel generationYoungestLabel =  new JLabel(cfg.getGenerationYoungestLabel());
        populationElement = new JTextField("0",3);
        generationYoungestElement = new JTextField("0",3);
        generationOldestElement = new JTextField("0",3);
        this.add(populationLabel);
        this.add(populationElement);
        this.add(generationOldestLabel);
        this.add(generationYoungestElement);
        this.add(generationYoungestLabel);
        this.add(generationOldestElement);
        update();
    }

    public void update() {
        CellPopulationRecord population = tabCtx.getTab().getPopulation();
        //log.info("update with currentGeneration="+population.toString());
        populationElement.setText(population.getPopulation()+"");
        generationYoungestElement.setText(population.getGenerationYoungest()+"");
        generationOldestElement.setText(population.getGenerationOldest()+"");
    }
}
