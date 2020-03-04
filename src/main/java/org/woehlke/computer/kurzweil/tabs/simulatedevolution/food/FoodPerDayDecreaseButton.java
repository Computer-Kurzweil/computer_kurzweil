package org.woehlke.computer.kurzweil.tabs.simulatedevolution.food;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolutionContext;

import javax.swing.*;

@Log4j2
@Getter
@ToString(callSuper = true)
public class FoodPerDayDecreaseButton extends JButton {

    @ToString.Exclude
    private final SimulatedEvolutionContext tabCtx;
    private final String labelFoodPerDayDecrease;

    public FoodPerDayDecreaseButton(SimulatedEvolutionContext tabCtx) {
        super(tabCtx.getCtx().getProperties().getEvolution().getFood().getButtonFoodPerDayDecrease());
        this.tabCtx = tabCtx;
        this.labelFoodPerDayDecrease = this.tabCtx.getCtx().getProperties().getEvolution().getFood().getButtonFoodPerDayDecrease();
    }
}
