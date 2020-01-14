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
        showMe();
        this.ctx.setFrame(this);
        this.ctx.start();
    }

    /**
     * TODO write doc.
     */
    public void showMe() {
        try {
            this.setVisible(true);
            this.ctx.getCanvas().setVisible(true);
            this.ctx.getPanelSubtitle().setVisible(true);
            this.ctx.getPanelButtons().setVisible(true);
        } catch (Exception e){
            log.warning("Error in showMe() "+e.getMessage());
        }
    }

    @Override
    public void repaint(){
        try {
            this.ctx.getCanvas().repaint();
        } catch (Exception e){
            log.warning("Error in repaint() "+e.getMessage());
        }
        try {
            this.ctx.getPanelSubtitle().repaint();
        } catch (Exception e){
            log.warning("Error in repaint() "+e.getMessage());
        }
        try {
            this.ctx.getPanelButtons().repaint();
        } catch (Exception e){
            log.warning("Error in repaint() "+e.getMessage());
        }
        try {
            super.repaint();
        } catch (Exception e){
            log.warning("Error in repaint() "+e.getMessage());
        }
    }

}
