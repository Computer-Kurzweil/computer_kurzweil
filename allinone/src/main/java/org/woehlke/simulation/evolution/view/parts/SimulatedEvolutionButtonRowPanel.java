package org.woehlke.simulation.evolution.view.parts;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.evolution.control.SimulatedEvolutionControllerThread;
import org.woehlke.simulation.evolution.model.SimulatedEvolutionContext;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.FlowLayout;

/**
 * TODO write doc.
 */
@Log
@Component
public class SimulatedEvolutionButtonRowPanel extends JPanel  {

  private final SimulatedEvolutionContext ctx;

    private final JButton buttonFoodPerDayIncrease;
    private final JButton buttonFoodPerDayDecrease;
    private final JButton buttonToggleGardenOfEden;
    private final JCheckBox gardenOfEdenEnabled;
    private final JTextField foodPerDayField;
    private final JLabel foodPerDayLabel;

  @Autowired
  public SimulatedEvolutionButtonRowPanel(
      SimulatedEvolutionContext ctx
  ) {
      this.ctx=ctx;
      foodPerDayLabel = new JLabel(this.ctx.getProperties().getFood().getFoodPerDayLabel());
        foodPerDayField = new JTextField(
            this.ctx.getProperties().getFood().getFoodPerDay()+"",
            this.ctx.getProperties().getFood().getFoodPerDayFieldColumns()
        );
        boolean selected = this.ctx.isGardenOfEdenEnabled();
        this.gardenOfEdenEnabled = new JCheckBox(this.ctx.getProperties().getGardenOfEden().getGardenOfEdenEnabledString(), selected);
        this.buttonFoodPerDayIncrease = new JButton(this.ctx.getProperties().getFood().getButtonFoodPerDayIncrease());
        this.buttonFoodPerDayDecrease = new JButton(this.ctx.getProperties().getFood().getButtonFoodPerDayDecrease());
        this.buttonToggleGardenOfEden = new JButton(this.ctx.getProperties().getGardenOfEden().getGardenOfEdenEnabledToggleButton());
      FlowLayout flowLayout2 = new FlowLayout();
      JPanel foodPanel = new JPanel(flowLayout2);
      foodPanel.setBorder(getBorder(  this.ctx.getProperties().getFood().getPanelFood()));
      foodPanel.add(this.foodPerDayLabel);
      foodPanel.add(this.foodPerDayField);
      foodPanel.add(this.buttonFoodPerDayIncrease);
      foodPanel.add(this.buttonFoodPerDayDecrease);
      FlowLayout flowLayout3 = new FlowLayout();
      JPanel gardenOfEdenPanel = new JPanel(flowLayout3);
      gardenOfEdenPanel.setBorder(getBorder(this.ctx.getProperties().getGardenOfEden().getPanelGardenOfEden()));
      gardenOfEdenPanel.add(this.gardenOfEdenEnabled);
      gardenOfEdenPanel.add(this.buttonToggleGardenOfEden);
        FlowLayout flowLayout1 = new FlowLayout();
        this.setLayout(flowLayout1);
        this.add(foodPanel);
        this.add(gardenOfEdenPanel);
  }

    private CompoundBorder getBorder(String label){
        int top = this.ctx.getProperties().getView().getBorderPadding();
        int left = this.ctx.getProperties().getView().getBorderPadding();
        int bottom = this.ctx.getProperties().getView().getBorderPadding();
        int right = this.ctx.getProperties().getView().getBorderPadding();
        return BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(label),
            BorderFactory.createEmptyBorder(top,left,bottom,right)
        );
    }

    public JButton getButtonFoodPerDayIncrease() {
        return buttonFoodPerDayIncrease;
    }

    public JButton getButtonFoodPerDayDecrease() {
        return buttonFoodPerDayDecrease;
    }

    public JButton getButtonToggleGardenOfEden() {
        return buttonToggleGardenOfEden;
    }

    public void setFoodPerDayFieldText(String text){
        this.foodPerDayField.setText(text);
    }

    public void setGardenOfEdenEnabled(boolean selected) {
        this.gardenOfEdenEnabled.setSelected(selected);
    }
}
