package org.woehlke.computer.kurzweil.tabs.mandelbrot.model.numbers;

import org.woehlke.computer.kurzweil.tabs.mandelbrot.control.state.FractalSetType;

import java.util.Objects;

import static org.woehlke.computer.kurzweil.tabs.mandelbrot.model.numbers.CellStatus.MAX_ITERATIONS;

public class ComplexNumberFractal {

    private final ComplexNumber z;
    private final ComplexNumber c;
    private final Boolean inMandelbrotSet;
    private final Boolean inJuliaSet;
    private final int iterations;
    private final FractalSetType fractalSetType;

    private ComplexNumberFractal() {
        this.z = null;
        this.c = null;
        this.inMandelbrotSet = null;
        this.inJuliaSet = null;
        this.iterations = 0;
        this.fractalSetType = null;
    }

    public ComplexNumberFractal(FractalSetType fractalSetType, ComplexNumber z, ComplexNumber c, Boolean inMandelbrotSet, Boolean inJuliaSet, int iterations) {
        this.z = z;
        this.c = c;
        this.inMandelbrotSet = inMandelbrotSet;
        this.inJuliaSet = inJuliaSet;
        this.iterations = iterations;
        this.fractalSetType = fractalSetType;
    }

    public static ComplexNumberFractal iterateMandelbrotSetFunction(final ComplexNumber latticePoint) {
        FractalSetType fractalSetType = FractalSetType.MANDELBROT_SET;
        int iterationsTmp = 0;
        ComplexNumber z = new ComplexNumber();
        ComplexNumber c = latticePoint.copy();
        do {
            iterationsTmp++;
            z = z.square().plus( c );
        } while (z.isNotDivergent() && (iterationsTmp < MAX_ITERATIONS));
        Boolean inMandelbrotSet = z.isNotDivergent();
        Boolean inJuliaSet = null;
        int iterations =  inMandelbrotSet?0:iterationsTmp;
        return new ComplexNumberFractal(fractalSetType,z,c,inMandelbrotSet,inJuliaSet, iterations);
    }

    public static ComplexNumberFractal iterateJuliaSetFunction(final ComplexNumber myZ, final ComplexNumber myC) {
        FractalSetType fractalSetType = FractalSetType.JULIA_SET;
        int iterationsTmp = 0;
        ComplexNumber z = myZ.copy();
        ComplexNumber c = myC.copy();
        do {
            iterationsTmp++;
            z = z.square().plus(c);
        } while (z.isNotDivergent() && (iterationsTmp < MAX_ITERATIONS));
        Boolean inMandelbrotSet = null;
        Boolean inJuliaSet =  z.isNotDivergent();
        int iterations =  inJuliaSet?0:iterationsTmp;
        return new ComplexNumberFractal(fractalSetType,z,c,inMandelbrotSet,inJuliaSet, iterations);
    }

    public ComplexNumber getZ() {
        return z;
    }

    public ComplexNumber getC() {
        return c;
    }

    public Boolean getInMandelbrotSet() {
        return inMandelbrotSet;
    }

    public Boolean getInJuliaSet() {
        return inJuliaSet;
    }

    public int getIterations() {
        return iterations;
    }

    public FractalSetType getFractalSetType() {
        return fractalSetType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComplexNumberFractal)) return false;
        ComplexNumberFractal that = (ComplexNumberFractal) o;
        return getIterations() == that.getIterations() &&
            getZ().equals(that.getZ()) &&
            getC().equals(that.getC()) &&
            Objects.equals(getInMandelbrotSet(), that.getInMandelbrotSet()) &&
            Objects.equals(getInJuliaSet(), that.getInJuliaSet()) &&
            getFractalSetType() == that.getFractalSetType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getZ(), getC(), getInMandelbrotSet(), getInJuliaSet(), getIterations(), getFractalSetType());
    }

    @Override
    public String toString() {
        return "ComplexNumberFractal{" +
            "z=" + z +
            ", c=" + c +
            ", inMandelbrotSet=" + inMandelbrotSet +
            ", inJuliaSet=" + inJuliaSet +
            ", iterations=" + iterations +
            ", fractalSetType=" + fractalSetType +
            '}';
    }
}
