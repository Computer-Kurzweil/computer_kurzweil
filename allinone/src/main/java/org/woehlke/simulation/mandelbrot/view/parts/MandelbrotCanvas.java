package org.woehlke.simulation.mandelbrot.view.parts;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.simulation.allinone.config.ComputerKurzweilApplicationContext;
import org.woehlke.simulation.allinone.model.lattice.LatticePoint;
import org.woehlke.simulation.mandelbrot.control.ComputeMandelbrotSetThread;
import org.woehlke.simulation.mandelbrot.model.ApplicationStateMachine;
import org.woehlke.simulation.mandelbrot.model.fractal.GaussianNumberPlaneBaseJulia;
import org.woehlke.simulation.mandelbrot.model.fractal.GaussianNumberPlaneMandelbrot;
import org.woehlke.simulation.mandelbrot.model.numbers.CellStatus;
import org.woehlke.simulation.mandelbrot.model.turing.MandelbrotTuringMachine;
import org.woehlke.simulation.mandelbrot.view.MandelbrotFrame;

import javax.swing.*;
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
public class MandelbrotCanvas extends JComponent implements MouseListener {

    @Getter private final ComputerKurzweilApplicationContext ctx;

    @Getter private final GaussianNumberPlaneBaseJulia gaussianNumberPlaneBaseJulia;
    @Getter private final GaussianNumberPlaneMandelbrot gaussianNumberPlaneMandelbrot;
    @Getter private final MandelbrotTuringMachine mandelbrotTuringMachine;
    @Getter private final ApplicationStateMachine applicationStateMachine;
    @Getter private final MandelbrotPanelButtons panelButtons;
    @Getter private final MandelbrotFrame frame;
    @Getter private final ComputeMandelbrotSetThread computeMandelbrotSetThread;

    public MandelbrotCanvas(
        ComputerKurzweilApplicationContext ctx,
        MandelbrotPanelButtons panelButtons,
        MandelbrotFrame frame
    ) {
        this.ctx = ctx;
        this.panelButtons = panelButtons;
        this.frame = frame;
        int width = this.ctx.getWorldDimensions().getWidth();
        int height = this.ctx.getWorldDimensions().getHeight();
        this.preferredSize = new Dimension(width, height);
        this.setSize(this.preferredSize);
        this.setPreferredSize(this.preferredSize);
        this.gaussianNumberPlaneBaseJulia = new GaussianNumberPlaneBaseJulia(    this.ctx );
        this.gaussianNumberPlaneMandelbrot = new GaussianNumberPlaneMandelbrot(    this.ctx );
        this.applicationStateMachine = new ApplicationStateMachine(   this.ctx );
        this.mandelbrotTuringMachine = new MandelbrotTuringMachine(
            this.gaussianNumberPlaneMandelbrot
        );
        this.computeMandelbrotSetThread = new ComputeMandelbrotSetThread(
            this.ctx, this.panelButtons,this, frame
        );
    }

    public CellStatus getCellStatusFor(int x, int y) {
        switch (applicationStateMachine.getState().getFractalSetType()) {
            case JULIA_SET:
                return gaussianNumberPlaneBaseJulia.getCellStatusFor(x, y);
            case MANDELBROT_SET:
            default:
                return gaussianNumberPlaneMandelbrot.getCellStatusFor(x, y);
        }
    }

    public LatticePoint getWorldDimensions() {
        return this.ctx.getWorldDimensions();
    }

    public void start() {
        this.gaussianNumberPlaneBaseJulia.start();
        this.gaussianNumberPlaneMandelbrot.start();
        this.mandelbrotTuringMachine.start();
        this.applicationStateMachine.start();
        this.setModeSwitch();
        this.computeTheMandelbrotSet();
    }

    public void setModeSwitch() {
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.gaussianNumberPlaneBaseJulia.setModeSwitch();
        this.gaussianNumberPlaneMandelbrot.setModeSwitch();
        this.applicationStateMachine.setModeSwitch();
        this.frame.setModeSwitch();
        this.frame.showMe();
    }

    public void setModeZoom() {
        this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        this.gaussianNumberPlaneBaseJulia.setModeZoom();
        this.gaussianNumberPlaneMandelbrot.setModeZoom();
        this.applicationStateMachine.setModeZoom();
        this.frame.setModeZoom();
        this.frame.showMe();
    }

    @Override public void paint(Graphics g) {
        this.setSize(this.preferredSize);
        this.setPreferredSize(this.preferredSize);
        super.paintComponent(g);
        for(int y = 0; y < this.ctx.getWorldDimensions().getY(); y++){
            for(int x = 0; x < this.ctx.getWorldDimensions().getX(); x++){
                g.setColor( this.getCellStatusFor(x,y).canvasColor());
                g.drawLine(x,y,x,y);
            }
        }
    }

    @Override public void update(Graphics g) {
        paint(g);
    }

    @Getter
    private final Dimension preferredSize;

    public void zoomOut() {
        switch (applicationStateMachine.getState().getFractalSetType()) {
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
        switch (applicationStateMachine.getState().getFractalSetType()) {
            case MANDELBROT_SET:
                return gaussianNumberPlaneMandelbrot.getZoomLevel();
            case JULIA_SET:
                return gaussianNumberPlaneBaseJulia.getZoomLevel();
            default:
                return "1";
        }
    }

    private void computeTheMandelbrotSet() {
        computeMandelbrotSetThread.start();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        LatticePoint latticePoint = new LatticePoint(e.getX(), e.getY());
        applicationStateMachine.click();
        switch (applicationStateMachine.getState()) {
            case MANDELBROT_SWITCH:
                this.computeTheMandelbrotSet();
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
        this.frame.showMe();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
