package org.woehlke.computer.kurzweil.view.tabs;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.apps.evolution.ctx.SimulatedEvolutionContext;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.control.signals.UserSignal;
import org.woehlke.computer.kurzweil.apps.SimulatedEvolutionTabApp;
import org.woehlke.computer.kurzweil.view.widgets.PanelSubtitle;
import org.woehlke.computer.kurzweil.view.widgets.StartStopButtonsPanel;
import org.woehlke.computer.kurzweil.view.tabs.common.TabPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Log
public class SimulatedEvolutionTab extends JPanel implements TabPanel, ActionListener {

    @Getter private final SimulatedEvolutionContext appCtx;

    @Getter private final ComputerKurzweilApplicationContext ctx;

    @Getter private final StartStopButtonsPanel startStopButtonsPanel;

    @Getter private final PanelSubtitle panelSubtitle;

    @Getter private final SimulatedEvolutionTabApp app;

    public SimulatedEvolutionTab(ComputerKurzweilApplicationContext ctx) {
        this.ctx=ctx;
        String subtitle = ctx.getProperties().getEvolution().getView().getSubtitle();
        this.appCtx = new SimulatedEvolutionContext();
        this.app = new SimulatedEvolutionTabApp(this);
        this.startStopButtonsPanel = new StartStopButtonsPanel( this );
        this.panelSubtitle = new PanelSubtitle(subtitle);
        this.add(this.panelSubtitle);
        this.add(this.app);
        this.add(this.startStopButtonsPanel);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void update() {

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
}
