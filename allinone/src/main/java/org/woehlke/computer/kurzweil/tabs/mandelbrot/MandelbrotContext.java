package org.woehlke.computer.kurzweil.tabs.mandelbrot;


import lombok.Getter;
import org.woehlke.computer.kurzweil.tabs.TabType;
import org.woehlke.computer.kurzweil.commons.tabs.TabContext;


import static org.woehlke.computer.kurzweil.tabs.TabType.MANDELBROT_SET;

@Getter
public class MandelbrotContext implements TabContext {

    private final TabType tabType = MANDELBROT_SET;

    //TODO:
    private MandelbrotController controller;

    private final MandelbrotTab tab;
    private final Mandelbrot stepper;
    private final MandelbrotCanvas canvas;

    public MandelbrotContext(MandelbrotTab tab) {
        this.tab = tab;
        this.canvas = new MandelbrotCanvas(this.tab.getCtx());
        this.stepper = new Mandelbrot(this.tab.getCtx());
    }

    @Override
    public void startController() {
        this.stepper.start();
        //TODO:
    }

    @Override
    public void stopController() {
        this.stepper.stop();
        //TODO:
    }

}
