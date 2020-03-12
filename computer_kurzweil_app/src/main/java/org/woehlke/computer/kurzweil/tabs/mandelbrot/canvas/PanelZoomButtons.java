package org.woehlke.computer.kurzweil.tabs.mandelbrot.canvas;

import lombok.Getter;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.Mandelbrot;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.MandelbrotContext;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.MandelbrotTab;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

@Getter
public class PanelZoomButtons extends JPanel implements Mandelbrot {

    private final MandelbrotContext tabCtx;

    private final JLabel zoomLevelFieldLabel;
    private final TextField zoomLevelField;
    private final JButton zoomOutButton;

    public PanelZoomButtons(MandelbrotContext tabCtx) {
        this.tabCtx = tabCtx;
        FlowLayout layout = new FlowLayout();
        String buttonsZoomOut = this.tabCtx.getCtx().getProperties().getMandelbrot().getView().getButtonsZoomOut();
        String buttonsZoomLabel = this.tabCtx.getCtx().getProperties().getMandelbrot().getView().getButtonsZoomLabel();
        CompoundBorder borderPanelPushButtons = this.tabCtx.getCtx().getBorder(buttonsZoomLabel);
        this.zoomLevelFieldLabel = new JLabel(buttonsZoomLabel);
        this.zoomLevelField = new TextField("1",3);
        this.zoomOutButton = new JButton(buttonsZoomOut);
        this.setLayout(layout);
        this.setBorder(borderPanelPushButtons);
        //this.add(this.zoomLevelFieldLabel);
        this.add(this.zoomLevelField);
        this.add(this.zoomOutButton);
    }

    public void addActionListener(MandelbrotTab tab) {
        this.getZoomOutButton().addActionListener(tab);
    }
}
