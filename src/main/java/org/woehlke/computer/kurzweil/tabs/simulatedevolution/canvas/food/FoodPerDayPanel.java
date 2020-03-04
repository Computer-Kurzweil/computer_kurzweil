package org.woehlke.computer.kurzweil.tabs.simulatedevolution.canvas.food;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.Updateable;
import org.woehlke.computer.kurzweil.tabs.TabPanel;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolution;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolutionContext;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolutionModel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

@Log4j2
@Getter
@ToString(callSuper = true)
public class FoodPerDayPanel extends JPanel implements SimulatedEvolution, Updateable {

    @ToString.Exclude
    private final SimulatedEvolutionContext tabCtx;
    @ToString.Exclude
    private final CompoundBorder foodPerDayBorder;
    private final String foodPerDayBorderLabel;
    private final FoodPerDayLabel foodPerDayLabel;
    private final FoodPerDayTextField foodPerDayTextField;
    private final FoodPerDayIncreaseButton foodPerDayIncreaseButton;
    private final FoodPerDayDecreaseButton foodPerDayDecreaseButton;
    private final SimulatedEvolutionModel tabModel;

    public FoodPerDayPanel(SimulatedEvolutionContext tabCtx) {
        super(new FlowLayout());
        this.tabCtx = tabCtx;
        this.tabModel = this.tabCtx.getTabModel();
        this.foodPerDayLabel = new FoodPerDayLabel(this.tabCtx);
        this.foodPerDayTextField = new FoodPerDayTextField(this.tabCtx);
        this.foodPerDayIncreaseButton = new FoodPerDayIncreaseButton(this.tabCtx);
        this.foodPerDayDecreaseButton = new FoodPerDayDecreaseButton(this.tabCtx);
        this.foodPerDayBorderLabel = this.tabCtx.getCtx().getProperties().getSimulatedevolution().getFood().getFoodPerDayLabel();
        this.foodPerDayBorder = this.tabCtx.getCtx().getBorder(this.foodPerDayBorderLabel);
        this.setBorder(this.foodPerDayBorder);
        this.add(this.foodPerDayLabel);
        this.add(this.foodPerDayTextField);
        this.add(this.foodPerDayIncreaseButton);
        this.add(this.foodPerDayDecreaseButton);
    }
    
    public void addActionListener(TabPanel myTabPanel) {
        this.foodPerDayIncreaseButton.addActionListener(myTabPanel);
        this.foodPerDayDecreaseButton.addActionListener(myTabPanel);
    }

    @Override
    public void update() {
        int foodPerDay = tabModel.getSimulatedEvolutionParameter().getFoodPerDay();
        this.foodPerDayTextField.setFoodPerDay(foodPerDay);
    }
}
