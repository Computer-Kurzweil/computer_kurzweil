package org.woehlke.computer.kurzweil.tabs.cca;


import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.tabs.TabType;
import org.woehlke.computer.kurzweil.commons.tabs.TabPanel;
import org.woehlke.computer.kurzweil.widgets.PanelSubtitle;
import org.woehlke.computer.kurzweil.widgets.StartStopButtonsPanel;
import org.woehlke.computer.kurzweil.widgets.layouts.TabLayout;
import org.woehlke.computer.kurzweil.commons.tabs.Tab;

import javax.swing.border.CompoundBorder;
import java.awt.event.ActionEvent;

@Log
@Getter
@ToString(exclude={"ctx","tabCtx","border","layout"})
public class CyclicCellularAutomatonTab extends TabPanel implements Tab {

    private final CyclicCellularAutomatonCanvas canvas;
    private final CyclicCellularAutomatonContext tabCtx;
    private final ComputerKurzweilApplicationContext ctx;
    private final CompoundBorder border;
    private final TabLayout layout;

    private final StartStopButtonsPanel startStopButtonsPanel;
    private final String labelSubtitle;
    private final PanelSubtitle panelSubtitle;
    private final CyclicCellularAutomatonButtons neighbourhoodButtonsPanel;

    private final static TabType TAB_TYPE = TabType.CYCLIC_CELLULAR_AUTOMATON;

    public CyclicCellularAutomatonTab(ComputerKurzweilApplicationContext ctx) {
        this.ctx = ctx;
        this.layout = new TabLayout(this);
        this.setLayout(layout);
        this.tabCtx = new CyclicCellularAutomatonContext(this);
        this.border = this.tabCtx.getCtx().getBorder();
        this.setBorder(border);
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
        this.ctx.getFrame().pack();
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
        this.startStopButtonsPanel.stop();
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
