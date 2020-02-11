package org.woehlke.computer.kurzweil.tabs.evolution.widgets;

import lombok.Getter;
import org.woehlke.computer.kurzweil.tabs.evolution.SimulatedEvolutionContext;

import javax.swing.*;

@Getter
public class GardenOfEdenPanel extends JPanel {

    private final SimulatedEvolutionContext tabCtx;
    private final JButton buttonToggleGardenOfEden;
    private final JCheckBox gardenOfEdenEnabled;
    private final GardenOfEdenPanelLayout gardenOfEdenPanelLayout;
    private final JPanel gardenOfEdenPanel;

    public GardenOfEdenPanel(SimulatedEvolutionContext tabCtx) {
        this.tabCtx = tabCtx;
        boolean selected = this.tabCtx.getCtx().getProperties().getEvolution().getGardenOfEden().getGardenOfEdenEnabled();
        String gardenOfEdenEnabledString = this.tabCtx.getCtx().getProperties().getEvolution().getGardenOfEden().getGardenOfEdenEnabledString();
        String label = this.tabCtx.getCtx().getProperties().getEvolution().getGardenOfEden().getPanelGardenOfEden();
        this.gardenOfEdenEnabled = new JCheckBox(gardenOfEdenEnabledString, selected);
        this.buttonToggleGardenOfEden = new JButton(this.tabCtx.getCtx().getProperties().getEvolution().getGardenOfEden().getGardenOfEdenEnabledToggleButton());
        this.gardenOfEdenPanelLayout = new GardenOfEdenPanelLayout();
        this.gardenOfEdenPanel = new JPanel(this.gardenOfEdenPanelLayout);
        this.gardenOfEdenPanel.setBorder(this.tabCtx.getCtx().getBorder(label));
        this.gardenOfEdenPanel.add(this.gardenOfEdenEnabled);
        this.gardenOfEdenPanel.add(this.buttonToggleGardenOfEden);
    }
}
