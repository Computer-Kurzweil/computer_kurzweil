package org.woehlke.simulation.mandelbrot.model;

import org.woehlke.simulation.mandelbrot.config.Config;
import org.woehlke.simulation.mandelbrot.control.ControllerThread;
import org.woehlke.simulation.mandelbrot.model.fractal.GaussianNumberPlane;
import org.woehlke.simulation.mandelbrot.model.fractal.LatticePoint;
import org.woehlke.simulation.mandelbrot.model.state.ApplicationStateMachine;
import org.woehlke.simulation.mandelbrot.model.turing.MandelbrotTuringMachine;
import org.woehlke.simulation.mandelbrot.view.ApplicationCanvas;
import org.woehlke.simulation.mandelbrot.view.ApplicationFrame;
import org.woehlke.simulation.mandelbrot.view.PanelButtons;
import org.woehlke.simulation.mandelbrot.view.PanelSubtitle;

import java.awt.*;
import java.awt.event.*;

public class OnjectRegistry implements WindowListener, MouseListener, ActionListener {

    private Rectangle rectangleBounds;
    private Dimension dimensionSize;
    private PanelButtons panelButtons;

    private final ControllerThread controllerThread;
    private final ApplicationCanvas canvas;

    private final PanelSubtitle panelSubtitle;
    private final Config config;
    private final ApplicationFrame frame;

    private final GaussianNumberPlane gaussianNumberPlane;
    private final MandelbrotTuringMachine mandelbrotTuringMachine;
    private final ApplicationStateMachine applicationStateMachine;

    public OnjectRegistry(Config config, ApplicationFrame frame) {
        this.config = config;
        this.frame = frame;
        this.canvas = new ApplicationCanvas(this);
        this.controllerThread = new ControllerThread(this);
        this.panelSubtitle = new PanelSubtitle(config);
        this.gaussianNumberPlane = new GaussianNumberPlane(this);
        this.mandelbrotTuringMachine = new MandelbrotTuringMachine(this);
        this.applicationStateMachine = new ApplicationStateMachine(this);
        this.canvas.addMouseListener( this );
        this.frame.addWindowListener( this );
    }

    public void step() {
        switch (applicationStateMachine.getApplicationState()) {
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

    public int getCellStatusFor(int x, int y) {
        return gaussianNumberPlane.getCellStatusFor(x, y);
    }

    public LatticePoint getWorldDimensions() {
        int width = config.getWidth();
        int height = config.getHeight();
        return new LatticePoint(width, height);
    }

    public void setModeSwitch() {
        this.getGaussianNumberPlane().setModeSwitch();
        this.getApplicationStateMachine().setModeSwitch();
        this.getCanvas().setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.getPanelButtons().enableZoomButton();
        this.showMe();
    }

    public void setModeZoom() {
        this.getGaussianNumberPlane().setModeZoom();
        this.getApplicationStateMachine().setModeZoom();
        this.getCanvas().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        this.getPanelButtons().disableZoomButton();
        this.showMe();
    }

    public void zoomOut() {
        switch (applicationStateMachine.getApplicationState()) {
            case MANDELBROT:
                //gaussianNumberPlane.zoomOutOfTheMandelbrotSet();
                break;
            case JULIA_SET:
                //gaussianNumberPlane.zoomOutOfTheJuliaSet();
                break;
            case MANDELBROT_ZOOM:
                //gaussianNumberPlane.zoomOutOfTheJuliaSet();
                gaussianNumberPlane.zoomOutOfTheMandelbrotSet();
                break;
            case JULIA_SET_ZOOM:
                gaussianNumberPlane.zoomOutOfTheJuliaSet();
                break;
        }
    }

    private void showMe(){
        this.getCanvas().repaint();
        this.getPanelButtons().repaint();
        this.getFrame().showMe();
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

    public void windowOpened(WindowEvent e) {
        showMe();
    }

    public void windowClosing(WindowEvent e) {
        this.getControllerThread().exit();
    }

    public void windowClosed(WindowEvent e) {
        this.getControllerThread().exit();
    }

    public void windowIconified(WindowEvent e) {}

    public void windowDeiconified(WindowEvent e) {
        showMe();
    }

    public void windowActivated(WindowEvent e) {
        showMe();
    }

    public void windowDeactivated(WindowEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        LatticePoint c = new LatticePoint(e.getX(), e.getY());
        applicationStateMachine.click();
        switch (applicationStateMachine.getApplicationState()) {
            case MANDELBROT:
                this.getMandelbrotTuringMachine().start();
                break;
            case JULIA_SET:
                this.getGaussianNumberPlane().computeTheJuliaSetFor(c);
                break;
            case MANDELBROT_ZOOM:
                this.getGaussianNumberPlane().zoomIntoTheMandelbrotSet(c);
                break;
            case JULIA_SET_ZOOM:
                this.getGaussianNumberPlane().zoomIntoTheJuliaSetFor(c);
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

    public void setPanelButtons(PanelButtons panelButtons) {
        this.panelButtons = panelButtons;
    }

    public GaussianNumberPlane getGaussianNumberPlane() {
        return gaussianNumberPlane;
    }

    public ControllerThread getControllerThread() {
        return controllerThread;
    }

    public ApplicationCanvas getCanvas() {
        return canvas;
    }

    public PanelButtons getPanelButtons() {
        return panelButtons;
    }

    public Config getConfig() {
        return config;
    }

    public ApplicationFrame getFrame() {
        return frame;
    }

    public Rectangle getRectangleBounds() {
        return rectangleBounds;
    }

    public void setRectangleBounds(Rectangle rectangleBounds) {
        this.rectangleBounds = rectangleBounds;
    }

    public Dimension getDimensionSize() {
        return dimensionSize;
    }

    public void setDimensionSize(Dimension dimensionSize) {
        this.dimensionSize = dimensionSize;
    }

    public PanelSubtitle getPanelSubtitle() {
        return panelSubtitle;
    }

    public MandelbrotTuringMachine getMandelbrotTuringMachine() {
        return mandelbrotTuringMachine;
    }

    public ApplicationStateMachine getApplicationStateMachine() {
        return applicationStateMachine;
    }

    public void startControllerThread() {
        this.setModeSwitch();
        this.getControllerThread().start();
    }
}
