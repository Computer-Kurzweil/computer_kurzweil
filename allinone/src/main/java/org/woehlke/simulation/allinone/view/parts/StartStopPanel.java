package org.woehlke.simulation.allinone.view.parts;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.simulation.allinone.view.tabs.CyclicCellularAutomatonTab;
import org.woehlke.simulation.allinone.view.tabs.DiffusionLimitedAggregationTab;
import org.woehlke.simulation.allinone.view.tabs.MandelbrotTab;
import org.woehlke.simulation.allinone.view.tabs.SimulatedEvolutionTab;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
@Log
@ToString
@EqualsAndHashCode(callSuper=true)
public class StartStopPanel extends JPanel {

    @Getter private final JButton startButton;
    @Getter private final JButton stopButton;

    private final JPanel panelButton;

    public StartStopPanel(CyclicCellularAutomatonTab tab) {
        CompoundBorder border = PanelBorder.getBorder("Start / Stop");
        FlowLayout layout = new FlowLayout();
        this.panelButton = new JPanel();
        this.panelButton.setLayout(layout);
        this.panelButton.setBorder(border);
        this.startButton = new JButton("Start");
        this.stopButton = new JButton("Stop");
        this.panelButton.add(    this.startButton);
        this.panelButton.add(    this.stopButton);
        this.add(panelButton);
        this.startButton.addActionListener(tab);
        this.stopButton.addActionListener(tab);
        this.startButton.setEnabled(true);
        this.stopButton.setEnabled(false);
    }

    public StartStopPanel(DiffusionLimitedAggregationTab tab) {
        CompoundBorder border = PanelBorder.getBorder("Start / Stop");
        FlowLayout layout = new FlowLayout();
        this.panelButton = new JPanel();
        this.panelButton.setLayout(layout);
        this.panelButton.setBorder(border);
        this.startButton = new JButton("Start");
        this.stopButton = new JButton("Stop");
        this.panelButton.add(    this.startButton);
        this.panelButton.add(    this.stopButton);
        this.add(panelButton);
        this.startButton.addActionListener(tab);
        this.stopButton.addActionListener(tab);
        this.startButton.setEnabled(true);
        this.stopButton.setEnabled(false);
    }

    public StartStopPanel(MandelbrotTab tab) {
        CompoundBorder border = PanelBorder.getBorder("Start / Stop");
        FlowLayout layout = new FlowLayout();
        this.panelButton = new JPanel();
        this.panelButton.setLayout(layout);
        this.panelButton.setBorder(border);
        this.startButton = new JButton("Start");
        this.stopButton = new JButton("Stop");
        this.panelButton.add(    this.startButton);
        this.panelButton.add(    this.stopButton);
        this.add(panelButton);
        this.startButton.addActionListener(tab);
        this.stopButton.addActionListener(tab);
        this.startButton.setEnabled(true);
        this.stopButton.setEnabled(false);
    }

    public StartStopPanel(SimulatedEvolutionTab tab) {
        CompoundBorder border = PanelBorder.getBorder("Start / Stop");
        FlowLayout layout = new FlowLayout();
        this.panelButton = new JPanel();
        this.panelButton.setLayout(layout);
        this.panelButton.setBorder(border);
        this.startButton = new JButton("Start");
        this.stopButton = new JButton("Stop");
        this.panelButton.add(    this.startButton);
        this.panelButton.add(    this.stopButton);
        this.add(panelButton);
        this.startButton.addActionListener(tab);
        this.stopButton.addActionListener(tab);
        this.startButton.setEnabled(true);
        this.stopButton.setEnabled(false);
    }

}
