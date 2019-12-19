package org.woehlke.simulation.mandelbrot.model.fractal;

import org.woehlke.simulation.mandelbrot.model.ApplicationModel;

import java.util.ArrayDeque;
import java.util.Deque;

public class GaussianNumberPlaneBaseJulia extends GaussianNumberPlaneBaseMandelbrot {

    private volatile Deque<ComplexNumber> complexCenterForZoomedJulia = new ArrayDeque<>();

    public GaussianNumberPlaneBaseJulia(ApplicationModel model) {
        super(model);
    }

    private ComplexNumber getComplexNumberFromLatticeCoordsForJulia(LatticePoint turingPosition) {
        double realX = complexCenterForJulia.getReal()
            + (complexWorldDimensions.getReal()*turingPosition.getX())/this.getWorldDimensions().getX();
        double imgY = complexCenterForJulia.getImg()
            + (complexWorldDimensions.getImg()*turingPosition.getY())/this.getWorldDimensions().getY();
        return new ComplexNumber(realX,imgY);
    }

    //TODO:
    private ComplexNumber getComplexNumberFromLatticeCoordsForZoomedJulia(LatticePoint turingPosition) {
        double realX = ( complexCenterForJulia.getReal() / this.getZoomLevel() )
            + (complexWorldDimensions.getReal()*turingPosition.getX())/(this.getWorldDimensions().getX() * this.getZoomLevel());
        double imgY = ( complexCenterForJulia.getImg() / this.getZoomLevel() )
            + (complexWorldDimensions.getImg()*turingPosition.getY())/(this.getWorldDimensions().getY() * this.getZoomLevel());
        return new ComplexNumber(realX,imgY);
    }


    private void computeTheJuliaSetForC(ComplexNumber c) {
        for(int y = 0; y < this.getWorldDimensions().getY(); y++) {
            for (int x = 0; x < this.getWorldDimensions().getX(); x++) {
                LatticePoint zLatticePoint = new LatticePoint(x, y);
                ComplexNumber z = this.getComplexNumberFromLatticeCoordsForJulia(zLatticePoint);
                super.setCellStatusFor(x,y,z.computeJuliaSet(c));
            }
        }
    }

    private void computeTheZoomedJuliaSetForC(ComplexNumber c) {
        for(int y = 0; y < this.getWorldDimensions().getY(); y++) {
            for (int x = 0; x < this.getWorldDimensions().getX(); x++) {
                LatticePoint zLatticePoint = new LatticePoint(x, y);
                ComplexNumber z = this.getComplexNumberFromLatticeCoordsForZoomedJulia(zLatticePoint);
                super.setCellStatusFor(x,y,z.computeJuliaSet(c));
            }
        }
    }

    public synchronized void computeTheJuliaSetFor(LatticePoint latticePoint) {
        ComplexNumber c = getComplexNumberFromLatticeCoordsForJulia(latticePoint);
        this.complexNumberForJuliaSetC = c;
        computeTheJuliaSetForC(c);
    }

    public synchronized void computeTheZoomedJuliaSetFor(LatticePoint latticePoint) {
        ComplexNumber c = getComplexNumberFromLatticeCoordsForZoomedJulia(latticePoint);
        this.complexNumberForJuliaSetC = c;
        computeTheZoomedJuliaSetForC(c);
    }

    public synchronized boolean isInZooomedJuliaSet(LatticePoint turingPosition) {
        ComplexNumber c = this.getComplexNumberFromLatticeCoordsForZoomedJulia(turingPosition);
        ComplexNumber z = new ComplexNumber();
        super.setCellStatusFor(turingPosition.getX(),turingPosition.getY(), z.computeJuliaSet(c));
        return z.isInJuliaSet();
    }

    public synchronized boolean isInJuliaSet(LatticePoint turingPosition) {
        ComplexNumber c = this.getComplexNumberFromLatticeCoordsForJulia(turingPosition);
        ComplexNumber z = new ComplexNumber();
        super.setCellStatusFor(turingPosition.getX(),turingPosition.getY(), z.computeJuliaSet(c));
        return z.isInJuliaSet();
    }


    //TODO:
    public synchronized void zoomIntoTheJuliaSetFor(LatticePoint zoomLatticePoint) {
        if(model.getConfig().getLogDebug()){
            log.info("zoomIntoTheMandelbrotSet: "+ zoomLatticePoint +" - old:  "+this.getZoomCenter());
        }
        boolean LowestZoomLevel = isLowestZoomLevel();
        this.inceaseZoomLevel();
        if(LowestZoomLevel){
            ComplexNumber complexCenter = new ComplexNumber(this.complexCenterForMandelbrot);
            complexCenterForZoomedJulia.push(complexCenter);
            this.setZoomCenter(getComplexNumberFromLatticeCoordsForJulia(zoomLatticePoint));
        } else {
            this.setZoomCenter(getComplexNumberFromLatticeCoordsForZoomedJulia(zoomLatticePoint));
        }
        complexCenterForZoomedJulia.push(this.getZoomCenter());
        if(model.getConfig().getLogDebug()) {
            String msg = "zoomPoint: "+ zoomLatticePoint
                + " zoomCenterNew: " + this.getZoomCenter()
                + " zoomLevel:  "+ this.getZoomLevel();
            log.info(msg);
        }
        //    ComplexNumber c = this.getComplexNumberFromLatticeCoordsForZoomedJulia(zoomLatticePoint);
        ComplexNumber c = this.complexNumberForJuliaSetC;
        for(int y = 0; y < this.getWorldDimensions().getY(); y++){
            for(int x = 0; x < this.getWorldDimensions().getX(); x++){
                LatticePoint zLatticePoint = new LatticePoint(x, y);
                ComplexNumber z = this.getComplexNumberFromLatticeCoordsForZoomedJulia(zLatticePoint);
                super.setCellStatusFor(x,y,z.computeJuliaSet(c));
            }
        }

    }

    //TODO:
    public synchronized void zoomOutOfTheJuliaSet() {
        if(model.getConfig().getLogDebug()) {
            log.info("zoomOutOfTheMandelbrotSet: " + this.getZoomCenter());
        }
        if(!this.isLowestZoomLevel()) {
            this.decreaseZoomLevel();
            try {
                ComplexNumber zoomCenter = complexCenterForZoomedJulia.pop();
                this.setZoomCenter(zoomCenter);
            } catch (Exception e){

            }
        }
        if(model.getConfig().getLogDebug()) {
            log.info("zoomCenter: " + this.getZoomCenter() + " - zoomLevel:  "+ this.getZoomLevel());
        }
        for(int y = 0; y < this.getWorldDimensions().getY(); y++){
            for(int x = 0; x < this.getWorldDimensions().getX(); x++){
                LatticePoint p = new LatticePoint(x, y);
                this.isInZooomedJuliaSet(p);
            }
        }
    }
}
