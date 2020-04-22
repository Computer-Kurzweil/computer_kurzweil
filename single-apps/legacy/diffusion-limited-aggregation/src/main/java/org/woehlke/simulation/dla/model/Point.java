package org.woehlke.simulation.dla.model;

import org.woehlke.simulation.dla.DiffusionLimitedAggregation;

/**
 * Diffusion Limited Aggregation.
 *
 * (C) 2006 - 2013 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/diffusion-limited-aggregation.html
 * @author Thomas Woehlke
 *
 * Date: 04.02.2006
 * Time: 23:47:05
 */
public class Point implements DiffusionLimitedAggregation {

    static final long serialVersionUID = mySerialVersionUID;


    private int x = 0;
    private int y = 0;

    public Point(Point p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

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

    public void killNagative() {
        if (y < 0) {
            y *= -1;
        }
        if (x < 0) {
            x *= -1;
        }
    }

    public void add(Point p) {
        this.x += p.getX();
        this.y += p.getY();
    }

    public void normalize(Point p) {
        this.x %= p.getX();
        this.y %= p.getY();
    }

    public Point[] getNeighbourhood(Point max){
        Point neighbourhood[] = new Point[9];
        int maxX = max.getX();
        int maxY = max.getY();
        neighbourhood[0]= new Point((this.x+maxX-1) % maxX,(this.y+maxY-1) % maxY);
        neighbourhood[1]= new Point((this.x+maxX-1) % maxX,this.y);
        neighbourhood[2]= new Point((this.x+maxX-1) % maxX,(this.y+maxY+1) % maxY);
        neighbourhood[3]= new Point(this.x,(this.y+maxY-1) % maxY);
        neighbourhood[4]= new Point(this.x,this.y);
        neighbourhood[5]= new Point(this.x,(this.y+maxY+1) % maxY);
        neighbourhood[6]= new Point((this.x+maxX+1) % maxX,(this.y+maxY-1) % maxY);
        neighbourhood[7]= new Point((this.x+maxX+1) % maxX,this.y);
        neighbourhood[8]= new Point((this.x+maxX+1) % maxX,(this.y+maxY+1) % maxY);
        return neighbourhood;
    }
}
