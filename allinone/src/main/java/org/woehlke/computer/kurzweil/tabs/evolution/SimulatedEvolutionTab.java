package org.woehlke.computer.kurzweil.tabs.evolution;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.commons.tabs.TabPanel;
import org.woehlke.computer.kurzweil.tabs.evolution.widgets.SimulatedEvolutionButtonRowPanel;
import org.woehlke.computer.kurzweil.tabs.evolution.widgets.SimulatedEvolutionStatisticsPanel;
import org.woehlke.computer.kurzweil.widgets.layouts.TabLayout;
import org.woehlke.computer.kurzweil.widgets.PanelSubtitle;
import org.woehlke.computer.kurzweil.widgets.StartStopButtonsPanel;
import org.woehlke.computer.kurzweil.commons.tabs.Tab;

import javax.swing.border.CompoundBorder;
import java.awt.event.ActionEvent;


@Log
@Getter
public class SimulatedEvolutionTab extends TabPanel implements Tab {

    private final ComputerKurzweilApplicationContext ctx;
    private final SimulatedEvolutionContext tabCtx;
    private final CompoundBorder border;
    private final TabLayout layout;
    private final StartStopButtonsPanel startStopButtonsPanel;
    private final SimulatedEvolutionStatisticsPanel statisticsPanel;
    private final SimulatedEvolutionButtonRowPanel panelButtons;
    private final String subtitle;
    private final PanelSubtitle panelSubtitle;
    private final SimulatedEvolutionCanvas canvas;

    public SimulatedEvolutionTab(ComputerKurzweilApplicationContext ctx) {
        this.ctx = ctx;
        this.border = this.ctx.getBorder();
        this.layout = new TabLayout(this);
        this.setBorder(border);
        this.setLayout(layout);
        this.tabCtx = new SimulatedEvolutionContext(this);
        this.canvas = new SimulatedEvolutionCanvas( this);
        this.tabCtx.setCanvas(this.canvas);
        this.tabCtx.setStepper(this.canvas.getWorld());
        this.statisticsPanel = this.canvas.getStatisticsPanel();
        this.panelButtons =  this.canvas.getPanelButtons();
        this.startStopButtonsPanel = new StartStopButtonsPanel( this );
        this.subtitle = ctx.getProperties().getEvolution().getView().getSubtitle();
        this.panelSubtitle = new PanelSubtitle(subtitle);
        this.add(this.panelSubtitle);
        this.add(this.canvas);
        this.add(this.statisticsPanel);
        this.add(this.panelButtons);
        this.add(this.startStopButtonsPanel);
        this.canvas.getPanelButtons().getFoodPanel().getButtonFoodPerDayIncrease().addActionListener(this);
        this.canvas.getPanelButtons().getFoodPanel().getButtonFoodPerDayDecrease().addActionListener(this);
        this.canvas.getPanelButtons().getGardenOfEdenPanel().getButtonToggleGardenOfEden().addActionListener(this);
        this.ctx.getFrame().pack();
        this.startStopButtonsPanel.stop();
    }

    @Override
    public void start() {
        log.info("start");
        this.canvas.getWorld().start();
        this.getTabCtx().startController();
        this.getTabCtx().getController().start();
        this.ctx.getFrame().pack();
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        log.info("start with canvas x="+x+" y="+y);
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stop");
        this.canvas.stop();
        this.getTabCtx().stopController();
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        log.info("stop with canvas x="+x+" y="+y);
        log.info("stopped");
    }

    @Override
    public void showMe() {
        log.info("showMe");
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        log.info("showMe with canvas x="+x+" y="+y);
    }

    @Override
    public String getTitle() {
        return this.tabCtx.getCtx().getProperties().getEvolution().getView().getTitle();
    }

    @Override
    public String getSubTitle() {
        return this.tabCtx.getCtx().getProperties().getEvolution().getView().getSubtitle();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() ==  this.canvas.getPanelButtons().getFoodPanel().getButtonFoodPerDayIncrease()) {
            log.info("actionPerformed: increaseFoodPerDay");
            tabCtx.increaseFoodPerDay();
            this.canvas.update();
        } else if (ae.getSource() == this.canvas.getPanelButtons().getFoodPanel().getButtonFoodPerDayDecrease()) {
            log.info("actionPerformed: decreaseFoodPerDay");
            tabCtx.decreaseFoodPerDay();
            this.canvas.update();
        } else if (ae.getSource() == this.canvas.getPanelButtons().getGardenOfEdenPanel().getButtonToggleGardenOfEden()) {
            log.info("actionPerformed: toggleGardenOfEden");
            tabCtx.toggleGardenOfEden();
            this.canvas.update();
        }
        if(ae.getSource() ==  this.startStopButtonsPanel.getStartButton()){
            log.info("actionPerformed: start");
            this.start();
            this.canvas.update();
        }
        if(ae.getSource() ==  this.startStopButtonsPanel.getStopButton()){
            log.info("actionPerformed: stop");
            this.stop();
            this.canvas.update();
        }
    }
}
