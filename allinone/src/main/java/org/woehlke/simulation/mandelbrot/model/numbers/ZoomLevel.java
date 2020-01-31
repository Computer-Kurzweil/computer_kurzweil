package org.woehlke.simulation.mandelbrot.model.numbers;

import lombok.Getter;

import java.io.Serializable;

public class ZoomLevel implements Serializable {

    @Getter
    private int zoomLevel;

    public ZoomLevel(){
        this.zoomLevel = LEVEL_NO_ZOOM;
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

    private final int LEVEL_NO_ZOOM = 1;

}
