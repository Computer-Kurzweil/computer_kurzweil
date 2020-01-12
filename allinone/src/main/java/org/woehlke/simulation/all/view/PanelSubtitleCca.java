package org.woehlke.simulation.all.view;

import org.woehlke.simulation.cca.config.CyclicCellularAutomatonContext;

import javax.swing.*;
import java.awt.*;

/**
 * TODO write doc.
 */
@Deprecated
public class PanelSubtitleCca extends JPanel {

  private final JLabel subtitleLabel;

  @Deprecated
    public PanelSubtitleCca(CyclicCellularAutomatonContext ctx) {
        this.subtitleLabel = new JLabel(ctx.getConfig().getSubtitle());
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);
        this.add(subtitleLabel);
    }

}
