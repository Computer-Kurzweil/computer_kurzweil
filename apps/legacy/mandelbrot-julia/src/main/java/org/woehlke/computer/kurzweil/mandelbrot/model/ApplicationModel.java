package org.woehlke.computer.kurzweil.mandelbrot.model;

import org.woehlke.computer.kurzweil.mandelbrot.config.Config;
import org.woehlke.computer.kurzweil.mandelbrot.model.fractal.GaussianNumberPlane;
import org.woehlke.computer.kurzweil.mandelbrot.model.common.Point;
import org.woehlke.computer.kurzweil.mandelbrot.view.state.ApplicationStateMachine;
import org.woehlke.computer.kurzweil.mandelbrot.model.turing.MandelbrotTuringMachine;
import org.woehlke.computer.kurzweil.mandelbrot.view.ApplicationFrame;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 * (C) 2006 - 2022 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see <a href="https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html">Blog Article</a>
 * @see <a href="https://github.com/Computer-Kurzweil/mandelbrot-julia">Github Repository</a>
 * @see <a href="https://java.woehlke.org/mandelbrot-julia/">Maven Project Repository</a>
 *
 * @see GaussianNumberPlane
 * @see MandelbrotTuringMachine
 * @see ApplicationStateMachine
 *
 * @see Config
 * @see ApplicationFrame
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

    public GaussianNumberPlane getGaussianNumberPlane() {
        return gaussianNumberPlane;
    }

}
