package org.woehlke.simulation.all.model;

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
public class LatticePointDla {

    private int x = 0;
    private int y = 0;

    public LatticePointDla(LatticePointDla p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    public LatticePointDla(int x, int y) {
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

    public void add(LatticePointDla p) {
        this.x += p.getX();
        this.y += p.getY();
    }

    public void normalize(LatticePointDla p) {
        this.x %= p.getX();
        this.y %= p.getY();
    }

    public LatticePointDla[] getNeighbourhood(LatticePointDla max){
        LatticePointDla neighbourhood[] = new LatticePointDla[9];
        int maxX = max.getX();
        int maxY = max.getY();
        neighbourhood[0]= new LatticePointDla((this.x+maxX-1) % maxX,(this.y+maxY-1) % maxY);
        neighbourhood[1]= new LatticePointDla((this.x+maxX-1) % maxX,this.y);
        neighbourhood[2]= new LatticePointDla((this.x+maxX-1) % maxX,(this.y+maxY+1) % maxY);
        neighbourhood[3]= new LatticePointDla(this.x,(this.y+maxY-1) % maxY);
        neighbourhood[4]= new LatticePointDla(this.x,this.y);
        neighbourhood[5]= new LatticePointDla(this.x,(this.y+maxY+1) % maxY);
        neighbourhood[6]= new LatticePointDla((this.x+maxX+1) % maxX,(this.y+maxY-1) % maxY);
        neighbourhood[7]= new LatticePointDla((this.x+maxX+1) % maxX,this.y);
        neighbourhood[8]= new LatticePointDla((this.x+maxX+1) % maxX,(this.y+maxY+1) % maxY);
        return neighbourhood;
    }
}
