package org.woehlke.computer.kurzweil.tabs.mandelbrot;


import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilContext;
import org.woehlke.computer.kurzweil.commons.tabs.TabContext;


import static java.lang.Thread.State.NEW;

@Log4j2
@Getter
public class MandelbrotContext implements TabContext, Mandelbrot {

    private final ComputerKurzweilContext ctx;
    private final MandelbrotTab tab;
    private final MandelbrotModel tabModel;
    private final MandelbrotCanvas canvas;

    private MandelbrotController controller;

    public MandelbrotContext(MandelbrotTab tab, ComputerKurzweilContext ctx) {
        this.tab = tab;
        this.ctx = ctx;
        this.canvas = new MandelbrotCanvas(this);
        this.controller = new MandelbrotController(this);
        this.tabModel = new MandelbrotModel(this);
    }

    @Override
    public void stopController() {
        this.controller.exit();
        this.controller = null;
        this.controller = new MandelbrotController(this);
    }

    @Override
    public void startController() {
        if (this.controller == null) {
            this.controller = new MandelbrotController(this);
        } else {
            if (this.controller.getState() != NEW) {
                this.stopController();
            }
        }
    }
}


