package org.woehlke.simulation.mandelbrot.model.numbers;

import java.io.Serializable;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 18.08.15.
 */
public class ComplexNumber implements Serializable {

    private final double real;
    private final double img;

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
    }

    public ComplexNumber(ComplexNumber complexNumber) {
        this.real = complexNumber.real;
        this.img = complexNumber.img;
    }

    public ComplexNumber(double real, double img) {
        this.real = real;
        this.img = img;
    }

    public ComplexNumber copy() {
        double newRealZ = this.real;
        double newImgZ = this.img;
        return new ComplexNumber(newRealZ,newImgZ);
    }

    public ComplexNumber half() {
        double newRealZ = this.real / 2.0d;
        double newImgZ = this.img  / 2.0d;
        return new ComplexNumber(newRealZ,newImgZ);
    }

    public ComplexNumber minus(ComplexNumber complexNumber){
        double newRealZ = this.real - complexNumber.real;
        double newImgZ = this.img - complexNumber.img;
        return new ComplexNumber(newRealZ,newImgZ);
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

    public boolean isNotDivergent(){
        return (( real*real + img*img ) < DIVERGENCE_THRESHOLD);
    }

}
