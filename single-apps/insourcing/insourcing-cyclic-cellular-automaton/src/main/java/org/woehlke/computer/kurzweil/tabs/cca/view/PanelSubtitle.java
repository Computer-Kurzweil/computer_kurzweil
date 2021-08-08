package org.woehlke.computer.kurzweil.tabs.cca.view;

import org.woehlke.computer.kurzweil.tabs.cca.config.ObjectRegistry;

import javax.swing.*;
import java.awt.*;

/**
 * TODO write doc.
 */
public class PanelSubtitle extends JPanel {

    private static final long serialVersionUID = 4357793241219932594L;

    private final JLabel subtitleLabel;

    public PanelSubtitle(ObjectRegistry ctx) {
        this.subtitleLabel = new JLabel(ctx.getConfig().getSubtitle());
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);
        this.add(subtitleLabel);
    }

}
