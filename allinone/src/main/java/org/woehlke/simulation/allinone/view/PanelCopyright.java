package org.woehlke.simulation.allinone.view;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.simulation.cca.model.CyclicCellularAutomatonContext;
import org.woehlke.simulation.evolution.model.SimulatedEvolutionContext;

import javax.swing.*;
import java.awt.*;


@Log
@ToString
@EqualsAndHashCode(callSuper=true)
public class PanelCopyright extends JPanel {

    @Getter
    private final JLabel copyrightLabel;

    public PanelCopyright(CyclicCellularAutomatonContext ctx) {
        this.copyrightLabel = new JLabel(ctx.getProperties().getCopyright());
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
