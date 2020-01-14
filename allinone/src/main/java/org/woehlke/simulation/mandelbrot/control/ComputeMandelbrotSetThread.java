package org.woehlke.simulation.mandelbrot.control;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.simulation.mandelbrot.model.MandelbrotContext;
import org.woehlke.simulation.mandelbrot.view.parts.MandelbrotCanvas;
import org.woehlke.simulation.mandelbrot.view.parts.MandelbrotPanelButtons;

@Log
public class ComputeMandelbrotSetThread extends Thread implements Runnable {

    private final MandelbrotContext ctx;

    @Getter private final MandelbrotPanelButtons panelButtons;
    @Getter private final MandelbrotCanvas canvas;

    public ComputeMandelbrotSetThread(MandelbrotContext ctx, MandelbrotPanelButtons panelButtons, MandelbrotCanvas canvas) {
        super("ComputeMandelbrotSetThread");
        this.ctx = ctx;
        this.panelButtons = panelButtons;
        this.canvas = canvas;
    }

    public void run() {
        canvas.getMandelbrotTuringMachine().start();
        ctx.showMe();
        log.info(" ( ");
        while( ! canvas.getMandelbrotTuringMachine().isFinished()){
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
        }
        log.info(" ) ");
        ctx.showMe();
    }

}
