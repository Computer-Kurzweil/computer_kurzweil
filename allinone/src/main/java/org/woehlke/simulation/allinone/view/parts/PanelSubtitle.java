package org.woehlke.simulation.allinone.view.parts;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.simulation.cca.model.CyclicCellularAutomatonContext;
import org.woehlke.simulation.evolution.model.SimulatedEvolutionContext;
import org.woehlke.simulation.mandelbrot.model.MandelbrotContext;

import javax.swing.*;
import java.awt.*;


@Log
@ToString
@EqualsAndHashCode(callSuper=true)
public class PanelSubtitle extends JPanel {

    @Getter
    private final JLabel subtitleLabel;

    public PanelSubtitle(String text) {
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);
        this.subtitleLabel = new JLabel(text);
        this.add(subtitleLabel);
    }

    public PanelSubtitle(CyclicCellularAutomatonContext ctx) {
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);
        this.subtitleLabel = new JLabel(ctx.getProperties().getSubtitle());
        this.add(subtitleLabel);
    }

    public PanelSubtitle(SimulatedEvolutionContext ctx) {
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);
        this.subtitleLabel = new JLabel(ctx.getProperties().getView().getSubtitle());
        this.add(subtitleLabel);
    }

    public PanelSubtitle(MandelbrotContext ctx) {
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);
        String label = ctx.getProperties().getSubtitle() + " - " + ctx.getProperties().getSubtitle();
        subtitleLabel = new JLabel(label);
        this.add(subtitleLabel);
    }
}
