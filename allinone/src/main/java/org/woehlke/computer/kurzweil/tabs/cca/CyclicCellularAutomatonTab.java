package org.woehlke.computer.kurzweil.tabs.cca;


import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.tabs.TabType;
import org.woehlke.computer.kurzweil.widgets.borders.PanelBorder;
import org.woehlke.computer.kurzweil.commons.tabs.TabPanel;
import org.woehlke.computer.kurzweil.widgets.layouts.TabLayout;
import org.woehlke.computer.kurzweil.commons.tabs.Tab;

import java.awt.event.ActionEvent;

@Log
@Getter
public class CyclicCellularAutomatonTab extends TabPanel implements Tab {

    private final CyclicCellularAutomatonCanvas canvas;
    private final CyclicCellularAutomatonContext tabCtx;
    private final ComputerKurzweilApplicationContext ctx;

    private final static TabType TAB_TYPE = TabType.CYCLIC_CELLULAR_AUTOMATON;

    public CyclicCellularAutomatonTab(ComputerKurzweilApplicationContext ctx) {
        this.ctx = ctx;
        this.setBorder(PanelBorder.getBorder());
        this.setLayout(new TabLayout(this));
        this.setBounds(ctx.getFrameBounds());
        this.tabCtx = new CyclicCellularAutomatonContext(this);
        this.canvas = this.tabCtx.getCanvas();
        this.add(this.canvas.getPanelSubtitle());
        this.add(this.canvas);
        this.add(this.canvas.getNeighbourhoodButtonsPanel());
        this.add(this.canvas.getStartStopButtonsPanel());
        this.canvas.getStartStopButtonsPanel().getStartButton().addActionListener(this);
        this.canvas.getStartStopButtonsPanel().getStopButton().addActionListener(this);
        this.canvas.getNeighbourhoodButtonsPanel().getButtonVonNeumann().addActionListener(this);
        this.canvas.getNeighbourhoodButtonsPanel().getButtonMoore().addActionListener(this);
        this.canvas.getNeighbourhoodButtonsPanel().getButtonWoehlke().addActionListener(this);
        showMe();
    }

    @Override
    public void start() {
        log.info("start");
        this.showMe();
        this.canvas.start();
        this.getTabCtx().startController();
        this.getTabCtx().getController().start();
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stop");
        this.canvas.stop();
        this.getTabCtx().stopController();
        log.info("stopped");
    }

    @Override
    public void showMe() {
        log.info("showMe");
        this.canvas.getStartStopButtonsPanel().setVisible(true);
        this.canvas.getNeighbourhoodButtonsPanel().setVisible(true);
        this.canvas.getPanelSubtitle().setVisible(true);
        this.canvas.setVisible(true);
        this.setVisible(true);
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
        if (ae.getSource() == this.canvas.getNeighbourhoodButtonsPanel().getButtonVonNeumann()) {
            this.canvas.startWithNeighbourhoodVonNeumann();
            this.start();
        } else if (ae.getSource() == this.canvas.getNeighbourhoodButtonsPanel().getButtonMoore()) {
            this.canvas.startWithNeighbourhoodMoore();
            this.start();
        } else if (ae.getSource() == this.canvas.getNeighbourhoodButtonsPanel().getButtonWoehlke()) {
            this.canvas.startWithNeighbourhoodWoehlke();
            this.start();
        }
        if(ae.getSource() == this.canvas.getStartStopButtonsPanel().getStartButton()){
            this.start();
        }
        if(ae.getSource() == this.canvas.getStartStopButtonsPanel().getStopButton()){
            this.stop();
        }
    }
}
