package org.woehlke.simulation.mandelbrot.view;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.mandelbrot.model.MandelbrotContext;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * (C) 2006 - 2013 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 * Date: 04.02.2006
 * Time: 18:47:46
 */
@Log
@Component
public class MandelbrotFrame extends JPanel implements
        ImageObserver,
        MenuContainer,
        Accessible {

    private final MandelbrotContext ctx;

    @Autowired
    public MandelbrotFrame(
        MandelbrotContext ctx
    ) {
        this.ctx=ctx;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(this.ctx.getPanelSubtitle());
        this.add(this.ctx.getCanvas());
        this.add(this.ctx.getPanelButtons());
        showMeInit();
        this.ctx.setFrame(this);
        this.ctx.start();
    }

    public void showMeInit() {
        showMe();
        repaint();
    }

    /**
     * TODO write doc.
     */
    public void showMe() {
        this.setVisible(true);
        this.ctx.getCanvas().setVisible(true);
        this.ctx.getPanelSubtitle().setVisible(true);
        this.ctx.getPanelButtons().setVisible(true);
    }

    @Override
    public void repaint(){
        this.ctx.getCanvas().repaint();
        this.ctx.getPanelSubtitle().repaint();
        this.ctx.getPanelButtons().repaint();
        super.repaint();
    }

}
