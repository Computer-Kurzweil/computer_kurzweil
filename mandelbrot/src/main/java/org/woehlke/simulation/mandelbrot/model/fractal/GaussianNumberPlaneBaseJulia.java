package org.woehlke.simulation.mandelbrot.model.fractal;

import org.woehlke.simulation.mandelbrot.model.ApplicationModel;

public class GaussianNumberPlaneBaseJulia extends GaussianNumberPlaneBaseMandelbrot {


    public GaussianNumberPlaneBaseJulia(ApplicationModel model) {
        super(model);
    }

    private synchronized ComplexNumber getComplexNumberFromLatticeCoordsForJulia(LatticePoint turingPosition) {
        double realX = complexCenterForJulia.getReal()
            + (complexWorldDimensions.getReal()*turingPosition.getX())/worldDimensions.getX();
        double imgY = complexCenterForJulia.getImg()
            + (complexWorldDimensions.getImg()*turingPosition.getY())/worldDimensions.getY();
        return new ComplexNumber(realX,imgY);
    }

    //TODO:
    private synchronized ComplexNumber getComplexNumberFromLatticeCoordsForZoomedJulia(LatticePoint turingPosition) {
        double realX = ( complexCenterForJulia.getReal() / this.getZoomLevel() )
            + (complexWorldDimensions.getReal()*turingPosition.getX())/(worldDimensions.getX() * this.getZoomLevel());
        double imgY = ( complexCenterForJulia.getImg() / this.getZoomLevel() )
            + (complexWorldDimensions.getImg()*turingPosition.getY())/(worldDimensions.getY() * this.getZoomLevel());
        return new ComplexNumber(realX,imgY);
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

    public synchronized void computeTheJuliaSetFor(LatticePoint latticePoint) {
        ComplexNumber c = getComplexNumberFromLatticeCoordsForJulia(latticePoint);
        this.complexNumberForJuliaSetC = c;
        computeTheJuliaSetForC(c);
    }

    public synchronized boolean isInZooomedJuliaSet(LatticePoint turingPosition) {
        ComplexNumber c = this.getComplexNumberFromLatticeCoordsForZoomedJulia(turingPosition);
        ComplexNumber z = new ComplexNumber();
        lattice[turingPosition.getX()][turingPosition.getY()] = z.computeJuliaSet(c);
        return z.isInJuliaSet();
    }

    public synchronized boolean isInJuliaSet(LatticePoint turingPosition) {
        ComplexNumber c = this.getComplexNumberFromLatticeCoordsForJulia(turingPosition);
        ComplexNumber z = new ComplexNumber();
        lattice[turingPosition.getX()][turingPosition.getY()] = z.computeJuliaSet(c);
        return z.isInJuliaSet();
    }


    //TODO:
    public void zoomIntoTheJuliaSetFor(LatticePoint zoomLatticePoint) {
        ComplexNumber c = this.complexNumberForJuliaSetC;
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
        for(int y = 0; y < worldDimensions.getY(); y++){
            for(int x = 0; x < worldDimensions.getX(); x++){
                LatticePoint zLatticePoint = new LatticePoint(x, y);
                ComplexNumber z = this.getComplexNumberFromLatticeCoordsForZoomedJulia(zLatticePoint);
                lattice[x][y] = z.computeJuliaSet(c);
            }
        }

    }

    //TODO:
    public void zoomOutOfTheJuliaSet() {
        if(model.getConfig().getLogDebug()) {
            log.info("zoomOutOfTheMandelbrotSet: " + this.getZoomCenter());
        }
        if(!this.isLowestZoomLevel()){
            this.decreaseZoomLevel();
            ComplexNumber zoomCenter = complexCenterForZoomedJulia.pop();
            this.setZoomCenter(zoomCenter);
            if(model.getConfig().getLogDebug()) {
                log.info("zoomCenter: " + this.getZoomCenter() + " - zoomLevel:  "+ this.getZoomLevel());
            }
            for(int y = 0; y < worldDimensions.getY(); y++){
                for(int x = 0; x < worldDimensions.getX(); x++){
                    LatticePoint p = new LatticePoint(x, y);
                    this.isInZooomedJuliaSet(p);
                }
            }
        }
    }
}
