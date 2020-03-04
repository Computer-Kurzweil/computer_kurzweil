package org.woehlke.computer.kurzweil.tabs;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilContext;
import org.woehlke.computer.kurzweil.commons.gui.GuiComponent;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.tabs.cca.CyclicCellularAutomatonTab;
import org.woehlke.computer.kurzweil.tabs.dla.DiffusionLimitedAggregationTab;
import org.woehlke.computer.kurzweil.tabs.gameoflive.ConwaysGameOfLifeTab;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.MandelbrotTab;
import org.woehlke.computer.kurzweil.tabs.sierpinskitriangle.SierpinskiTriangleTab;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolutionTab;
import org.woehlke.computer.kurzweil.tabs.randomwalk.RandomWalkTab;
import org.woehlke.computer.kurzweil.tabs.kochsnowflake.KochSnowflakeTab;
import org.woehlke.computer.kurzweil.tabs.samegame.SameGameTab;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Getter
@ToString(exclude={"ctx","apps","border"},callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class ComputerKurzweilTabbedPane extends JTabbedPane implements Startable,
    GuiComponent {

    private final CyclicCellularAutomatonTab cyclicCellularAutomatonTab;
    private final RandomWalkTab randomWalkTab;
    private final DiffusionLimitedAggregationTab diffusionLimitedAggregationTab;
    private final MandelbrotTab mandelbrotTab;
    private final SimulatedEvolutionTab simulatedEvolutionTab;
    private final KochSnowflakeTab kochSnowflakeTab;
    private final SameGameTab sameGameTab;
    ConwaysGameOfLifeTab conwaysGameOfLifeTab;
    SierpinskiTriangleTab sierpinskiTriangleTab;

    public ComputerKurzweilTabbedPane(
        ComputerKurzweilContext ctx
    ) {
        this.ctx = ctx;
        this.border = ctx.getTabbedPaneBorder();
        this.setBorder(border);
        this.cyclicCellularAutomatonTab = new CyclicCellularAutomatonTab(this);
        this.randomWalkTab = new RandomWalkTab(this);
        this.diffusionLimitedAggregationTab = new DiffusionLimitedAggregationTab(this);
        this.mandelbrotTab = new MandelbrotTab(this);
        this.simulatedEvolutionTab = new SimulatedEvolutionTab(this);
        this.kochSnowflakeTab = new KochSnowflakeTab(this);
        this.sameGameTab = new SameGameTab(this);
        TabPanel[] tabPanelAbstractPanels = {
            this.cyclicCellularAutomatonTab,
            this.randomWalkTab,
            this.diffusionLimitedAggregationTab,
            this.simulatedEvolutionTab,
            this.mandelbrotTab,
            this.kochSnowflakeTab,
            this.sameGameTab
        };
        int[] events = {
            KeyEvent.VK_F1,
            KeyEvent.VK_F2,
            KeyEvent.VK_F3,
            KeyEvent.VK_F4,
            KeyEvent.VK_F5,
            KeyEvent.VK_F6,
            KeyEvent.VK_F7
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
        getActiveTab().stop();
        log.info("stopped");
    }

    @Override
    public void showMe() {
        getActiveTab().showMe();
    }

    private final List<TabPanel> apps = new ArrayList<>();
    private final CompoundBorder border;
    private final ComputerKurzweilContext ctx;
}
