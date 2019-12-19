package org.woehlke.simulation.mandelbrot.model.fractal;

import org.woehlke.simulation.mandelbrot.model.ApplicationModel;

import java.util.ArrayDeque;
import java.util.Deque;
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
public class GaussianNumberPlaneBase {

    protected volatile int[][] lattice;

    protected volatile ComplexNumber complexNumberForJuliaSetC;

    protected final LatticePoint worldDimensions;

    public final static int YET_UNCOMPUTED = -1;

    private final static double complexWorldDimensionRealX = 3.2d;
    private final static double complexWorldDimensionImgY = 2.34d;
    private final static double complexCenterForMandelbrotRealX = -2.2f;
    private final static double complexCenterForMandelbrotImgY = -1.17f;
    private final static double complexCenterForJuliaRealX = -1.6d;
    private final static double complexCenterForJuliaImgY =  -1.17d;

    protected volatile ComplexNumber complexWorldDimensions;
    protected volatile ComplexNumber complexCenterForMandelbrot;
    protected volatile ComplexNumber complexCenterForJulia;

    public volatile int zoomLevel;

    protected volatile Deque<ComplexNumber> complexCenterForZoomedJulia = new ArrayDeque<>();

    protected volatile ComplexNumber zoomCenter;

    protected volatile ApplicationModel model;


    public static Logger log = Logger.getLogger(GaussianNumberPlaneBase.class.getName());

    public GaussianNumberPlaneBase(ApplicationModel model) {
        this.model = model;
        this.worldDimensions = model.getWorldDimensions();
        this.lattice = new int[worldDimensions.getWidth()][worldDimensions.getHeight()];
        this.complexWorldDimensions = new ComplexNumber(
            complexWorldDimensionRealX,
            complexWorldDimensionImgY
        );
        this.complexCenterForMandelbrot = new ComplexNumber(
            complexCenterForMandelbrotRealX,
            complexCenterForMandelbrotImgY
        );
        this.complexCenterForJulia = new ComplexNumber(
            complexCenterForJuliaRealX,
            complexCenterForJuliaImgY
        );
        start();
    }

    public void setModeZoom() {
        this.setZoomLevel(1);
        this.setZoomCenter(complexCenterForMandelbrot);
    }

    public synchronized void start(){
        zoomLevel = 1;
        for(int y = 0;y < this.worldDimensions.getY(); y++){
            for(int x=0; x < worldDimensions.getX(); x++){
                lattice[x][y] = YET_UNCOMPUTED;
            }
        }
    }

    public synchronized int getCellStatusFor(int x,int y){
        return (lattice[x][y])<0?0:lattice[x][y];
    }




    public synchronized int getZoomLevel() {
        return zoomLevel;
    }

    public synchronized void inceaseZoomLevel() {
        zoomLevel *= 2;
    }

    public synchronized void decreaseZoomLevel() {
        if(zoomLevel > 1){
            zoomLevel /= 2;
        }
    }

    public synchronized boolean isLowestZoomLevel(){
        return zoomLevel == 1;
    }

    public synchronized void setZoomLevel(int zoomLevel) {
        this.zoomLevel = zoomLevel;
    }

    public synchronized ComplexNumber getZoomCenter() {
        return zoomCenter;
    }

    public synchronized void setZoomCenter(ComplexNumber zoomCenter) {
        this.zoomCenter = zoomCenter;
    }

}
