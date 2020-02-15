package org.woehlke.computer.kurzweil.tabs.mandelbrot;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.tabs.TabCanvas;
import org.woehlke.computer.kurzweil.model.LatticePoint;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.fractal.GaussianNumberPlaneBaseJulia;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.fractal.GaussianNumberPlaneMandelbrot;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.numbers.CellStatus;
import org.woehlke.computer.kurzweil.widgets.layouts.CanvasLayout;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


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
public class MandelbrotCanvas extends JComponent implements TabCanvas, MouseListener {

    private final MandelbrotContext tabCtx;
    private final CompoundBorder border;
    private final Dimension preferredSize;
    private final CanvasLayout layout;
    private final GaussianNumberPlaneBaseJulia gaussianNumberPlaneBaseJulia;
    private final GaussianNumberPlaneMandelbrot gaussianNumberPlaneMandelbrot;
    private final Mandelbrot mandelbrot;

    private final static int startX = 0;
    private final static int startY = 0;
    private final int worldX;
    private final int worldY;

    public MandelbrotCanvas(
        MandelbrotContext tabCtx
    ) {
        this.tabCtx = tabCtx;
        this.border = this.tabCtx.getCtx().getBorder();
        this.worldX = this.tabCtx.getCtx().getWorldDimensions().getWidth();
        this.worldY = this.tabCtx.getCtx().getWorldDimensions().getHeight();
        this.layout = new CanvasLayout(this);
        this.gaussianNumberPlaneBaseJulia = new GaussianNumberPlaneBaseJulia( this.tabCtx);
        this.gaussianNumberPlaneMandelbrot = new GaussianNumberPlaneMandelbrot( this.tabCtx);
        this.mandelbrot = new Mandelbrot(this.tabCtx);
        this.preferredSize = new Dimension(worldX, worldY);
        this.setBorder(border);
        this.setLayout(layout);
        this.setSize(this.preferredSize);
        this.setPreferredSize(this.preferredSize);
    }

    public CellStatus getCellStatusFor(int x, int y) {
        switch (mandelbrot.getState().getFractalSetType()) {
            case JULIA_SET:
                return gaussianNumberPlaneBaseJulia.getCellStatusFor(x, y);
            case MANDELBROT_SET:
            default:
                return gaussianNumberPlaneMandelbrot.getCellStatusFor(x, y);
        }
    }

    public LatticePoint getWorldDimensions() {
        return this.tabCtx.getCtx().getWorldDimensions();
    }

    public void start() {
        this.gaussianNumberPlaneBaseJulia.start();
        this.gaussianNumberPlaneMandelbrot.start();
        this.mandelbrot.start();
        this.setModeSwitch();
        this.computeTheMandelbrotSet();
    }

    public void setModeSwitch() {
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.gaussianNumberPlaneBaseJulia.setModeSwitch();
        this.gaussianNumberPlaneMandelbrot.setModeSwitch();
        this.mandelbrot.setModeSwitch();
    }

    public void setModeZoom() {
        this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        this.gaussianNumberPlaneBaseJulia.setModeZoom();
        this.gaussianNumberPlaneMandelbrot.setModeZoom();
        this.mandelbrot.setModeZoom();
    }

    @Override public void paint(Graphics g) {
        this.setSize(this.preferredSize);
        this.setPreferredSize(this.preferredSize);
        super.paintComponent(g);
        int y;
        int x;
        for(y = 0; y < worldY; y++){
            for(x = 0; x < worldX; x++){
                g.setColor( this.getCellStatusFor(x,y).canvasColor() );
                g.drawLine(x,y,x,y);
            }
        }
    }

    @Override public void update(Graphics g) {
        paint(g);
    }

    public void zoomOut() {
        switch (mandelbrot.getState().getFractalSetType()) {
            case JULIA_SET:
                gaussianNumberPlaneBaseJulia.zoomOut();
                break;
            case MANDELBROT_SET:
            default:
                gaussianNumberPlaneMandelbrot.zoomOut();
                break;
        }
    }

    public String getZoomLevel() {
        switch (mandelbrot.getState().getFractalSetType()) {
            case MANDELBROT_SET:
                return gaussianNumberPlaneMandelbrot.getZoomLevel();
            case JULIA_SET:
                return gaussianNumberPlaneBaseJulia.getZoomLevel();
            default:
                return "1";
        }
    }

    private void computeTheMandelbrotSet() {
        tabCtx.getController().start();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        LatticePoint latticePoint = new LatticePoint(e.getX(), e.getY());
        mandelbrot.click();
        switch (mandelbrot.getState()) {
            case MANDELBROT_SWITCH:
                computeTheMandelbrotSet();
                break;
            case JULIA_SET_SWITCH:
                this.gaussianNumberPlaneBaseJulia.computeTheSet(latticePoint);
                break;
            case MANDELBROT_ZOOM:
                this.gaussianNumberPlaneMandelbrot.zoomInto(latticePoint);
                break;
            case JULIA_SET_ZOOM:
                this.gaussianNumberPlaneBaseJulia.zoomInto(latticePoint);
                break;
        }
        e.consume();
        this.showMe();
    }

    @Override
    public void update() {
        //repaint();
    }

    @Override
    public void showMe() {
        //this.setVisible(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
