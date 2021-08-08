package org.woehlke.computer.kurzweil.tabs.mandelbrot2julia;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilProperties;
import org.woehlke.computer.kurzweil.commons.tabs.TabModel;
import org.woehlke.computer.kurzweil.commons.model.fractal.GaussianNumberPlane;
import org.woehlke.computer.kurzweil.tabs.mandelbrotzoom.model.state.MandelbrotTabStateMachine;
import org.woehlke.computer.kurzweil.tabs.mandelbrotzoom.model.turing.MandelbrotTuringMachine;
import org.woehlke.computer.kurzweil.tabs.mandelbrotzoom.model.turing.Point;

import java.util.concurrent.ForkJoinTask;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 16.12.2019.
 */
@Log
@Getter
public class MandelbrotModel extends ForkJoinTask<Void> implements TabModel {

    private static final long serialVersionUID = 7526471155622776147L;

    private final GaussianNumberPlane gaussianNumberPlane;
    private final MandelbrotTuringMachine mandelbrotTuringMachine;
    private final MandelbrotTabStateMachine mandelbrotTabStateMachine;

    private final ComputerKurzweilProperties properties;
    private final MandelbrotTab tab;

    public MandelbrotModel(ComputerKurzweilProperties properties, MandelbrotTab tab) {
        this.properties = properties;
        this.tab = tab;
        this.gaussianNumberPlane = new GaussianNumberPlane(this);
        this.mandelbrotTuringMachine = new MandelbrotTuringMachine(this);
        this.mandelbrotTabStateMachine = new MandelbrotTabStateMachine();
    }

    public synchronized boolean click(Point c) {
        mandelbrotTabStateMachine.click();
        boolean repaint = true;
        switch (mandelbrotTabStateMachine.getMandelbrotTabState()) {
            case MANDELBROT:
                mandelbrotTuringMachine.start();
                repaint = false;
                break;
            case JULIA_SET:
                gaussianNumberPlane.computeTheJuliaSetFor(c);
                break;
            case MANDELBROT_ZOOM:
                gaussianNumberPlane.zoomIntoTheMandelbrotSet(c);
                break;
            case JULIA_SET_ZOOM:
                gaussianNumberPlane.zoomIntoTheJuliaSetFor(c);
                break;
        }
        return repaint;
    }

    public synchronized boolean step() {
        boolean repaint = false;
        switch (mandelbrotTabStateMachine.getMandelbrotTabState()) {
            case MANDELBROT:
                repaint = mandelbrotTuringMachine.step();
                break;
            case JULIA_SET:
            case MANDELBROT_ZOOM:
            case JULIA_SET_ZOOM:
                break;
        }
        return repaint;
    }

    public synchronized int getCellStatusFor(int x, int y) {
        return gaussianNumberPlane.getCellStatusFor(x, y);
    }

    public Point getWorldDimensions() {
        int width = properties.getAllinone().getLattice().getWidth();
        int height = properties.getAllinone().getLattice().getHeight();
        return new Point(width, height);
    }

    public void setModeSwitch() {
        this.mandelbrotTabStateMachine.setModeSwitch();
        //this.tab.setModeSwitch();
    }

    public void setModeZoom() {
        this.gaussianNumberPlane.setModeZoom();
        this.mandelbrotTabStateMachine.setModeZoom();
        //this.tab.setModeZoom();
    }

    public GaussianNumberPlane getGaussianNumberPlane() {
        return gaussianNumberPlane;
    }

    public MandelbrotTab getTab() {
        return tab;
    }

    public void zoomOut() {
        switch (mandelbrotTabStateMachine.getMandelbrotTabState()) {
            case MANDELBROT:
            case JULIA_SET:
                break;
            case MANDELBROT_ZOOM:
                gaussianNumberPlane.zoomOutOfTheMandelbrotSet();
                break;
            case JULIA_SET_ZOOM:
                gaussianNumberPlane.zoomOutOfTheJuliaSet();
                break;
        }
    }

    @Override
    public Void getRawResult() {
        return null;
    }

    @Override
    protected void setRawResult(Void value) {

    }

    @Override
    protected boolean exec() {
        return false;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
