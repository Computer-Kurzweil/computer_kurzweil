package org.woehlke.computer.kurzweil.tabs.cca;


import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.tabs.TabType;
import org.woehlke.computer.kurzweil.commons.tabs.TabPanel;
import org.woehlke.computer.kurzweil.widgets.BottomButtonsPanel;
import org.woehlke.computer.kurzweil.widgets.StartStopButtonsPanel;
import org.woehlke.computer.kurzweil.commons.tabs.Tab;

import java.awt.event.ActionEvent;

@Log
@Getter
@ToString(callSuper = true)
public class CyclicCellularAutomatonTab extends TabPanel implements Tab {

    @ToString.Exclude
    private final CyclicCellularAutomatonContext tabCtx;
    private final CyclicCellularAutomatonCanvas canvas;
    private final CyclicCellularAutomatonButtons neighbourhoodButtonsPanel;
    private final BottomButtonsPanel bottomButtonsPanel;

    public CyclicCellularAutomatonTab(ComputerKurzweilApplicationContext ctx) {
        super(ctx,TabType.CYCLIC_CELLULAR_AUTOMATON,ctx.getProperties().getCca().getView().getSubtitle(),ctx.getProperties().getCca().getView().getTitle());
        this.tabCtx = new CyclicCellularAutomatonContext(this);
        this.canvas = this.tabCtx.getCanvas();
        this.neighbourhoodButtonsPanel = new CyclicCellularAutomatonButtons(this.canvas);
        this.bottomButtonsPanel = new BottomButtonsPanel( this );
        this.bottomButtonsPanel.add(this.neighbourhoodButtonsPanel);
        this.add(this.panelSubtitle);
        this.add(this.canvas);
        this.add(this.bottomButtonsPanel);
        this.neighbourhoodButtonsPanel.getButtonVonNeumann().addActionListener(this);
        this.neighbourhoodButtonsPanel.getButtonMoore().addActionListener(this);
        this.neighbourhoodButtonsPanel.getButtonWoehlke().addActionListener(this);
        this.bottomButtonsPanel.getStartButton().addActionListener(this);
        this.bottomButtonsPanel.getStopButton().addActionListener(this);
        this.bottomButtonsPanel.stop();
        this.ctx.getFrame().pack();
        showMe();
    }

    @Override
    public void start() {
        log.info("start");
        this.showMe();
        this.canvas.start();
        this.bottomButtonsPanel.start();
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
        this.bottomButtonsPanel.stop();
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
        log.info("this: "+this.toString());
        log.info("showMe with canvas x="+x+" y="+y);
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
        if(ae.getSource() == this.bottomButtonsPanel.getStartButton()){
            this.start();
        }
        if(ae.getSource() == this.bottomButtonsPanel.getStopButton()){
            this.stop();
        }
    }
}
