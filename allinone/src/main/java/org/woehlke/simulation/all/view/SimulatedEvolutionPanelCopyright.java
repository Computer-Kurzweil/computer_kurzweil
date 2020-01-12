package org.woehlke.simulation.all.view;

import org.springframework.stereotype.Component;
import org.woehlke.simulation.evolution.config.SimulatedEvolutionProperties;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;

/**
 * TODO write doc.
 */
@Deprecated
@Component
public class SimulatedEvolutionPanelCopyright extends JPanel {

  private final FlowLayout layout = new FlowLayout();

  private final JLabel copyrightLabel;

    @Deprecated
  public SimulatedEvolutionPanelCopyright(SimulatedEvolutionProperties simulatedEvolutionProperties) {
    this.copyrightLabel = new JLabel(simulatedEvolutionProperties.getView().getFooter());
    this.setLayout(this.layout);
    this.layout.setAlignment(FlowLayout.CENTER);
    this.add(this.copyrightLabel);
  }
}
