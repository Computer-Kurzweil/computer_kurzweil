package org.woehlke.simulation.mandelbrot.model;

import org.junit.Assert;
import org.junit.Test;
import org.woehlke.simulation.mandelbrot.model.fractal.ComplexNumber;

import java.util.logging.Logger;

/**
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 * Created by tw on 24.08.15.
 */
public class ComplexNumberTest {

    public static Logger log = Logger.getLogger(ComplexNumberTest.class.getName());

    @Test
    public void computeMandelbrotTest1(){
        log.info("computeMandelbrotTest1 start");
        ComplexNumber complexNumber1 = new ComplexNumber();
        int iterations = complexNumber1.computeMandelbrotSet();
        log.info("computeMandelbrotTest1 iterations : "+ iterations);
        Assert.assertTrue(complexNumber1.isInMandelbrotSet());
        Assert.assertNotEquals(ComplexNumber.MAX_ITERATIONS, iterations);
        Assert.assertTrue(0 == iterations);
        log.info("computeMandelbrotTest1 done");
    }

    @Test
    public void computeMandelbrotTest2(){
        log.info("computeMandelbrotTest2 start");
        ComplexNumber complexNumber2 = new ComplexNumber(1.0d,1.2d);
        int iterations = complexNumber2.computeMandelbrotSet();
        log.info("computeMandelbrotTest2 iterations : "+ iterations);
        Assert.assertFalse(complexNumber2.isInMandelbrotSet());
        Assert.assertNotEquals(iterations, ComplexNumber.MAX_ITERATIONS);
        Assert.assertTrue(iterations < ComplexNumber.MAX_ITERATIONS);
        Assert.assertTrue(2 == iterations);
        log.info("computeMandelbrotTest2 done");
    }

    @Test
    public void computeJuliaTest1(){
        log.info("computeJuliaTest1 start");
        ComplexNumber z = new ComplexNumber(0.1d,0.2d);
        ComplexNumber c = new ComplexNumber(0.2d,0.1d);
        int iterations = z.computeJuliaSet(c);
        log.info("computeJuliaTest1 iterations : "+ iterations);
        boolean isInJuliaSet = z.isInJuliaSet();
        Assert.assertTrue(isInJuliaSet);
        Assert.assertNotEquals(ComplexNumber.MAX_ITERATIONS, iterations);
        Assert.assertTrue(iterations < ComplexNumber.MAX_ITERATIONS);
        Assert.assertTrue(0 == iterations);
        log.info("computeJuliaTest1 done");
    }

    @Test
    public void computeJuliaTest2(){
        log.info("computeJuliaTest2 start");
        ComplexNumber z = new ComplexNumber(0.1d,0.2d);
        ComplexNumber c = new ComplexNumber(1.0d,1.1d);
        int iterations = z.computeJuliaSet(c);
        log.info("computeJuliaTest2 iterations :"+ iterations);
        boolean isInJuliaSet = z.isInJuliaSet();
        Assert.assertFalse(isInJuliaSet);
        Assert.assertNotEquals(ComplexNumber.MAX_ITERATIONS, iterations);
        Assert.assertTrue(iterations < ComplexNumber.MAX_ITERATIONS);
        Assert.assertTrue(2 == iterations);
        log.info("computeJuliaTest2 done");
    }
}
