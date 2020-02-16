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
public class GardenOfEdenPanelRow extends JPanel {

    @ToString.Exclude
    private final SimulatedEvolutionContext tabCtx;
    private final GardenOfEdenCheckBox gardenOfEdenEnabled;
    private final GardenOfEdenToggleButton buttonToggleGardenOfEden;
    private final GardenOfEdenPanel gardenOfEdenPanel;

    public GardenOfEdenPanelRow(SimulatedEvolutionContext tabCtx) {
        this.tabCtx = tabCtx;
        this.gardenOfEdenEnabled = new GardenOfEdenCheckBox(this.tabCtx);
        this.buttonToggleGardenOfEden = new GardenOfEdenToggleButton(this.tabCtx);

        this.gardenOfEdenPanel = new GardenOfEdenPanel(this.tabCtx);
        this.gardenOfEdenPanel.add(this.gardenOfEdenEnabled);
        this.gardenOfEdenPanel.add(this.buttonToggleGardenOfEden);
        this.add( this.gardenOfEdenPanel);
    }
}
