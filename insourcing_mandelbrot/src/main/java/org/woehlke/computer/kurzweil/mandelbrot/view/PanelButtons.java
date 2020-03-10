package org.woehlke.computer.kurzweil.mandelbrot.view;

import org.woehlke.computer.kurzweil.mandelbrot.model.ApplicationModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.woehlke.computer.kurzweil.mandelbrot.view.RradioButtons.RADIO_BUTTONS_SWITCH;
import static org.woehlke.computer.kurzweil.mandelbrot.view.RradioButtons.RADIO_BUTTONS_ZOOM;


/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 16.12.2019.
 */
public class PanelButtons extends JPanel implements ActionListener {

    private volatile JRadioButton radioButtonsSwitch;
    private volatile JRadioButton radioButtonsZoom;
    private volatile JButton zoomOut;
    private volatile ButtonGroup radioButtonsGroup;
    private volatile ApplicationModel model;

    public PanelButtons(ApplicationModel model) {
        this.model = model;
        JLabel buttonsLabel = new JLabel(model.getConfig().getButtonsLabel());
        this.radioButtonsSwitch = new JRadioButton(model.getConfig().getButtonsSwitch());
        this.radioButtonsSwitch.setMnemonic(RADIO_BUTTONS_SWITCH.ordinal());
        this.radioButtonsSwitch.setSelected(true);
        this.radioButtonsSwitch.addActionListener(this);
        this.radioButtonsZoom = new JRadioButton(model.getConfig().getButtonsZoom());
        this.radioButtonsZoom.setMnemonic(RADIO_BUTTONS_ZOOM.ordinal());
        this.radioButtonsZoom.addActionListener(this);
        this.radioButtonsGroup = new ButtonGroup();
        this.radioButtonsGroup.add(radioButtonsSwitch);
        this.radioButtonsGroup.add(radioButtonsZoom);
        this.zoomOut = new JButton(model.getConfig().getButtonsZoomOut());
        this.zoomOut.addActionListener(this);
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);
        this.add(buttonsLabel);
        this.add(radioButtonsSwitch);
        this.add(radioButtonsZoom);
        this.add(zoomOut);
    }

    /**
     * TODO write doc.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.radioButtonsSwitch) {
            this.model.setModeSwitch();
        } else if(ae.getSource() == this.radioButtonsZoom) {
            this.model.setModeZoom();
        } else if(ae.getSource() == this.zoomOut){
            this.model.zoomOut();
            this.model.getFrame().getCanvas().repaint();
        }
    }
}
