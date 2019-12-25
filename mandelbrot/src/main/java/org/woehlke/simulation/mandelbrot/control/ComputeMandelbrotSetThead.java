package org.woehlke.simulation.mandelbrot.control;

import java.util.logging.Logger;

public class ComputeMandelbrotSetThead extends Thread implements Runnable {

    private final ApplicationContext ctx;

    public ComputeMandelbrotSetThead(ApplicationContext ctx) {
        super("ComputeMandelbrotSetThread");
        this.ctx = ctx;
    }

    public void run() {
        ctx.getMandelbrotTuringMachine().start();
        ctx.showMe();
        log.info(" < ");
        while( ! ctx.getMandelbrotTuringMachine().isFinished()){
            //log.info(".");
            ctx.getMandelbrotTuringMachine().step();
            //log.info("|");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                log.info(e.getLocalizedMessage());
            }
            ctx.getCanvas().repaint();
        }
        log.info(" > ");
        ctx.showMe();
    }

    private static Logger log = Logger.getLogger(ComputeMandelbrotSetThead.class.getName());
}
