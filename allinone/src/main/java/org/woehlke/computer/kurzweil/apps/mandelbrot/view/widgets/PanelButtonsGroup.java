package org.woehlke.computer.kurzweil.apps.mandelbrot.view.widgets;

import lombok.Getter;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.view.layouts.PanelBorder;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class PanelButtonsGroup extends JPanel {

    private final ComputerKurzweilApplicationContext ctx;

    @Getter
    private final RadioButtonsGroup radioButtonsGroup;

    public PanelButtonsGroup(ComputerKurzweilApplicationContext ctx) {
        this.ctx = ctx;
        this.radioButtonsGroup = new RadioButtonsGroup(ctx);
        CompoundBorder borderPanelRadioButtons = PanelBorder.getBorder(this.ctx.getProperties().getMandelbrot().getView().getButtonsLabel());
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);
        this.setBorder(borderPanelRadioButtons);
        this.add(radioButtonsGroup.getRadioButtonsSwitch());
        this.add(radioButtonsGroup.getRadioButtonsZoom());
    }
}
