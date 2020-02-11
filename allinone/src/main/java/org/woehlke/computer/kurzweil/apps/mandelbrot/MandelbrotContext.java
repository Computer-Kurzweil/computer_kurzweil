package org.woehlke.computer.kurzweil.apps.mandelbrot;


import lombok.Getter;
import org.woehlke.computer.kurzweil.apps.TabType;
import org.woehlke.computer.kurzweil.apps.dla.DiffusionLimitedAggregationCanvas;
import org.woehlke.computer.kurzweil.apps.dla.DiffusionLimitedAggregation;
import org.woehlke.computer.kurzweil.commons.tabs.TabContext;
import org.woehlke.computer.kurzweil.widgets.PanelSubtitle;
import org.woehlke.computer.kurzweil.widgets.StartStopButtonsPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.woehlke.computer.kurzweil.apps.TabType.MANDELBROT_SET;

@Getter
public class MandelbrotContext implements TabContext, ActionListener {

    private final TabType tabType = MANDELBROT_SET;

    //TODO:
    private MandelbrotTabController controller;

    private final MandelbrotTabPanel tab;
    private final Mandelbrot stateMachine;
    private final DiffusionLimitedAggregation stepper;
    private final DiffusionLimitedAggregationCanvas canvas;
    private final StartStopButtonsPanel startStopButtonsPanel;
    private final PanelSubtitle panelSubtitle;

    public MandelbrotContext(MandelbrotTabPanel tab) {
        this.tab = tab;
        this.canvas = new DiffusionLimitedAggregationCanvas(this.tab.getCtx());
        this.stepper = this.canvas.getStepper();
        this.startStopButtonsPanel = new StartStopButtonsPanel( tab );
        this.panelSubtitle = new PanelSubtitle(this.tab.getSubTitle());
        this.tab.add(this.panelSubtitle);
        this.tab.add(this.canvas);
        this.tab.add(this.startStopButtonsPanel);
        this.stateMachine = new Mandelbrot(this.tab.getCtx());
    }

    @Override
    public void startController() {
//TODO:
    }

    @Override
    public void stopController() {
//TODO:
    }

    @Override
    public void start() {
        this.stateMachine.start();
    }

    @Override
    public void stop() {
        this.stateMachine.stop();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == this.getStartStopButtonsPanel().getStartButton()){
            this.getStartStopButtonsPanel().getStartButton().setEnabled(false);
            this.getStartStopButtonsPanel().getStopButton().setEnabled(true);
            this.start();
        }
        if(ae.getSource() == this.getStartStopButtonsPanel().getStopButton()){
            this.getStartStopButtonsPanel().getStartButton().setEnabled(true);
            this.getStartStopButtonsPanel().getStopButton().setEnabled(false);
            this.stop();
        }
    }
}
