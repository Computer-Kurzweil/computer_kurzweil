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
import org.woehlke.simulation.all.model.LatticePoint;
import org.woehlke.simulation.mandelbrot.model.turing.impl.MandelbrotTuringMachineImpl;
import org.woehlke.simulation.mandelbrot.view.ApplicationCanvas;
import org.woehlke.simulation.mandelbrot.view.MandelbrotApplicationFrame;
import org.woehlke.simulation.mandelbrot.view.PanelButtons;
import org.woehlke.simulation.mandelbrot.view.PanelSubtitle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

@Component
public class MandelbrotApplicationContextImpl implements MandelbrotApplicationContext {

    private Rectangle rectangleBounds;
    private Dimension dimensionSize;
    private PanelButtons panelButtons;
    private MandelbrotApplicationFrame frame;

    private final PanelSubtitle panelSubtitle;
    private final ApplicationCanvas canvas;
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
        this.canvas = new ApplicationCanvas(this);
        this.panelSubtitle = new PanelSubtitle(properties);
        this.gaussianNumberPlaneBaseJulia = new GaussianNumberPlaneBaseJuliaImpl(this);
        this.gaussianNumberPlaneMandelbrot = new GaussianNumberPlaneMandelbrotImpl(this);
        this.mandelbrotTuringMachine = new MandelbrotTuringMachineImpl(this);
        this.applicationStateMachine = new ApplicationStateMachineImpl(this);

    }

    @Override public void start() {
        this.canvas.addMouseListener( this );
        this.frame.addWindowListener( this );
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

    @Override public void windowOpened(WindowEvent e) {
        showMe();
    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override public void windowIconified(WindowEvent e) {}

    @Override public void windowDeiconified(WindowEvent e) {
        showMe();
    }

    @Override public void windowActivated(WindowEvent e) {
        showMe();
    }

    @Override public void windowDeactivated(WindowEvent e) {}

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

    @Override public void setPanelButtons(PanelButtons panelButtons) {
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

    @Override public ApplicationCanvas getCanvas() {
        return canvas;
    }

    @Override public PanelButtons getPanelButtons() {
        return panelButtons;
    }

    @Override public MandelbrotProperties getProperties() {
        return properties;
    }

    public void setFrame(MandelbrotApplicationFrame frame) {
        this.frame = frame;
    }

    @Override public MandelbrotApplicationFrame getFrame() {
        return frame;
    }

    @Override public Rectangle getRectangleBounds() {
        return rectangleBounds;
    }

    @Override public void setRectangleBounds(Rectangle rectangleBounds) {
        this.rectangleBounds = rectangleBounds;
    }

    @Override public Dimension getDimensionSize() {
        return dimensionSize;
    }

    @Override public void setDimensionSize(Dimension dimensionSize) {
        this.dimensionSize = dimensionSize;
    }

    @Override public PanelSubtitle getPanelSubtitle() {
        return panelSubtitle;
    }

    @Override public MandelbrotTuringMachine getMandelbrotTuringMachine() {
        return mandelbrotTuringMachine;
    }

    @Override public ApplicationStateMachine getApplicationStateMachine() {
        return applicationStateMachine;
    }



}
