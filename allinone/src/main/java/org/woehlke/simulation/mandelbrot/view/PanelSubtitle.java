package org.woehlke.simulation.mandelbrot.view;

import org.woehlke.simulation.mandelbrot.config.Config;

import javax.swing.*;
import java.awt.*;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 16.12.2019.
 */
public class PanelSubtitle extends JPanel {

  public PanelSubtitle(Config config) {
      this.setLayout(new FlowLayout());
      String label = config.getSubtitle() + " - " + config.getCopyright();
      this.add(new JLabel(label));
  }

}
