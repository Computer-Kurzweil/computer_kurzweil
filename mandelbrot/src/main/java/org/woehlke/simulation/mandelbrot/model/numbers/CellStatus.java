package org.woehlke.simulation.mandelbrot.model.numbers;

import static org.woehlke.simulation.mandelbrot.model.numbers.ComplexNumber.MAX_ITERATIONS;

public class CellStatus {

    public final static int NULL_OBJ = Integer.MAX_VALUE;
    public final static int YET_UNCOMPUTED = -1;
    public final static int MAX_CELL_STATUS = 256;
    private final int LATTICE_VALUE_FACTOR;

    private final int latticeValue;

    public CellStatus(int latticeValue) {
        this.latticeValue = latticeValue;
        LATTICE_VALUE_FACTOR = MAX_CELL_STATUS / MAX_ITERATIONS;
    }

    public int intValue(){
        return (this.latticeValue*LATTICE_VALUE_FACTOR)%MAX_CELL_STATUS;
    }

    public boolean isCellStatusForYetUncomputed(){
        return (  this.latticeValue == YET_UNCOMPUTED);
    }

    public boolean isNullObject(){
        return latticeValue == NULL_OBJ;
    }

    public static CellStatus getNullObject(){
        return new CellStatus(NULL_OBJ);
    }
}
