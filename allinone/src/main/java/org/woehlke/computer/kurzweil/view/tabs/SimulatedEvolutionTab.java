package org.woehlke.computer.kurzweil.view.tabs;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.apps.evolution.ctx.SimulatedEvolutionStateService;
import org.woehlke.computer.kurzweil.apps.evolution.view.SimulatedEvolutionCanvas;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.trashcan.signals.UserSignal;
import org.woehlke.computer.kurzweil.view.tabs.common.Tab;
import org.woehlke.computer.kurzweil.view.widgets.PanelSubtitle;
import org.woehlke.computer.kurzweil.view.widgets.StartStopButtonsPanel;
import org.woehlke.computer.kurzweil.view.tabs.common.TabPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Log
@Getter
public class SimulatedEvolutionTab extends Tab implements TabPanel, ActionListener {

    private final ComputerKurzweilApplicationContext ctx;
    private final SimulatedEvolutionStateService appCtx;
    private final StartStopButtonsPanel startStopButtonsPanel;
    private final PanelSubtitle panelSubtitle;
    private final SimulatedEvolutionCanvas canvas;

    public SimulatedEvolutionTab(ComputerKurzweilApplicationContext ctx) {
        this.ctx = ctx;
        this.canvas = new SimulatedEvolutionCanvas(ctx);
        this.appCtx = new SimulatedEvolutionStateService(this, canvas);
        this.startStopButtonsPanel = new StartStopButtonsPanel( this );
        String subtitle = ctx.getProperties().getEvolution().getView().getSubtitle();
        this.panelSubtitle = new PanelSubtitle(subtitle);
        this.add(this.panelSubtitle);
        this.add(this.canvas);
        this.add(this.startStopButtonsPanel);
    }

    @Override
    public void update() {
        this.appCtx.update();
    }

    @Override
    public void showMe() {

    }

    @Override
    public void hideMe() {

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

    @Override
    public void start() {
        this.appCtx.start();
    }

    @Override
    public void stop() {
        this.appCtx.stop();
    }
}
