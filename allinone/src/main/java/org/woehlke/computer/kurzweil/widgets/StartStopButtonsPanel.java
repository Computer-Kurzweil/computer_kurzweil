package org.woehlke.computer.kurzweil.widgets;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.apps.cca.CyclicCellularAutomatonCanvas;
import org.woehlke.computer.kurzweil.trashcan.signals.UserSignal;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.commons.AppGuiComponent;
import org.woehlke.computer.kurzweil.borders.PanelBorder;
import org.woehlke.computer.kurzweil.layouts.StartStopButtonsPanelLayout;
import org.woehlke.computer.kurzweil.tabs.DiffusionLimitedAggregationTab;
import org.woehlke.computer.kurzweil.tabs.MandelbrotTab;
import org.woehlke.computer.kurzweil.tabs.SimulatedEvolutionTab;

import javax.swing.*;

@Log
public class StartStopButtonsPanel extends JPanel implements Startable, AppGuiComponent {

    @Getter private final JButton startButton;
    @Getter private final JButton stopButton;

    public StartStopButtonsPanel(SimulatedEvolutionTab tab){
        String getStartStopp = tab.getCtx().getProperties().getAllinone().getView().getStartStopp();
        String getStart = tab.getCtx().getProperties().getAllinone().getView().getStart();
        String getStop = tab.getCtx().getProperties().getAllinone().getView().getStop();
        this.setLayout(new StartStopButtonsPanelLayout());
        this.setBorder(PanelBorder.getBorder(getStartStopp));
        this.startButton = new JButton(getStart);
        this.stopButton = new JButton(getStop);
        this.add(this.startButton);
        this.add(this.stopButton);
        this.startButton.addActionListener(tab.getAppCtx());
        this.stopButton.addActionListener(tab.getAppCtx());
        this.startButton.setEnabled(true);
        this.stopButton.setEnabled(false);
    }

    public StartStopButtonsPanel(MandelbrotTab tab){
        String getStartStopp = tab.getCtx().getProperties().getAllinone().getView().getStartStopp();
        String getStart = tab.getCtx().getProperties().getAllinone().getView().getStart();
        String getStop = tab.getCtx().getProperties().getAllinone().getView().getStop();
        this.setLayout(new StartStopButtonsPanelLayout());
        this.setBorder(PanelBorder.getBorder(getStartStopp));
        this.startButton = new JButton(getStart);
        this.stopButton = new JButton(getStop);
        this.add(this.startButton);
        this.add(this.stopButton);
        this.startButton.addActionListener(tab.getAppCtx());
        this.stopButton.addActionListener(tab.getAppCtx());
        this.startButton.setEnabled(true);
        this.stopButton.setEnabled(false);
    }

    public StartStopButtonsPanel(DiffusionLimitedAggregationTab tab){
        String getStartStopp = tab.getCtx().getProperties().getAllinone().getView().getStartStopp();
        String getStart = tab.getCtx().getProperties().getAllinone().getView().getStart();
        String getStop = tab.getCtx().getProperties().getAllinone().getView().getStop();
        this.setLayout(new StartStopButtonsPanelLayout());
        this.setBorder(PanelBorder.getBorder(getStartStopp));
        this.startButton = new JButton(getStart);
        this.stopButton = new JButton(getStop);
        this.add(this.startButton);
        this.add(this.stopButton);
        this.startButton.addActionListener(tab.getAppCtx());
        this.stopButton.addActionListener(tab.getAppCtx());
        this.startButton.setEnabled(true);
        this.stopButton.setEnabled(false);
    }

    public StartStopButtonsPanel(CyclicCellularAutomatonCanvas canvas) {
        String getStartStopp = canvas.getCtx().getProperties().getAllinone().getView().getStartStopp();
        String getStart = canvas.getCtx().getProperties().getAllinone().getView().getStart();
        String getStop = canvas.getCtx().getProperties().getAllinone().getView().getStop();
        this.setLayout(new StartStopButtonsPanelLayout());
        this.setBorder(PanelBorder.getBorder(getStartStopp));
        this.startButton = new JButton(getStart);
        this.stopButton = new JButton(getStop);
        this.add(this.startButton);
        this.add(this.stopButton);
        this.startButton.addActionListener(canvas);
        this.stopButton.addActionListener(canvas);
        this.startButton.setEnabled(true);
        this.stopButton.setEnabled(false);
    }

    @Override
    public void start() {
        showMe();
    }

    public void stop() {
        hideMe();
    }

    @Override
    public void update() {
        this.repaint();
    }

    @Override
    public void showMe() {
        this.setVisible(true);
    }

    @Override
    public void hideMe() {
        this.setVisible(false);
    }

    @Override
    public void handleUserSignal(UserSignal userSignal) {
        log.info("handleUserSignal: "+userSignal.name());
    }
}
