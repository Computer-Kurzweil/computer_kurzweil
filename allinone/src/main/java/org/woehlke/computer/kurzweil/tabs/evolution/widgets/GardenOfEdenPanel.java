package org.woehlke.computer.kurzweil.tabs.evolution.widgets;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.tabs.evolution.SimulatedEvolutionContext;

import javax.swing.*;
import javax.swing.border.CompoundBorder;

@Log
@Getter
@ToString(callSuper = true)
public class GardenOfEdenPanel extends JPanel {

    @ToString.Exclude
    private final SimulatedEvolutionContext tabCtx;
    //----
    private final JCheckBox gardenOfEdenEnabled;
    private final String gardenOfEdenEnabledString;
    private final boolean gardenOfEdenEnabledSelected;
    //----
    private final String buttonToggleGardenOfEdenString;
    private final JButton buttonToggleGardenOfEden;
    //----
    private final JPanel gardenOfEdenPanel;
    private final GardenOfEdenPanelLayout gardenOfEdenPanelLayout;
    private final String gardenOfEdenPanelBorderLabel;
    private final CompoundBorder gardenOfEdenPanelBorder;
    //----

    public GardenOfEdenPanel(SimulatedEvolutionContext tabCtx) {
        this.tabCtx = tabCtx;
        //----
        this.gardenOfEdenEnabledSelected = this.tabCtx.getCtx().getProperties().getEvolution().getGardenOfEden().getGardenOfEdenEnabled();
        this.gardenOfEdenEnabledString = this.tabCtx.getCtx().getProperties().getEvolution().getGardenOfEden().getGardenOfEdenEnabledString();
        this.gardenOfEdenEnabled = new JCheckBox(gardenOfEdenEnabledString, gardenOfEdenEnabledSelected);
        //----
        this.buttonToggleGardenOfEdenString = this.tabCtx.getCtx().getProperties().getEvolution().getGardenOfEden().getGardenOfEdenEnabledToggleButton();
        this.buttonToggleGardenOfEden = new JButton(buttonToggleGardenOfEdenString);
        //----
        this.gardenOfEdenPanelBorderLabel = this.tabCtx.getCtx().getProperties().getEvolution().getGardenOfEden().getPanelGardenOfEden();
        this.gardenOfEdenPanelBorder = this.tabCtx.getCtx().getBorder(gardenOfEdenPanelBorderLabel);
        this.gardenOfEdenPanelLayout = new GardenOfEdenPanelLayout();
        this.gardenOfEdenPanel = new JPanel(this.gardenOfEdenPanelLayout);
        this.gardenOfEdenPanel.setBorder(gardenOfEdenPanelBorder);
        //----
        this.gardenOfEdenPanel.add(this.gardenOfEdenEnabled);
        this.gardenOfEdenPanel.add(this.buttonToggleGardenOfEden);
        //----
        this.add( this.gardenOfEdenPanel);
    }
}
