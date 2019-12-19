package org.woehlke.simulation.mandelbrot.model;

import org.woehlke.simulation.mandelbrot.config.Config;
import org.woehlke.simulation.mandelbrot.model.fractal.GaussianNumberPlane;
import org.woehlke.simulation.mandelbrot.model.helper.Point;
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
        this.applicationStateMachine = new ApplicationStateMachine();
    }

    public synchronized boolean click(Point c) {
        applicationStateMachine.click();
        boolean repaint = true;
        switch (applicationStateMachine.getApplicationState()) {
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
        switch (applicationStateMachine.getApplicationState()) {
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
        int width = config.getWidth();
        int height = config.getHeight();
        return new Point(width, height);
    }

    public void setModeSwitch() {
        this.applicationStateMachine.setModeSwitch();
        this.frame.setModeSwitch();
    }

    public void setModeZoom() {
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

    public void zoomOut() {
        switch (applicationStateMachine.getApplicationState()) {
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
}
