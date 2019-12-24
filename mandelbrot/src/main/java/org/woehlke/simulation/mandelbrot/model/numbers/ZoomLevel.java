package org.woehlke.simulation.mandelbrot.model.numbers;

public class ZoomLevel {

    private int zoomLevel;

    public ZoomLevel(ZoomLevel zoomLevel) {
        this.zoomLevel = zoomLevel.zoomLevel;
    }

    public ZoomLevel() {
       setLowestZoomLevel();
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

    public void start() {
        setLowestZoomLevel();
    }

    public static ZoomLevel getDefaultZoomLevel(){
        ZoomLevel zl = new ZoomLevel();
        zl.start();
        return zl;
    }
}
