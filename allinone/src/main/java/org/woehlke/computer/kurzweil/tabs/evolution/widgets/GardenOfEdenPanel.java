package org.woehlke.computer.kurzweil.tabs.evolution.widgets;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.tabs.evolution.SimulatedEvolutionContext;
import org.woehlke.computer.kurzweil.tabs.evolution.widgets.layouts.GardenOfEdenPanelLayout;

import javax.swing.*;
import javax.swing.border.CompoundBorder;

@Log
@Getter
@ToString(callSuper = true)
public class GardenOfEdenPanel extends JPanel {

    @ToString.Exclude
    private final SimulatedEvolutionContext tabCtx;
    private final String gardenOfEdenPanelBorderLabel;
    private final CompoundBorder gardenOfEdenPanelBorder;

    public GardenOfEdenPanel(SimulatedEvolutionContext tabCtx) {
        super(new GardenOfEdenPanelLayout());
        this.tabCtx=tabCtx;
        this.gardenOfEdenPanelBorderLabel = tabCtx.getCtx().getProperties().getEvolution().getGardenOfEden().getPanelGardenOfEden();
        this.gardenOfEdenPanelBorder = this.tabCtx.getCtx().getBorder(gardenOfEdenPanelBorderLabel);
        this.setBorder(gardenOfEdenPanelBorder);
    }
}
