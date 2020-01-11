package org.woehlke.simulation.mandelbrot.model.fractal.impl;

import org.woehlke.simulation.mandelbrot.control.common.MandelbrotApplicationContext;
import org.woehlke.simulation.mandelbrot.control.state.ApplicationState;
import org.woehlke.simulation.mandelbrot.control.state.ClickBehaviour;
import org.woehlke.simulation.mandelbrot.control.state.FractalSetType;
import org.woehlke.simulation.mandelbrot.model.fractal.common.GaussianNumberPlaneBase;
import org.woehlke.simulation.mandelbrot.model.numbers.CellStatus;
import org.woehlke.simulation.mandelbrot.model.numbers.ComplexNumber;
import org.woehlke.simulation.mandelbrot.model.numbers.LatticePoint;

import java.util.logging.Logger;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 16.12.2019.
 */
public abstract class GaussianNumberPlaneBaseImpl implements GaussianNumberPlaneBase {

    private int[][] lattice;

    private ComplexNumber zoomCenter;

    protected final MandelbrotApplicationContext ctx;

    private ClickBehaviour clickBehaviour;
    private final FractalSetType fractalSetType;

    public static Logger log = Logger.getLogger(GaussianNumberPlaneBaseImpl.class.getName());

    protected GaussianNumberPlaneBaseImpl(MandelbrotApplicationContext ctx, FractalSetType fractalSetType) {
        this.ctx = ctx;
        this.fractalSetType = fractalSetType;
        clickBehaviour = ClickBehaviour.start();
    }

    public void start(){
        this.lattice = new int[ctx.getWorldDimensions().getWidth()][ctx.getWorldDimensions().getHeight()];
        for(int y = 0;y < ctx.getWorldDimensions().getY(); y++){
            for(int x=0; x < ctx.getWorldDimensions().getX(); x++){
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
        x %= ctx.getWorldDimensions().getX();
        y %= ctx.getWorldDimensions().getY();
        return new CellStatus(lattice[x][y]);
    }

    public CellStatus getCellStatusFor(final LatticePoint turingPosition){
        int x = turingPosition.getX() % ctx.getWorldDimensions().getX();
        int y = turingPosition.getY() % ctx.getWorldDimensions().getY();
        return new CellStatus(lattice[x][y]);
    }

    public void setCellStatusFor(final LatticePoint turingPosition, final int state){
        int x = turingPosition.getX() % ctx.getWorldDimensions().getX();
        int y = turingPosition.getY() % ctx.getWorldDimensions().getY();
        lattice[x][y]=state;
    }

    public void setCellStatusFor(int x, int y, final int state){
        x %= ctx.getWorldDimensions().getX();
        y %= ctx.getWorldDimensions().getY();
        lattice[x][y]=state;
    }

    public ApplicationState resolve(){
        return ApplicationState.resolve(fractalSetType,clickBehaviour);
    }

    public ClickBehaviour getClickBehaviour() {
        return clickBehaviour;
    }

    public FractalSetType getFractalSetType() {
        return fractalSetType;
    }

    public ComplexNumber getZoomCenter() {
        return zoomCenter;
    }

    public void setZoomCenter(ComplexNumber zoomCenter) {
        this.zoomCenter = zoomCenter;
    }




}
