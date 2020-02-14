package org.woehlke.computer.kurzweil.tabs.evolution.widgets;

import lombok.Getter;
import org.woehlke.computer.kurzweil.tabs.evolution.SimulatedEvolutionContext;

import javax.swing.*;

@Getter
public class FoodPanel extends JPanel {

    private final SimulatedEvolutionContext tabCtx;
    private final JButton buttonFoodPerDayIncrease;
    private final JButton buttonFoodPerDayDecrease;
    private final JTextField foodPerDayField;
    private final JLabel foodPerDayLabel;

    public FoodPanel(SimulatedEvolutionContext tabCtx) {
        this.tabCtx = tabCtx;
        String foodPerDayLabelString = this.tabCtx.getCtx().getProperties().getEvolution().getFood().getFoodPerDayLabel();
        String foodPerDayTextFieldString = this.tabCtx.getCtx().getProperties().getEvolution().getFood().getFoodPerDay()+"";
        int foodPerDayTextFieldCols = this.tabCtx.getCtx().getProperties().getEvolution().getFood().getFoodPerDayFieldColumns();
        foodPerDayLabel = new JLabel(foodPerDayLabelString);
        foodPerDayField = new JTextField(
            foodPerDayTextFieldString,
            foodPerDayTextFieldCols
        );
        this.buttonFoodPerDayIncrease = new JButton(this.tabCtx.getCtx().getProperties().getEvolution().getFood().getButtonFoodPerDayIncrease());
        this.buttonFoodPerDayDecrease = new JButton(this.tabCtx.getCtx().getProperties().getEvolution().getFood().getButtonFoodPerDayDecrease());
        FoodPanelLayout foodPanelLayout = new FoodPanelLayout();
        this.setLayout(foodPanelLayout);
        this.setBorder(this.tabCtx.getCtx().getBorder());
        this.add(this.foodPerDayLabel);
        this.add(this.foodPerDayField);
        this.add(this.buttonFoodPerDayIncrease);
        this.add(this.buttonFoodPerDayDecrease);
    }
}
