package org.woehlke.simulation.mandelbrot.model.fractal.impl;


import org.woehlke.simulation.mandelbrot.control.ApplicationContext;
import org.woehlke.simulation.mandelbrot.model.fractal.GaussianNumberPlaneBaseJuliaIF;
import org.woehlke.simulation.mandelbrot.model.numbers.ComplexNumber;
import org.woehlke.simulation.mandelbrot.model.numbers.LatticePoint;
import org.woehlke.simulation.mandelbrot.model.numbers.ZoomLevel;

import java.util.ArrayDeque;
import java.util.Deque;

public class GaussianNumberPlaneBaseJuliaImpl extends GaussianNumberPlaneBaseImpl implements GaussianNumberPlaneBaseJuliaIF {

    private Deque<ComplexNumber> complexCenterForZoomedJulia = new ArrayDeque<>();

    private final ZoomLevel zoomLevel;

    private final ComplexNumber complexCenterForJulia;
    private ComplexNumber complexNumberForJuliaSetC;

    private final static double complexCenterForJuliaRealX = -1.6d;
    private final static double complexCenterForJuliaImgY =  -1.17d;

    public GaussianNumberPlaneBaseJuliaImpl(ApplicationContext ctx) {
        super(ctx);
        this.complexCenterForJulia = new ComplexNumber(
            complexCenterForJuliaRealX,
            complexCenterForJuliaImgY
        );
        zoomLevel = new ZoomLevel();
    }

    @Override
    public void start(){
        super.start();
    }

    @Override
    public void setModeZoom() {
        zoomLevel.setLowestZoomLevel();
        this.setZoomCenter(complexCenterForJulia);
    }


    //TODO: implement and Bugfix
    @Override
    public void setModeSwitch(){

    }

    private ComplexNumber getComplexNumberFromLatticeCoordsForJulia(LatticePoint turingPosition) {
        double realX = complexCenterForJulia.getReal()
            + (complexWorldDimensions.getReal()*turingPosition.getX())/this.ctx.getWorldDimensions().getX();
        double imgY = complexCenterForJulia.getImg()
            + (complexWorldDimensions.getImg()*turingPosition.getY())/this.ctx.getWorldDimensions().getY();
        return new ComplexNumber(realX,imgY);
    }

    //TODO:
    private ComplexNumber getComplexNumberFromLatticeCoordsForZoomedJulia(LatticePoint turingPosition) {
        double realX = ( complexCenterForJulia.getReal() / this.zoomLevel.getZoomLevel() )
            + (complexWorldDimensions.getReal()*turingPosition.getX())/(this.ctx.getWorldDimensions().getX() * this.zoomLevel.getZoomLevel());
        double imgY = ( complexCenterForJulia.getImg() / this.zoomLevel.getZoomLevel() )
            + (complexWorldDimensions.getImg()*turingPosition.getY())/(this.ctx.getWorldDimensions().getY() * this.zoomLevel.getZoomLevel());
        return new ComplexNumber(realX,imgY);
    }


    private void computeTheJuliaSetForC(ComplexNumber c) {
        for(int y = 0; y < this.ctx.getWorldDimensions().getY(); y++) {
            for (int x = 0; x < this.ctx.getWorldDimensions().getX(); x++) {
                LatticePoint zLatticePoint = new LatticePoint(x, y);
                ComplexNumber z = this.getComplexNumberFromLatticeCoordsForJulia(zLatticePoint);
                super.setCellStatusFor(x,y,z.computeJuliaSet(c));
            }
        }
    }

    private void computeTheZoomedJuliaSetForC(ComplexNumber c) {
        for(int y = 0; y < this.ctx.getWorldDimensions().getY(); y++) {
            for (int x = 0; x < this.ctx.getWorldDimensions().getX(); x++) {
                LatticePoint zLatticePoint = new LatticePoint(x, y);
                ComplexNumber z = this.getComplexNumberFromLatticeCoordsForZoomedJulia(zLatticePoint);
                super.setCellStatusFor(x,y,z.computeJuliaSet(c));
            }
        }
    }

    public void computeTheJuliaSetFor(LatticePoint latticePoint) {
        ComplexNumber c = getComplexNumberFromLatticeCoordsForJulia(latticePoint);
        this.complexNumberForJuliaSetC = c;
        computeTheJuliaSetForC(c);
    }

