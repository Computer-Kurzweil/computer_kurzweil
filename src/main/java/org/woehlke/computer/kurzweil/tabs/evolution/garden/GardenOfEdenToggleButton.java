package org.woehlke.computer.kurzweil.tabs.evolution.garden;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.tabs.evolution.SimulatedEvolutionContext;

import javax.swing.*;

@Log4j2
@Getter
@ToString(callSuper = true)
public class GardenOfEdenToggleButton extends JToggleButton {

    @ToString.Exclude
    private final SimulatedEvolutionContext tabCtx;
    private final String buttonToggleGardenOfEdenString;

    public GardenOfEdenToggleButton(SimulatedEvolutionContext tabCtx) {
        super(tabCtx.getCtx().getProperties().getEvolution().getGardenOfEden().getGardenOfEdenEnabledToggleButton());
        this.tabCtx = tabCtx;
        this.buttonToggleGardenOfEdenString = this.tabCtx.getCtx().getProperties().getEvolution().getGardenOfEden().getGardenOfEdenEnabledToggleButton();
    }
}
