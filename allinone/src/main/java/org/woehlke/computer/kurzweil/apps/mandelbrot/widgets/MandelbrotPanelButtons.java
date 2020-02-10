package org.woehlke.computer.kurzweil.apps.mandelbrot.widgets;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.apps.mandelbrot.MandelbrotCanvas;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;

import javax.swing.*;
import java.awt.*;


/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 16.12.2019.
 */
@Log
public class MandelbrotPanelButtons extends JPanel {

    @Getter
    private final PanelButtonsGroup panelButtonsGroup;

    @Getter
    private final PanelZoomButtons panelZoomButtons;

    private final ComputerKurzweilApplicationContext ctx;

    @Getter @Setter
    private MandelbrotCanvas canvas;

    public MandelbrotPanelButtons(ComputerKurzweilApplicationContext ctx) {
        this.ctx = ctx;
        FlowLayout layout = new FlowLayout();
        this.panelButtonsGroup = new PanelButtonsGroup(ctx);
        this.panelZoomButtons = new PanelZoomButtons(ctx);
        this.setLayout(layout);
        this.add(panelButtonsGroup);
        this.add(panelZoomButtons);
        this.disableZoomButton();
    }

    //TODO: seems strange
    @Override
    public void repaint() {
        try {
            if(canvas!=null){
                String text = canvas.getZoomLevel();
                this.panelZoomButtons.getZoomLevelField().setText(text);
                //TODO: seems strange:
                this.panelZoomButtons.getZoomLevelField().repaint();
            }
        } catch (NullPointerException e){
            log.info(e.getMessage());
        }
        //TODO: seems strange
        super.repaint();
    }

    public void enableZoomButton() {
        this.panelZoomButtons.setEnabled(false);
    }

    public void disableZoomButton() {
        this.panelZoomButtons.setEnabled(true);
    }

    public JRadioButton getRadioButtonsSwitch() {
        return this.panelButtonsGroup.getRadioButtonsGroup().getRadioButtonsSwitch();
    }

    public JRadioButton getRadioButtonsZoom() {
        return this.panelButtonsGroup.getRadioButtonsGroup().getRadioButtonsZoom();
    }

    public AbstractButton getZoomOutButton() {
        return this.panelZoomButtons.getZoomOutButton();
    }
}
