package org.woehlke.simulation.evolution.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.evolution.config.SimulatedEvolutionProperties;
import org.woehlke.simulation.evolution.control.SimulatedEvolutionController;
import org.woehlke.simulation.evolution.model.SimulatedEvolutionContext;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);
        this.add(this.foodPerDayLabel);
        this.add(this.foodPerDayField);
        this.add(this.buttonFoodPerDayIncrease);
        this.add(this.buttonFoodPerDayDecrease);
        this.add(this.gardenOfEdenEnabled);
        this.add(this.buttonToggleGardenOfEden);
  }

  public void registerController(SimulatedEvolutionController controller){
      this.buttonFoodPerDayIncrease.addActionListener(controller);
      this.buttonFoodPerDayDecrease.addActionListener(controller);
      this.buttonToggleGardenOfEden.addActionListener(controller);
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
