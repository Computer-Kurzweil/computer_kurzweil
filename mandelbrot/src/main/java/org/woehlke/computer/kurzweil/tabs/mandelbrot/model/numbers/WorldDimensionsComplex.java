package org.woehlke.computer.kurzweil.tabs.mandelbrot.model.numbers;

public class WorldDimensionsComplex {

    private ComplexNumber northWest;
    private ComplexNumber southEast;
    private ComplexNumber center;

    public WorldDimensionsComplex(ComplexNumber northWest, ComplexNumber southEast, ComplexNumber center) {
        this.northWest = northWest;
        this.southEast = southEast;
        this.center = center;
    }
}
