package org.woehlke.simulation.mandelbrot.control.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.mandelbrot.config.MandelbrotProperties;
import org.woehlke.simulation.mandelbrot.control.common.MandelbrotApplicationContext;
import org.woehlke.simulation.mandelbrot.control.ComputeMandelbrotSetThread;
import org.woehlke.simulation.mandelbrot.control.common.ApplicationStateMachine;
import org.woehlke.simulation.mandelbrot.model.MandelbrotTuringMachine;
import org.woehlke.simulation.mandelbrot.model.fractal.GaussianNumberPlaneBaseJulia;
import org.woehlke.simulation.mandelbrot.model.fractal.GaussianNumberPlaneMandelbrot;
import org.woehlke.simulation.mandelbrot.model.fractal.impl.GaussianNumberPlaneBaseJuliaImpl;
import org.woehlke.simulation.mandelbrot.model.fractal.impl.GaussianNumberPlaneMandelbrotImpl;
import org.woehlke.simulation.mandelbrot.model.numbers.CellStatus;
import org.woehlke.simulation.allinone.model.LatticePoint;
import org.woehlke.simulation.mandelbrot.model.turing.impl.MandelbrotTuringMachineImpl;
import org.woehlke.simulation.mandelbrot.view.MandelbrotCanvas;
import org.woehlke.simulation.mandelbrot.view.MandelbrotFrame;
import org.woehlke.simulation.mandelbrot.view.MandelbrotPanelButtons;
import org.woehlke.simulation.mandelbrot.view.MandelbrotPanelSubtitle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

@Component
public class MandelbrotApplicationContextImpl implements MandelbrotApplicationContext {

    private MandelbrotPanelButtons panelButtons;
    private MandelbrotFrame frame;

    private final MandelbrotPanelSubtitle panelSubtitle;
    private final MandelbrotCanvas canvas;
    private final GaussianNumberPlaneBaseJulia gaussianNumberPlaneBaseJulia;
    private final GaussianNumberPlaneMandelbrot gaussianNumberPlaneMandelbrot;
    private final MandelbrotTuringMachine mandelbrotTuringMachine;
    private final ApplicationStateMachine applicationStateMachine;
    private final MandelbrotProperties properties;


    @Autowired
    public MandelbrotApplicationContextImpl(
        MandelbrotProperties properties
    ) {
        this.properties = properties;
        this.canvas = new MandelbrotCanvas(this);
        this.panelSubtitle = new MandelbrotPanelSubtitle(properties);
        this.gaussianNumberPlaneBaseJulia = new GaussianNumberPlaneBaseJuliaImpl(this);
        this.gaussianNumberPlaneMandelbrot = new GaussianNumberPlaneMandelbrotImpl(this);
        this.mandelbrotTuringMachine = new MandelbrotTuringMachineImpl(this);
        this.applicationStateMachine = new ApplicationStateMachineImpl(this);

    }

    @Override public void start() {
        this.canvas.addMouseListener( this );
        this.gaussianNumberPlaneBaseJulia.start();
        this.gaussianNumberPlaneMandelbrot.start();
        this.mandelbrotTuringMachine.start();
        this.applicationStateMachine.start();
        this.setModeSwitch();
        this.computeTheMandelbrotSet();
    }

    @Override public void showMe(){
        this.getCanvas().repaint();
        this.getPanelButtons().repaint();
        this.getFrame().showMe();
    }

    @Override public CellStatus getCellStatusFor(int x, int y) {
        switch (applicationStateMachine.getState().getFractalSetType()) {
            case JULIA_SET:
                return gaussianNumberPlaneBaseJulia.getCellStatusFor(x, y);
            case MANDELBROT_SET:
            default:
                return gaussianNumberPlaneMandelbrot.getCellStatusFor(x, y);
        }
    }

    @Deprecated
    @Override public LatticePoint getWorldDimensions() {
        return properties.getWorldDimensions();
    }

