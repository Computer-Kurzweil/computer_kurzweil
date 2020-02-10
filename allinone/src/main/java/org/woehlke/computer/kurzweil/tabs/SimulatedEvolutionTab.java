package org.woehlke.computer.kurzweil.tabs;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.apps.evolution.SimulatedEvolutionContext;
import org.woehlke.computer.kurzweil.apps.evolution.SimulatedEvolutionCanvas;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.trashcan.signals.UserSignal;
import org.woehlke.computer.kurzweil.borders.PanelBorder;
import org.woehlke.computer.kurzweil.tabs.common.Tab;
import org.woehlke.computer.kurzweil.tabs.common.TabLayout;
import org.woehlke.computer.kurzweil.widgets.PanelSubtitle;
import org.woehlke.computer.kurzweil.widgets.StartStopButtonsPanel;
import org.woehlke.computer.kurzweil.tabs.common.TabPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Log
@Getter
public class SimulatedEvolutionTab extends Tab implements TabPanel, ActionListener {

    private final ComputerKurzweilApplicationContext ctx;
    private final SimulatedEvolutionContext appCtx;
    private final StartStopButtonsPanel startStopButtonsPanel;
    private final PanelSubtitle panelSubtitle;
    private final SimulatedEvolutionCanvas canvas;

    public SimulatedEvolutionTab(ComputerKurzweilApplicationContext ctx) {
        this.ctx = ctx;
        this.setBorder(PanelBorder.getBorder());
        this.setLayout(new TabLayout(this));
        this.setBounds(ctx.getFrameBounds());
        this.canvas = new SimulatedEvolutionCanvas(ctx);
        this.appCtx = new SimulatedEvolutionContext(this, canvas);
        this.startStopButtonsPanel = new StartStopButtonsPanel( this );
        String subtitle = ctx.getProperties().getEvolution().getView().getSubtitle();
        this.panelSubtitle = new PanelSubtitle(subtitle);
        this.add(this.panelSubtitle);
        this.add(this.canvas);
        this.add(this.startStopButtonsPanel);
    }

    @Override
    public void start() {
        log.info("start");
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
        boolean visible = true;
        this.setVisibleMe(visible);
    }

    @Override
    public void hideMe() {
        log.info("hideMe");
        boolean visible = false;
        this.setVisibleMe(visible);
    }

    private void setVisibleMe(boolean visible){
        this.canvas.getPanelButtons().setVisible(visible);
        this.canvas.getStatisticsPanel().setVisible(visible);
        this.canvas.setVisible(visible);
        this.setVisible(visible);
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
        return this.appCtx.getCtx().getProperties().getEvolution().getView().getTitle();
    }

    @Override
    public String getSubTitle() {
        return this.appCtx.getCtx().getProperties().getEvolution().getView().getSubtitle();
    }

}
