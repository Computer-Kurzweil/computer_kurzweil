package org.woehlke.computer.kurzweil.tabs.mandelbrot;

import lombok.Getter;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilProperties;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.fractal.GaussianNumberPlane;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.turing.Point;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.state.ApplicationStateMachine;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.turing.MandelbrotTuringMachine;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 16.12.2019.
 */
@Getter
public class MandelbrotModel {

    private volatile GaussianNumberPlane gaussianNumberPlane;
    private volatile MandelbrotTuringMachine mandelbrotTuringMachine;
    private volatile ApplicationStateMachine applicationStateMachine;

    private volatile ComputerKurzweilProperties properties;
    private volatile MandelbrotTab frame;

    public MandelbrotModel(ComputerKurzweilProperties properties, MandelbrotTab frame) {
        this.properties = properties;
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
        int width = properties.getAllinone().getLattice().getWidth();
        int height = properties.getAllinone().getLattice().getHeight();
        return new Point(width, height);
    }

    public void setModeSwitch() {
        //this.applicationStateMachine.setModeSwitch();
        this.frame.setModeSwitch();
    }

    public GaussianNumberPlane getGaussianNumberPlane() {
        return gaussianNumberPlane;
    }

    public MandelbrotTab getFrame() {
        return frame;
    }

}
