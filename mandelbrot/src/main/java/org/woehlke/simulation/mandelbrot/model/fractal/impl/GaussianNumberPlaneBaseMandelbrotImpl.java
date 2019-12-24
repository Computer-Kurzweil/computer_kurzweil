package org.woehlke.simulation.mandelbrot.model.fractal.impl;

import org.woehlke.simulation.mandelbrot.control.ObjectRegistryAndEventDispatcher;
import org.woehlke.simulation.mandelbrot.model.fractal.GaussianNumberPlaneBaseMandelbrot;
import org.woehlke.simulation.mandelbrot.model.numbers.CellStatus;
import org.woehlke.simulation.mandelbrot.model.numbers.ComplexNumber;
import org.woehlke.simulation.mandelbrot.model.numbers.LatticePoint;
import org.woehlke.simulation.mandelbrot.model.numbers.ZoomLevel;

import java.util.ArrayDeque;
import java.util.Deque;

public class GaussianNumberPlaneBaseMandelbrotImpl extends GaussianNumberPlaneBaseImpl implements GaussianNumberPlaneBaseMandelbrot {

    private Deque<ComplexNumber> complexCenterForZoomedMandelbrot = new ArrayDeque<>();

    private final ZoomLevel zoomLevel;

    protected ComplexNumber complexCenterForMandelbrot;

    private final static double complexCenterForMandelbrotRealX = -2.2f;
    private final static double complexCenterForMandelbrotImgY = -1.17f;

    public GaussianNumberPlaneBaseMandelbrotImpl(ObjectRegistryAndEventDispatcher ctx) {
        super(ctx);
        zoomLevel = new ZoomLevel();
        this.complexCenterForMandelbrot = new ComplexNumber(
            complexCenterForMandelbrotRealX,
            complexCenterForMandelbrotImgY
        );
    }

    @Override
    public void start(){
        super.start();
        this.zoomLevel.start();
    }

    @Override
    public void setModeZoom() {
        super.setModeZoom();
        zoomLevel.setLowestZoomLevel();
        this.setZoomCenter(complexCenterForMandelbrot);
    }


    //TODO: implement and Bugfix
    @Override
    public void setModeSwitch(){
        super.setModeSwitch();
    }

    private ComplexNumber getComplexNumberFromLatticeCoordsForMandelbrot(LatticePoint turingPosition) {
        double realX = (
            complexCenterForMandelbrot.getReal()
                + ( complexWorldDimensions.getReal() * turingPosition.getX() )
                / this.ctx.getWorldDimensions().getX()
        );
        double imgY = (
            complexCenterForMandelbrot.getImg()
                + ( complexWorldDimensions.getImg() * turingPosition.getY() )
                / this.ctx.getWorldDimensions().getY()
        );
        return new ComplexNumber(realX,imgY);
    }

    private ComplexNumber getComplexNumberFromLatticeCoordsForZoomedMandelbrot(LatticePoint turingPosition) {
        double realX = (
            ( complexCenterForMandelbrot.getReal() / this.zoomLevel.getZoomLevel() )
                + getZoomCenter().getReal()
                + ( complexWorldDimensions.getReal() * turingPosition.getX() )
                / ( this.ctx.getWorldDimensions().getX() * this.zoomLevel.getZoomLevel() )
        );
        double imgY = (
            ( complexCenterForMandelbrot.getImg() / this.zoomLevel.getZoomLevel() )
                + getZoomCenter().getImg()
                + ( complexWorldDimensions.getImg() * turingPosition.getY() )
                / ( this.ctx.getWorldDimensions().getY() * this.zoomLevel.getZoomLevel() )
        );
        return new ComplexNumber(realX,imgY);
    }

    public void zoomIntoTheMandelbrotSet(LatticePoint zoomLatticePoint) {
        if(ctx.getConfig().getLogDebug()){
            log.info("zoomIntoTheMandelbrotSet: "+ zoomLatticePoint +" - old:  "+this.getZoomCenter());
        }
        boolean LowestZoomLevel = this.zoomLevel.isLowestZoomLevel();
        this.zoomLevel.inceaseZoomLevel();
        if(LowestZoomLevel){
            ComplexNumber complexCenter = new ComplexNumber(this.complexCenterForMandelbrot);
            complexCenterForZoomedMandelbrot.push(complexCenter);
            this.setZoomCenter(getComplexNumberFromLatticeCoordsForMandelbrot(zoomLatticePoint));
        } else {
            this.setZoomCenter(getComplexNumberFromLatticeCoordsForZoomedMandelbrot(zoomLatticePoint));
        }
        complexCenterForZoomedMandelbrot.push(this.getZoomCenter());
        if(ctx.getConfig().getLogDebug()) {
            String msg = "zoomPoint: "+ zoomLatticePoint
                + " zoomCenterNew: " + this.getZoomCenter()
                + " zoomLevel:  "+ this.zoomLevel.getZoomLevel();
            log.info(msg);
        }
        for(int y = 0; y < ctx.getWorldDimensions().getY(); y++){
            for(int x = 0; x < ctx.getWorldDimensions().getX(); x++){
                LatticePoint p = new LatticePoint(x, y);
                this.isInZooomedMandelbrotSet(p);
            }
        }
    }

    public void zoomOutOfTheMandelbrotSet() {
        if(ctx.getConfig().getLogDebug()) {
            log.info("zoomOutOfTheMandelbrotSet: " + this.getZoomCenter());
        }
        if(!this.zoomLevel.isLowestZoomLevel()) {
            this.zoomLevel.decreaseZoomLevel();
        }
        ComplexNumber zoomCenter = complexCenterForZoomedMandelbrot.pop();
        this.setZoomCenter(zoomCenter);
        if(ctx.getConfig().getLogDebug()) {
            log.info("zoomCenter: " + this.getZoomCenter() + " - zoomLevel:  "+ this.zoomLevel.getZoomLevel());
        }
        for(int y = 0; y < ctx.getWorldDimensions().getY(); y++){
            for(int x = 0; x < ctx.getWorldDimensions().getX(); x++){
                LatticePoint p = new LatticePoint(x, y);
                this.isInZooomedMandelbrotSet(p);
            }
        }
    }

    public boolean isInZooomedMandelbrotSet(LatticePoint turingPosition) {
        ComplexNumber position = this.getComplexNumberFromLatticeCoordsForZoomedMandelbrot(turingPosition);
        super.setCellStatusFor(turingPosition.getX(),turingPosition.getY(),position.computeMandelbrotSet());
        return position.isInMandelbrotSet();
    }

    public boolean isInMandelbrotSet(LatticePoint turingPosition) {
        ComplexNumber position = this.getComplexNumberFromLatticeCoordsForMandelbrot(turingPosition);
        super.setCellStatusFor(turingPosition.getX(),turingPosition.getY(),position.computeMandelbrotSet());
        return position.isInMandelbrotSet();
    }

    public void fillTheOutsideWithColors(){
        for(int y=0;y < ctx.getWorldDimensions().getY();y++){
            for(int x=0;x < ctx.getWorldDimensions().getX();x++){
                CellStatus cellStatus  = super.getCellStatusFor(x,y);
                if(cellStatus.isCellStatusForYetUncomputed()){
                    this.isInMandelbrotSet(new LatticePoint(x, y));
                }
            }
        }
    }

    public String getZoomLevel() {
        return this.zoomLevel.getZoomLevel() + "";
    }
}
