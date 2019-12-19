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
public class GaussianNumberPlane {

    private volatile int[][] lattice;

    private volatile ComplexNumber complexNumberForJuliaSetC;

    private final LatticePoint worldDimensions;

    public final static int YET_UNCOMPUTED = -1;

    private final static double complexWorldDimensionRealX = 3.2d;
    private final static double complexWorldDimensionImgY = 2.34d;
    private final static double complexCenterForMandelbrotRealX = -2.2f;
    private final static double complexCenterForMandelbrotImgY = -1.17f;
    private final static double complexCenterForJuliaRealX = -1.6d;
    private final static double complexCenterForJuliaImgY =  -1.17d;

    private volatile ComplexNumber complexWorldDimensions;
    private volatile ComplexNumber complexCenterForMandelbrot;
    private volatile ComplexNumber complexCenterForJulia;

    public volatile int zoomLevel;

    private volatile Deque<ComplexNumber> complexCenterForZoomedMandelbrot = new ArrayDeque<>();

    private volatile ComplexNumber zoomCenter;

    private volatile ApplicationModel model;


    public static Logger log = Logger.getLogger(GaussianNumberPlane.class.getName());

    public GaussianNumberPlane(ApplicationModel model) {
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

    private synchronized ComplexNumber getComplexNumberFromLatticeCoordsForJulia(LatticePoint turingPosition) {
        double realX = complexCenterForJulia.getReal()
            + (complexWorldDimensions.getReal()*turingPosition.getX())/worldDimensions.getX();
        double imgY = complexCenterForJulia.getImg()
            + (complexWorldDimensions.getImg()*turingPosition.getY())/worldDimensions.getY();
        return new ComplexNumber(realX,imgY);
    }

    private synchronized ComplexNumber getComplexNumberFromLatticeCoordsForMandelbrot(LatticePoint turingPosition) {
        double realX = (
            complexCenterForMandelbrot.getReal()
            + ( complexWorldDimensions.getReal() * turingPosition.getX() )
            / worldDimensions.getX()
        );
        double imgY = (
            complexCenterForMandelbrot.getImg()
            + ( complexWorldDimensions.getImg() * turingPosition.getY() )
            / worldDimensions.getY()
        );
        return new ComplexNumber(realX,imgY);
    }

    private synchronized ComplexNumber getComplexNumberFromLatticeCoordsForZoomedMandelbrot(LatticePoint turingPosition) {
        double realX = (
            ( complexCenterForMandelbrot.getReal() / this.getZoomLevel() )
            + getZoomCenter().getReal()
            + ( complexWorldDimensions.getReal() * turingPosition.getX() )
            / ( worldDimensions.getX() * this.getZoomLevel() )
        );
        double imgY = (
            ( complexCenterForMandelbrot.getImg() / this.getZoomLevel() )
            + getZoomCenter().getImg()
            + ( complexWorldDimensions.getImg() * turingPosition.getY() )
            / ( worldDimensions.getY() * this.getZoomLevel() )
        );
        return new ComplexNumber(realX,imgY);
    }

    public synchronized boolean isInZooomedMandelbrotSet(LatticePoint turingPosition) {
        ComplexNumber position = this.getComplexNumberFromLatticeCoordsForZoomedMandelbrot(turingPosition);
        lattice[turingPosition.getX()][turingPosition.getY()] = position.computeMandelbrotSet();
        return position.isInMandelbrotSet();
    }

    public synchronized boolean isInMandelbrotSet(LatticePoint turingPosition) {
        ComplexNumber position = this.getComplexNumberFromLatticeCoordsForMandelbrot(turingPosition);
        lattice[turingPosition.getX()][turingPosition.getY()] = position.computeMandelbrotSet();
        return position.isInMandelbrotSet();
    }

    public synchronized void fillTheOutsideWithColors(){
        for(int y=0;y<worldDimensions.getY();y++){
            for(int x=0;x<worldDimensions.getX();x++){
                if(lattice[x][y] == YET_UNCOMPUTED){
                    this.isInMandelbrotSet(new LatticePoint(x, y));
                }
            }
        }
    }

    private synchronized void computeTheJuliaSetForC(ComplexNumber c) {
        for(int y = 0; y < worldDimensions.getY(); y++) {
            for (int x = 0; x < worldDimensions.getX(); x++) {
                LatticePoint zLatticePoint = new LatticePoint(x, y);
                ComplexNumber z = this.getComplexNumberFromLatticeCoordsForJulia(zLatticePoint);
                lattice[x][y] = z.computeJuliaSet(c);
            }
        }
    }

    public synchronized void computeTheJuliaSetFor(LatticePoint latticePointFromMandelbrotSet) {
        ComplexNumber c = getComplexNumberFromLatticeCoordsForMandelbrot(latticePointFromMandelbrotSet);
        this.complexNumberForJuliaSetC = c;
        computeTheJuliaSetForC(c);
    }

    public void zoomIntoTheMandelbrotSet(LatticePoint zoomLatticePoint) {
        if(model.getConfig().getLogDebug()){
            log.info("zoomIntoTheMandelbrotSet: "+ zoomLatticePoint +" - old:  "+this.getZoomCenter());
        }
        boolean LowestZoomLevel = isLowestZoomLevel();
        this.inceaseZoomLevel();
        if(LowestZoomLevel){
            ComplexNumber complexCenter = new ComplexNumber(this.complexCenterForMandelbrot);
            complexCenterForZoomedMandelbrot.push(complexCenter);
            this.setZoomCenter(getComplexNumberFromLatticeCoordsForMandelbrot(zoomLatticePoint));
        } else {
            this.setZoomCenter(getComplexNumberFromLatticeCoordsForZoomedMandelbrot(zoomLatticePoint));
        }
        complexCenterForZoomedMandelbrot.push(this.getZoomCenter());
        if(model.getConfig().getLogDebug()) {
            String msg = "zoomPoint: "+ zoomLatticePoint
                + " zoomCenterNew: " + this.getZoomCenter()
                + " zoomLevel:  "+ this.getZoomLevel();
            log.info(msg);
        }
        for(int y = 0; y < worldDimensions.getY(); y++){
            for(int x = 0; x < worldDimensions.getX(); x++){
                LatticePoint p = new LatticePoint(x, y);
                this.isInZooomedMandelbrotSet(p);
            }
        }
    }

    public void zoomOutOfTheMandelbrotSet() {
        if(model.getConfig().getLogDebug()) {
            log.info("zoomOutOfTheMandelbrotSet: " + this.getZoomCenter());
        }
        if(!this.isLowestZoomLevel()){
            this.decreaseZoomLevel();
            ComplexNumber zoomCenter = complexCenterForZoomedMandelbrot.pop();
            this.setZoomCenter(zoomCenter);
            if(model.getConfig().getLogDebug()) {
                log.info("zoomCenter: " + this.getZoomCenter() + " - zoomLevel:  "+ this.getZoomLevel());
            }
            for(int y = 0; y < worldDimensions.getY(); y++){
                for(int x = 0; x < worldDimensions.getX(); x++){
                    LatticePoint p = new LatticePoint(x, y);
                    this.isInZooomedMandelbrotSet(p);
                }
            }
        }
    }

    public void zoomIntoTheJuliaSetFor(LatticePoint zoomLatticePoint) {
        ComplexNumber c = this.complexNumberForJuliaSetC;
        computeTheJuliaSetForC(c);
    }

    public void zoomOutOfTheJuliaSet() {
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
