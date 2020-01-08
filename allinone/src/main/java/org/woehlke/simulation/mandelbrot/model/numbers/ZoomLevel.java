package org.woehlke.simulation.mandelbrot.model.numbers;

public class ZoomLevel {

    private final int LEVEL_NO_ZOOM = 1;

    private int zoomLevel;

    public ZoomLevel(){
        this.zoomLevel = LEVEL_NO_ZOOM;
    }

    public int getZoomLevel() {
        return zoomLevel;
    }

    public void inceaseZoomLevel() {
        zoomLevel *= 2;
    }

    public void decreaseZoomLevel() {
        if(zoomLevel > LEVEL_NO_ZOOM){
            zoomLevel /= 2;
        }
    }

    public boolean isLowestZoomLevel(){
        return zoomLevel == LEVEL_NO_ZOOM;
    }

    public void setLowestZoomLevel() {
        this.zoomLevel = LEVEL_NO_ZOOM;
    }

    public void start() {
        this.zoomLevel = LEVEL_NO_ZOOM;
    }

}
