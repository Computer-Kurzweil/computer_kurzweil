package org.woehlke.computer.kurzweil.mandelbrot.zoom.view.panels;

import org.woehlke.computer.kurzweil.mandelbrot.zoom.model.ApplicationModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Mandelbrot Set drawn by a Turing Machine.
 * (C) 2006 - 2022 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see <a href="https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html">Blog Article</a>
 * @see <a href="https://github.com/Computer-Kurzweil/mandelbrot-zoom">Github Repository</a>
 * @see <a href="https://java.woehlke.org/mandelbrot-zoom/">Maven Project Repository</a>
 *
 * @see ApplicationModel
 *
 * @see JPanel
 * @see ActionListener
 *
 * Created by tw on 16.12.2019.
 */
public class PanelButtons extends JPanel implements ActionListener {

    private final static long serialVersionUID = 242L;

    private volatile JButton zoomOut;
    private volatile ApplicationModel model;

    public PanelButtons(ApplicationModel model) {
        this.model = model;
        this.add(new JLabel(model.getConfig().getCopyright()));
        this.zoomOut = new JButton(model.getConfig().getButtonsZoomOut());
        this.zoomOut.addActionListener(this);
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);
        this.add(zoomOut);
    }

    /**
     * TODO write doc.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == this.zoomOut){
            this.model.zoomOut();
            this.model.getFrame().getCanvas().repaint();
        }
    }
}