    public void computeTheZoomedJuliaSetFor(LatticePoint latticePoint) {
        ComplexNumber c = getComplexNumberFromLatticeCoordsForZoomedJulia(latticePoint);
        this.complexNumberForJuliaSetC = c;
        computeTheZoomedJuliaSetForC(c);
    }

    public boolean isInZooomedJuliaSet(LatticePoint turingPosition) {
        ComplexNumber c = this.getComplexNumberFromLatticeCoordsForZoomedJulia(turingPosition);
        ComplexNumber z = new ComplexNumber();
        super.setCellStatusFor(turingPosition.getX(),turingPosition.getY(), z.computeJuliaSet(c));
        return z.isInJuliaSet();
    }

    public boolean isInJuliaSet(LatticePoint turingPosition) {
        ComplexNumber c = this.getComplexNumberFromLatticeCoordsForJulia(turingPosition);
        ComplexNumber z = new ComplexNumber();
        super.setCellStatusFor(turingPosition.getX(),turingPosition.getY(), z.computeJuliaSet(c));
        return z.isInJuliaSet();
    }


    //TODO:
    public void zoomIntoTheJuliaSetFor(LatticePoint zoomLatticePoint) {
        if(ctx.getConfig().getLogDebug()){
            log.info("zoomIntoTheMandelbrotSet: "+ zoomLatticePoint +" - old:  "+this.getZoomCenter());
        }
        boolean LowestZoomLevel = zoomLevel.isLowestZoomLevel();
        this.zoomLevel.inceaseZoomLevel();
        if(LowestZoomLevel){
            ComplexNumber complexCenter = new ComplexNumber(this.complexCenterForJulia);
            complexCenterForZoomedJulia.push(complexCenter);
            this.setZoomCenter(getComplexNumberFromLatticeCoordsForJulia(zoomLatticePoint));
        } else {
            this.setZoomCenter(getComplexNumberFromLatticeCoordsForZoomedJulia(zoomLatticePoint));
        }
        complexCenterForZoomedJulia.push(this.getZoomCenter());
        if(ctx.getConfig().getLogDebug()) {
            String msg = "zoomPoint: "+ zoomLatticePoint
                + " zoomCenterNew: " + this.getZoomCenter()
                + " zoomLevel:  "+ this.zoomLevel.getZoomLevel();
            log.info(msg);
        }
        //    ComplexNumber c = this.getComplexNumberFromLatticeCoordsForZoomedJulia(zoomLatticePoint);
        ComplexNumber c = this.complexNumberForJuliaSetC;
        for(int y = 0; y < this.ctx.getWorldDimensions().getY(); y++){
            for(int x = 0; x < this.ctx.getWorldDimensions().getX(); x++){
                LatticePoint zLatticePoint = new LatticePoint(x, y);
                ComplexNumber z = this.getComplexNumberFromLatticeCoordsForZoomedJulia(zLatticePoint);
                super.setCellStatusFor(x,y,z.computeJuliaSet(c));
            }
        }

    }

    //TODO:
    public void zoomOutOfTheJuliaSet() {
        if(ctx.getConfig().getLogDebug()) {
            log.info("zoomOutOfTheMandelbrotSet: " + this.getZoomCenter());
        }
        if(!this.zoomLevel.isLowestZoomLevel()) {
            this.zoomLevel.decreaseZoomLevel();
            try {
                ComplexNumber zoomCenter = complexCenterForZoomedJulia.pop();
                this.setZoomCenter(zoomCenter);
            } catch (Exception e){

            }
        }
        if(ctx.getConfig().getLogDebug()) {
            log.info("zoomCenter: " + this.getZoomCenter() + " - zoomLevel:  "+ this.zoomLevel.getZoomLevel());
        }
        for(int y = 0; y < this.ctx.getWorldDimensions().getY(); y++){
            for(int x = 0; x < this.ctx.getWorldDimensions().getX(); x++){
                LatticePoint p = new LatticePoint(x, y);
                this.isInZooomedJuliaSet(p);
            }
        }
    }

    public String getZoomLevel() {
        return this.zoomLevel.getZoomLevel() + "";
    }
}
