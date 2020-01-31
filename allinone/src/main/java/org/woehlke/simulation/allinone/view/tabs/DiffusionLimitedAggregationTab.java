package org.woehlke.simulation.allinone.view.tabs;

import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.allinone.model.ComputerKurzweilApplicationContext;
import org.woehlke.simulation.allinone.view.parts.FramePanel;
import org.woehlke.simulation.allinone.view.parts.PanelBorder;
import org.woehlke.simulation.allinone.view.parts.PanelSubtitle;
import org.woehlke.simulation.allinone.view.parts.StartStopPanel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@Log
@Component
public class DiffusionLimitedAggregationTab extends JPanel implements ActionListener {

    @Getter
    private final ComputerKurzweilApplicationContext ctx;

    private final StartStopPanel startStopPanel;

    private final FramePanel framePanel;

    @Autowired
    public DiffusionLimitedAggregationTab(ComputerKurzweilApplicationContext ctx) {
        CompoundBorder border = PanelBorder.getBorder();
        this.setBorder(border);
        this.ctx = ctx;
        PanelSubtitle panelSubtitle = PanelSubtitle.getPanelSubtitleForDla(ctx);
        this.startStopPanel = new StartStopPanel(this);
        this.framePanel = new FramePanel(this);
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);
        this.add(panelSubtitle);
        this.add(this.framePanel);
        this.add(this.startStopPanel);
    }

    public void start(){
        log.info("started");
    }

    public void stop(){
        log.info("stopped");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.startStopPanel.getStartButton()){
            this.startStopPanel.getStartButton().setEnabled(false);
            this.startStopPanel.getStopButton().setEnabled(true);
            this.start();
        }
        if(e.getSource() == this.startStopPanel.getStopButton()){
            this.startStopPanel.getStartButton().setEnabled(true);
            this.startStopPanel.getStopButton().setEnabled(false);
            this.stop();
        }
    }
}
