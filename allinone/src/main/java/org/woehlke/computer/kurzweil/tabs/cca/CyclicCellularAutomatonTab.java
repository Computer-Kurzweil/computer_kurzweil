package org.woehlke.computer.kurzweil.tabs.cca;


import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.tabs.TabType;
import org.woehlke.computer.kurzweil.commons.tabs.TabPanel;
import org.woehlke.computer.kurzweil.widgets.PanelSubtitle;
import org.woehlke.computer.kurzweil.widgets.StartStopButtonsPanel;
import org.woehlke.computer.kurzweil.widgets.layouts.TabLayout;
import org.woehlke.computer.kurzweil.commons.tabs.Tab;

import java.awt.event.ActionEvent;

@Log
@Getter
public class CyclicCellularAutomatonTab extends TabPanel implements Tab {

    private final CyclicCellularAutomatonCanvas canvas;
    private final CyclicCellularAutomatonContext tabCtx;
    private final ComputerKurzweilApplicationContext ctx;

    private final StartStopButtonsPanel startStopButtonsPanel;
    private final String labelSubtitle;
    private final PanelSubtitle panelSubtitle;
    private final CyclicCellularAutomatonButtons neighbourhoodButtonsPanel;

    private final static TabType TAB_TYPE = TabType.CYCLIC_CELLULAR_AUTOMATON;

    public CyclicCellularAutomatonTab(ComputerKurzweilApplicationContext ctx) {
        this.ctx = ctx;
        this.setLayout(new TabLayout(this));
        this.tabCtx = new CyclicCellularAutomatonContext(this);
        this.canvas = this.tabCtx.getCanvas();
        this.startStopButtonsPanel = new StartStopButtonsPanel( this );
        this.labelSubtitle = this.tabCtx.getCtx().getProperties().getCca().getView().getSubtitle();
        this.panelSubtitle = new PanelSubtitle(labelSubtitle);
        this.neighbourhoodButtonsPanel = new CyclicCellularAutomatonButtons(this.canvas);
        this.add(this.panelSubtitle);
        this.add(this.canvas);
        this.add(this.neighbourhoodButtonsPanel);
        this.add(this.startStopButtonsPanel);
        this.neighbourhoodButtonsPanel.getButtonVonNeumann().addActionListener(this);
        this.neighbourhoodButtonsPanel.getButtonMoore().addActionListener(this);
        this.neighbourhoodButtonsPanel.getButtonWoehlke().addActionListener(this);
        this.startStopButtonsPanel.getStartButton().addActionListener(this);
        this.startStopButtonsPanel.getStopButton().addActionListener(this);
        this.startStopButtonsPanel.stop();
        showMe();
    }

    @Override
    public void start() {
        log.info("start");
        this.showMe();
        this.canvas.start();
        this.startStopButtonsPanel.start();
        this.getTabCtx().startController();
        this.getTabCtx().getController().start();
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stop");
        this.canvas.stop();
        this.startStopButtonsPanel.stop();
        this.getTabCtx().stopController();
        log.info("stopped");
    }

    @Override
    public void showMe() {
        log.info("showMe");
        /*
        this.canvas.getStartStopButtonsPanel().setVisible(true);
        this.canvas.getNeighbourhoodButtonsPanel().setVisible(true);
        this.canvas.getPanelSubtitle().setVisible(true);
        this.canvas.setVisible(true);
        this.setVisible(true);
        */
    }

    @Override
    public String getTitle() {
        return ctx.getProperties().getCca().getView().getTitle();
    }

    @Override
    public String getSubTitle() {
        return ctx.getProperties().getCca().getView().getSubtitle();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() ==  this.neighbourhoodButtonsPanel.getButtonVonNeumann()) {
            this.canvas.startWithNeighbourhoodVonNeumann();
            this.start();
        } else if (ae.getSource() ==  this.neighbourhoodButtonsPanel.getButtonMoore()) {
            this.canvas.startWithNeighbourhoodMoore();
            this.start();
        } else if (ae.getSource() ==  this.neighbourhoodButtonsPanel.getButtonWoehlke()) {
            this.canvas.startWithNeighbourhoodWoehlke();
            this.start();
        }
        if(ae.getSource() == this.startStopButtonsPanel.getStartButton()){
            this.start();
        }
        if(ae.getSource() == this.startStopButtonsPanel.getStopButton()){
            this.stop();
        }
    }
}
