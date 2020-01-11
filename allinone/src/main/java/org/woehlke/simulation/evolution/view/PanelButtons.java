package org.woehlke.simulation.evolution.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.evolution.config.SimulatedEvolutionProperties;
import org.woehlke.simulation.evolution.control.ControllerThreadDesktop;
import org.woehlke.simulation.evolution.control.ObjectRegistry;

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
public class PanelButtons extends JPanel implements ActionListener {

  private final JButton buttonFoodPerDayIncrease;
  private final JButton buttonFoodPerDayDecrease;
  private final JButton buttonToggleGardenOfEden;
  private final SimulatedEvolutionProperties simulatedEvolutionProperties;
  private final ControllerThreadDesktop controllerThreadDesktop;
    private final ObjectRegistry ctx;

    private JCheckBox gardenOfEdenEnabled;
    private JTextField foodPerDayField;

    @Autowired
  public PanelButtons(ObjectRegistry ctx, SimulatedEvolutionProperties simulatedEvolutionProperties, ControllerThreadDesktop controllerThreadDesktop) {
    this.ctx=ctx;
      this.simulatedEvolutionProperties = simulatedEvolutionProperties;
        this.controllerThreadDesktop = controllerThreadDesktop;
        JLabel foodPerDayLabel = new JLabel(this.simulatedEvolutionProperties.getFoodPerDayLabel());
        foodPerDayField = new JTextField((this.simulatedEvolutionProperties.getFoodPerDay()+"", 3);
        boolean selected = this.ctx.isGardenOfEdenEnabled();
        this.gardenOfEdenEnabled = new JCheckBox("Garden of Eden enabled", selected);
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
        controllerThreadDesktop.increaseFoodPerDay();
      this.foodPerDayField.setText(ctx.getFoodPerDay()+"");
    } else if (ae.getSource() == this.buttonFoodPerDayDecrease) {
        controllerThreadDesktop.decreaseFoodPerDay();
      this.foodPerDayField.setText(ctx.getFoodPerDay()+"");
    } else if (ae.getSource() == this.buttonToggleGardenOfEden) {
        controllerThreadDesktop.toggleGardenOfEden();
      boolean selected = ctx.isGardenOfEdenEnabled();
      gardenOfEdenEnabled.setSelected(selected);
    }
  }
}
