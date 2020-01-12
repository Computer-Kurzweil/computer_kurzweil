package org.woehlke.simulation.all.view;

import org.springframework.beans.factory.annotation.Autowired;
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
public class SimulatedEvolutionPanelSubtitle extends JPanel {

    private final JLabel subtitleLabel;

    @Deprecated
    @Autowired
    public SimulatedEvolutionPanelSubtitle(
      SimulatedEvolutionProperties simulatedEvolutionProperties
    ) {
        this.subtitleLabel = new JLabel(simulatedEvolutionProperties.getView().getSubtitle());
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);
        this.add(subtitleLabel);
    }

    public JLabel getSubtitleLabel() {
        return subtitleLabel;
    }
}
