package org.woehlke.computer.kurzweil.apps.mandelbrot;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.commons.ControllerThread;
import org.woehlke.computer.kurzweil.trashcan.signals.UserSignal;

@Log
public class MandelbrotControllerThread extends Thread implements Runnable, ControllerThread {

    @Getter private final ComputerKurzweilApplicationContext ctx;
    @Getter private final MandelbrotPanelButtons panelButtons;
    @Getter private final MandelbrotCanvas canvas;

    private Boolean goOn;

    private final int time2wait;

    public MandelbrotControllerThread(
        ComputerKurzweilApplicationContext ctx,
        MandelbrotPanelButtons panelButtons,
        MandelbrotCanvas canvas
    ) {
        super("Mandelbrot-Controller");
        this.ctx = ctx;
        this.panelButtons = panelButtons;
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
                canvas.getApplicationStateMachine().step();
            }
            synchronized (this.canvas) {
                log.info("[");
                this.canvas.repaint();
                log.info("]");
            }
            try {
                Thread.sleep(this.time2wait);
            } catch (InterruptedException e) {
                log.info(e.getLocalizedMessage());
            }
        } while( doMyJob && (! canvas.getApplicationStateMachine().isFinished()));
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

    @Override
    public void handleUserSignal(UserSignal userSignal) {

    }
}
