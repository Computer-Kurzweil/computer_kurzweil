package org.woehlke.computer.kurzweil.tabs.evolution.widgets;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.tabs.evolution.SimulatedEvolutionContext;

import javax.swing.*;
import javax.swing.border.CompoundBorder;

@Log
@Getter
@ToString(callSuper = true)
public class FoodPanel extends JPanel {

    @ToString.Exclude
    private final SimulatedEvolutionContext tabCtx;
    private final JButton buttonFoodPerDayIncrease;
    private final JButton buttonFoodPerDayDecrease;
    private final JTextField foodPerDayField;
    private final JLabel foodPerDayLabel;

    private final String foodPerDayLabelString;
    private final String foodPerDayTextFieldString;
    private final int foodPerDayTextFieldCols;
    private final String labelFoodPerDayIncrease;
    private final String labelFoodPerDayDecrease;
    private final FoodPanelLayout foodPanelLayout;
    private final CompoundBorder border;

    public FoodPanel(SimulatedEvolutionContext tabCtx) {
        this.tabCtx = tabCtx;
        this.foodPerDayLabelString = this.tabCtx.getCtx().getProperties().getEvolution().getFood().getFoodPerDayLabel();
        this.foodPerDayTextFieldString = this.tabCtx.getCtx().getProperties().getEvolution().getFood().getFoodPerDay()+"";
        this.foodPerDayTextFieldCols = this.tabCtx.getCtx().getProperties().getEvolution().getFood().getFoodPerDayFieldColumns();
        this.labelFoodPerDayIncrease = this.tabCtx.getCtx().getProperties().getEvolution().getFood().getButtonFoodPerDayIncrease();
        this.labelFoodPerDayDecrease = this.tabCtx.getCtx().getProperties().getEvolution().getFood().getButtonFoodPerDayDecrease();
        this.foodPanelLayout = new FoodPanelLayout();
        this.border = this.tabCtx.getCtx().getBorder();
        this.foodPerDayLabel = new JLabel(this.foodPerDayLabelString);
        this.foodPerDayField = new JTextField(foodPerDayTextFieldString, foodPerDayTextFieldCols);
        this.buttonFoodPerDayIncrease = new JButton(this.labelFoodPerDayIncrease);
        this.buttonFoodPerDayDecrease = new JButton(this.labelFoodPerDayDecrease);
        this.setLayout(this.foodPanelLayout);
        this.setBorder(this.border);
        this.add(this.foodPerDayLabel);
        this.add(this.foodPerDayField);
        this.add(this.buttonFoodPerDayIncrease);
        this.add(this.buttonFoodPerDayDecrease);
    }
}
