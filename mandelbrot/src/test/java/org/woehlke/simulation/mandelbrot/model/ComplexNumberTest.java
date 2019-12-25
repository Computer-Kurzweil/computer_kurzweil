package org.woehlke.simulation.mandelbrot.model;

import org.junit.Assert;
import org.junit.Test;
import org.woehlke.simulation.mandelbrot.model.numbers.ComplexNumber;
import org.woehlke.simulation.mandelbrot.model.numbers.ComplexNumberFractal;

import java.util.logging.Logger;

import static org.woehlke.simulation.mandelbrot.model.numbers.CellStatus.MAX_ITERATIONS;

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
        ComplexNumberFractal fractal = ComplexNumberFractal.iterateMandelbrotSetFunction(complexNumber1);
        int iterations = fractal.getIterations();
        log.info("computeMandelbrotTest1 iterations : "+ iterations);
        Assert.assertTrue(fractal.getInMandelbrotSet());
        Assert.assertNotEquals(MAX_ITERATIONS, iterations);
        Assert.assertTrue(0 == iterations);
        log.info("computeMandelbrotTest1 done");
    }

    @Test
    public void computeMandelbrotTest2(){
        log.info("computeMandelbrotTest2 start");
        ComplexNumber complexNumber2 = new ComplexNumber(1.0d,1.2d);
        ComplexNumberFractal fractal = ComplexNumberFractal.iterateMandelbrotSetFunction(complexNumber2);
        int iterations = fractal.getIterations();
        log.info("computeMandelbrotTest2 iterations : "+ iterations);
        Assert.assertFalse(fractal.getInMandelbrotSet());
        Assert.assertNotEquals(iterations, MAX_ITERATIONS);
        Assert.assertTrue(iterations < MAX_ITERATIONS);
        Assert.assertTrue(2 == iterations);
        log.info("computeMandelbrotTest2 done");
    }

    @Test
    public void computeJuliaTest1(){
        log.info("computeJuliaTest1 start");
        ComplexNumber z = new ComplexNumber(0.1d,0.2d);
        ComplexNumber c = new ComplexNumber(0.2d,0.1d);

        ComplexNumberFractal fractal = ComplexNumberFractal.iterateJuliaSetFunction(z,c);
        int iterations = fractal.getIterations();
        boolean isInJuliaSet = fractal.getInJuliaSet();
        log.info("computeJuliaTest1 iterations : "+ iterations);

        Assert.assertTrue(isInJuliaSet);
        Assert.assertNotEquals(MAX_ITERATIONS, iterations);
        Assert.assertTrue(iterations < MAX_ITERATIONS);
        Assert.assertTrue(0 == iterations);
        log.info("computeJuliaTest1 done");
    }

    @Test
    public void computeJuliaTest2(){
        log.info("computeJuliaTest2 start");
        ComplexNumber z = new ComplexNumber(0.1d,0.2d);
        ComplexNumber c = new ComplexNumber(1.0d,1.1d);        ComplexNumberFractal fractal = ComplexNumberFractal.iterateJuliaSetFunction(z,c);
        int iterations = fractal.getIterations();
        boolean isInJuliaSet = fractal.getInJuliaSet();

        log.info("computeJuliaTest2 iterations :"+ iterations);

        Assert.assertFalse(isInJuliaSet);
        Assert.assertNotEquals(MAX_ITERATIONS, iterations);
        Assert.assertTrue(iterations < MAX_ITERATIONS);
        Assert.assertTrue(2 == iterations);
        log.info("computeJuliaTest2 done");
    }
}
