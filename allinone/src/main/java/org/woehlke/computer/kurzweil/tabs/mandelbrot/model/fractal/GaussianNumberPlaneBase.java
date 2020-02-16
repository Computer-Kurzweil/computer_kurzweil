package org.woehlke.computer.kurzweil.tabs.mandelbrot.model.fractal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.MandelbrotContext;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.state.ApplicationState;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.state.ClickBehaviour;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.state.FractalSetType;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.numbers.CellStatus;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.model.numbers.ComplexNumber;
import org.woehlke.computer.kurzweil.model.LatticePoint;
import org.woehlke.computer.kurzweil.commons.Startable;


/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 16.12.2019.
 */
@Getter
@Setter
@ToString(callSuper = true)
public abstract class GaussianNumberPlaneBase implements Startable {

    @ToString.Exclude
    protected final MandelbrotContext tabCtx;
    private final FractalSetType fractalSetType;

    private volatile int[][] lattice;
    private volatile ComplexNumber zoomCenter;
    private volatile ClickBehaviour clickBehaviour;

    private final int worldX;
    private final int worldY;

    protected GaussianNumberPlaneBase(
        MandelbrotContext tabCtx,
        FractalSetType fractalSetType
    ) {
        this.tabCtx = tabCtx;
        this.fractalSetType = fractalSetType;
        clickBehaviour = ClickBehaviour.start();
        worldX = this.tabCtx.getCtx().getWorldDimensions().getWidth();
        worldY = this.tabCtx.getCtx().getWorldDimensions().getHeight();
        this.lattice = new int[worldX][worldY];
    }

    public void start(){
        int y;
        int x;
        for(y = 0; y < worldY; y++){
            for(x = 0; x < worldX; x++){
                lattice[x][y] = CellStatus.YET_UNCOMPUTED;
            }
        }
    }

    public boolean isModeZoom(){
       return clickBehaviour == ClickBehaviour.ZOOM_IN;
    }

    public boolean isModeSwitch(){
        return clickBehaviour == ClickBehaviour.SWITCH_BETWEEN_MANDELBROT_AND_JULIA_SET;
    }

    public void setModeZoom(){
        clickBehaviour = ClickBehaviour.ZOOM_IN;
    }

    public void setModeSwitch(){
        clickBehaviour = ClickBehaviour.SWITCH_BETWEEN_MANDELBROT_AND_JULIA_SET;
    }

    public CellStatus getCellStatusFor(int x, int y){
        x %= worldX;
        y %= worldY;
        int cellStatus = lattice[x][y];
        return new CellStatus(cellStatus);
    }

    public CellStatus getCellStatusFor(final LatticePoint turingPosition){
        int x = turingPosition.getX() % worldX;
        int y = turingPosition.getY() % worldY;
        return new CellStatus(lattice[x][y]);
    }

    public void setCellStatusFor(final LatticePoint turingPosition, final int state){
        int x = turingPosition.getX() % worldX;
        int y = turingPosition.getY() % worldY;
        lattice[x][y]=state;
    }

    public void setCellStatusFor(int x, int y, final int state){
        x %= worldX;
        y %= worldY;
        lattice[x][y]=state;
    }

    public ApplicationState resolve(){
        return ApplicationState.resolve(fractalSetType,clickBehaviour);
    }


}
