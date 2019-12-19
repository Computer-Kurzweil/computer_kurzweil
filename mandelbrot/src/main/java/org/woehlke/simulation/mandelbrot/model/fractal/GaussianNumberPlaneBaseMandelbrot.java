package org.woehlke.simulation.mandelbrot.model.fractal;

import org.woehlke.simulation.mandelbrot.model.ApplicationModel;

import java.util.ArrayDeque;
import java.util.Deque;

public class GaussianNumberPlaneBaseMandelbrot extends GaussianNumberPlaneBase {

    private volatile Deque<ComplexNumber> complexCenterForZoomedMandelbrot = new ArrayDeque<>();

    public GaussianNumberPlaneBaseMandelbrot(ApplicationModel model) {
        super(model);
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

}
