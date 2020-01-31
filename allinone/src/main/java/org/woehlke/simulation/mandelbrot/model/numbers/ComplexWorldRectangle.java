package org.woehlke.simulation.mandelbrot.model.numbers;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;


@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class ComplexWorldRectangle implements Serializable {

    private ComplexNumber northWest;
    private ComplexNumber southEast;

    /*
    public ComplexWorldRectangle(ComplexNumber northWest, ComplexNumber southEast) {
        this.northWest = northWest;
        this.southEast = southEast;
    }
    */

    public ComplexNumber getDelta(){
        return this.southEast.minus(this.northWest);
    }

    public ComplexNumber getCenter(){
        ComplexNumber delta = getDelta().half();
        return this.southEast.minus(this.northWest);
    }

}
