package org.woehlke.simulation.mandelbrot.control;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.mandelbrot.model.MandelbrotContext;

@Log
@Component
public class ComputeMandelbrotSetThread extends Thread implements Runnable {

    private final MandelbrotContext ctx;

    @Autowired
    public ComputeMandelbrotSetThread(MandelbrotContext ctx) {
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
            ctx.getFrame().getCanvas().repaint();
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

}
