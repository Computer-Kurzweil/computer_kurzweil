package org.woehlke.computer.kurzweil.tabs.mandelbrot;


import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilContext;
import org.woehlke.computer.kurzweil.tabs.TabType;
import org.woehlke.computer.kurzweil.commons.tabs.TabContext;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.Mandelbrot;


import static java.lang.Thread.State.NEW;
import static org.woehlke.computer.kurzweil.tabs.TabType.MANDELBROT_SET;

@Log4j2
@Getter
public class MandelbrotContext implements TabContext {

    private final TabType tabType = MANDELBROT_SET;
    private final ComputerKurzweilContext ctx;
    private final MandelbrotTab tab;
    private final Mandelbrot stepper;
    private final MandelbrotCanvas canvas;

    private MandelbrotController controller;

    public MandelbrotContext(MandelbrotTab tab, ComputerKurzweilContext ctx) {
        this.tab = tab;
        this.ctx = ctx;
        this.canvas = new MandelbrotCanvas( this );
        this.controller = new MandelbrotController(this);
        this.stepper = new Mandelbrot( this );
    }

    @Override
    public void stopController() {
        this.controller.exit();
        this.controller = null;
        this.controller = new MandelbrotController(this);
    }

    @Override
    public void startController() {
        if(this.controller == null){
            this.controller = new MandelbrotController(this);
        } else {
            if(this.controller.getState() != NEW){
                this.stopController();
            }
        }
    }
}
