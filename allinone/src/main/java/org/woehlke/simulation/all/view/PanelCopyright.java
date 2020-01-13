package org.woehlke.simulation.all.view;

import lombok.Getter;
import org.woehlke.simulation.cca.model.CyclicCellularAutomatonContext;
import org.woehlke.simulation.evolution.model.SimulatedEvolutionContext;

import javax.swing.*;
import java.awt.*;

public class PanelCopyright extends JPanel {

    @Getter
    private final JLabel copyrightLabel;

    public PanelCopyright(CyclicCellularAutomatonContext ctx) {
        this.copyrightLabel = new JLabel(ctx.getConfig().getCopyright());
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);
        this.add(copyrightLabel);
    }

    public PanelCopyright(SimulatedEvolutionContext ctx) {
        FlowLayout layout = new FlowLayout();
        this.copyrightLabel = new JLabel(ctx.getProperties().getView().getFooter());
        layout.setAlignment(FlowLayout.CENTER);
        this.setLayout(layout);
        this.add(this.copyrightLabel);
    }
}
