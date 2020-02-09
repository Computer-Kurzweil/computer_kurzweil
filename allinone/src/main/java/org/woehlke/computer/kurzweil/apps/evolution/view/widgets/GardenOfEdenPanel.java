package org.woehlke.computer.kurzweil.apps.evolution.view.widgets;

import lombok.Getter;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilProperties;

import javax.swing.*;
import java.awt.*;

@Getter
public class GardenOfEdenPanel extends JPanel {

    private final ComputerKurzweilApplicationContext ctx;
    private final JButton buttonToggleGardenOfEden;
    private final JCheckBox gardenOfEdenEnabled;

    public GardenOfEdenPanel(ComputerKurzweilApplicationContext ctx) {
        this.ctx = ctx;
        ComputerKurzweilProperties.Evolution cnf = this.ctx.getProperties().getEvolution();
        boolean selected = cnf.getGardenOfEden().getGardenOfEdenEnabled();
        String gardenOfEdenEnabledString = cnf.getGardenOfEden().getGardenOfEdenEnabledString();
        this.gardenOfEdenEnabled = new JCheckBox(gardenOfEdenEnabledString, selected);
        this.buttonToggleGardenOfEden = new JButton(cnf.getGardenOfEden().getGardenOfEdenEnabledToggleButton());
        FlowLayout gardenOfEdenPanelLayout = new FlowLayout();
        JPanel gardenOfEdenPanel = new JPanel(gardenOfEdenPanelLayout);
        String label = this.ctx.getProperties().getEvolution().getGardenOfEden().getPanelGardenOfEden();
        gardenOfEdenPanel.setBorder(this.ctx.getBorder(label));
        gardenOfEdenPanel.add(this.gardenOfEdenEnabled);
        gardenOfEdenPanel.add(this.buttonToggleGardenOfEden);
    }
}
