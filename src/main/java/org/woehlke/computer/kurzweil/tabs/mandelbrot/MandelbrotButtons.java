package org.woehlke.computer.kurzweil.tabs.mandelbrot;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.widgets.PanelButtonsGroup;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.widgets.PanelZoomButtons;

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
@Log4j2
@Getter
public class MandelbrotButtons extends JPanel {

    private final PanelButtonsGroup panelButtonsGroup;
    private final PanelZoomButtons panelZoomButtons;
    private final MandelbrotContext tabCtx;

    @Setter
    private MandelbrotCanvas canvas;

    public MandelbrotButtons(MandelbrotContext tabCtx) {
        this.tabCtx = tabCtx;
        FlowLayout layout = new FlowLayout();
        this.panelButtonsGroup = new PanelButtonsGroup(  this.tabCtx);
        this.panelZoomButtons = new PanelZoomButtons(  this.tabCtx);
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
