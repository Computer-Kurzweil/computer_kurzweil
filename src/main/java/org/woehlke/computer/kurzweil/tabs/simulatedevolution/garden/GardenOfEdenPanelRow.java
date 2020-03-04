package org.woehlke.computer.kurzweil.tabs.simulatedevolution.garden;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolution;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolutionContext;

import javax.swing.*;

@Log4j2
@Getter
@ToString(callSuper = true)
public class GardenOfEdenPanelRow extends JPanel implements SimulatedEvolution {

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

    public void setSelected(boolean selected) {
        this.gardenOfEdenEnabled.setSelected(selected);
    }

    public void toggleGardenOfEden() {
        boolean selected = buttonToggleGardenOfEden.isSelected();
        this.buttonToggleGardenOfEden.setSelected(!selected);
    }
}
