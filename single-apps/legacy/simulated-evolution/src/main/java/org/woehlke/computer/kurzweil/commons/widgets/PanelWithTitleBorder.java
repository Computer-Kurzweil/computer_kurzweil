package org.woehlke.computer.kurzweil.commons.widgets;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilProperties;
import org.woehlke.computer.kurzweil.commons.layouts.FlowLayoutCenter;

import javax.swing.*;
import javax.swing.border.CompoundBorder;

@Log
@Getter
public class PanelWithTitleBorder extends JPanel {

    private static final long serialVersionUID = 7526471155622776147L;

    private final FlowLayoutCenter panelStartStopButtonsLayout;
    private final CompoundBorder panelStartStopButtonsBorder;

    public PanelWithTitleBorder(String label, ComputerKurzweilProperties p) {
        panelStartStopButtonsLayout = new FlowLayoutCenter();
        int top = p.getAllinone().getView().getBorderPaddingY();
        int left = p.getAllinone().getView().getBorderPaddingX();
        int bottom = p.getAllinone().getView().getBorderPaddingY();
        int right = p.getAllinone().getView().getBorderPaddingX();
        panelStartStopButtonsBorder = BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(label),
            BorderFactory.createEmptyBorder(top,left,bottom,right)
        );
        this.setLayout(panelStartStopButtonsLayout);
        this.setBorder(panelStartStopButtonsBorder);
    }
}
