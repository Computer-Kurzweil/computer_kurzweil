package org.woehlke.computer.kurzweil.mandelbrot.view.labels;

import javax.swing.*;
import java.awt.*;

/**
 * Mandelbrot Set drawn by a Turing Machine.
 * (C) 2006 - 2022 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see <a href="https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html">Blog Article</a>
 * @see <a href="https://github.com/Computer-Kurzweil/mandelbrot-julia">Github Repository</a>
 * @see <a href="https://java.woehlke.org/mandelbrot-julia/">Maven Project Repository</a>
 *
 * Created by tw on 16.12.2019.
 */
public class PanelCopyright extends JPanel {

    private final static long serialVersionUID = 242L;

    public PanelCopyright(String subtitle) {
        this.setLayout(new FlowLayout());
        this.add(new JLabel(subtitle));
    }

}
