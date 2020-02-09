package org.woehlke.computer.kurzweil.apps.evolution.view.widgets;

import lombok.Getter;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilProperties;
import org.woehlke.computer.kurzweil.apps.evolution.model.SimulatedEvolutionStateService;

import javax.swing.*;
import java.awt.*;

@Getter
public class GardenOfEdenPanel extends JPanel {

    private final SimulatedEvolutionStateService stateService;

    private final JButton buttonToggleGardenOfEden;
    private final JCheckBox gardenOfEdenEnabled;

    public GardenOfEdenPanel(SimulatedEvolutionStateService stateService) {
        this.stateService = stateService;
        ComputerKurzweilProperties.Evolution cnf = this.stateService.getCtx().getProperties().getEvolution();
        boolean selected = cnf.getGardenOfEden().getGardenOfEdenEnabled();
        String gardenOfEdenEnabledString = cnf.getGardenOfEden().getGardenOfEdenEnabledString();
        this.gardenOfEdenEnabled = new JCheckBox(gardenOfEdenEnabledString, selected);
        this.buttonToggleGardenOfEden = new JButton(cnf.getGardenOfEden().getGardenOfEdenEnabledToggleButton());
        FlowLayout gardenOfEdenPanelLayout = new FlowLayout();
        JPanel gardenOfEdenPanel = new JPanel(gardenOfEdenPanelLayout);
        String label = this.stateService.getCtx().getProperties().getEvolution().getGardenOfEden().getPanelGardenOfEden();
        gardenOfEdenPanel.setBorder(this.stateService.getCtx().getBorder(label));
        gardenOfEdenPanel.add(this.gardenOfEdenEnabled);
        gardenOfEdenPanel.add(this.buttonToggleGardenOfEden);
    }
}
