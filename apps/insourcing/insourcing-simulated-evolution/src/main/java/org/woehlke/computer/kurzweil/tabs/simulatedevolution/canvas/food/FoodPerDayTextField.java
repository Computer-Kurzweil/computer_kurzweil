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
public class FoodPerDayTextField extends JTextField implements SimulatedEvolution {

    private static final long serialVersionUID = 7526471155622776147L;

    @ToString.Exclude
    private final SimulatedEvolutionContext tabCtx;
    private final String foodPerDayTextFieldString;
    private final int foodPerDayTextFieldCols;

    public FoodPerDayTextField(SimulatedEvolutionContext tabCtx) {
        super(
            tabCtx.getCtx().getProperties().getSimulatedevolution().getFood().getFoodPerDay()+"",
            tabCtx.getCtx().getProperties().getSimulatedevolution().getFood().getFoodPerDayFieldColumns()
        );
        this.tabCtx = tabCtx;
        this.foodPerDayTextFieldString = this.tabCtx.getCtx().getProperties().getSimulatedevolution().getFood().getFoodPerDay()+"";
        this.foodPerDayTextFieldCols = this.tabCtx.getCtx().getProperties().getSimulatedevolution().getFood().getFoodPerDayFieldColumns();
    }

    public void setFoodPerDay(int foodPerDay){
        this.setText(""+foodPerDay);
    }
}
