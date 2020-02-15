package org.woehlke.computer.kurzweil.tabs.mandelbrot.widgets;

import lombok.Getter;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;

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
        CompoundBorder borderPanelRadioButtons = ctx.getBorder(
            this.ctx.getProperties().getMandelbrot().getView().getButtonsLabel()
        );
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);
        this.setBorder(borderPanelRadioButtons);
        this.add(radioButtonsGroup.getRadioButtonsSwitch());
        this.add(radioButtonsGroup.getRadioButtonsZoom());
    }
}
