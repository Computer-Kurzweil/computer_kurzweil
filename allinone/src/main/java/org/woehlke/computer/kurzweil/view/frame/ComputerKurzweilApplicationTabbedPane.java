package org.woehlke.computer.kurzweil.view.frame;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.config.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.control.events.UserSignal;
import org.woehlke.computer.kurzweil.control.events.UserSlot;
import org.woehlke.computer.kurzweil.control.startables.Startable;
import org.woehlke.computer.kurzweil.view.common.PanelBorder;
import org.woehlke.computer.kurzweil.view.tabs.CyclicCellularAutomatonTab;
import org.woehlke.computer.kurzweil.view.tabs.DiffusionLimitedAggregationTab;
import org.woehlke.computer.kurzweil.view.tabs.MandelbrotTab;
import org.woehlke.computer.kurzweil.view.tabs.SimulatedEvolutionTab;
import org.woehlke.computer.kurzweil.view.tabs.parts.TabPanel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

@Log
@ToString
@EqualsAndHashCode(callSuper=true)
public class ComputerKurzweilApplicationTabbedPane extends JTabbedPane implements Startable, UserSlot {

    @Getter private final ComputerKurzweilApplicationContext ctx;

    @Getter private final CyclicCellularAutomatonTab cyclicCellularAutomatonTab;
    @Getter private final DiffusionLimitedAggregationTab diffusionLimitedAggregationTab;
    @Getter private final MandelbrotTab mandelbrotTab;
    @Getter private final SimulatedEvolutionTab simulatedEvolutionTab;

    public ComputerKurzweilApplicationTabbedPane(ComputerKurzweilApplicationContext ctx) {
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

    public TabPanel getActiveTab(){
        Component c = this.getSelectedComponent();
        if(c instanceof TabPanel){
            return (TabPanel)c;
        } else {
            return null;
        }
    }

    @Override
    public void start(){
        log.info("start");
        getActiveTab().start();
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stop");
        getActiveTab().stop();
        log.info("stopped");
    }

    @Override
    public void update() {
        log.info("update");
        getActiveTab().update();
        log.info("updated");
    }

    @Override
    public void handleUserSignal(UserSignal userSignal) {
        log.info("handleUserSignal: "+userSignal.name());
    }
}
