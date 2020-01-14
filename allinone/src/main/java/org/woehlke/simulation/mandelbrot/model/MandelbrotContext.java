package org.woehlke.simulation.mandelbrot.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.mandelbrot.config.MandelbrotProperties;
import org.woehlke.simulation.mandelbrot.control.ComputeMandelbrotSetThread;
import org.woehlke.simulation.mandelbrot.model.fractal.GaussianNumberPlaneBaseJulia;
import org.woehlke.simulation.mandelbrot.model.fractal.GaussianNumberPlaneMandelbrot;
import org.woehlke.simulation.mandelbrot.model.numbers.CellStatus;
import org.woehlke.simulation.allinone.model.LatticePoint;
import org.woehlke.simulation.mandelbrot.model.turing.TuringPhaseStateMachine;
import org.woehlke.simulation.mandelbrot.model.turing.TuringPositionsStateMachine;
import org.woehlke.simulation.mandelbrot.model.turing.MandelbrotTuringMachine;
import org.woehlke.simulation.mandelbrot.view.MandelbrotFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@Log
@Component
public class MandelbrotContext implements MouseListener, ActionListener {

    @Getter @Setter private MandelbrotFrame frame;
    @Getter private final MandelbrotProperties properties;
    @Getter private final GaussianNumberPlaneBaseJulia gaussianNumberPlaneBaseJulia;
    @Getter private final GaussianNumberPlaneMandelbrot gaussianNumberPlaneMandelbrot;
    @Getter private final MandelbrotTuringMachine mandelbrotTuringMachine;
    @Getter private final ApplicationStateMachine applicationStateMachine;
    @Getter private final TuringPhaseStateMachine turingPhaseStateMachine;
    @Getter private final TuringPositionsStateMachine turingPositionsStateMachine;

    private final ComputeMandelbrotSetThread computeMandelbrotSetThread;

    @Autowired
    public MandelbrotContext(
       MandelbrotProperties properties
    ) {
        this.properties = properties;
        this.gaussianNumberPlaneBaseJulia = new GaussianNumberPlaneBaseJulia(this);
        this.gaussianNumberPlaneMandelbrot = new GaussianNumberPlaneMandelbrot(this);
        this.applicationStateMachine = new ApplicationStateMachine(this);
        this.turingPhaseStateMachine = new TuringPhaseStateMachine();
        this.turingPositionsStateMachine = new TuringPositionsStateMachine(this);
        this.mandelbrotTuringMachine = new MandelbrotTuringMachine(this);
        this.computeMandelbrotSetThread = new ComputeMandelbrotSetThread(this);
    }

    public void start() {
        this.gaussianNumberPlaneBaseJulia.start();
        this.gaussianNumberPlaneMandelbrot.start();
        this.mandelbrotTuringMachine.start();
        this.applicationStateMachine.start();
        this.setModeSwitch();
        this.computeTheMandelbrotSet();
    }

    public void showMe(){
        this.getFrame().showMe();
    }

    public CellStatus getCellStatusFor(int x, int y) {
        switch (applicationStateMachine.getState().getFractalSetType()) {
            case JULIA_SET:
                return gaussianNumberPlaneBaseJulia.getCellStatusFor(x, y);
            case MANDELBROT_SET:
            default:
                return gaussianNumberPlaneMandelbrot.getCellStatusFor(x, y);
        }
    }

    public LatticePoint getWorldDimensions() {
        return properties.getWorldDimensions();
    }

    public void setModeSwitch() {
        this.gaussianNumberPlaneBaseJulia.setModeSwitch();
        this.gaussianNumberPlaneMandelbrot.setModeSwitch();
        this.applicationStateMachine.setModeSwitch();
        this.frame.setModeSwitch();
        this.showMe();
    }

    public void setModeZoom() {
        this.gaussianNumberPlaneBaseJulia.setModeZoom();
        this.gaussianNumberPlaneMandelbrot.setModeZoom();
        this.applicationStateMachine.setModeZoom();
        this.frame.setModeZoom();
        this.showMe();
    }

    public void zoomOut() {
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

    public String getZoomLevel() {
        switch (applicationStateMachine.getState().getFractalSetType()) {
            case MANDELBROT_SET:
                return gaussianNumberPlaneMandelbrot.getZoomLevel();
            case JULIA_SET:
                return gaussianNumberPlaneBaseJulia.getZoomLevel();
            default:
                return "1";
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == frame.getPanelButtons().getRadioButtonsSwitch()) {
            this.setModeSwitch();
        } else if(ae.getSource() == frame.getPanelButtons().getRadioButtonsZoom()) {
            this.setModeZoom();
        } else if(ae.getSource() == frame.getPanelButtons().getZoomOutButton()){
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
        computeMandelbrotSetThread.start();
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

}
