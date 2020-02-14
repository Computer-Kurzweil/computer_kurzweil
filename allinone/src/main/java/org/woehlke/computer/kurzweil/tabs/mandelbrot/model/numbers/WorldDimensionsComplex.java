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

    /*
    public WorldDimensionsComplex(ComplexNumber northWest, ComplexNumber southEast, ComplexNumber center) {
        this.northWest = northWest;
        this.southEast = southEast;
        this.center = center;
    }

    public ComplexNumber getNorthWest() {
        return northWest;
    }

    public ComplexNumber getSouthEast() {
        return southEast;
    }

    public ComplexNumber getCenter() {
        return center;
    }
    */
}
