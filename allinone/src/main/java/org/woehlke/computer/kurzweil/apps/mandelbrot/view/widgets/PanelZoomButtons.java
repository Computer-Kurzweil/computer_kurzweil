package org.woehlke.computer.kurzweil.apps.mandelbrot.view.widgets;

import lombok.Getter;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.view.layouts.PanelBorder;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class PanelZoomButtons extends JPanel {

    private final ComputerKurzweilApplicationContext ctx;

    @Getter private final JLabel zoomLevelFieldLabel;
    @Getter private final TextField zoomLevelField;
    @Getter private final JButton zoomOutButton;

    public PanelZoomButtons(ComputerKurzweilApplicationContext ctx) {
        this.ctx = ctx;
        FlowLayout layout = new FlowLayout();
        CompoundBorder borderPanelPushButtons = PanelBorder.getBorder(this.ctx.getProperties().getMandelbrot().getView().getButtonsZoomLabel());
        this.zoomLevelFieldLabel = new JLabel("Zoom Level");
        this.zoomLevelField = new TextField("1",3);
        this.zoomOutButton = new JButton(this.ctx.getProperties().getMandelbrot().getView().getButtonsZoomOut());
        this.setLayout(layout);
        this.setBorder(borderPanelPushButtons);
        this.add(this.zoomLevelFieldLabel);
        this.add(this.zoomLevelField);
        this.add(this.zoomOutButton);
    }
}
