package org.woehlke.computer.kurzweil.tabs.mandelbrot;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.commons.tabs.TabController;

@Log
@Getter
public class MandelbrotController extends Thread implements Runnable, TabController {

    private final ComputerKurzweilApplicationContext ctx;
    private final MandelbrotCanvas canvas;
    private final int time2wait;

    private Boolean goOn;

    public MandelbrotController(
        ComputerKurzweilApplicationContext ctx,
        MandelbrotCanvas canvas
    ) {
        super("Mandelbrot-Controller");
        this.ctx = ctx;
        this.canvas = canvas;
        this.time2wait=1;
    }

    public void run() {
        log.info("run() - begin");
        boolean doMyJob;
        synchronized (this.canvas) {
            canvas.start();
        }
        do {
            synchronized (goOn) {
                doMyJob = goOn.booleanValue();
            }
            synchronized (this.canvas) {
                log.info(".");
                canvas.getMandelbrot().step();
                log.info("[");
                this.canvas.repaint();
                log.info("]");
            }
            try {
                Thread.sleep(this.time2wait);
            } catch (InterruptedException e) {
                log.info(e.getLocalizedMessage());
            }
        } while( doMyJob && (! canvas.getMandelbrot().isFinished()));
        log.info("run() - finished");
    }

    public void exit() {
        try {
            synchronized (goOn) {
                goOn = Boolean.FALSE;
            }
            join();
        } catch (InterruptedException e){
            log.info(e.getMessage());
        }
    }

}
