package org.woehlke.computer.kurzweil.tabs.mandelbrot2julia;


import org.junit.Test;
import org.woehlke.computer.kurzweil.commons.model.fractal.ComplexNumber;

import java.util.logging.Logger;

import static org.junit.Assert.*;


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
        assertTrue(complexNumber1.isInMandelbrotSet());
        assertNotEquals(ComplexNumber.MAX_ITERATIONS, iterations);
        assertTrue(0 == iterations);
        log.info("computeMandelbrotTest1 done");
    }

    @Test
    public void computeMandelbrotTest2(){
        log.info("computeMandelbrotTest2 start");
        ComplexNumber complexNumber2 = new ComplexNumber(1.0d,1.2d);
        int iterations = complexNumber2.computeMandelbrotSet();
        log.info("computeMandelbrotTest2 iterations : "+ iterations);
        assertFalse(complexNumber2.isInMandelbrotSet());
        assertNotEquals(iterations, ComplexNumber.MAX_ITERATIONS);
        assertTrue(iterations < ComplexNumber.MAX_ITERATIONS);
        assertTrue(2 == iterations);
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
        assertTrue(isInJuliaSet);
        assertNotEquals(ComplexNumber.MAX_ITERATIONS, iterations);
        assertTrue(iterations < ComplexNumber.MAX_ITERATIONS);
        assertTrue(0 == iterations);
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
        assertFalse(isInJuliaSet);
        assertNotEquals(ComplexNumber.MAX_ITERATIONS, iterations);
        assertTrue(iterations < ComplexNumber.MAX_ITERATIONS);
        assertTrue(2 == iterations);
        log.info("computeJuliaTest2 done");
    }
}
