package org.woehlke.simulation.mandelbrot.control;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.simulation.allinone.config.ComputerKurzweilApplicationContext;
import org.woehlke.simulation.mandelbrot.view.MandelbrotFrame;
import org.woehlke.simulation.mandelbrot.view.parts.MandelbrotCanvas;
import org.woehlke.simulation.mandelbrot.view.parts.MandelbrotPanelButtons;

@Log
public class ComputeMandelbrotSetThread extends Thread implements Runnable {

    private final ComputerKurzweilApplicationContext ctx;

    @Getter private final MandelbrotPanelButtons panelButtons;
    @Getter private final MandelbrotCanvas canvas;
    @Getter private final MandelbrotFrame frame;

    public ComputeMandelbrotSetThread(
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
        canvas.getMandelbrotTuringMachine().start();
        this.frame.showMe();
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
        this.frame.showMe();
    }

}
