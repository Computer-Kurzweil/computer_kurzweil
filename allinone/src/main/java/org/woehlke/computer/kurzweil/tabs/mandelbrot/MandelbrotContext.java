package org.woehlke.computer.kurzweil.tabs.mandelbrot;


import lombok.Getter;
import org.woehlke.computer.kurzweil.tabs.TabType;
import org.woehlke.computer.kurzweil.tabs.dla.DiffusionLimitedAggregationCanvas;
import org.woehlke.computer.kurzweil.tabs.dla.DiffusionLimitedAggregation;
import org.woehlke.computer.kurzweil.commons.tabs.TabContext;
import org.woehlke.computer.kurzweil.widgets.PanelSubtitle;
import org.woehlke.computer.kurzweil.widgets.StartStopButtonsPanel;


import static org.woehlke.computer.kurzweil.tabs.TabType.MANDELBROT_SET;

@Getter
public class MandelbrotContext implements TabContext {

    private final TabType tabType = MANDELBROT_SET;

    //TODO:
    private MandelbrotController controller;

    private final MandelbrotTab tab;
    private final Mandelbrot stateMachine;
    private final DiffusionLimitedAggregation stepper;
    private final DiffusionLimitedAggregationCanvas canvas;
    private final StartStopButtonsPanel startStopButtonsPanel;
    private final PanelSubtitle panelSubtitle;

    public MandelbrotContext(MandelbrotTab tab) {
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
        this.stateMachine.start();
        //TODO:
    }

    @Override
    public void stopController() {
        this.stateMachine.stop();
        //TODO:
    }

}
