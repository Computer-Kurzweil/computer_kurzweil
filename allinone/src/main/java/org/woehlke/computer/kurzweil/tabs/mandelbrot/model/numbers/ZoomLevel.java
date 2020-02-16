package org.woehlke.computer.kurzweil.tabs.mandelbrot.model.numbers;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
@EqualsAndHashCode
public class ZoomLevel implements Serializable {

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
