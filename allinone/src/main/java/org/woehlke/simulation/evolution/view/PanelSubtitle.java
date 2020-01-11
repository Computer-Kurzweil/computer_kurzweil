package org.woehlke.simulation.evolution.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.evolution.config.SimulatedEvolutionProperties;
import org.woehlke.simulation.evolution.control.ObjectRegistry;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;

/**
 * TODO write doc.
 */
@Component
public class PanelSubtitle extends JPanel {

  private final JLabel subtitleLabel;

  @Autowired
  public PanelSubtitle(SimulatedEvolutionProperties simulatedEvolutionProperties) {
    this.subtitleLabel = new JLabel(simulatedEvolutionProperties.getSubtitle());
    FlowLayout layout = new FlowLayout();
    this.setLayout(layout);
    this.add(subtitleLabel);
  }

}
