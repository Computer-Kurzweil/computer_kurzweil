package org.woehlke.simulation.mandelbrot.control.impl;

import org.woehlke.simulation.mandelbrot.config.Config;
import org.woehlke.simulation.mandelbrot.control.ApplicationStateMachine;
import org.woehlke.simulation.mandelbrot.model.MandelbrotTuringMachine;
import org.woehlke.simulation.mandelbrot.control.ApplicationContext;
import org.woehlke.simulation.mandelbrot.model.fractal.GaussianNumberPlaneBaseJuliaIF;
import org.woehlke.simulation.mandelbrot.model.fractal.impl.GaussianNumberPlaneBaseJuliaImpl;
import org.woehlke.simulation.mandelbrot.model.fractal.GaussianNumberPlaneBaseMandelbrot;
import org.woehlke.simulation.mandelbrot.model.fractal.impl.GaussianNumberPlaneBaseMandelbrotImpl;
import org.woehlke.simulation.mandelbrot.model.numbers.CellStatus;
import org.woehlke.simulation.mandelbrot.model.numbers.LatticePoint;
import org.woehlke.simulation.mandelbrot.model.turing.impl.MandelbrotTuringMachineImpl;
import org.woehlke.simulation.mandelbrot.view.ApplicationFrame;
import org.woehlke.simulation.mandelbrot.view.ApplicationCanvas;
import org.woehlke.simulation.mandelbrot.view.PanelButtons;
import org.woehlke.simulation.mandelbrot.view.impl.PanelSubtitle;

import java.awt.*;
import java.awt.event.*;

public class ApplicationContextImpl implements ApplicationContext {

    private Rectangle rectangleBounds;
    private Dimension dimensionSize;
    private PanelButtons panelButtons;

    private final ControllerThread controllerThread;
    private final ApplicationCanvas canvas;

    private final PanelSubtitle panelSubtitle;
    private final Config config;
    private final ApplicationFrame frame;

    private final GaussianNumberPlaneBaseJuliaIF gaussianNumberPlaneBaseJulia;
    private final GaussianNumberPlaneBaseMandelbrot gaussianNumberPlaneBaseMandelbrot;
    private final MandelbrotTuringMachine mandelbrotTuringMachine;
    private final ApplicationStateMachine applicationStateMachine;

    public ApplicationContextImpl(Config config, ApplicationFrame frame) {
        this.config = config;
        this.frame = frame;
        this.canvas = new ApplicationCanvas(this);
        this.controllerThread = new ControllerThread(this);
        this.panelSubtitle = new PanelSubtitle(config);
        this.gaussianNumberPlaneBaseJulia = new GaussianNumberPlaneBaseJuliaImpl(this);
        this.gaussianNumberPlaneBaseMandelbrot = new GaussianNumberPlaneBaseMandelbrotImpl(this);
        this.mandelbrotTuringMachine = new MandelbrotTuringMachineImpl(this);
        this.applicationStateMachine = new ApplicationStateMachineImpl(this);
        this.canvas.addMouseListener( this );
        this.frame.addWindowListener( this );
    }

    @Override public void start() {
        this.setModeSwitch();
    }

    @Override public void showMe(){
        this.getCanvas().repaint();
        this.getPanelButtons().repaint();
        this.getFrame().showMe();
    }

    @Override public void step() {
        switch (applicationStateMachine.getState()) {
            case MANDELBROT:
                this.getMandelbrotTuringMachine().step();
                this.getCanvas().repaint();
                break;
            case JULIA_SET:
            case MANDELBROT_ZOOM:
            case JULIA_SET_ZOOM:
                break;
        }
    }

    @Override public CellStatus getCellStatusFor(int x, int y) {
        switch (applicationStateMachine.getState()) {
            case MANDELBROT:
            case MANDELBROT_ZOOM:
                return gaussianNumberPlaneBaseMandelbrot.getCellStatusFor(x, y);
            case JULIA_SET:
            case JULIA_SET_ZOOM:
                return gaussianNumberPlaneBaseJulia.getCellStatusFor(x, y);
        }
        return null;
    }

