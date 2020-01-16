package org.woehlke.simulation.allinone.view.parts;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.simulation.allinone.model.ComputerKurzweilApplicationContext;
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

    public PanelCopyright(ComputerKurzweilApplicationContext ctx) {
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.CENTER);
        this.setLayout(layout);
        this.copyrightLabel = new JLabel(ctx.getProperties().getView().getFooter());
        this.add(copyrightLabel);
    }

    public PanelCopyright(CyclicCellularAutomatonContext ctx) {
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.CENTER);
        this.setLayout(layout);
        this.copyrightLabel = new JLabel(ctx.getProperties().getCopyright());
        this.add(copyrightLabel);
    }

    public PanelCopyright(SimulatedEvolutionContext ctx) {
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.CENTER);
        this.setLayout(layout);
        this.copyrightLabel = new JLabel(ctx.getProperties().getView().getFooter());
        this.add(this.copyrightLabel);
    }


}
