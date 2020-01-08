package org.woehlke.simulation.mandelbrot.model.numbers;

import java.util.Objects;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Date: 04.02.2006
 * Time: 23:47:05
 */
public class LatticePoint {

    private int x = 0;
    private int y = 0;

    public LatticePoint() {
    }

    public LatticePoint(LatticePoint p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    public LatticePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public LatticePoint copy() {
        return new LatticePoint(this);
    }

    public static LatticePoint start(LatticePoint worldDimensions){
        return new LatticePoint((worldDimensions.getX()-2),(worldDimensions.getY()/2+11));
    }

    public void moveUp() {
        y--;
    }

    public void moveRight() {
        x++;
    }

    public void moveDown() {
        y++;
    }

    public void moveLeft() {
        x--;
    }

    public int getWidth(){
        return x;
    }
    public int getHeight() { return y; }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LatticePoint)) return false;
        LatticePoint latticePoint = (LatticePoint) o;
        return getX() == latticePoint.getX() &&
            getY() == latticePoint.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    @Override
    public String toString() {
        return "Point{" +
            "x=" + x +
            ", y=" + y +
            '}';
    }
}
