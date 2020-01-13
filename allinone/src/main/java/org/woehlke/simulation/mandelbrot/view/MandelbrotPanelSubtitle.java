package org.woehlke.simulation.mandelbrot.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.mandelbrot.config.MandelbrotProperties;

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
@Component
public class MandelbrotPanelSubtitle extends JPanel {

    @Autowired
  public MandelbrotPanelSubtitle(MandelbrotProperties mandelbrotProperties) {
      this.setLayout(new FlowLayout());
      String label = mandelbrotProperties.getSubtitle() + " - " + mandelbrotProperties.getSubtitle();
      this.add(new JLabel(label));
  }

}
