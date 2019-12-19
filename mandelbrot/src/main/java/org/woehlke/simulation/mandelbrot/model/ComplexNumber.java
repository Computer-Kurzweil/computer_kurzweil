package org.woehlke.simulation.mandelbrot.model;

import org.woehlke.simulation.mandelbrot.MandelbrotSet;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 18.08.15.
 */
public class ComplexNumber implements MandelbrotSet {

    static final long serialVersionUID = mySerialVersionUID;

    private float real;
    private float img;

    private float realZ=0.0f;
    private float imgZ=0.0f;

    public float getReal() {
        return real;
    }

    public float getImg() {
        return img;
    }

    public ComplexNumber(float real, float img) {
        this.img = img;
        this.real = real;
    }

    public int computeMandelbrotIterations(final int maxIterations) {
        int i = 0;
        realZ=0.0f;
        imgZ=0.0f;
        float newRealZ;
        float newImgZ;
        do {
            newRealZ=realZ*realZ-imgZ*imgZ + real;
            newImgZ=2*realZ*imgZ + img;
            realZ=newRealZ;
            imgZ=newImgZ;
            i++;
        } while (i<maxIterations && isNotDivergent());
        return i;
    }

    private boolean isNotDivergent(){
        return (( realZ*realZ + imgZ*imgZ ) < 4.0f);
    }

    @Override
    public String toString() {
        return "ComplexNumber{" +
                "real=" + real +
                ", img=" + img +
                ", realZ=" + realZ +
                ", imgZ=" + imgZ +
                '}';
    }

    public int computeJuliaIterations(int maxIterations, ComplexNumber c) {
        int i = 0;
        realZ = real;
        imgZ = img;
        float newRealZ;
        float newImgZ;
        do {
            newRealZ=realZ*realZ-imgZ*imgZ + c.getReal();
            newImgZ=2*realZ*imgZ + c.getImg();
            realZ=newRealZ;
            imgZ=newImgZ;
            i++;
        } while (i<maxIterations && isNotDivergent());
        return i;
    }
}
