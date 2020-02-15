package org.woehlke.computer.kurzweil.tabs.mandelbrot.model.fractal;

import lombok.Getter;
import lombok.Setter;
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
public abstract class GaussianNumberPlaneBase implements Startable {

    private int[][] lattice;

    @Getter @Setter
    private ComplexNumber zoomCenter;

    @Getter @Setter
    private ClickBehaviour clickBehaviour;

    @Getter
    private final FractalSetType fractalSetType;

    @Getter
    protected final MandelbrotContext tabCtx;

    protected GaussianNumberPlaneBase(
        MandelbrotContext tabCtx,
        FractalSetType fractalSetType
    ) {
        this.tabCtx = tabCtx;
        this.fractalSetType = fractalSetType;
        clickBehaviour = ClickBehaviour.start();
    }

    public void start(){
        this.lattice = new int[this.tabCtx .getCtx().getWorldDimensions().getWidth()][this.tabCtx .getCtx().getWorldDimensions().getHeight()];
        for(int y = 0;y < this.tabCtx .getCtx().getWorldDimensions().getY(); y++){
            for(int x=0; x < this.tabCtx .getCtx().getWorldDimensions().getX(); x++){
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
        x %= this.tabCtx .getCtx().getWorldDimensions().getX();
        y %= this.tabCtx .getCtx().getWorldDimensions().getY();
        return new CellStatus(lattice[x][y]);
    }

    public CellStatus getCellStatusFor(final LatticePoint turingPosition){
        int x = turingPosition.getX() % this.tabCtx .getCtx().getWorldDimensions().getX();
        int y = turingPosition.getY() % this.tabCtx .getCtx().getWorldDimensions().getY();
        return new CellStatus(lattice[x][y]);
    }

    public void setCellStatusFor(final LatticePoint turingPosition, final int state){
        int x = turingPosition.getX() % this.tabCtx .getCtx().getWorldDimensions().getX();
        int y = turingPosition.getY() % this.tabCtx .getCtx().getWorldDimensions().getY();
        lattice[x][y]=state;
    }

    public void setCellStatusFor(int x, int y, final int state){
        x %= this.tabCtx .getCtx().getWorldDimensions().getX();
        y %= this.tabCtx .getCtx().getWorldDimensions().getY();
        lattice[x][y]=state;
    }

    public ApplicationState resolve(){
        return ApplicationState.resolve(fractalSetType,clickBehaviour);
    }


}
