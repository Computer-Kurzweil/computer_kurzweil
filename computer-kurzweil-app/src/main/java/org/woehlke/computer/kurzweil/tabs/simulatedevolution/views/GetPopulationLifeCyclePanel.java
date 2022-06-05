package org.woehlke.computer.kurzweil.tabs.simulatedevolution.views;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilProperties;
import org.woehlke.computer.kurzweil.commons.Updateable;
import org.woehlke.computer.kurzweil.commons.layouts.FlowLayoutCenter;
import org.woehlke.computer.kurzweil.commons.widgets.SubTab;
import org.woehlke.computer.kurzweil.commons.widgets.SubTabImpl;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.config.SimulatedEvolution;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.config.SimulatedEvolutionContext;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.population.CellPopulationRecord;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

import static org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.cell.CellLifeCycleStatus.*;

@Log
@Getter
@ToString(callSuper = true,exclude = {"tabCtx","border","layout","layoutSubPanel"})
@EqualsAndHashCode(callSuper=true,exclude = {"tabCtx","border","layout","layoutSubPanel"})
public class GetPopulationLifeCyclePanel extends SubTabImpl
    implements SimulatedEvolution, SubTab, Updateable {

    private static final long serialVersionUID = 7526471155622776147L;

    private final SimulatedEvolutionContext tabCtx;

    private final JTextField youngCellsElement = new JTextField("0",4);
    private final JTextField youngAndFatCellsElement = new JTextField("0",4);
    private final JTextField fullAgeCellsElement = new JTextField("0",4);
    private final JTextField hungryCellsElement = new JTextField("0",4);
    private final JTextField oldCellsElement = new JTextField("0",4);

    public GetPopulationLifeCyclePanel(
      SimulatedEvolutionContext tabCtx
    ) {
        super(
            tabCtx.getCtx().getProperties().getSimulatedevolution().getPopulation().getPanelLifeCycleStatistics(),
            tabCtx.getCtx().getProperties()
        );
        this.tabCtx = tabCtx;
        FlowLayout layoutSubPanel = new FlowLayout();
        this.setLayout(layoutSubPanel);
        String borderLabel =
            this.tabCtx.getCtx().getProperties().getSimulatedevolution().getPopulation().getPanelPopulationStatistics();
        FlowLayoutCenter layout = new FlowLayoutCenter();
        CompoundBorder border = tabCtx.getCtx().getBottomButtonsPanelBorder(borderLabel);
        this.setLayout(layout);
        this.setBorder(border);
        ComputerKurzweilProperties.SimulatedEvolution.Population cfg =
            this.tabCtx.getCtx().getProperties().getSimulatedevolution().getPopulation();
        JLabel youngCellsLabel = new JLabel(cfg.getYoungCellsLabel());
        JLabel youngAndFatCellsLabel = new JLabel(cfg.getYoungAndFatCellsLabel());
        JLabel fullAgeCellsLabel = new JLabel(cfg.getFullAgeCellsLabel());
        JLabel hungryCellsLabel = new JLabel(cfg.getHungryCellsLabel());
        JLabel oldCellsLabel = new JLabel(cfg.getOldCellsLabel());
        youngCellsElement.setForeground(YOUNG.getColorForeground());
        youngCellsElement.setBackground(YOUNG.getColorBackground());
        youngAndFatCellsElement.setForeground(YOUNG_AND_FAT.getColorForeground());
        youngAndFatCellsElement.setBackground(YOUNG_AND_FAT.getColorBackground());
        fullAgeCellsElement.setForeground(FULL_AGE.getColorForeground());
        fullAgeCellsElement.setBackground(FULL_AGE.getColorBackground());
        hungryCellsElement.setForeground(HUNGRY.getColorForeground());
        hungryCellsElement.setBackground(HUNGRY.getColorBackground());
        oldCellsElement.setForeground(OLD.getColorForeground());
        oldCellsElement.setBackground(OLD.getColorBackground());
        this.add(youngCellsLabel);
        this.add(youngCellsElement);
        this.add(youngAndFatCellsLabel);
        this.add(youngAndFatCellsElement);
        this.add(fullAgeCellsLabel);
        this.add(fullAgeCellsElement);
        this.add(hungryCellsLabel);
        this.add(hungryCellsElement);
        this.add(oldCellsLabel);
        this.add(oldCellsElement);
        update();
    }

    public void update() {
        CellPopulationRecord population = tabCtx.getTab().getPopulation();
        //log.info("update with currentGeneration="+population.toString());
        this.youngCellsElement.setText(population.getYoungCells()+"");
        this.youngAndFatCellsElement.setText(population.getYoungAndFatCells()+"");
        this.fullAgeCellsElement.setText(population.getFullAgeCells()+"");
        this.hungryCellsElement.setText(population.getHungryCells()+"");
        this.oldCellsElement.setText(population.getOldCells()+"");
        this.repaint();
    }

}
