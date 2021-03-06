package org.woehlke.computer.kurzweil.tabs.simulatedevolution.canvas.population;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Delegate;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilProperties;
import org.woehlke.computer.kurzweil.commons.Updateable;
import org.woehlke.computer.kurzweil.commons.layouts.FlowLayoutCenter;
import org.woehlke.computer.kurzweil.commons.widgets.SubTab;
import org.woehlke.computer.kurzweil.commons.widgets.SubTabImpl;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolution;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolutionContext;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.population.SimulatedEvolutionPopulation;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.population.SimulatedEvolutionPopulationContainer;

import javax.swing.border.CompoundBorder;
import java.awt.*;

import static org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.cell.CellLifeCycleStatus.*;

@Log4j2
@Getter
@ToString(callSuper = true,exclude = {"tabCtx","border","layout","layoutSubPanel"})
@EqualsAndHashCode(callSuper=true,exclude = {"tabCtx","border","layout","layoutSubPanel"})
public class PopulationStatisticsElementsPanelLifeCycle extends SubTabImpl implements SimulatedEvolution, SubTab, Updateable {

    private final PopulationStatisticsElement youngCellsElement;
    private final PopulationStatisticsElement youngAndFatCellsElement;
    private final PopulationStatisticsElement fullAgeCellsElement;
    private final PopulationStatisticsElement hungryCellsElement;
    private final PopulationStatisticsElement oldCellsElement;

    private final String borderLabel;

    private final int initialPopulation;
    private final String youngCellsLabel;
    private final String youngAndFatCellsLabel;
    private final String fullAgeCellsLabel;
    private final String hungryCellsLabel;
    private final String oldCellsLabel;

    private final SimulatedEvolutionContext tabCtx;
    private final CompoundBorder border;
    private final FlowLayoutCenter layout;
    private final FlowLayout layoutSubPanel;

    public PopulationStatisticsElementsPanelLifeCycle(
      SimulatedEvolutionContext tabCtx
    ) {
        super(tabCtx.getCtx().getProperties().getSimulatedevolution().getPopulation().getPanelLifeCycleStatistics(),tabCtx.getCtx().getProperties());
        this.tabCtx = tabCtx;
        layoutSubPanel = new FlowLayout();
        this.setLayout(layoutSubPanel);
        borderLabel = this.tabCtx.getCtx().getProperties().getSimulatedevolution().getPopulation().getPanelPopulationStatistics();
        layout = new FlowLayoutCenter();
        border = tabCtx.getCtx().getBottomButtonsPanelBorder(borderLabel);
        this.setLayout(layout);
        this.setBorder(border);
        ComputerKurzweilProperties.SimulatedEvolution.Population cfg = this.tabCtx.getCtx().getProperties().getSimulatedevolution().getPopulation();
        initialPopulation = cfg.getInitialPopulation();
        youngCellsLabel = cfg.getYoungCellsLabel();
        youngAndFatCellsLabel = cfg.getYoungAndFatCellsLabel();
        fullAgeCellsLabel = cfg.getFullAgeCellsLabel();
        hungryCellsLabel = cfg.getHungryCellsLabel();
        oldCellsLabel = cfg.getOldCellsLabel();
        youngCellsElement = new PopulationStatisticsElement(youngCellsLabel,YOUNG);
        youngAndFatCellsElement = new PopulationStatisticsElement(youngAndFatCellsLabel,YOUNG_AND_FAT);
        fullAgeCellsElement = new PopulationStatisticsElement(fullAgeCellsLabel,FULL_AGE);
        hungryCellsElement = new PopulationStatisticsElement(hungryCellsLabel,HUNGRY);
        oldCellsElement = new PopulationStatisticsElement(oldCellsLabel,OLD);
        this.add(youngCellsElement);
        this.add(youngAndFatCellsElement);
        this.add(fullAgeCellsElement);
        this.add(hungryCellsElement);
        this.add(oldCellsElement);
        update();
    }

    public void update() {
        SimulatedEvolutionPopulation population = tabCtx.getTab().getPopulation();
        if(population == null){
            log.info("update with currentGeneration=null");
        }else {
            log.info("update with currentGeneration="+population.toString());
            this.youngCellsElement.setText(population.getYoungCells());
            this.youngAndFatCellsElement.setText(population.getYoungAndFatCells());
            this.fullAgeCellsElement.setText(population.getFullAgeCells());
            this.hungryCellsElement.setText(population.getHungryCells());
            this.oldCellsElement.setText(population.getOldCells());
            this.repaint();
        }
    }

}
