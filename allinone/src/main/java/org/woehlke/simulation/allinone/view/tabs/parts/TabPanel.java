package org.woehlke.simulation.allinone.view.tabs.parts;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.simulation.allinone.config.ComputerKurzweilApplicationContext;
import org.woehlke.simulation.allinone.view.common.PanelBorder;
import org.woehlke.simulation.allinone.view.common.PanelSubtitle;
import org.woehlke.simulation.allinone.view.common.StartStopButtonsPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Log
public abstract class TabPanel extends JPanel implements ActionListener {

    @Getter
    protected final ComputerKurzweilApplicationContext ctx;

    @Getter
    protected final StartStopButtonsPanel startStopButtonsPanel;

    @Getter
    protected final PanelSubtitle panelSubtitle;

    public TabPanel(
        ComputerKurzweilApplicationContext ctx, String subtitle
    ) {
        this.ctx = ctx;
        this.startStopButtonsPanel = new StartStopButtonsPanel(this);
        this.panelSubtitle = new PanelSubtitle(subtitle);
        this.setLayout(new TabPanelLayout(this));
        this.setBorder(PanelBorder.getBorder());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.startStopButtonsPanel.getStartButton()){
            this.startStopButtonsPanel.getStartButton().setEnabled(false);
            this.startStopButtonsPanel.getStopButton().setEnabled(true);
            this.start();
        }
        if(e.getSource() == this.startStopButtonsPanel.getStopButton()){
            this.startStopButtonsPanel.getStartButton().setEnabled(true);
            this.startStopButtonsPanel.getStopButton().setEnabled(false);
            this.stop();
        }
    }

    public abstract void start();
    public abstract void stop();
}
