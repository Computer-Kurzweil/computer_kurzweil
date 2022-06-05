package org.woehlke.computer.kurzweil.tabs.simulatedevolution.views;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.Updateable;
import org.woehlke.computer.kurzweil.commons.widgets.SubTab;
import org.woehlke.computer.kurzweil.commons.widgets.SubTabImpl;
import org.woehlke.computer.kurzweil.tabs.TabPanel;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolutionTab;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.config.SimulatedEvolution;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.config.SimulatedEvolutionContext;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.SimulatedEvolutionModel;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.views.food.FoodPerDayDecreaseButton;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.views.food.FoodPerDayIncreaseButton;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.views.food.FoodPerDayLabel;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.views.food.FoodPerDayTextField;

@Log
@Getter
@ToString(callSuper = true)
public class FoodPerDayPanel extends SubTabImpl implements SimulatedEvolution, Updateable, SubTab {

    private static final long serialVersionUID = 7526471155622776147L;

    @ToString.Exclude
    private final SimulatedEvolutionContext tabCtx;
    private final String foodPerDayBorderLabel;
    private final FoodPerDayLabel foodPerDayLabel;
    private final FoodPerDayTextField foodPerDayTextField;
    private final FoodPerDayIncreaseButton foodPerDayIncreaseButton;
    private final FoodPerDayDecreaseButton foodPerDayDecreaseButton;

    public FoodPerDayPanel(SimulatedEvolutionContext tabCtx) {
        super(
            tabCtx.getCtx().getProperties().getSimulatedevolution().getFood()
                .getFoodPerDayLabel(),tabCtx.getCtx().getProperties()
        );
        this.tabCtx = tabCtx;
        this.foodPerDayLabel = new FoodPerDayLabel(this.tabCtx);
        this.foodPerDayTextField = new FoodPerDayTextField(this.tabCtx);
        this.foodPerDayIncreaseButton = new FoodPerDayIncreaseButton(this.tabCtx);
        this.foodPerDayDecreaseButton = new FoodPerDayDecreaseButton(this.tabCtx);
        this.foodPerDayBorderLabel = this.tabCtx.getCtx().getProperties()
            .getSimulatedevolution().getFood().getFoodPerDayLabel();
        //this.foodPerDayBorder = this.tabCtx.getCtx().getBorder(this.foodPerDayBorderLabel);
        //this.setBorder(this.foodPerDayBorder);
        this.add(this.foodPerDayLabel);
        this.add(this.foodPerDayTextField);
        this.add(this.foodPerDayIncreaseButton);
        this.add(this.foodPerDayDecreaseButton);
    }

    public void addActionListener(SimulatedEvolutionTab tab) {
        this.foodPerDayIncreaseButton.addActionListener(tab);
        this.foodPerDayDecreaseButton.addActionListener(tab);
    }

    public void update() {
        int foodPerDay = tabCtx.getTabModel().getWorldParameter().getFoodPerDay();
        this.foodPerDayTextField.setFoodPerDay(foodPerDay);
    }
}
