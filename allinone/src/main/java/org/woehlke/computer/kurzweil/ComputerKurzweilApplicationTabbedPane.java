package org.woehlke.computer.kurzweil;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.AppContext;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.trashcan.signals.UserSignal;
import org.woehlke.computer.kurzweil.trashcan.signals.UserSlot;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.borders.PanelBorder;
import org.woehlke.computer.kurzweil.tabs.CyclicCellularAutomatonTab;
import org.woehlke.computer.kurzweil.tabs.DiffusionLimitedAggregationTab;
import org.woehlke.computer.kurzweil.tabs.MandelbrotTab;
import org.woehlke.computer.kurzweil.tabs.SimulatedEvolutionTab;
import org.woehlke.computer.kurzweil.tabs.common.Tab;
import org.woehlke.computer.kurzweil.tabs.common.TabPanel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Log
@Getter
@ToString
@EqualsAndHashCode(callSuper=true)
public class ComputerKurzweilApplicationTabbedPane extends JTabbedPane implements Startable, UserSlot {

    private final ComputerKurzweilApplicationContext ctx;
    private final CyclicCellularAutomatonTab cyclicCellularAutomatonTab;
    private final DiffusionLimitedAggregationTab diffusionLimitedAggregationTab;
    private final MandelbrotTab mandelbrotTab;
    private final SimulatedEvolutionTab simulatedEvolutionTab;
    private final List<AppContext> apps = new ArrayList<>();

    public ComputerKurzweilApplicationTabbedPane(
        ComputerKurzweilApplicationContext ctx
    ) {
        this.ctx = ctx;
        CompoundBorder border = PanelBorder.getBorder();
        this.setBorder(border);
        this.cyclicCellularAutomatonTab = new CyclicCellularAutomatonTab(this.ctx);
        this.diffusionLimitedAggregationTab = new DiffusionLimitedAggregationTab(this.ctx);
        this.mandelbrotTab = new MandelbrotTab(this.ctx);
        this.simulatedEvolutionTab = new SimulatedEvolutionTab(this.ctx);
        Tab[] tabPanels = {
            this.cyclicCellularAutomatonTab,
            this.diffusionLimitedAggregationTab,
            this.mandelbrotTab,
            this.simulatedEvolutionTab
        };
        for(Tab t:tabPanels){
            this.apps.add(t.getAppCtx());
            this.add(t.getTitle(), t);
        }
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
    public void handleUserSignal(UserSignal userSignal) {
        log.info("handleUserSignal: "+userSignal.name());
    }
}