    @Override public void setModeSwitch() {
        this.gaussianNumberPlaneBaseJulia.setModeSwitch();
        this.gaussianNumberPlaneMandelbrot.setModeSwitch();
        this.getApplicationStateMachine().setModeSwitch();
        this.getCanvas().setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.getPanelButtons().enableZoomButton();
        this.showMe();
    }

    @Override public void setModeZoom() {
        this.gaussianNumberPlaneBaseJulia.setModeZoom();
        this.gaussianNumberPlaneMandelbrot.setModeZoom();
        this.getApplicationStateMachine().setModeZoom();
        this.getCanvas().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        this.getPanelButtons().disableZoomButton();
        this.showMe();
    }

    @Override public void zoomOut() {
        switch (applicationStateMachine.getState().getFractalSetType()) {
            case JULIA_SET:
                gaussianNumberPlaneBaseJulia.zoomOut();
                break;
            case MANDELBROT_SET:
            default:
                gaussianNumberPlaneMandelbrot.zoomOut();
                break;
        }
    }

    @Override
    public String getZoomLevel() {
        switch (applicationStateMachine.getState().getFractalSetType()) {
            case MANDELBROT_SET:
                return gaussianNumberPlaneMandelbrot.getZoomLevel();
            case JULIA_SET:
                return gaussianNumberPlaneBaseJulia.getZoomLevel();
            default:
                return "ERR";
        }
    }

    /**
     * TODO write doc.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.getPanelButtons().getRadioButtonsSwitch()) {
            this.setModeSwitch();
        } else if(ae.getSource() == this.getPanelButtons().getRadioButtonsZoom()) {
            this.setModeZoom();
        } else if(ae.getSource() == this.getPanelButtons().getZoomOutButton()){
            this.zoomOut();
        }
        showMe();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        LatticePoint latticePoint = new LatticePoint(e.getX(), e.getY());
        applicationStateMachine.click();
        switch (applicationStateMachine.getState()) {
            case MANDELBROT_SWITCH:
                this.computeTheMandelbrotSet();
                break;
            case JULIA_SET_SWITCH:
                this.gaussianNumberPlaneBaseJulia.computeTheSet(latticePoint);
                break;
            case MANDELBROT_ZOOM:
                this.gaussianNumberPlaneMandelbrot.zoomInto(latticePoint);
                break;
            case JULIA_SET_ZOOM:
                this.gaussianNumberPlaneBaseJulia.zoomInto(latticePoint);
                break;
        }
        showMe();
        e.consume();
    }

    private void computeTheMandelbrotSet() {
        ComputeMandelbrotSetThread t = new ComputeMandelbrotSetThread(this);
        t.start();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override public void setPanelButtons(MandelbrotPanelButtons panelButtons) {
        this.panelButtons = panelButtons;
    }

    @Override
    public GaussianNumberPlaneBaseJulia getGaussianNumberPlaneBaseJulia() {
        return gaussianNumberPlaneBaseJulia;
    }

    @Override
    public GaussianNumberPlaneMandelbrot getGaussianNumberPlaneMandelbrot() {
        return gaussianNumberPlaneMandelbrot;
    }

    @Override public MandelbrotCanvas getCanvas() {
        return canvas;
    }

    @Override public MandelbrotPanelButtons getPanelButtons() {
        return panelButtons;
    }

    @Override public MandelbrotProperties getProperties() {
        return properties;
    }

    public void setFrame(MandelbrotFrame frame) {
        this.frame = frame;
    }

    @Override public MandelbrotFrame getFrame() {
        return frame;
    }

    @Override public MandelbrotPanelSubtitle getPanelSubtitle() {
        return panelSubtitle;
    }

    @Override public MandelbrotTuringMachine getMandelbrotTuringMachine() {
        return mandelbrotTuringMachine;
    }

    @Override public ApplicationStateMachine getApplicationStateMachine() {
        return applicationStateMachine;
    }



}
