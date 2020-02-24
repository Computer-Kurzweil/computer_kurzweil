package org.woehlke.computer.kurzweil.tabs.mandelbrot.canvas;

import lombok.Getter;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.MandelbrotContext;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

@Getter
public class PanelButtonsGroup extends JPanel {

    private final MandelbrotContext tabCtx;
    private final RadioButtonsGroup radioButtonsGroup;
    private final CompoundBorder borderPanelRadioButtons;

    public PanelButtonsGroup(MandelbrotContext tabCtx) {
        this.tabCtx = tabCtx;
        this.radioButtonsGroup = new RadioButtonsGroup(this.tabCtx);
        String buttonsLabel = this.tabCtx.getCtx().getProperties().getMandelbrot().getView().getButtonsLabel();
        this.borderPanelRadioButtons = this.tabCtx.getCtx().getBorder(buttonsLabel);
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);
        this.setBorder(borderPanelRadioButtons);
        this.add(radioButtonsGroup.getRadioButtonsSwitch());
        this.add(radioButtonsGroup.getRadioButtonsZoom());
    }
}
