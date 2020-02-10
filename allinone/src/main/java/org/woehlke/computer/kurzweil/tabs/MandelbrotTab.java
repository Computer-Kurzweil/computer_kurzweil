package org.woehlke.computer.kurzweil.tabs;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.apps.mandelbrot.MandelbrotContext;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.trashcan.signals.UserSignal;
import org.woehlke.computer.kurzweil.trashcan.MandelbrotTabApp;
import org.woehlke.computer.kurzweil.tabs.common.Tab;
import org.woehlke.computer.kurzweil.widgets.PanelSubtitle;
import org.woehlke.computer.kurzweil.widgets.StartStopButtonsPanel;
import org.woehlke.computer.kurzweil.tabs.common.TabPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Log
public class MandelbrotTab extends Tab implements TabPanel, ActionListener {

    @Getter
    private final MandelbrotTabApp app;

    @Getter
    private final MandelbrotContext appCtx;

    @Getter
    private final ComputerKurzweilApplicationContext ctx;

    @Getter private final StartStopButtonsPanel startStopButtonsPanel;

    @Getter private final PanelSubtitle panelSubtitle;

    public MandelbrotTab(ComputerKurzweilApplicationContext ctx ) {
        this.ctx = ctx;
        String subtitle = ctx.getProperties().getMandelbrot().getView().getSubtitle();
        this.appCtx = new MandelbrotContext();
        this.app = new MandelbrotTabApp(this);
        this.startStopButtonsPanel = new StartStopButtonsPanel( this );
        this.panelSubtitle = new PanelSubtitle(subtitle);
        this.add(this.panelSubtitle);
        this.add(this.app);
        this.add(this.startStopButtonsPanel);
    }

    @Override
    public void start() {
        log.info("start");
        this.showMe();
        this.getAppCtx().start();
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stop");
        this.getAppCtx().stop();
        log.info("stopped");
    }

    @Override
    public void update() {
        log.info("update");
        this.getAppCtx().update();
        log.info("updated");
    }

    @Override
    public void showMe() {
        log.info("showMe");
        this.setVisible(true);
    }

    @Override
    public void hideMe() {
        log.info("hideMe");
        this.setVisible(false);
    }

    @Override
    public void handleUserSignal(UserSignal userSignal) {
        log.info("handleUserSignal: "+userSignal.name());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == this.startStopButtonsPanel.getStartButton()){
            this.startStopButtonsPanel.getStartButton().setEnabled(false);
            this.startStopButtonsPanel.getStopButton().setEnabled(true);
            this.start();
        }
        if(ae.getSource() == this.startStopButtonsPanel.getStopButton()){
            this.startStopButtonsPanel.getStartButton().setEnabled(true);
            this.startStopButtonsPanel.getStopButton().setEnabled(false);
            this.stop();
        }
    }

    @Override
    public String getTitle() {
        return ctx.getProperties().getMandelbrot().getView().getTitle();
    }

    @Override
    public String getSubTitle() {
        return ctx.getProperties().getMandelbrot().getView().getSubtitle();
    }
}
