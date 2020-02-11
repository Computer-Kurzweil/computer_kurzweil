package org.woehlke.computer.kurzweil.apps.evolution.widgets;

import lombok.Getter;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;

import javax.swing.*;

@Getter
public class FoodPanel extends JPanel {

    private final ComputerKurzweilApplicationContext ctx;
    private final JButton buttonFoodPerDayIncrease;
    private final JButton buttonFoodPerDayDecrease;
    private final JTextField foodPerDayField;
    private final JLabel foodPerDayLabel;

    public FoodPanel(ComputerKurzweilApplicationContext ctx) {
        this.ctx = ctx;
        String foodPerDayLabelString = this.ctx.getProperties().getEvolution().getFood().getFoodPerDayLabel();
        String foodPerDayTextFieldString = this.ctx.getProperties().getEvolution().getFood().getFoodPerDay()+"";
        int foodPerDayTextFieldCols = this.ctx.getProperties().getEvolution().getFood().getFoodPerDayFieldColumns();
        foodPerDayLabel = new JLabel(foodPerDayLabelString);
        foodPerDayField = new JTextField(
            foodPerDayTextFieldString,
            foodPerDayTextFieldCols
        );
        this.buttonFoodPerDayIncrease = new JButton(this.ctx.getProperties().getEvolution().getFood().getButtonFoodPerDayIncrease());
        this.buttonFoodPerDayDecrease = new JButton(this.ctx.getProperties().getEvolution().getFood().getButtonFoodPerDayDecrease());
        FoodPanelLayout foodPanelLayout = new FoodPanelLayout();
        this.setLayout(foodPanelLayout);
        this.setBorder(this.ctx.getBorder());
        this.add(this.foodPerDayLabel);
        this.add(this.foodPerDayField);
        this.add(this.buttonFoodPerDayIncrease);
        this.add(this.buttonFoodPerDayDecrease);
    }
}
