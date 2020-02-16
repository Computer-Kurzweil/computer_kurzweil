package org.woehlke.computer.kurzweil.tabs.evolution.widgets;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.tabs.evolution.SimulatedEvolutionContext;

import javax.swing.*;

@Log
@Getter
@ToString(callSuper = true)
public class FoodPerDayTextField extends JTextField {

    @ToString.Exclude
    private final SimulatedEvolutionContext tabCtx;
    private final String foodPerDayTextFieldString;
    private final int foodPerDayTextFieldCols;

    public FoodPerDayTextField(SimulatedEvolutionContext tabCtx) {
        super(
            tabCtx.getCtx().getProperties().getEvolution().getFood().getFoodPerDay()+"",
            tabCtx.getCtx().getProperties().getEvolution().getFood().getFoodPerDayFieldColumns()
        );
        this.tabCtx = tabCtx;
        this.foodPerDayTextFieldString = this.tabCtx.getCtx().getProperties().getEvolution().getFood().getFoodPerDay()+"";
        this.foodPerDayTextFieldCols = this.tabCtx.getCtx().getProperties().getEvolution().getFood().getFoodPerDayFieldColumns();
    }

    public void setFoodPerDay(int foodPerDay){
        this.setText(""+foodPerDay);
    }
}
