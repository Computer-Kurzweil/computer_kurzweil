package org.woehlke.computer.kurzweil.tabs.mandelbrot;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.commons.tabs.TabPanel;
import org.woehlke.computer.kurzweil.commons.tabs.Tab;
import org.woehlke.computer.kurzweil.widgets.PanelSubtitle;
import org.woehlke.computer.kurzweil.widgets.StartStopButtonsPanel;

import java.awt.event.ActionEvent;

@Log
@Getter
public class MandelbrotTab extends TabPanel implements Tab {

    private final MandelbrotContext tabCtx;
    private final ComputerKurzweilApplicationContext ctx;

    private final String title;
    private final String subTitle;

    private final MandelbrotButtons panelButtons;
    private final MandelbrotCanvas canvas;
    private final StartStopButtonsPanel startStopButtonsPanel;
    private final PanelSubtitle panelSubtitle;

    public MandelbrotTab(ComputerKurzweilApplicationContext ctx ) {
        this.ctx = ctx;
        this.title = ctx.getProperties().getMandelbrot().getView().getTitle();
        this.subTitle = ctx.getProperties().getMandelbrot().getView().getSubtitle();
        this.tabCtx = new MandelbrotContext(this);
        this.canvas = this.tabCtx.getCanvas();
        this.startStopButtonsPanel = new StartStopButtonsPanel( this );
        this.panelSubtitle = new PanelSubtitle(  this.subTitle);
        this.panelButtons = new MandelbrotButtons(  this.ctx);
        this.add(this.panelSubtitle);
        this.add(this.canvas);
        this.add(this.panelButtons);
        this.add(this.startStopButtonsPanel);
        this.panelButtons.getPanelZoomButtons().getZoomOutButton().addActionListener(this);
        this.panelButtons.getPanelButtonsGroup().getRadioButtonsGroup().getRadioButtonsSwitch().addActionListener(this);
        this.panelButtons.getPanelButtonsGroup().getRadioButtonsGroup().getRadioButtonsZoom().addActionListener(this);
        this.startStopButtonsPanel.getStartButton().addActionListener(this);
        this.startStopButtonsPanel.getStopButton().addActionListener(this);
        this.startStopButtonsPanel.stop();
        showMe();
    }

    @Override
    public void start() {
        log.info("start");
        this.showMe();
        this.startStopButtonsPanel.start();
        this.tabCtx.startController();
        this.tabCtx.getController().start();
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stop");
        this.startStopButtonsPanel.stop();
        this.tabCtx.stopController();
        log.info("stopped");
    }

    @Override
    public void showMe() {
        log.info("showMe");
        //this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == this.panelButtons.getPanelButtonsGroup().getRadioButtonsGroup().getRadioButtonsSwitch()){
            this.canvas.setModeSwitch();
        }
        if(ae.getSource() == this.panelButtons.getPanelButtonsGroup().getRadioButtonsGroup().getRadioButtonsZoom()){
            this.canvas.setModeZoom();
        }
        if(ae.getSource() == this.panelButtons.getPanelZoomButtons().getZoomOutButton()){
            this.canvas.zoomOut();
        }
        if(ae.getSource() ==  this.startStopButtonsPanel.getStartButton()){
            start();
        }
        if(ae.getSource() ==  this.startStopButtonsPanel.getStopButton()){
            stop();
        }
    }
}
