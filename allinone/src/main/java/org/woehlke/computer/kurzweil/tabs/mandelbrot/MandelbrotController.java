package org.woehlke.computer.kurzweil.tabs.mandelbrot;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.tabs.TabController;

@Log
@Getter
public class MandelbrotController extends Thread implements Runnable, TabController {

    private final MandelbrotContext tabCtx;
    private final int time2wait;

    private Boolean goOn;

    public MandelbrotController(
        MandelbrotContext tabCtx
    ) {
        super("Mandelbrot-Controller");
        this.tabCtx = tabCtx;
        this.time2wait=1;
    }

    public void run() {
        log.info("run() - begin");
        boolean doMyJob;
        do {
            synchronized (goOn) {
                doMyJob = goOn.booleanValue();
            }
            synchronized (this.tabCtx) {
                log.info(".");
                this.tabCtx.getCanvas().getMandelbrot().step();
                log.info("[");
                this.tabCtx.getCanvas().update();
                this.tabCtx.getCanvas().repaint();
                log.info("]");
            }
            try {
                Thread.sleep(this.time2wait);
            } catch (InterruptedException e) {
                log.info(e.getLocalizedMessage());
            }
        } while( doMyJob && (! this.tabCtx.getCanvas().getMandelbrot().isFinished()));
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
