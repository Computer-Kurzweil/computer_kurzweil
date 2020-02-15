package org.woehlke.computer.kurzweil.application;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.GuiComponentTab;
import org.woehlke.computer.kurzweil.commons.tabs.TabContext;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.widgets.borders.PanelBorder;
import org.woehlke.computer.kurzweil.tabs.cca.CyclicCellularAutomatonTab;
import org.woehlke.computer.kurzweil.tabs.dla.DiffusionLimitedAggregationTab;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.MandelbrotTab;
import org.woehlke.computer.kurzweil.tabs.evolution.SimulatedEvolutionTab;
import org.woehlke.computer.kurzweil.commons.tabs.TabPanel;
import org.woehlke.computer.kurzweil.commons.tabs.Tab;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

@Log
@Getter
@ToString
@EqualsAndHashCode(callSuper=true)
public class ComputerKurzweilApplicationTabbedPane extends JTabbedPane implements Startable, GuiComponentTab {

    private final ComputerKurzweilApplicationContext ctx;
    private final CyclicCellularAutomatonTab cyclicCellularAutomatonTab;
    private final DiffusionLimitedAggregationTab diffusionLimitedAggregationTab;
    private final MandelbrotTab mandelbrotTab;
    private final SimulatedEvolutionTab simulatedEvolutionTab;
    private final List<TabPanel> apps = new ArrayList<>();

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
        TabPanel[] tabPanelAbstractPanels = {
            this.cyclicCellularAutomatonTab,
            this.diffusionLimitedAggregationTab,
            this.mandelbrotTab,
            this.simulatedEvolutionTab
        };
        int[] events = {
            KeyEvent.VK_1,
            KeyEvent.VK_2,
            KeyEvent.VK_3,
            KeyEvent.VK_4
        };
        int i = 0;
        ImageIcon icon = null;
        for(TabPanel tabPanelAbstract : tabPanelAbstractPanels){
            this.apps.add(tabPanelAbstract);
            this.addTab(tabPanelAbstract.getTitle(), icon, tabPanelAbstract, tabPanelAbstract.getSubTitle());
            this.setMnemonicAt(i,events[i]);
        }
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

    public void switchTab(){
        Tab tabPanelActive = getActiveTab();
        for(TabPanel tabPanel:apps){
            if(!tabPanelActive.equals(tabPanel)){
                tabPanel.stop();
            }
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

}
