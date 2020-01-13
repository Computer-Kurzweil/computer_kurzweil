package org.woehlke.simulation.all.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.all.model.ComputerKurzweilApplicationContext;
import org.woehlke.simulation.all.view.ComputerKurzweilApplicationFrame;
import org.woehlke.simulation.mandelbrot.control.common.EventDispatcher;

import java.awt.event.*;

@Component
public class ComputerKurzweilControllerThread extends Thread implements Runnable, EventDispatcher {

    private final ComputerKurzweilApplicationContext ctx;

    private final ComputerKurzweilApplicationFrame frame;

    @Autowired
    public ComputerKurzweilControllerThread(ComputerKurzweilApplicationContext ctx, ComputerKurzweilApplicationFrame frame) {
        this.ctx = ctx;
        this.frame = frame;
    }

    public void showMe() { this.frame.showMe(); }

    @Override public void windowOpened(WindowEvent e) {
        showMe();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        this.ctx.exit();
    }

    @Override
    public void windowClosed(WindowEvent e) {
        this.ctx.exit();
    }

    @Override public void windowIconified(WindowEvent e) {}

    @Override public void windowDeiconified(WindowEvent e){
        showMe();
    }

    @Override public void windowActivated(WindowEvent e) {
        showMe();
    }

    @Override public void windowDeactivated(WindowEvent e) {}



    @Override
    public void windowGainedFocus(WindowEvent e) {

    }

    @Override
    public void windowLostFocus(WindowEvent e) {

    }

    @Override
    public void windowStateChanged(WindowEvent e) {

    }

}
