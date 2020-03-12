package org.woehlke.computer.kurzweil.tabs.cca;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.tabs.ComputerKurzweilTabbedPane;
import org.woehlke.computer.kurzweil.tabs.TabPanel;
import org.woehlke.computer.kurzweil.tabs.Tab;
import org.woehlke.computer.kurzweil.tabs.TabType;

import java.awt.event.ActionEvent;

@Log4j2
@Getter
@ToString(callSuper = true, exclude = {"tabCtx"})
@EqualsAndHashCode(callSuper=true, exclude = {"tabCtx"})
public class CyclicCellularAutomatonTab extends TabPanel implements Tab, CyclicCellularAutomaton {

    private final TabType tabType = TabType.CYCLIC_CELLULAR_AUTOMATON;
    private final CyclicCellularAutomatonContext tabCtx;
    private final CyclicCellularAutomatonCanvas canvas;
    private final CyclicCellularAutomatonModel tabModel;

    private final CyclicCellularAutomatonTabPane cyclicCellularAutomatonTabPane;

    public CyclicCellularAutomatonTab(ComputerKurzweilTabbedPane tabbedPane) {
        super(tabbedPane, TAB_TYPE);
        this.tabCtx = new CyclicCellularAutomatonContext(this);
        this.canvas = this.tabCtx.getCanvas();
        this.tabModel = this.canvas.getCyclicCellularAutomatonModel();
        this.cyclicCellularAutomatonTabPane = new CyclicCellularAutomatonTabPane(this);
        this.add(this.panelSubtitle);
        this.add(this.canvas);
        this.add(this.cyclicCellularAutomatonTabPane);
        this.ctx.getFrame().pack();
        this.tabModel.stop();
        this.cyclicCellularAutomatonTabPane.stop();
    }

    @Override
    public void start() {
        log.info("start");
        this.tabModel.start();
        this.cyclicCellularAutomatonTabPane.start();
        this.getTabCtx().startController();
        this.getTabCtx().getController().start();
        this.ctx.getFrame().pack();
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        log.info("started with canvas x="+x+" y="+y);
    }

    @Override
    public void stop() {
        log.info("stop");
        this.tabModel.stop();
        this.cyclicCellularAutomatonTabPane.stop();
        this.getTabCtx().stopController();
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        log.info("stopped with canvas x="+x+" y="+y);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() ==  this.cyclicCellularAutomatonTabPane.getButtonVonNeumann()) {
            this.stop();
            this.tabModel.startWithNeighbourhoodVonNeumann();
            this.start();
        } else if (ae.getSource() ==  this.cyclicCellularAutomatonTabPane.getButtonMoore()) {
            this.stop();
            this.tabModel.startWithNeighbourhoodMoore();
            this.start();
        } else if (ae.getSource() ==  this.cyclicCellularAutomatonTabPane.getButtonWoehlke()) {
            this.stop();
            this.tabModel.startWithNeighbourhoodWoehlke();
            this.start();
        }
        if(ae.getSource() == this.cyclicCellularAutomatonTabPane.getStartButton()){
            super.ctx.getFrame().start();
        }
        if(ae.getSource() == this.cyclicCellularAutomatonTabPane.getStopButton()){
            super.ctx.getFrame().stop();
        }
    }
}
