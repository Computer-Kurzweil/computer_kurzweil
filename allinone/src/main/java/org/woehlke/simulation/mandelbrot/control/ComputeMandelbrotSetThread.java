package org.woehlke.simulation.mandelbrot.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.mandelbrot.control.common.MandelbrotApplicationContext;

import java.util.logging.Logger;

@Component
public class ComputeMandelbrotSetThread extends Thread implements Runnable {

    private final MandelbrotApplicationContext ctx;

    @Autowired
    public ComputeMandelbrotSetThread(MandelbrotApplicationContext ctx) {
        super("ComputeMandelbrotSetThread");
        this.ctx = ctx;
    }

    public void run() {
        ctx.getMandelbrotTuringMachine().start();
        ctx.showMe();
        log.info(" ( ");
        while( ! ctx.getMandelbrotTuringMachine().isFinished()){
            log.info(".");
            ctx.getMandelbrotTuringMachine().step();
            log.info("[");
            ctx.getCanvas().repaint();
            log.info("]");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                log.info(e.getLocalizedMessage());
            }
        }
        log.info(" ) ");
        ctx.showMe();
    }

    private static Logger log = Logger.getLogger(ComputeMandelbrotSetThread.class.getName());
}
