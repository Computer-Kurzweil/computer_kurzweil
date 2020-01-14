package org.woehlke.simulation.mandelbrot.view;

import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.allinone.view.PanelSubtitle;
import org.woehlke.simulation.mandelbrot.model.MandelbrotContext;
import org.woehlke.simulation.mandelbrot.view.parts.MandelbrotCanvas;
import org.woehlke.simulation.mandelbrot.view.parts.MandelbrotPanelButtons;

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

    @Getter private final PanelSubtitle panelSubtitle;
    @Getter private final MandelbrotPanelButtons panelButtons;
    @Getter private final MandelbrotCanvas canvas;


    @Autowired
    public MandelbrotFrame(
        MandelbrotContext ctx
    ) {
        this.ctx=ctx;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.panelSubtitle = new PanelSubtitle(ctx);
        this.panelButtons = new MandelbrotPanelButtons(ctx);
        this.canvas = new MandelbrotCanvas(ctx);
        this.add(this.panelSubtitle);
        this.add(this.canvas);
        this.add(this.panelButtons);
        this.canvas.addMouseListener( ctx );
        this.ctx.setFrame(this);
    }

    public void start() {
        showMe();
        this.ctx.start();
    }

    public void setModeSwitch() {
        this.canvas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.panelButtons.enableZoomButton();
    }

    public void setModeZoom() {
        this.canvas.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        this.panelButtons.disableZoomButton();
    }

    /**
     * TODO write doc.
     */
    public void showMe() {
        try {
            this.setVisible(true);
            this.canvas.setVisible(true);
            this.panelSubtitle.setVisible(true);
            this.panelButtons.setVisible(true);
            this.repaint();
        } catch (NullPointerException e){
            log.warning("Error in showMe() "+e.getMessage());
        }
    }

    @Override
    public void repaint(){
        try {
            this.canvas.repaint();
        } catch (NullPointerException e){
            log.warning("Error MandelbrotFrame.repaint() -> canvas.repaint() " + e.getMessage());
        }
        try {
            this.panelSubtitle.repaint();
        } catch (NullPointerException e){
            log.warning("Error MandelbrotFrame.repaint() ->  panelSubtitle.repaint() " + e.getMessage());
        }
        try {
            this.panelButtons.repaint();
        } catch (NullPointerException e){
            log.warning("Error MandelbrotFrame.repaint() -> panelButtons.repaint() " + e.getMessage());
        }
        try {
            super.repaint();
        } catch (NullPointerException e){
            log.warning("Error MandelbrotFrame.repaint() -> super.repaint() " + e.getMessage());
        }
    }

}
