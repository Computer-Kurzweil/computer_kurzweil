package org.woehlke.computer.kurzweil.cyclic.cellular.automaton.view;

import org.woehlke.computer.kurzweil.cyclic.cellular.automaton.config.ObjectRegistry;

import javax.swing.*;
import java.awt.*;

/**
 * TODO write doc.
 */
public class PanelSubtitle extends JPanel {

    private static final long serialVersionUID = -3057254130516052936L;

  private final JLabel subtitleLabel;

  public PanelSubtitle(ObjectRegistry ctx) {
    this.subtitleLabel = new JLabel(ctx.getConfig().getSubtitle());
    FlowLayout layout = new FlowLayout();
    this.setLayout(layout);
    this.add(subtitleLabel);
  }

}
