package org.woehlke.simulation.all.view;

import lombok.Getter;
import org.woehlke.simulation.cca.model.CyclicCellularAutomatonContext;
import org.woehlke.simulation.evolution.model.SimulatedEvolutionContext;

import javax.swing.*;
import java.awt.*;

/**
 * TODO write doc.
 */
public class PanelSubtitle extends JPanel {

    @Getter
    private final JLabel subtitleLabel;

    public PanelSubtitle(String text) {
        this.subtitleLabel = new JLabel(text);
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);
        this.add(subtitleLabel);
    }

    public PanelSubtitle(CyclicCellularAutomatonContext ctx) {
        this.subtitleLabel = new JLabel(ctx.getConfig().getSubtitle());
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);
        this.add(subtitleLabel);
    }

    public PanelSubtitle(SimulatedEvolutionContext ctx) {
        this.subtitleLabel = new JLabel(ctx.getProperties().getView().getSubtitle());
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);
        this.add(subtitleLabel);
    }
}
