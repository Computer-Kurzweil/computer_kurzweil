package org.woehlke.computer.kurzweil.tabs.evolution.widgets;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.tabs.evolution.SimulatedEvolutionContext;

import javax.swing.*;

@Log
@Getter
@ToString(callSuper = true)
public class FoodPerDayIncreaseButton extends JButton {

    @ToString.Exclude
    private final SimulatedEvolutionContext tabCtx;
    private final String labelFoodPerDayIncrease;

    public FoodPerDayIncreaseButton(SimulatedEvolutionContext tabCtx) {
        super(tabCtx.getCtx().getProperties().getEvolution().getFood().getButtonFoodPerDayIncrease());
        this.tabCtx = tabCtx;
        this.labelFoodPerDayIncrease = this.tabCtx.getCtx().getProperties().getEvolution().getFood().getButtonFoodPerDayIncrease();
    }
}
