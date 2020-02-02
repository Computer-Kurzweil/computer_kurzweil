package org.woehlke.computer.kurzweil.apps.evolution.view.widgets;

import lombok.Getter;
import org.woehlke.computer.kurzweil.config.ComputerKurzweilProperties;
import org.woehlke.computer.kurzweil.apps.evolution.model.SimulatedEvolutionStateService;

import javax.swing.*;
import java.awt.*;

@Getter
public class FoodPanel extends JPanel {

    private final SimulatedEvolutionStateService stateService;

    private final JButton buttonFoodPerDayIncrease;
    private final JButton buttonFoodPerDayDecrease;
    private final JTextField foodPerDayField;
    private final JLabel foodPerDayLabel;

    public FoodPanel(SimulatedEvolutionStateService stateService) {
        this.stateService = stateService;
        ComputerKurzweilProperties.Evolution cnf = this.stateService.getCtx().getProperties().getEvolution();
        String foodPerDayLabelString = cnf.getFood().getFoodPerDayLabel();
        String foodPerDayTextFieldString = cnf.getFood().getFoodPerDay()+"";
        int foodPerDayTextFieldCols = cnf.getFood().getFoodPerDayFieldColumns();
        foodPerDayLabel = new JLabel(foodPerDayLabelString);
        foodPerDayField = new JTextField(
            foodPerDayTextFieldString,
            foodPerDayTextFieldCols
        );
        //boolean selected = cnf.getGardenOfEden().getGardenOfEdenEnabled();
        //String gardenOfEdenEnabledString = cnf.getGardenOfEden().getGardenOfEdenEnabledString();
       // this.gardenOfEdenEnabled = new JCheckBox(gardenOfEdenEnabledString, selected);
        this.buttonFoodPerDayIncrease = new JButton(cnf.getFood().getButtonFoodPerDayIncrease());
        this.buttonFoodPerDayDecrease = new JButton(cnf.getFood().getButtonFoodPerDayDecrease());
        //this.buttonToggleGardenOfEden = new JButton(cnf.getGardenOfEden().getGardenOfEdenEnabledToggleButton());
        FlowLayout foodPanelLayout = new FlowLayout();
        this.setLayout(foodPanelLayout);
        this.setBorder(getBorder());
        this.add(this.foodPerDayLabel);
        this.add(this.foodPerDayField);
        this.add(this.buttonFoodPerDayIncrease);
        this.add(this.buttonFoodPerDayDecrease);
    }
}
