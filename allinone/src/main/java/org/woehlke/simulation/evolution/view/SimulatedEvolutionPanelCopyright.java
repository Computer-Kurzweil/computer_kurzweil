package org.woehlke.simulation.evolution.view;

import org.springframework.stereotype.Component;
import org.woehlke.simulation.evolution.config.SimulatedEvolutionProperties;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;

/**
 * TODO write doc.
 */

@Component
public class SimulatedEvolutionPanelCopyright extends JPanel {

  private final FlowLayout layout = new FlowLayout();

  private final JLabel copyrightLabel;

  public SimulatedEvolutionPanelCopyright(SimulatedEvolutionProperties simulatedEvolutionProperties) {
    this.copyrightLabel = new JLabel(simulatedEvolutionProperties.getFooter());
    this.setLayout(this.layout);
    this.layout.setAlignment(FlowLayout.CENTER);
    this.add(this.copyrightLabel);
  }
}
