package org.woehlke.simulation.mandelbrot.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 * Created by tw on 24.08.15.
 */
public class ComplexNumberTest {

    @Test
    public void computeMandelbrotIterationsTest1(){
        ComplexNumber complexNumber1 = new ComplexNumber(0.0f,0.0f);
        int maxIterations = 32;
        int iterations = complexNumber1.computeMandelbrotIterations(maxIterations);
        Assert.assertEquals(maxIterations, iterations);
    }

    @Test
    public void computeMandelbrotIterationsTest2(){
        int maxIterations = 32;
        ComplexNumber complexNumber2 = new ComplexNumber(1.0f,1.2f);
        int iterations = complexNumber2.computeMandelbrotIterations(maxIterations);
        Assert.assertNotEquals(maxIterations,iterations);
        Assert.assertTrue(iterations < maxIterations);
    }

    @Test
    public void computeJuliaIterationsTest1(){
        ComplexNumber z = new ComplexNumber(0.1f,0.2f);
        ComplexNumber c = new ComplexNumber(0.2f,0.1f);
        int maxIterations = 32;
        int iterations = z.computeJuliaIterations(maxIterations, c);
        Assert.assertEquals(maxIterations, iterations);
    }

    @Test
    public void computeJuliaIterationsTest2(){
        ComplexNumber z = new ComplexNumber(0.1f,0.2f);
        ComplexNumber c = new ComplexNumber(1.0f,1.1f);
        int maxIterations = 32;
        int iterations = z.computeJuliaIterations(maxIterations,c);
        Assert.assertNotEquals(maxIterations,iterations);
        Assert.assertTrue(iterations < maxIterations);
    }
}
