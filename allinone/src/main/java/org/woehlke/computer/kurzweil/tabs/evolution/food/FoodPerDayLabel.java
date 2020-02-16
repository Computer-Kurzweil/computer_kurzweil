package org.woehlke.computer.kurzweil.tabs.evolution.food;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.tabs.evolution.SimulatedEvolutionContext;

import javax.swing.*;

@Log
@Getter
@ToString(callSuper = true)
public class FoodPerDayLabel extends JLabel {

    @ToString.Exclude
    private final SimulatedEvolutionContext tabCtx;
    private final String foodPerDayLabelString;

    public FoodPerDayLabel(SimulatedEvolutionContext tabCtx) {
        super(tabCtx.getCtx().getProperties().getEvolution().getFood().getFoodPerDayLabel());
        this.tabCtx = tabCtx;
        this.foodPerDayLabelString = this.tabCtx.getCtx().getProperties().getEvolution().getFood().getFoodPerDayLabel();
    }
}
