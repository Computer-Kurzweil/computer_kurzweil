package org.woehlke.computer.kurzweil.tabs.evolution.widgets;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.tabs.evolution.SimulatedEvolutionContext;

import javax.swing.*;

@Log
@Getter
@ToString(callSuper = true)
public class GardenOfEdenCheckBox extends JCheckBox {

    @ToString.Exclude
    private final SimulatedEvolutionContext tabCtx;
    private final String gardenOfEdenEnabledString;
    private final boolean gardenOfEdenEnabledSelected;

    public GardenOfEdenCheckBox(SimulatedEvolutionContext tabCtx) {
        super(
            tabCtx.getCtx().getProperties().getEvolution().getGardenOfEden().getGardenOfEdenEnabledString(),
            tabCtx.getCtx().getProperties().getEvolution().getGardenOfEden().getGardenOfEdenEnabled()
        );
        this.tabCtx = tabCtx;
        this.gardenOfEdenEnabledSelected = tabCtx.getCtx().getProperties().getEvolution().getGardenOfEden().getGardenOfEdenEnabled();
        this.gardenOfEdenEnabledString = tabCtx.getCtx().getProperties().getEvolution().getGardenOfEden().getGardenOfEdenEnabledString();
    }
}
