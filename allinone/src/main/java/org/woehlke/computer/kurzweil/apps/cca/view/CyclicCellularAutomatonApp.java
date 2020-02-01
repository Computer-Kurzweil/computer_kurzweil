package org.woehlke.computer.kurzweil.apps.cca.view;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.config.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.model.Startable;
import org.woehlke.computer.kurzweil.view.common.BoxLayoutVertical;
import org.woehlke.computer.kurzweil.apps.cca.control.CyclicCellularAutomatonControllerThread;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.image.ImageObserver;
import java.io.Serializable;

/**
 * Cyclic Cellular Automaton.
 *
 * (C) 2006 - 2013 Thomas Woehlke.
 * http://thomas-woehlke.de/p/cyclic-cellular-automaton/
 * @author Thomas Woehlke
 * Date: 04.02.2006
 * Time: 18:47:46
 */
@Log
@ToString
@EqualsAndHashCode(callSuper=true)
public class CyclicCellularAutomatonApp extends JPanel implements ImageObserver,
        Serializable,
        Accessible, Startable {

    private static final long serialVersionUID = 4357793241219932594L;

    @Getter private ComputerKurzweilApplicationContext ctx;
    @Getter private CyclicCellularAutomatonCanvas canvas;
    @Getter private CyclicCellularAutomatonButtonsPanel panelButtons;

    @Getter private CyclicCellularAutomatonControllerThread controller;

    public CyclicCellularAutomatonApp(
        ComputerKurzweilApplicationContext ctx
    ) {
        this.ctx=ctx;
        this.setLayout(new BoxLayoutVertical(this));
        this.setBounds(ctx.getFrameBounds());
    }

    public void start() {
        log.info("start");
        this.canvas = new CyclicCellularAutomatonCanvas( this.ctx);
        this.panelButtons = new CyclicCellularAutomatonButtonsPanel( this.ctx);
        this.controller = new CyclicCellularAutomatonControllerThread(ctx, this.canvas, this.panelButtons);
        this.add(this.canvas);
        this.add(this.panelButtons);
        this.showMe();
        this.controller.start();
        log.info("started");
    }

    public void stop() {
        log.info("stop");
        this.controller.exit();
        this.remove(this.canvas);
        this.remove(this.panelButtons);
        this.canvas.stop();
        this.panelButtons.stop();
        this.canvas=null;
        this.panelButtons=null;
        hideMe();
        log.info("stopped");
    }

    public void showMe() {
        log.info("showMe");
        this.setVisible(true);
    }

    public void hideMe() {
        log.info("hideMe");
        this.setVisible(false);
    }
}
