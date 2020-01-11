package org.woehlke.simulation.evolution.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.evolution.config.SimulatedEvolutionProperties;
import org.woehlke.simulation.evolution.control.SimulatedEvolutionController;
import org.woehlke.simulation.evolution.control.SimulatedEvolutionContext;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TODO write doc.
 */
@Component
public class SimulatedEvolutionPanelButtons extends JPanel implements ActionListener {


  private final SimulatedEvolutionProperties simulatedEvolutionProperties;
  private final SimulatedEvolutionController simulatedEvolutionController;
  private final SimulatedEvolutionContext ctx;

    private final JButton buttonFoodPerDayIncrease;
    private final JButton buttonFoodPerDayDecrease;
    private final JButton buttonToggleGardenOfEden;
    private JCheckBox gardenOfEdenEnabled;
    private JTextField foodPerDayField;

    @Autowired
  public SimulatedEvolutionPanelButtons(SimulatedEvolutionProperties simulatedEvolutionProperties, SimulatedEvolutionController simulatedEvolutionController, SimulatedEvolutionContext ctx) {
    this.ctx=ctx;
      this.simulatedEvolutionProperties = simulatedEvolutionProperties;
        this.simulatedEvolutionController = simulatedEvolutionController;
        JLabel foodPerDayLabel = new JLabel(this.simulatedEvolutionProperties.getFoodPerDayLabel());
        foodPerDayField = new JTextField(this.simulatedEvolutionProperties.getFoodPerDay()+"", simulatedEvolutionProperties.getFoodPerDayFieldColumns());
        boolean selected = this.ctx.isGardenOfEdenEnabled();
        this.gardenOfEdenEnabled = new JCheckBox(this.simulatedEvolutionProperties.getGardenOfEdenEnabledString(), selected);
        this.buttonFoodPerDayIncrease = new JButton(simulatedEvolutionProperties.getButtonFoodPerDayIncrease());
        this.buttonFoodPerDayDecrease = new JButton(simulatedEvolutionProperties.getButtonFoodPerDayDecrease());
        this.buttonToggleGardenOfEden = new JButton(simulatedEvolutionProperties.getGardenOfEdenEnabledString());
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);
        this.add(foodPerDayLabel);
        this.add(foodPerDayField);
        this.add(this.buttonFoodPerDayIncrease);
        this.add(this.buttonFoodPerDayDecrease);
        this.add(gardenOfEdenEnabled);
        this.add(this.buttonToggleGardenOfEden);
        this.buttonFoodPerDayIncrease.addActionListener(this);
        this.buttonFoodPerDayDecrease.addActionListener(this);
        this.buttonToggleGardenOfEden.addActionListener(this);
  }

  /**
   * TODO write doc.
   */
  @Override
  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == this.buttonFoodPerDayIncrease) {
        simulatedEvolutionController.increaseFoodPerDay();
      this.foodPerDayField.setText(ctx.getFoodPerDay()+"");
    } else if (ae.getSource() == this.buttonFoodPerDayDecrease) {
        simulatedEvolutionController.decreaseFoodPerDay();
      this.foodPerDayField.setText(ctx.getFoodPerDay()+"");
    } else if (ae.getSource() == this.buttonToggleGardenOfEden) {
        simulatedEvolutionController.toggleGardenOfEden();
      boolean selected = ctx.isGardenOfEdenEnabled();
      gardenOfEdenEnabled.setSelected(selected);
    }
  }
}
