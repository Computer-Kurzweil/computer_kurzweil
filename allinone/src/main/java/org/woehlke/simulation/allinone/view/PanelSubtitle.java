package org.woehlke.simulation.allinone.view;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.simulation.cca.model.CyclicCellularAutomatonContext;
import org.woehlke.simulation.evolution.model.SimulatedEvolutionContext;

import javax.swing.*;
import java.awt.*;

/**
 * TODO write doc.
 */
@Log
@ToString
@EqualsAndHashCode
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
