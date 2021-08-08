package org.woehlke.computer.kurzweil.tabs.mandelbrotzoom.canvas;

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
public class PanelCopyright extends JPanel {

    private static final long serialVersionUID = 7526471155622776147L;

  public PanelCopyright(String subtitle) {
    this.setLayout(new FlowLayout());
    this.add(new JLabel(subtitle));
  }

}
