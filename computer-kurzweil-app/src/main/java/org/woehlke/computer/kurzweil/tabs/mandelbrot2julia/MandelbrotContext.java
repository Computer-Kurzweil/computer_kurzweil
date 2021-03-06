package org.woehlke.computer.kurzweil.tabs.mandelbrot2julia;


import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilContext;
import org.woehlke.computer.kurzweil.commons.tabs.TabContext;


import java.util.concurrent.ForkJoinTask;

import static java.lang.Thread.State.NEW;

@Log4j2
@Getter
public class MandelbrotContext extends ForkJoinTask<Void> implements TabContext, Mandelbrot {

    private final ComputerKurzweilContext ctx;
    private final MandelbrotTab tab;
    private final MandelbrotModel tabModel;
    private final MandelbrotCanvas canvas;

    private volatile MandelbrotController controller;

    public MandelbrotContext(MandelbrotTab tab, ComputerKurzweilContext ctx) {
        this.tab = tab;
        this.ctx = ctx;
        this.tabModel = new MandelbrotModel(this.ctx.getProperties(), this.tab );
        this.canvas = new MandelbrotCanvas(this);
        this.controller = new MandelbrotController(this.tabModel,  this.tab );
    }

    @Override
    public void stopController() {
        this.controller.exit();
        this.controller = null;
        this.controller = new MandelbrotController(this.tabModel,  this.tab );
    }

    @Override
    public void startController() {
        if (this.controller == null) {
            this.controller = new MandelbrotController(this.tabModel,  this.tab );
        } else {
            if (this.controller.getState() != NEW) {
                this.stopController();
            }
        }
    }

    @Override
    public Void getRawResult() {
        return null;
    }

    @Override
    protected void setRawResult(Void value) {

    }

    @Override
    protected boolean exec() {
        this.tab.repaint();
        return true;
    }
}


