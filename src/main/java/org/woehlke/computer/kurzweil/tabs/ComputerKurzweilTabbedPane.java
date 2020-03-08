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
import org.woehlke.computer.kurzweil.tabs.tetris.TetrisTab;
import org.woehlke.computer.kurzweil.tabs.turmite.TurmiteTab;
import org.woehlke.computer.kurzweil.tabs.wator.WaTorTab;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;


/**
 * https://docs.oracle.com/en/java/javase/13/docs/api/java.base/java/util/concurrent/ThreadPoolExecutor.html
 * https://docs.oracle.com/en/java/javase/13/docs/api/java.base/java/util/concurrent/Executor.html
 * TODO: https://github.com/Computer-Kurzweil/computer_kurzweil/issues/18
 * TODO: https://github.com/Computer-Kurzweil/computer_kurzweil/issues/19
 * http://openbook.rheinwerk-verlag.de/javainsel9/javainsel_14_004.htm
 */
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
    private final ConwaysGameOfLifeTab conwaysGameOfLifeTab;
    private final SierpinskiTriangleTab sierpinskiTriangleTab;
    private final TetrisTab tetrisTab;
    private final TurmiteTab turmiteTab;
    private final WaTorTab waTorTab;

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
        this.kochSnowflakeTab = new KochSnowflakeTab(this);
        this.sameGameTab = new SameGameTab(this);
        this.conwaysGameOfLifeTab = new ConwaysGameOfLifeTab(this);
        this.sierpinskiTriangleTab = new SierpinskiTriangleTab(this);
        this.tetrisTab = new TetrisTab(this);
        this.turmiteTab =new TurmiteTab(this );
        this.waTorTab = new WaTorTab(this);
        TabPanel[] tabPanelAbstractPanels = {
            this.cyclicCellularAutomatonTab,
            this.simulatedEvolutionTab,
            this.randomWalkTab,
            this.diffusionLimitedAggregationTab,
            this.mandelbrotTab,
            this.kochSnowflakeTab,
            this.sameGameTab,
            this.conwaysGameOfLifeTab,
            this.sierpinskiTriangleTab,
            this.tetrisTab,
            this.turmiteTab,
            this.waTorTab
        };
        int[] events = {
            KeyEvent.VK_F1,
            KeyEvent.VK_F2,
            KeyEvent.VK_F3,
            KeyEvent.VK_F4,
            KeyEvent.VK_F5,
            KeyEvent.VK_F6,
            KeyEvent.VK_F7,
            KeyEvent.VK_F8,
            KeyEvent.VK_F9,
            KeyEvent.VK_F10,
            KeyEvent.VK_F11,
            KeyEvent.VK_F12
        };
        int i = 0;
        ImageIcon icon = null;
        for(TabPanel tabPanelAbstract : tabPanelAbstractPanels){
            this.apps.add(tabPanelAbstract);
            this.addTab(tabPanelAbstract.getTitle(), icon, tabPanelAbstract, tabPanelAbstract.getSubTitle());
            this.setMnemonicAt(i,events[i]);
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

    @Override
    public void showMe() {
        getActiveTab().showMe();
    }

}
