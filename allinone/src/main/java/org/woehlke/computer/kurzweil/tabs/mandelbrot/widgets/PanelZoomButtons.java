package org.woehlke.computer.kurzweil.tabs.mandelbrot.widgets;

import lombok.Getter;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.MandelbrotContext;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class PanelZoomButtons extends JPanel {

    private final MandelbrotContext tabCtx;

    @Getter private final JLabel zoomLevelFieldLabel;
    @Getter private final TextField zoomLevelField;
    @Getter private final JButton zoomOutButton;

    public PanelZoomButtons(MandelbrotContext tabCtx) {
        this.tabCtx = tabCtx;
        FlowLayout layout = new FlowLayout();
        String buttonsZoomOut = this.tabCtx.getCtx().getProperties().getMandelbrot().getView().getButtonsZoomOut();
        String buttonsZoomLabel = this.tabCtx.getCtx().getProperties().getMandelbrot().getView().getButtonsZoomLabel();
        CompoundBorder borderPanelPushButtons = this.tabCtx.getCtx().getBorder();
        this.zoomLevelFieldLabel = new JLabel(buttonsZoomLabel);
        this.zoomLevelField = new TextField("1",3);
        this.zoomOutButton = new JButton(buttonsZoomOut);
        this.setLayout(layout);
        this.setBorder(borderPanelPushButtons);
        this.add(this.zoomLevelFieldLabel);
        this.add(this.zoomLevelField);
        this.add(this.zoomOutButton);
    }
}
