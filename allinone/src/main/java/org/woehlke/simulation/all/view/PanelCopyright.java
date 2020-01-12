package org.woehlke.simulation.all.view;

import lombok.Getter;
import org.woehlke.simulation.evolution.config.SimulatedEvolutionProperties;

import javax.swing.*;
import java.awt.*;

public class PanelCopyright extends JPanel {

    @Getter
    private final JLabel copyrightLabel;

    public PanelCopyright(SimulatedEvolutionProperties simulatedEvolutionProperties) {
        FlowLayout layout = new FlowLayout();
        this.copyrightLabel = new JLabel(simulatedEvolutionProperties.getView().getFooter());
        layout.setAlignment(FlowLayout.CENTER);
        this.setLayout(layout);
        this.add(this.copyrightLabel);
    }
}
