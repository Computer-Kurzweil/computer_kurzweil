package org.woehlke.computer.kurzweil.mandelbrot.zoom.model;

import org.woehlke.computer.kurzweil.mandelbrot.zoom.config.Config;
import org.woehlke.computer.kurzweil.mandelbrot.zoom.model.fractal.GaussianNumberPlane;
import org.woehlke.computer.kurzweil.mandelbrot.zoom.model.common.Point;
import org.woehlke.computer.kurzweil.mandelbrot.zoom.model.turing.MandelbrotTuringMachine;
import org.woehlke.computer.kurzweil.mandelbrot.zoom.view.ApplicationFrame;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 * (C) 2006 - 2015 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see <a href="https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html">Blog Article</a>
 * @see <a href="https://github.com/Computer-Kurzweil/mandelbrot-zoom">Github Repository</a>
 * @see <a href="https://java.woehlke.org/mandelbrot-zoom/">Maven Project Repository</a>
 *
 * @see org.woehlke.computer.kurzweil.mandelbrot.zoom.model.fractal.GaussianNumberPlane
 * @see org.woehlke.computer.kurzweil.mandelbrot.zoom.model.turing.MandelbrotTuringMachine
 *
 * @see org.woehlke.computer.kurzweil.mandelbrot.zoom.config.Config
 * @see org.woehlke.computer.kurzweil.mandelbrot.zoom.view.ApplicationFrame
 *
 * Created by tw on 16.12.2019.
 */
public class ApplicationModel {

    private volatile GaussianNumberPlane gaussianNumberPlane;
    private volatile MandelbrotTuringMachine mandelbrotTuringMachine;

    private volatile Config config;
    private volatile ApplicationFrame frame;

    public ApplicationModel(Config config, ApplicationFrame frame) {
        this.config = config;
        this.frame = frame;
        this.gaussianNumberPlane = new GaussianNumberPlane(this);
        this.mandelbrotTuringMachine = new MandelbrotTuringMachine(this);
    }

    public synchronized boolean click(Point c) {
        gaussianNumberPlane.zoomIntoTheMandelbrotSet(c);
        boolean repaint = true;
        return repaint;
    }

    public synchronized boolean step() {
        boolean repaint = mandelbrotTuringMachine.step();
        return repaint;
    }

    public void zoomOut() {
        gaussianNumberPlane.zoomOutOfTheMandelbrotSet();
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

    public ApplicationFrame getFrame() {
        return frame;
    }

    public Config getConfig() {
        return config;
    }

}
