package org.woehlke.computer.kurzweil.tabs.mandelbrotzoom.canvas;

import lombok.Getter;
import org.woehlke.computer.kurzweil.commons.widgets.SubTabImpl;
import org.woehlke.computer.kurzweil.tabs.mandelbrot2julia.Mandelbrot;
import org.woehlke.computer.kurzweil.tabs.mandelbrot2julia.MandelbrotContext;

import javax.swing.*;
import java.awt.*;

@Getter
public class PanelZoom extends SubTabImpl implements Mandelbrot {

    private static final long serialVersionUID = 7526471155622776147L;

    private final MandelbrotContext tabCtx;

    private final JLabel zoomLevelFieldLabel;
    private final TextField zoomLevelField;
    private final JButton zoomOutButton;

    public PanelZoom(MandelbrotContext tabCtx) {
        super(
            tabCtx.getCtx().getProperties().getMandelbrot().getView().getButtonsZoomLabel(),
            tabCtx.getCtx().getProperties()
        );
        this.tabCtx = tabCtx;
        String buttonsZoomOut = this.tabCtx.getCtx().getProperties().getMandelbrot().getView().getButtonsZoomOut();
        this.zoomLevelFieldLabel = new JLabel(super.getTitle());
        this.zoomLevelField = new TextField("1",3);
        this.zoomOutButton = new JButton(buttonsZoomOut);
        this.add(this.zoomLevelFieldLabel);
        this.add(this.zoomLevelField);
        this.add(this.zoomOutButton);
        this.zoomOutButton.addActionListener(tabCtx.getTab());
    }

}
