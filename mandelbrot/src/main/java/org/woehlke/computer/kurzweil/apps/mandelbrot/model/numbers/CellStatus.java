package org.woehlke.computer.kurzweil.apps.mandelbrot.model.numbers;


import java.awt.*;

public class CellStatus {

    public final static int YET_UNCOMPUTED = -1;
    public final static int MAX_ITERATIONS = 32;
    private final static int MAX_CELL_STATUS = 256;
    private final static int latticeValuefactor = (MAX_CELL_STATUS / MAX_ITERATIONS);

    private final int latticeValue;

    public CellStatus(int latticeValue) {
        this.latticeValue = latticeValue;
    }

    public Color canvasColor() {
        int mylatticeValue = (this.latticeValue==YET_UNCOMPUTED)?0:this.latticeValue;
        int blue =  ((mylatticeValue * latticeValuefactor) % MAX_CELL_STATUS);
        int red = 0;
        int green = 0;
        return new Color(red, green, blue);
    }

    public boolean isCellStatusForYetUncomputed(){
        return (  this.latticeValue == YET_UNCOMPUTED);
    }
}
