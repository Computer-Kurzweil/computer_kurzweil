package org.woehlke.simulation.mandelbrot.model.fractal;

import org.woehlke.simulation.mandelbrot.model.OnjectRegistry;

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

    private int[][] lattice;

    public final static int YET_UNCOMPUTED = -1;
    private final static double complexWorldDimensionRealX = 3.2d;
    private final static double complexWorldDimensionImgY = 2.34d;
    private final static double complexCenterForMandelbrotRealX = -2.2f;
    private final static double complexCenterForMandelbrotImgY = -1.17f;
    private final static double complexCenterForJuliaRealX = -1.6d;
    private final static double complexCenterForJuliaImgY =  -1.17d;

    private final LatticePoint worldDimensions;

    private int zoomLevel;

    protected ComplexNumber complexNumberForJuliaSetC;
    protected ComplexNumber complexWorldDimensions;
    protected ComplexNumber complexCenterForMandelbrot;
    protected ComplexNumber complexCenterForJulia;

    protected ComplexNumber zoomCenter;

    protected OnjectRegistry model;

    public static Logger log = Logger.getLogger(GaussianNumberPlaneBase.class.getName());

    public GaussianNumberPlaneBase(OnjectRegistry controllerThread) {
        this.model = controllerThread;
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
        this.setLowestZoomLevel();
        this.setZoomCenter(complexCenterForMandelbrot);
    }

    public void start(){
        setLowestZoomLevel();
        for(int y = 0;y < this.worldDimensions.getY(); y++){
            for(int x=0; x < worldDimensions.getX(); x++){
                lattice[x][y] = YET_UNCOMPUTED;
            }
        }
    }

    public boolean isCellStatusForYetUncomputed(int x,int y){
        return ( lattice[x][y] == YET_UNCOMPUTED);
    }


    public int getCellStatusFor(int x,int y){
        return (lattice[x][y])<0?0:lattice[x][y];
    }

    public void setCellStatusFor(int x,int y, int state){
        lattice[x][y]=state;
    }

    public int getZoomLevel() {
        return zoomLevel;
    }

    public void inceaseZoomLevel() {
        zoomLevel *= 2;
    }

    public void decreaseZoomLevel() {
        if(zoomLevel > 1){
            zoomLevel /= 2;
        }
    }

    public boolean isLowestZoomLevel(){
        return zoomLevel == 1;
    }

    public void setLowestZoomLevel() {
        this.zoomLevel = 1;
    }

    public ComplexNumber getZoomCenter() {
        return zoomCenter;
    }

    public void setZoomCenter(ComplexNumber zoomCenter) {
        this.zoomCenter = zoomCenter;
    }

    public LatticePoint getWorldDimensions() {
        return worldDimensions;
    }
}
