package org.woehlke.simulation.mandelbrot.model.fractal.impl;

import org.woehlke.simulation.mandelbrot.control.ObjectRegistryAndEventDispatcher;
import org.woehlke.simulation.mandelbrot.model.fractal.GaussianNumberPlaneBase;
import org.woehlke.simulation.mandelbrot.model.numbers.CellStatus;
import org.woehlke.simulation.mandelbrot.model.numbers.ComplexNumber;

import java.util.logging.Logger;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 16.12.2019.
 */
public abstract class GaussianNumberPlaneBaseImpl implements GaussianNumberPlaneBase {

    private int[][] lattice;

    private final static double complexWorldDimensionRealX = 3.2d;
    private final static double complexWorldDimensionImgY = 2.34d;

    protected ComplexNumber complexNumberForJuliaSetC;
    protected ComplexNumber complexWorldDimensions;

    protected ComplexNumber zoomCenter;

    protected ObjectRegistryAndEventDispatcher ctx;

    public static Logger log = Logger.getLogger(GaussianNumberPlaneBaseImpl.class.getName());

    public GaussianNumberPlaneBaseImpl(ObjectRegistryAndEventDispatcher ctx) {
        this.ctx = ctx;
        this.lattice = new int[ctx.getWorldDimensions().getWidth()][ctx.getWorldDimensions().getHeight()];
        this.complexWorldDimensions = new ComplexNumber(
            complexWorldDimensionRealX,
            complexWorldDimensionImgY
        );
        start();
    }

    public void start(){
        for(int y = 0;y < ctx.getWorldDimensions().getY(); y++){
            for(int x=0; x < ctx.getWorldDimensions().getX(); x++){
                lattice[x][y] = CellStatus.YET_UNCOMPUTED;
            }
        }
    }

    public void setModeZoom() {

    }

    public void setModeSwitch(){

    }

    public CellStatus getCellStatusFor(int x,int y){
        x %= ctx.getWorldDimensions().getX();
        y %= ctx.getWorldDimensions().getY();
        CellStatus cellStatus = new CellStatus(lattice[x][y]);
        return cellStatus;
    }

    public void setCellStatusFor(int x,int y, int state){
        lattice[x][y]=state;
    }

    public ComplexNumber getZoomCenter() {
        return zoomCenter;
    }

    public void setZoomCenter(ComplexNumber zoomCenter) {
        this.zoomCenter = zoomCenter;
    }

}
