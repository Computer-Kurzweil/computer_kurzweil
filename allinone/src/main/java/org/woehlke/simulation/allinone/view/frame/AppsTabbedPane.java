package org.woehlke.simulation.allinone.view.frame;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.simulation.allinone.model.ComputerKurzweilApplicationContext;
import org.woehlke.simulation.allinone.view.common.PanelBorder;
import org.woehlke.simulation.allinone.view.tabs.CyclicCellularAutomatonTab;
import org.woehlke.simulation.allinone.view.tabs.DiffusionLimitedAggregationTab;
import org.woehlke.simulation.allinone.view.tabs.MandelbrotTab;
import org.woehlke.simulation.allinone.view.tabs.SimulatedEvolutionTab;

import javax.swing.*;
import javax.swing.border.CompoundBorder;

@Log
@ToString
@EqualsAndHashCode(callSuper=true)
public class AppsTabbedPane extends JTabbedPane {

    @Getter private final ComputerKurzweilApplicationContext ctx;

    @Getter private final CyclicCellularAutomatonTab cyclicCellularAutomatonTab;
    @Getter private final DiffusionLimitedAggregationTab diffusionLimitedAggregationTab;
    @Getter private final MandelbrotTab mandelbrotTab;
    @Getter private final SimulatedEvolutionTab simulatedEvolutionTab;

    public AppsTabbedPane(ComputerKurzweilApplicationContext ctx) {
        this.ctx = ctx;
        CompoundBorder border = PanelBorder.getBorder();
        this.setBorder(border);
        String cyclicCellularAutomatonTabTitle = ctx.getProperties().getCca().getView().getTitle();
        String diffusionLimitedAggregationTabTitle = ctx.getProperties().getDla().getView().getTitle();
        String mandelbrotTabTitle =  ctx.getProperties().getMandelbrot().getView().getTitle();
        String simulatedEvolutionTabTitle = ctx.getProperties().getEvolution().getView().getTitle();
        this.cyclicCellularAutomatonTab = new CyclicCellularAutomatonTab(this.ctx);
        this.diffusionLimitedAggregationTab = new DiffusionLimitedAggregationTab(this.ctx);
        this.mandelbrotTab = new MandelbrotTab(this.ctx);
        this.simulatedEvolutionTab = new SimulatedEvolutionTab(this.ctx);
        this.add(cyclicCellularAutomatonTabTitle, this.cyclicCellularAutomatonTab);
        this.add(diffusionLimitedAggregationTabTitle, this.diffusionLimitedAggregationTab);
        this.add(mandelbrotTabTitle, this.mandelbrotTab);
        this.add(simulatedEvolutionTabTitle, this.simulatedEvolutionTab);
    }
}
