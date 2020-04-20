package org.woehlke.computer.kurzweil.mandelbrot.model.fractal;

import java.util.Objects;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 18.08.15.
 */
public class ComplexNumber {

    private volatile double real;
    private volatile double img;

    public final static int MAX_ITERATIONS = 64;
    private final static double DIVERGENCE_THRESHOLD = 4.0d;

    public double getReal() {
        return real;
    }

    public double getImg() {
        return img;
    }

    public ComplexNumber() {
        this.real = 0.0d;
        this.img = 0.0d;
        this.iterations=0;
        this.inMandelbrotSet=false;
        this.inJuliaSet=false;
    }

    public ComplexNumber(ComplexNumber complexNumber) {
        this.real = complexNumber.real;
        this.img = complexNumber.img;
        this.iterations=complexNumber.iterations;
        this.inMandelbrotSet=complexNumber.inMandelbrotSet;
        this.inJuliaSet=complexNumber.inJuliaSet;
    }

    public ComplexNumber(double real, double img) {
        this.real = real;
        this.img = img;
        this.iterations=0;
        this.inMandelbrotSet=false;
        this.inJuliaSet=false;
    }

    public ComplexNumber plus(ComplexNumber complexNumber){
        double newRealZ = this.real + complexNumber.real;
        double newImgZ = this.img + complexNumber.img;
        return new ComplexNumber(newRealZ,newImgZ);
    }

    public ComplexNumber square(){
        double realZ=real;
        double imgZ=img;
        double newRealZ=realZ*realZ-imgZ*imgZ;
        double newImgZ=2*realZ*imgZ;
        return new ComplexNumber(newRealZ,newImgZ);
    }

    private volatile int iterations;
    private volatile boolean inMandelbrotSet;
    private volatile boolean inJuliaSet;

    public synchronized int computeMandelbrotSet() {
        int iterationsTmp = 0;
        ComplexNumber z = new ComplexNumber();
        do {
            iterationsTmp++;
            z = z.square().plus(this);
        } while (z.isNotDivergent() && (iterationsTmp < MAX_ITERATIONS));
        this.inMandelbrotSet = z.isNotDivergent();
        this.iterations = this.inMandelbrotSet?0:iterationsTmp;
        return this.iterations;
    }

    public synchronized int computeJuliaSet(ComplexNumber c) {
        int iterationsTmp = 0;
        ComplexNumber z = new ComplexNumber(this);
        do {
            iterationsTmp++;
            z = z.square().plus(c);
        } while (z.isNotDivergent() && (iterationsTmp < MAX_ITERATIONS));
        this.inJuliaSet = z.isNotDivergent();
        this.iterations = this.inJuliaSet?0:iterationsTmp;
        return this.iterations;
    }

    public synchronized boolean isInMandelbrotSet() {
        return inMandelbrotSet;
    }

    public synchronized boolean isInJuliaSet() {
        return inJuliaSet;
    }

    public synchronized boolean isNotDivergent(){
        return (( real*real + img*img ) < DIVERGENCE_THRESHOLD);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComplexNumber)) return false;
        ComplexNumber that = (ComplexNumber) o;
        return Double.compare(that.getReal(), getReal()) == 0 &&
            Double.compare(that.getImg(), getImg()) == 0 &&
            iterations == that.iterations &&
            isInMandelbrotSet() == that.isInMandelbrotSet() &&
            isInJuliaSet() == that.isInJuliaSet();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReal(), getImg(), iterations, isInMandelbrotSet(), isInJuliaSet());
    }

    @Override
    public String toString() {
        return "ComplexNumber{" +
            "real=" + real +
            ", img=" + img +
            ", iterations=" + iterations +
            ", inMandelbrotSet=" + inMandelbrotSet +
            ", inJuliaSet=" + inJuliaSet +
            '}';
    }
}
