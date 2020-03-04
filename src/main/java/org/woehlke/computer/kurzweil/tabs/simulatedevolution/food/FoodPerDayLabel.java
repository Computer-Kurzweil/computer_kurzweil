package org.woehlke.computer.kurzweil.tabs.simulatedevolution.food;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolutionContext;

import javax.swing.*;

@Log4j2
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
