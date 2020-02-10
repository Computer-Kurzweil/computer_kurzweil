package org.woehlke.computer.kurzweil.apps.mandelbrot;


import lombok.Getter;
import org.woehlke.computer.kurzweil.apps.AppType;
import org.woehlke.computer.kurzweil.apps.dla.DiffusionLimitedAggregationCanvas;
import org.woehlke.computer.kurzweil.commons.AppContext;
import org.woehlke.computer.kurzweil.commons.ControllerThread;
import org.woehlke.computer.kurzweil.commons.Stepper;
import org.woehlke.computer.kurzweil.tabs.MandelbrotTab;
import org.woehlke.computer.kurzweil.tabs.common.TabPanel;
import org.woehlke.computer.kurzweil.widgets.PanelSubtitle;
import org.woehlke.computer.kurzweil.widgets.StartStopButtonsPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.woehlke.computer.kurzweil.apps.AppType.MANDELBROT_SET;

@Getter
public class MandelbrotContext implements AppContext, ActionListener {

    private final AppType appType = MANDELBROT_SET;

    private MandelbrotControllerThread mandelbrotControllerThread;

    private final MandelbrotTab tab;
    private final ApplicationStateMachine world;
    private final DiffusionLimitedAggregationCanvas canvas;
    private final StartStopButtonsPanel startStopButtonsPanel;
    private final PanelSubtitle panelSubtitle;

    public MandelbrotContext(MandelbrotTab tab) {
        this.tab = tab;
        this.canvas = new DiffusionLimitedAggregationCanvas(this.tab.getCtx());
        this.world = new ApplicationStateMachine(this.tab.getCtx());
        this.startStopButtonsPanel = new StartStopButtonsPanel( tab );
        this.panelSubtitle = new PanelSubtitle(this.tab.getSubTitle());
        this.tab.add(this.panelSubtitle);
        this.tab.add(this.canvas);
        this.tab.add(this.startStopButtonsPanel);
    }

    @Override
    public ControllerThread getControllerThread() {
        return mandelbrotControllerThread;
    }

    @Override
    public TabPanel getTabPanel() {
        return tab;
    }

    @Override
    public Stepper getStepper() {
        return world;
    }

    @Override
    public void startController() {

    }

    @Override
    public void stopController() {

    }

    @Override
    public void step() {
        this.world.step();
    }

    @Override
    public void update() {
        this.canvas.update();
    }

    @Override
    public void start() {
        this.world.start();
    }

    @Override
    public void stop() {
        this.world.stop();
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
