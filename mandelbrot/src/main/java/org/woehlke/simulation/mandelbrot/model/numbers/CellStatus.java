package org.woehlke.simulation.mandelbrot.model.numbers;


public class CellStatus {

    public final static int YET_UNCOMPUTED = -1;
    public final static int MAX_ITERATIONS = 32;
    private final static int MAX_CELL_STATUS = 256;
    private final static int latticeValuefactor = (MAX_CELL_STATUS / MAX_ITERATIONS);

    private final int latticeValue;

    public CellStatus(int latticeValue) {
        this.latticeValue = latticeValue;
    }

    public int intValue(){
        int mylatticeValue = this.latticeValue;
        return ((mylatticeValue * latticeValuefactor) % MAX_CELL_STATUS);
    }

    public boolean isCellStatusForYetUncomputed(){
        return (  this.latticeValue == YET_UNCOMPUTED);
    }
}
