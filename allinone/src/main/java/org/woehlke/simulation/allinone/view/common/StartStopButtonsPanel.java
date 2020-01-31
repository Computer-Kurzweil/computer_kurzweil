package org.woehlke.simulation.allinone.view.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.simulation.allinone.view.tabs.parts.TabPanel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

@Log
@ToString
@EqualsAndHashCode(callSuper=true)
public class StartStopButtonsPanel extends JPanel {

    @Getter private final JButton startButton;
    @Getter private final JButton stopButton;

    public StartStopButtonsPanel(TabPanel tab) {
        String getStartStopp = tab.getCtx().getProperties().getAllinone().getView().getStartStopp();
        String getStart = tab.getCtx().getProperties().getAllinone().getView().getStart();
        String getStop = tab.getCtx().getProperties().getAllinone().getView().getStop();
        CompoundBorder border = PanelBorder.getBorder(getStartStopp);
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);
        this.setBorder(border);
        this.startButton = new JButton(getStart);
        this.stopButton = new JButton(getStop);
        this.add(this.startButton);
        this.add(this.stopButton);
        this.startButton.addActionListener(tab);
        this.stopButton.addActionListener(tab);
        this.startButton.setEnabled(true);
        this.stopButton.setEnabled(false);
    }

}
