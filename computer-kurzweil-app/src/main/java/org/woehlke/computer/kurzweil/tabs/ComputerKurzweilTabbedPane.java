package org.woehlke.computer.kurzweil.tabs;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilContext;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.tabs.cca.CyclicCellularAutomatonTab;
import org.woehlke.computer.kurzweil.tabs.dla.DiffusionLimitedAggregationTab;
import org.woehlke.computer.kurzweil.tabs.mandelbrot2julia.MandelbrotTab;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolutionTab;
import org.woehlke.computer.kurzweil.tabs.randomwalk.RandomWalkTab;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * https://docs.oracle.com/en/java/javase/13/docs/api/java.base/java/util/concurrent/ThreadPoolExecutor.html
 * https://docs.oracle.com/en/java/javase/13/docs/api/java.base/java/util/concurrent/Executor.html
 * TODO: https://github.com/Computer-Kurzweil/computer_kurzweil/issues/18
 * TODO: https://github.com/Computer-Kurzweil/computer_kurzweil/issues/19
 * http://openbook.rheinwerk-verlag.de/javainsel9/javainsel_14_004.htm
 */
@Log
@Getter
@ToString(exclude={"ctx","apps","border"},callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class ComputerKurzweilTabbedPane extends JTabbedPane implements Startable {

    private static final long serialVersionUID = 7526471155622776147L;

    private final CyclicCellularAutomatonTab cyclicCellularAutomatonTab;
    private final RandomWalkTab randomWalkTab;
    private final DiffusionLimitedAggregationTab diffusionLimitedAggregationTab;
    private final MandelbrotTab mandelbrotTab;
    private final SimulatedEvolutionTab simulatedEvolutionTab;

    private final List<TabPanel> apps = new ArrayList<>();
    private final CompoundBorder border;
    private final ComputerKurzweilContext ctx;

    public ComputerKurzweilTabbedPane(
        ComputerKurzweilContext ctx
    ) {
        this.ctx = ctx;
        this.border = ctx.getTabbedPaneBorder();
        this.cyclicCellularAutomatonTab = new CyclicCellularAutomatonTab(this);
        this.randomWalkTab = new RandomWalkTab(this);
        this.diffusionLimitedAggregationTab = new DiffusionLimitedAggregationTab(this);
        this.mandelbrotTab = new MandelbrotTab(this);
        this.simulatedEvolutionTab = new SimulatedEvolutionTab(this);
        TabPanel[] tabPanelAbstractPanels = {
            this.cyclicCellularAutomatonTab,
            this.simulatedEvolutionTab,
            this.randomWalkTab,
            this.diffusionLimitedAggregationTab,
            this.mandelbrotTab
        };
        for(int i = 0; i<tabPanelAbstractPanels.length; i++){
            this.apps.add(tabPanelAbstractPanels[i]);
            this.addTab(
                tabPanelAbstractPanels[i].getTitle(),
                tabPanelAbstractPanels[i].getIcon(),
                tabPanelAbstractPanels[i],
                tabPanelAbstractPanels[i].getToolTipText());
            this.setMnemonicAt(i,tabPanelAbstractPanels[i].getKeyEvent());
        }
        this.setBorder(border);
        //TODO: change tab lister:
        //this.addAncestorListener(this);
    }

    public Tab getActiveTab(){
        Component c = this.getSelectedComponent();
        if(c instanceof Tab){
            return (Tab)c;
        } else {
            return null;
        }
    }

    @Override
    public void start(){
        log.info("start");
        for(TabPanel tabPanel:apps){
            tabPanel.stop();
        }
        getActiveTab().start();
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stop");
        for(TabPanel tabPanel:apps){
            tabPanel.stop();
        }
        log.info("stopped");
    }

}
