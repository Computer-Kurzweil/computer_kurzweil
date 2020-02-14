package org.woehlke.computer.kurzweil.tabs.mandelbrot;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.commons.tabs.TabPanel;
import org.woehlke.computer.kurzweil.commons.tabs.Tab;

import java.awt.event.ActionEvent;

@Log
@Getter
public class MandelbrotTab extends TabPanel implements Tab {

    private final MandelbrotContext tabCtx;
    private final ComputerKurzweilApplicationContext ctx;

    private final String title;
    private final String subTitle;

    public MandelbrotTab(ComputerKurzweilApplicationContext ctx ) {
        this.ctx = ctx;
        this.title = ctx.getProperties().getMandelbrot().getView().getTitle();
        this.subTitle = ctx.getProperties().getMandelbrot().getView().getSubtitle();
        this.tabCtx = new MandelbrotContext(this);
        this.tabCtx.getStartStopButtonsPanel().getStartButton().addActionListener(this);
        this.tabCtx.getStartStopButtonsPanel().getStopButton().addActionListener(this);
        this.tabCtx.getStartStopButtonsPanel().getStartButton().setEnabled(false);
        this.tabCtx.getStartStopButtonsPanel().getStopButton().setEnabled(true);
        showMe();
    }

    @Override
    public void start() {
        log.info("start");
        this.showMe();
        this.tabCtx.getCanvas().start();
        this.tabCtx.getStartStopButtonsPanel().start();
        this.tabCtx.startController();
        this.tabCtx.getController().start();
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stop");
        this.tabCtx.getStartStopButtonsPanel().stop();
        this.tabCtx.stopController();
        this.tabCtx.getCanvas().stop();
        log.info("stopped");
    }

    @Override
    public void showMe() {
        log.info("showMe");
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() ==  this.tabCtx.getStartStopButtonsPanel().getStartButton()){
            start();
        }
        if(ae.getSource() ==  this.tabCtx.getStartStopButtonsPanel().getStopButton()){
            stop();
        }
    }
}
