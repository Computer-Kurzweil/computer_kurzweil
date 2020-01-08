package org.woehlke.simulation.mandelbrot.model.numbers;

public class ComplexWorldRectangle {

    private ComplexNumber northWest;
    private ComplexNumber southEast;

    public ComplexWorldRectangle(ComplexNumber northWest, ComplexNumber southEast) {
        this.northWest = northWest;
        this.southEast = southEast;
    }

    public ComplexNumber getDelta(){
        return this.southEast.minus(this.northWest);
    }

    public ComplexNumber getCenter(){
        ComplexNumber delta = getDelta().half();
        return this.southEast.minus(this.northWest);
    }

    public ComplexNumber getNorthWest() {
        return northWest;
    }

    public ComplexNumber getSouthEast() {
        return southEast;
    }
}
