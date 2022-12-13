package org.woehlke.computer.kurzweil.cyclic.cellular.automaton.view;

import org.woehlke.computer.kurzweil.cyclic.cellular.automaton.config.ObjectRegistry;

import javax.swing.*;
import java.awt.*;

/**
 * Cyclic Cellular Automaton.
 * <p>
 * (C) 2006 - 2022 Thomas Woehlke.
 * @see <a href="https://java.woehlke.org/cyclic-cellular-automaton">Maven Project Page</a>
 *
 * @author Thomas Woehlke
 */
public class PanelSubtitle extends JPanel {

    private static final long serialVersionUID = 242L;

    private final JLabel subtitleLabel;

    public PanelSubtitle(ObjectRegistry ctx) {
        this.subtitleLabel = new JLabel(ctx.getConfig().getSubtitle());
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);
        this.add(subtitleLabel);
    }

}
