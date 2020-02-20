package org.woehlke.computer.kurzweil.tabs.mandelbrot;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.tabs.TabController;

@Log4j2
@Getter
public class MandelbrotController extends Thread implements Runnable, TabController {

    private final MandelbrotContext tabCtx;
    private final int threadSleepTime;

    private Boolean goOn;

    public MandelbrotController(
        MandelbrotContext tabCtx
    ) {
        super("Mandelbrot-Controller");
        this.tabCtx = tabCtx;
        this.threadSleepTime=1;
        goOn = Boolean.TRUE;
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
                Thread.sleep(this.threadSleepTime);
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
