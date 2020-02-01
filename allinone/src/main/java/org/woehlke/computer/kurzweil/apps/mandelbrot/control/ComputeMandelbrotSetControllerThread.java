package org.woehlke.computer.kurzweil.apps.mandelbrot.control;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.config.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.apps.mandelbrot.view.MandelbrotFrame;
import org.woehlke.computer.kurzweil.apps.mandelbrot.view.parts.MandelbrotCanvas;
import org.woehlke.computer.kurzweil.apps.mandelbrot.view.parts.MandelbrotPanelButtons;
import org.woehlke.computer.kurzweil.control.ctx.ControllerThread;
import org.woehlke.computer.kurzweil.control.signals.UserSignal;

@Log
public class ComputeMandelbrotSetControllerThread extends Thread implements Runnable, ControllerThread {

    private final ComputerKurzweilApplicationContext ctx;

    @Getter private final MandelbrotPanelButtons panelButtons;
    @Getter private final MandelbrotCanvas canvas;
    @Getter private final MandelbrotFrame frame;

    private Boolean goOn;

    public ComputeMandelbrotSetControllerThread(
        ComputerKurzweilApplicationContext ctx,
        MandelbrotPanelButtons panelButtons,
        MandelbrotCanvas canvas,
        MandelbrotFrame frame
    ) {
        super("ComputeMandelbrotSetThread");
        this.ctx = ctx;
        this.panelButtons = panelButtons;
        this.canvas = canvas;
        this.frame = frame;
    }

    public void run() {
        boolean doMyJob;
        canvas.getMandelbrotTuringMachine().start();
        this.frame.showMe();
        log.info(" ( ");
        do {
            synchronized (goOn) {
                doMyJob = goOn.booleanValue();
            }
            log.info(".");
            canvas.getMandelbrotTuringMachine().step();
            log.info("[");
            this.canvas.repaint();
            log.info("]");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                log.info(e.getLocalizedMessage());
            }
        } while( doMyJob && (! canvas.getMandelbrotTuringMachine().isFinished()));
        log.info(" ) ");
        this.frame.showMe();
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
