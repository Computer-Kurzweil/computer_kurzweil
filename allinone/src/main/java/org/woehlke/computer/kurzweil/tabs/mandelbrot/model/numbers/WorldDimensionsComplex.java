package org.woehlke.computer.kurzweil.tabs.mandelbrot.model.numbers;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class WorldDimensionsComplex implements Serializable {

    private ComplexNumber northWest;
    private ComplexNumber southEast;
    private ComplexNumber center;

}
