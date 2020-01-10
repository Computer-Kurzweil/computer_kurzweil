package org.woehlke.simulation.mandelbrot.model.numbers;

import java.io.Serializable;

public class WorldDimensionsComplex implements Serializable {

    private ComplexNumber northWest;
    private ComplexNumber southEast;
    private ComplexNumber center;

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
}
