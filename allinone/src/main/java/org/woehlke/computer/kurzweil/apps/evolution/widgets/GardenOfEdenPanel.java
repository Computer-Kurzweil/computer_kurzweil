package org.woehlke.computer.kurzweil.apps.evolution.widgets;

import lombok.Getter;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;

import javax.swing.*;

@Getter
public class GardenOfEdenPanel extends JPanel {

    private final ComputerKurzweilApplicationContext ctx;
    private final JButton buttonToggleGardenOfEden;
    private final JCheckBox gardenOfEdenEnabled;
    private final GardenOfEdenPanelLayout gardenOfEdenPanelLayout;
    private final JPanel gardenOfEdenPanel;

    public GardenOfEdenPanel(ComputerKurzweilApplicationContext ctx) {
        this.ctx = ctx;
        boolean selected = this.ctx.getProperties().getEvolution().getGardenOfEden().getGardenOfEdenEnabled();
        String gardenOfEdenEnabledString = this.ctx.getProperties().getEvolution().getGardenOfEden().getGardenOfEdenEnabledString();
        String label = this.ctx.getProperties().getEvolution().getGardenOfEden().getPanelGardenOfEden();
        this.gardenOfEdenEnabled = new JCheckBox(gardenOfEdenEnabledString, selected);
        this.buttonToggleGardenOfEden = new JButton(this.ctx.getProperties().getEvolution().getGardenOfEden().getGardenOfEdenEnabledToggleButton());
        this.gardenOfEdenPanelLayout = new GardenOfEdenPanelLayout();
        this.gardenOfEdenPanel = new JPanel(this.gardenOfEdenPanelLayout);
        this.gardenOfEdenPanel.setBorder(this.ctx.getBorder(label));
        this.gardenOfEdenPanel.add(this.gardenOfEdenEnabled);
        this.gardenOfEdenPanel.add(this.buttonToggleGardenOfEden);
    }
}
