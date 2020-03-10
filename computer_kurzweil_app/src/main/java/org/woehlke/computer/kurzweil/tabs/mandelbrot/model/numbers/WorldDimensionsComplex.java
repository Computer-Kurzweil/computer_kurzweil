package org.woehlke.computer.kurzweil.tabs.mandelbrot.model.numbers;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.Mandelbrot;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class WorldDimensionsComplex implements Mandelbrot {

    private ComplexNumber northWest;
    private ComplexNumber southEast;
    private ComplexNumber center;

}
