package org.woehlke.computer.kurzweil.apps.mandelbrot.model.numbers;

import lombok.*;
import org.woehlke.computer.kurzweil.apps.mandelbrot.model.state.FractalSetType;

import java.io.Serializable;
import static org.woehlke.computer.kurzweil.apps.mandelbrot.model.numbers.CellStatus.MAX_ITERATIONS;


@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class ComplexNumberFractal implements Serializable {

    @Getter
    private final ComplexNumber z;

    @Getter
    private final ComplexNumber c;

    @Getter
    private final Boolean inMandelbrotSet;

    @Getter
    private final Boolean inJuliaSet;

    @Getter
    private final int iterations;

    @Getter
    private final FractalSetType fractalSetType;

    private ComplexNumberFractal() {
        this.z = null;
        this.c = null;
        this.inMandelbrotSet = null;
        this.inJuliaSet = null;
        this.iterations = 0;
        this.fractalSetType = null;
    }

    public ComplexNumberFractal(
        FractalSetType fractalSetType,
        ComplexNumber z,
        ComplexNumber c,
        Boolean inMandelbrotSet,
        Boolean inJuliaSet,
        int iterations
    ) {
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
}
