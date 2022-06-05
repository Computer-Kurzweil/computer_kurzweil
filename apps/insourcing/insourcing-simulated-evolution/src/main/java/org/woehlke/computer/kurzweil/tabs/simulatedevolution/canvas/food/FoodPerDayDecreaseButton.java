package org.woehlke.computer.kurzweil.tabs.simulatedevolution.canvas.food;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolution;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolutionContext;

import javax.swing.*;

@Log
@Getter
@ToString(callSuper = true)
public class FoodPerDayDecreaseButton extends JButton implements SimulatedEvolution {

    private static final long serialVersionUID = 7526471155622776147L;

    @ToString.Exclude
    private final SimulatedEvolutionContext tabCtx;
    private final String labelFoodPerDayDecrease;

    public FoodPerDayDecreaseButton(SimulatedEvolutionContext tabCtx) {
        super(tabCtx.getCtx().getProperties().getSimulatedevolution().getFood().getButtonFoodPerDayDecrease());
        this.tabCtx = tabCtx;
        this.labelFoodPerDayDecrease = this.tabCtx.getCtx().getProperties().getSimulatedevolution().getFood().getButtonFoodPerDayDecrease();
    }
}
