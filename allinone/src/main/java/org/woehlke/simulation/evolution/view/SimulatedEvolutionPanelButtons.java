package org.woehlke.simulation.evolution.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.evolution.config.SimulatedEvolutionProperties;
import org.woehlke.simulation.evolution.control.SimulatedEvolutionController;
import org.woehlke.simulation.evolution.model.SimulatedEvolutionContext;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.FlowLayout;

/**
 * TODO write doc.
 */
@Component
public class SimulatedEvolutionPanelButtons extends JPanel  {


  private final SimulatedEvolutionProperties simulatedEvolutionProperties;
  private final SimulatedEvolutionContext ctx;

    private final JButton buttonFoodPerDayIncrease;
    private final JButton buttonFoodPerDayDecrease;
    private final JButton buttonToggleGardenOfEden;
    private final JCheckBox gardenOfEdenEnabled;
    private final JTextField foodPerDayField;
    private final JLabel foodPerDayLabel;

    @Autowired
  public SimulatedEvolutionPanelButtons(
      SimulatedEvolutionProperties simulatedEvolutionProperties,
      SimulatedEvolutionContext ctx
    ) {
    this.ctx=ctx;
      this.simulatedEvolutionProperties = simulatedEvolutionProperties;
      foodPerDayLabel = new JLabel(this.simulatedEvolutionProperties.getFoodPerDayLabel());
        foodPerDayField = new JTextField(
            this.simulatedEvolutionProperties.getFoodPerDay()+"",
            simulatedEvolutionProperties.getFoodPerDayFieldColumns()
        );
        boolean selected = this.ctx.isGardenOfEdenEnabled();
        this.gardenOfEdenEnabled = new JCheckBox(this.simulatedEvolutionProperties.getGardenOfEdenEnabledString(), selected);
        this.buttonFoodPerDayIncrease = new JButton(simulatedEvolutionProperties.getButtonFoodPerDayIncrease());
        this.buttonFoodPerDayDecrease = new JButton(simulatedEvolutionProperties.getButtonFoodPerDayDecrease());
        this.buttonToggleGardenOfEden = new JButton(simulatedEvolutionProperties.getGardenOfEdenEnabledString());
        FlowLayout flowLayout1 = new FlowLayout();
        this.setLayout(flowLayout1);
        this.add(getFoodPanel());
        this.add(getGardenOfEdenPanel());
  }

  public void registerController(SimulatedEvolutionController controller){
      this.buttonFoodPerDayIncrease.addActionListener(controller);
      this.buttonFoodPerDayDecrease.addActionListener(controller);
      this.buttonToggleGardenOfEden.addActionListener(controller);
  }

  private JPanel getFoodPanel(){
      FlowLayout flowLayout2 = new FlowLayout();
      JPanel foodPanel = new JPanel(flowLayout2);
      foodPanel.setBorder(getBorder(  this.simulatedEvolutionProperties.getPanelFood()));
      foodPanel.add(this.foodPerDayLabel);
      foodPanel.add(this.foodPerDayField);
      foodPanel.add(this.buttonFoodPerDayIncrease);
      foodPanel.add(this.buttonFoodPerDayDecrease);
      return foodPanel;
  }
    private JPanel getGardenOfEdenPanel(){
        FlowLayout flowLayout3 = new FlowLayout();
        JPanel gardenOfEdenPanel = new JPanel(flowLayout3);
        gardenOfEdenPanel.setBorder(getBorder(this.simulatedEvolutionProperties.getPanelGardenOfEden()));
        gardenOfEdenPanel.add(this.gardenOfEdenEnabled);
        gardenOfEdenPanel.add(this.buttonToggleGardenOfEden);
        return gardenOfEdenPanel;
    }

    private CompoundBorder getBorder(String label){
        int top = this.simulatedEvolutionProperties.getBorderPadding();
        int left = this.simulatedEvolutionProperties.getBorderPadding();
        int bottom = this.simulatedEvolutionProperties.getBorderPadding();
        int right = this.simulatedEvolutionProperties.getBorderPadding();
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

    public JCheckBox getGardenOfEdenEnabled() {
        return gardenOfEdenEnabled;
    }

    public void setFoodPerDayFieldText(String text){
        this.foodPerDayField.setText(text);
    }

    public void setGardenOfEdenEnabled(boolean selected) {
        this.gardenOfEdenEnabled.setSelected(selected);
    }
}
