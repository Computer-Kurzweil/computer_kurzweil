package org.woehlke.simulation.mandelbrot.model.fractal.impl;

import org.springframework.stereotype.Service;
import org.woehlke.simulation.mandelbrot.control.ApplicationContext;
import org.woehlke.simulation.mandelbrot.control.state.FractalSetType;
import org.woehlke.simulation.mandelbrot.model.fractal.GaussianNumberPlaneMandelbrot;
import org.woehlke.simulation.mandelbrot.model.numbers.*;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.woehlke.simulation.mandelbrot.model.numbers.ComputingPlan.startCenterForMandelbrot;
import static org.woehlke.simulation.mandelbrot.model.numbers.ComputingPlan.startWorldDimension;

@Service
public class GaussianNumberPlaneMandelbrotImpl extends GaussianNumberPlaneBaseImpl
    implements GaussianNumberPlaneMandelbrot {

    private Deque<ComplexNumber> complexCenterForZoomedMandelbrot = new ArrayDeque<>();

    private final ZoomLevel zoomLevel;

    public GaussianNumberPlaneMandelbrotImpl(ApplicationContext ctx) {
        super(ctx,FractalSetType.MANDELBROT_SET);
        zoomLevel = new ZoomLevel();
        this.setZoomCenter(startCenterForMandelbrot);
    }

    @Override
    public void start(){
        super.start();
    }

    @Override
    public void setModeZoom() {
        zoomLevel.setLowestZoomLevel();
        this.setZoomCenter(startCenterForMandelbrot);
    }


    //TODO: implement and Bugfix
    @Override
    public void setModeSwitch(){

    }

    private ComplexNumber getComplexNumberFromLatticeCoordsForMandelbrot(LatticePoint turingPosition) {
        double realX = (
            startCenterForMandelbrot.getReal()
                + ( startWorldDimension.getReal() * turingPosition.getX() )
                / this.ctx.getWorldDimensions().getX()
        );
        double imgY = (
            startCenterForMandelbrot.getImg()
                + ( startWorldDimension.getImg() * turingPosition.getY() )
                / this.ctx.getWorldDimensions().getY()
        );
        return new ComplexNumber(realX,imgY);
    }

    private ComplexNumber getComplexNumberFromLatticeCoordsForZoomedMandelbrot(LatticePoint turingPosition) {
        double realX = (
            ( startCenterForMandelbrot.getReal() / this.zoomLevel.getZoomLevel() )
                + getZoomCenter().getReal()
                + ( startWorldDimension.getReal() * turingPosition.getX() )
                / ( this.ctx.getWorldDimensions().getX() * this.zoomLevel.getZoomLevel() )
        );
        double imgY = (
            ( startCenterForMandelbrot.getImg() / this.zoomLevel.getZoomLevel() )
                + getZoomCenter().getImg()
                + ( startWorldDimension.getImg() * turingPosition.getY() )
                / ( this.ctx.getWorldDimensions().getY() * this.zoomLevel.getZoomLevel() )
        );
        return new ComplexNumber(realX,imgY);
    }

    private void computeWZoomedWorld(){
        for(int y = 0; y < ctx.getWorldDimensions().getY(); y++){
            for(int x = 0; x < ctx.getWorldDimensions().getX(); x++){
                LatticePoint p = new LatticePoint(x, y);
                ComplexNumber position = this.getComplexNumberFromLatticeCoordsForZoomedMandelbrot(p);
                ComplexNumberFractal f = ComplexNumberFractal.iterateMandelbrotSetFunction(position);
                if(f.getInMandelbrotSet()){
                    super.setCellStatusFor(p.getX(),p.getY(),f.getIterations());
                } else {
                    super.setCellStatusFor(p.getX(),p.getY(),0);
                }
            }
        }
    }

    public void zoomInto(LatticePoint zoomLatticePoint) {
        if(ctx.getConfig().getLogDebug()){
            log.info("zoomIntoTheMandelbrotSet: "+ zoomLatticePoint +" - old:  "+this.getZoomCenter());
        }
        boolean LowestZoomLevel = this.zoomLevel.isLowestZoomLevel();
        this.zoomLevel.inceaseZoomLevel();
        if(LowestZoomLevel){
            ComplexNumber complexCenter = new ComplexNumber(startCenterForMandelbrot);
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
        computeWZoomedWorld();
    }


    public void zoomOut() {
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
        computeWZoomedWorld();
    }

    public void fillTheOutsideWithColors(){
        for(int y=0;y < ctx.getWorldDimensions().getY();y++){
            for(int x=0;x < ctx.getWorldDimensions().getX();x++){
                CellStatus cellStatus  = super.getCellStatusFor(x,y);
                if(cellStatus.isCellStatusForYetUncomputed()){
                    LatticePoint p = new LatticePoint(x, y);
                    ComplexNumber position = this.getComplexNumberFromLatticeCoordsForMandelbrot(p);
                    ComplexNumberFractal.iterateMandelbrotSetFunction(position);
                    ComplexNumberFractal f = ComplexNumberFractal.iterateMandelbrotSetFunction(position);
                    if(f.getInMandelbrotSet()){
                        super.setCellStatusFor(p.getX(),p.getY(),f.getIterations());
                    } else {
                        super.setCellStatusFor(p.getX(),p.getY(),0);
                    }
                }
            }
        }
    }

    @Override
    public boolean isInSet(LatticePoint p) {
        ComplexNumber position = this.getComplexNumberFromLatticeCoordsForMandelbrot(p);
        ComplexNumberFractal f = ComplexNumberFractal.iterateMandelbrotSetFunction(position);
        if(f.getInMandelbrotSet()){
            super.setCellStatusFor(p.getX(),p.getY(),f.getIterations());
        } else {
            super.setCellStatusFor(p.getX(),p.getY(),0);
        }
        return f.getInMandelbrotSet();
    }

    public String getZoomLevel() {
        return this.zoomLevel.getZoomLevel() + "";
    }
}
