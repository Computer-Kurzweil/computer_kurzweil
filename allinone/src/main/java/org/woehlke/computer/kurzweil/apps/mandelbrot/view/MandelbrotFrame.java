package org.woehlke.computer.kurzweil.apps.mandelbrot.view;

import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.computer.kurzweil.config.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.view.common.PanelSubtitle;
import org.woehlke.computer.kurzweil.apps.mandelbrot.view.parts.MandelbrotCanvas;
import org.woehlke.computer.kurzweil.apps.mandelbrot.view.parts.MandelbrotPanelButtons;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;

/**
 * (C) 2006 - 2013 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 * Date: 04.02.2006
 * Time: 18:47:46
 */
@Log
@Component
public class MandelbrotFrame extends JPanel implements
        ImageObserver,
        MenuContainer,
        Accessible,
        ActionListener {

    @Getter private final ComputerKurzweilApplicationContext ctx;

    @Getter private final PanelSubtitle panelSubtitle;
    @Getter private final MandelbrotPanelButtons panelButtons;
    @Getter private final MandelbrotCanvas canvas;

    @Autowired
    public MandelbrotFrame(
        ComputerKurzweilApplicationContext ctx
    ) {
        this.ctx=ctx;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.panelSubtitle = PanelSubtitle.getPanelSubtitleForMandelbrot(this.ctx);
        this.panelButtons = new MandelbrotPanelButtons(this.ctx);
        this.canvas = new MandelbrotCanvas(this.ctx, this.panelButtons,this);
        this.panelButtons.setCanvas(this.canvas);
        this.add(this.panelSubtitle);
        this.add(this.canvas);
        this.add(this.panelButtons);
        this.canvas.addMouseListener( canvas );
        this.getPanelButtons().getRadioButtonsSwitch().addActionListener(this);
        this.getPanelButtons().getRadioButtonsZoom().addActionListener(this);
        this.getPanelButtons().getZoomOutButton().addActionListener(this);
    }

    public void start() {
        this.canvas.start();
        showMe();
    }

    public void setModeSwitch() {
        this.canvas.setModeSwitch();
        this.panelButtons.enableZoomButton();
    }

    public void setModeZoom() {
        this.canvas.setModeZoom();
        this.panelButtons.disableZoomButton();
    }

    public void showMe() {
        try {
            this.setVisible(true);
            this.canvas.setVisible(true);
            this.panelSubtitle.setVisible(true);
            this.panelButtons.setVisible(true);
            this.repaint();
        } catch (NullPointerException e){
            log.warning("Error in showMe() "+e.getMessage());
        }
    }

    @Override
    public void repaint(){
        try {
            this.canvas.repaint();
        } catch (NullPointerException e){
            log.warning("Error MandelbrotFrame.repaint() -> canvas.repaint() " + e.getMessage());
        }
        try {
            this.panelSubtitle.repaint();
        } catch (NullPointerException e){
            log.warning("Error MandelbrotFrame.repaint() ->  panelSubtitle.repaint() " + e.getMessage());
        }
        try {
            this.panelButtons.repaint();
        } catch (NullPointerException e){
            log.warning("Error MandelbrotFrame.repaint() -> panelButtons.repaint() " + e.getMessage());
        }
        try {
            super.repaint();
        } catch (NullPointerException e){
            log.warning("Error MandelbrotFrame.repaint() -> super.repaint() " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.getPanelButtons().getRadioButtonsSwitch()) {
            this.setModeSwitch();
        } else if(ae.getSource() == this.getPanelButtons().getRadioButtonsZoom()) {
            this.setModeZoom();
        } else if(ae.getSource() == this.getPanelButtons().getZoomOutButton()){
            this.canvas.zoomOut();
        }
        showMe();
    }
}