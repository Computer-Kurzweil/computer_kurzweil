package org.woehlke.computer.kurzweil.tabs.mandelbrot.model.numbers;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.Mandelbrot;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode
@AllArgsConstructor
public class ComplexWorldRectangle implements Mandelbrot {

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
