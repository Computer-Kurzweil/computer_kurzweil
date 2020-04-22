package org.woehlke.computer.kurzweil.tabs.mandelbrot.model.numbers;

import lombok.*;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.Mandelbrot;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.state.FractalSetType;

import static org.woehlke.computer.kurzweil.tabs.mandelbrot.model.numbers.CellStatus.MAX_ITERATIONS;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode
@AllArgsConstructor
public class ComplexNumberFractal implements Mandelbrot {

    private final ComplexNumber z;
    private final ComplexNumber c;
    private final Boolean inMandelbrotSet;
    private final Boolean inJuliaSet;
    private final int iterations;
    private final FractalSetType fractalSetType;

    public static ComplexNumberFractal iterateMandelbrotSetFunction(
        final ComplexNumber latticePoint
    ) {
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
        return new ComplexNumberFractal(z,c,inMandelbrotSet,inJuliaSet, iterations,fractalSetType);
    }

    public static ComplexNumberFractal iterateJuliaSetFunction(
        final ComplexNumber myZ,
        final ComplexNumber myC
    ) {
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
        return new ComplexNumberFractal(z, c, inMandelbrotSet,inJuliaSet, iterations,fractalSetType);
    }
}