    @Deprecated
    @Override public LatticePoint getWorldDimensions() {
        int width = config.getWidth();
        int height = config.getHeight();
        return new LatticePoint(width, height);
    }

    @Override public void setModeSwitch() {
        this.gaussianNumberPlaneBaseJulia.setModeSwitch();
        this.gaussianNumberPlaneBaseMandelbrot.setModeSwitch();
        this.getApplicationStateMachine().setModeSwitch();
        this.getCanvas().setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.getPanelButtons().enableZoomButton();
        this.showMe();
    }

    @Override public void setModeZoom() {
        this.gaussianNumberPlaneBaseJulia.setModeZoom();
        this.gaussianNumberPlaneBaseMandelbrot.setModeZoom();
        this.getApplicationStateMachine().setModeZoom();
        this.getCanvas().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        this.getPanelButtons().disableZoomButton();
        this.showMe();
    }

    @Override public void zoomOut() {
        switch (applicationStateMachine.getState()) {
            case MANDELBROT:
                //gaussianNumberPlane.zoomOutOfTheMandelbrotSet();
                break;
            case JULIA_SET:
                //gaussianNumberPlane.zoomOutOfTheJuliaSet();
                break;
            case MANDELBROT_ZOOM:
                //gaussianNumberPlane.zoomOutOfTheJuliaSet();
                gaussianNumberPlaneBaseMandelbrot.zoomOutOfTheMandelbrotSet();
                break;
            case JULIA_SET_ZOOM:
                gaussianNumberPlaneBaseJulia.zoomOutOfTheJuliaSet();
                break;
        }
    }

    @Override
    public String getZoomLevel() {
        switch (applicationStateMachine.getState()) {
            case MANDELBROT:
            case MANDELBROT_ZOOM:
                return gaussianNumberPlaneBaseMandelbrot.getZoomLevel();
            case JULIA_SET:
            case JULIA_SET_ZOOM:
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

    @Override public void windowClosing(WindowEvent e) {
        this.getControllerThread().exit();
    }

    @Override public void windowClosed(WindowEvent e) {
        this.getControllerThread().exit();
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
        LatticePoint c = new LatticePoint(e.getX(), e.getY());
        applicationStateMachine.click();
        switch (applicationStateMachine.getState()) {
            case MANDELBROT:
                this.getMandelbrotTuringMachine().start();
                while( ! this.getMandelbrotTuringMachine().isFinished()){
                    this.getMandelbrotTuringMachine().step();
                    this.canvas.repaint();
                }
                break;
            case JULIA_SET:
                this.gaussianNumberPlaneBaseJulia.computeTheJuliaSetFor(c);
                break;
            case MANDELBROT_ZOOM:
                this.gaussianNumberPlaneBaseMandelbrot.zoomIntoTheMandelbrotSet(c);
                break;
            case JULIA_SET_ZOOM:
                this.gaussianNumberPlaneBaseJulia.zoomIntoTheJuliaSetFor(c);
                break;
        }
        showMe();
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
    public GaussianNumberPlaneBaseJuliaIF getGaussianNumberPlaneBaseJulia() {
        return gaussianNumberPlaneBaseJulia;
    }

    @Override
    public GaussianNumberPlaneBaseMandelbrot getGaussianNumberPlaneBaseMandelbrot() {
        return gaussianNumberPlaneBaseMandelbrot;
    }

    @Override public ControllerThread getControllerThread() {
        return controllerThread;
    }

    @Override public ApplicationCanvas getCanvas() {
        return canvas;
    }

    @Override public PanelButtons getPanelButtons() {
        return panelButtons;
    }

    @Override public Config getConfig() {
        return config;
    }

    @Override public ApplicationFrame getFrame() {
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
