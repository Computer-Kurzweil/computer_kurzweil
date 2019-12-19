package org.woehlke.simulation.mandelbrot.model;

import org.woehlke.simulation.mandelbrot.config.Config;
import org.woehlke.simulation.mandelbrot.model.fractal.GaussianNumberPlane;
import org.woehlke.simulation.mandelbrot.model.fractal.LatticePoint;
import org.woehlke.simulation.mandelbrot.model.state.ApplicationStateMachine;
import org.woehlke.simulation.mandelbrot.model.turing.MandelbrotTuringMachine;
import org.woehlke.simulation.mandelbrot.view.ApplicationFrame;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 16.12.2019.
 */
public class ApplicationModel {

    private volatile GaussianNumberPlane gaussianNumberPlane;
    private volatile MandelbrotTuringMachine mandelbrotTuringMachine;
    private volatile ApplicationStateMachine applicationStateMachine;

    private volatile Config config;
    private volatile ApplicationFrame frame;

    public ApplicationModel(Config config, ApplicationFrame frame) {
        this.config = config;
        this.frame = frame;
        this.gaussianNumberPlane = new GaussianNumberPlane(this);
        this.mandelbrotTuringMachine = new MandelbrotTuringMachine(this);
        this.applicationStateMachine = new ApplicationStateMachine(this);
    }

    public synchronized void click(LatticePoint c) {
        applicationStateMachine.click();
        switch (applicationStateMachine.getApplicationState()) {
            case MANDELBROT:
                mandelbrotTuringMachine.start();
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
    }

    public synchronized void step() {
        switch (applicationStateMachine.getApplicationState()) {
            case MANDELBROT:
                mandelbrotTuringMachine.step();
                break;
            case JULIA_SET:
            case MANDELBROT_ZOOM:
            case JULIA_SET_ZOOM:
                break;
        }
    }

    public synchronized int getCellStatusFor(int x, int y) {
        return gaussianNumberPlane.getCellStatusFor(x, y);
    }

    public LatticePoint getWorldDimensions() {
        int width = config.getWidth();
        int height = config.getHeight();
        return new LatticePoint(width, height);
    }

    public synchronized void setModeSwitch() {
        this.gaussianNumberPlane.setModeSwitch();
        this.applicationStateMachine.setModeSwitch();
        this.frame.setModeSwitch();
    }

    public synchronized void setModeZoom() {
        this.gaussianNumberPlane.setModeZoom();
        this.applicationStateMachine.setModeZoom();
        this.frame.setModeZoom();
    }

    public GaussianNumberPlane getGaussianNumberPlane() {
        return gaussianNumberPlane;
    }

    public ApplicationFrame getFrame() {
        return frame;
    }

    public Config getConfig() {
        return config;
    }

    public synchronized void zoomOut() {
        switch (applicationStateMachine.getApplicationState()) {
            case MANDELBROT:
                gaussianNumberPlane.zoomOutOfTheMandelbrotSet();
                break;
            case JULIA_SET:
                gaussianNumberPlane.zoomOutOfTheJuliaSet();
                break;
            case MANDELBROT_ZOOM:
                //gaussianNumberPlane.zoomOutOfTheJuliaSet();
                gaussianNumberPlane.zoomOutOfTheMandelbrotSet();
                break;
            case JULIA_SET_ZOOM:
                gaussianNumberPlane.zoomOutOfTheJuliaSet();
                break;
        }
    }
}
