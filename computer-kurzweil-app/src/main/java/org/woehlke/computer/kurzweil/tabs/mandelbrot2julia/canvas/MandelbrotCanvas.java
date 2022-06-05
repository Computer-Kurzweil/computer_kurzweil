package org.woehlke.computer.kurzweil.tabs.mandelbrot2julia.canvas;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.layouts.LayoutCanvas;
import org.woehlke.computer.kurzweil.commons.tabs.TabCanvas;
import org.woehlke.computer.kurzweil.tabs.mandelbrot2julia.config.Mandelbrot;
import org.woehlke.computer.kurzweil.tabs.mandelbrot2julia.config.MandelbrotContext;
import org.woehlke.computer.kurzweil.tabs.mandelbrot2julia.model.MandelbrotModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2013 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Date: 05.02.2006
 * Time: 00:51:51
 */
@Log
@Getter
@ToString(callSuper = true, exclude = {"tabCtx","border","preferredSize","layout","model"})
@EqualsAndHashCode(callSuper=true, exclude = {"tabCtx","border","preferredSize","layout","model"})
public class MandelbrotCanvas extends JComponent implements
    Serializable, TabCanvas, Mandelbrot, Future<Void> {

    private static final long serialVersionUID = 7526471155622776147L;

    private final MandelbrotContext tabCtx;
    private final Border border;
    private final LayoutCanvas layout;

    private final MandelbrotModel model;
    private final Dimension preferredSize;

    public MandelbrotCanvas(MandelbrotContext tabCtx) {
        this.tabCtx = tabCtx;
        this.model = tabCtx.getTabModel();
        this.border = this.tabCtx.getCtx().getCanvasBorder();
        this.layout = new LayoutCanvas(this);
        int width = this.model.getWorldDimensions().getWidth();
        int height = this.model.getWorldDimensions().getHeight();
        this.preferredSize = new Dimension(width, height);
        this.setSize(this.preferredSize);
        this.setPreferredSize(preferredSize);
    }

    @Override
    public void paint(Graphics g) {
        this.setSize(this.preferredSize);
        this.setPreferredSize(preferredSize);
        super.paintComponent(g);
        int red = 0;
        int green = 0;
        int blue = 0;
        for(int y = 0; y < model.getWorldDimensions().getY(); y++){
            for(int x = 0; x < model.getWorldDimensions().getX(); x++){
                blue = (((model.getCellStatusFor(x,y))*4)%256);
                Color stateColor = new Color(red, green, blue);
                g.setColor(stateColor);
                g.drawLine(x,y,x,y);
            }
        }
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    public void update() {
        Graphics g = this.getGraphics();
        paint(g);
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public Void get() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public Void get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }

    @Override
    public void showMe() {
        log.info("showMe "+this.toString());
    }
}
