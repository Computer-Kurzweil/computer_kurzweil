package org.woehlke.computer.kurzweil.tabs.mandelbrot;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.tabs.TabController;

/**
 * https://docs.oracle.com/en/java/javase/13/docs/api/java.base/java/util/concurrent/ThreadPoolExecutor.html
 * https://docs.oracle.com/en/java/javase/13/docs/api/java.base/java/util/concurrent/Executor.html
 * TODO: https://github.com/Computer-Kurzweil/computer_kurzweil/issues/18
 * TODO: https://github.com/Computer-Kurzweil/computer_kurzweil/issues/19
 * http://openbook.rheinwerk-verlag.de/javainsel9/javainsel_14_004.htm
 */
@Log4j2
@Getter
public class MandelbrotController extends Thread implements TabController, Mandelbrot {

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
                this.tabCtx.getCanvas().getTabModel().exec();
                log.info("[");
                this.tabCtx.exec();
                log.info("]");
            }
            try {
                Thread.sleep(this.threadSleepTime);
            } catch (InterruptedException e) {
                log.info(e.getLocalizedMessage());
            }
        } while( doMyJob && (! this.tabCtx.getCanvas().getTabModel().isFinished()));
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
