package org.woehlke.computer.kurzweil.tabs.simulatedevolution.views;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.Updateable;
import org.woehlke.computer.kurzweil.commons.widgets.SubTab;
import org.woehlke.computer.kurzweil.commons.widgets.SubTabImpl;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolutionTab;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.config.SimulatedEvolution;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.config.SimulatedEvolutionContext;

import javax.swing.*;

@Log
@Getter
@ToString(callSuper = true)
public class SetFoodPerDayPanel extends SubTabImpl implements SimulatedEvolution, Updateable, SubTab {

    private static final long serialVersionUID = 7526471155622776147L;

    @ToString.Exclude
    private final SimulatedEvolutionContext tabCtx;
    private final JLabel foodPerDayLabel;
    private final JTextField foodPerDayInput;
    private final JButton foodPerDayIncreaseButton;
    private final JButton foodPerDayDecreaseButton;

    public SetFoodPerDayPanel(SimulatedEvolutionContext tabCtx) {
        super(
            tabCtx.getCtx().getProperties().getSimulatedevolution().getFood()
                .getFoodPerDayLabel(),tabCtx.getCtx().getProperties()
        );
        this.tabCtx = tabCtx;
        String foodPerDayLabelString = this.tabCtx.getCtx().getProperties().getSimulatedevolution().getFood().getFoodPerDayLabel();
        String labelFoodPerDayIncrease = this.tabCtx.getCtx().getProperties().getSimulatedevolution().getFood().getButtonFoodPerDayIncrease();
        String labelFoodPerDayDecrease = this.tabCtx.getCtx().getProperties().getSimulatedevolution().getFood().getButtonFoodPerDayDecrease();
        int foodPerDayTextFieldCols = this.tabCtx.getCtx().getProperties().getSimulatedevolution().getFood().getFoodPerDayFieldColumns();
        this.foodPerDayLabel = new JLabel(foodPerDayLabelString);
        this.foodPerDayInput = new JTextField("", foodPerDayTextFieldCols);
        update();
        this.foodPerDayIncreaseButton = new JButton(labelFoodPerDayIncrease);
        this.foodPerDayDecreaseButton = new JButton(labelFoodPerDayDecrease);
        this.add(this.foodPerDayLabel);
        this.add(this.foodPerDayInput);
        this.add(this.foodPerDayIncreaseButton);
        this.add(this.foodPerDayDecreaseButton);
    }

    public void addActionListener(SimulatedEvolutionTab tab) {
        this.foodPerDayIncreaseButton.addActionListener(tab);
        this.foodPerDayDecreaseButton.addActionListener(tab);
    }

    public void update() {
        int foodPerDay = tabCtx.getTabModel().getWorldParameter().getFoodPerDay();
        String foodPerDayString = ""+foodPerDay+"";
        this.foodPerDayInput.setText(foodPerDayString);
    }
}
