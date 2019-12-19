package org.woehlke.simulation.mandelbrot.view;

import org.woehlke.simulation.mandelbrot.model.ApplicationModel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.woehlke.simulation.mandelbrot.config.ConfigProperties.BORDER_PADDING;
import static org.woehlke.simulation.mandelbrot.view.RadioButtons.RADIO_BUTTONS_SWITCH;
import static org.woehlke.simulation.mandelbrot.view.RadioButtons.RADIO_BUTTONS_ZOOM;


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
    private volatile JButton zoomOutButton;
    private volatile ButtonGroup radioButtonsGroup;
    private volatile JPanel panelButtonsGroup;
    private volatile JPanel panelZoomButtons;
    private volatile TextField zoomLevelField;

    private volatile ApplicationModel model;

    public PanelButtons(ApplicationModel model) {
        this.model = model;
        FlowLayout layout = new FlowLayout();
        this.radioButtonsSwitch = new JRadioButton(model.getConfig().getButtonsSwitch());
        this.radioButtonsSwitch.setMnemonic(RADIO_BUTTONS_SWITCH.ordinal());
        this.radioButtonsZoom = new JRadioButton(model.getConfig().getButtonsZoom());
        this.radioButtonsZoom.setMnemonic(RADIO_BUTTONS_ZOOM.ordinal());
        this.radioButtonsGroup = new ButtonGroup();
        this.radioButtonsGroup.add(radioButtonsSwitch);
        this.radioButtonsGroup.add(radioButtonsZoom);
        this.zoomOutButton = new JButton(model.getConfig().getButtonsZoomOut());
        this.panelButtonsGroup = new JPanel();
        CompoundBorder borderPanelRadioButtons = getBorder(model.getConfig().getButtonsLabel());
        CompoundBorder borderPanelPushButtons = getBorder(model.getConfig().getButtonsZoomLabel());
        JLabel zoomLevelFieldLabel = new JLabel("Zoom Level");
        zoomLevelField = new TextField("0");
        zoomLevelField.setText(model.getGaussianNumberPlane().getZoomLevel()+"");
        panelButtonsGroup.setLayout(layout);
        panelButtonsGroup.setBorder(borderPanelRadioButtons);
        panelButtonsGroup.add(radioButtonsSwitch);
        panelButtonsGroup.add(radioButtonsZoom);
        this.panelZoomButtons = new JPanel();
        this.panelZoomButtons.setLayout(layout);
        this.panelZoomButtons.setBorder(borderPanelPushButtons);
        this.panelZoomButtons.add(zoomLevelFieldLabel);
        this.panelZoomButtons.add(this.zoomLevelField);
        this.panelZoomButtons.add(this.zoomOutButton);
        this.setLayout(layout);
        this.add(panelButtonsGroup);
        this.add(panelZoomButtons);
        this.radioButtonsSwitch.setSelected( true );
        this.radioButtonsSwitch.addActionListener(this);
        this.radioButtonsZoom.addActionListener(this);
        this.zoomOutButton.addActionListener(this);
        this.panelZoomButtons.setEnabled(false);
    }

    private CompoundBorder getBorder(String label){
        int top = BORDER_PADDING;
        int left = BORDER_PADDING;
        int bottom = BORDER_PADDING;
        int right = BORDER_PADDING;
        return BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(label),
            BorderFactory.createEmptyBorder(top,left,bottom,right)
        );
    }

    @Override
    public void repaint() {
        if(this.zoomLevelField == null){
            zoomLevelField = new TextField("Zoom Level");
        }
        String text;
        try {
            text = model.getGaussianNumberPlane().getZoomLevel() + "";
        } catch (NullPointerException e){
            text = "0";
        }
        this.zoomLevelField.setText(text);
        super.repaint();
    }

    /**
     * TODO write doc.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        boolean repaintCanvas = false;
        if (ae.getSource() == this.radioButtonsSwitch) {
            repaintCanvas = this.model.setModeSwitch();
            this.panelZoomButtons.setEnabled(false);
        } else if(ae.getSource() == this.radioButtonsZoom) {
            repaintCanvas = this.model.setModeZoom();
            this.panelZoomButtons.setEnabled(true);
        } else if(ae.getSource() == this.zoomOutButton){
            repaintCanvas = this.model.zoomOut();
        }
        if(repaintCanvas){
            this.model.getFrame().getCanvas().repaint();
        }
        this.repaint();
    }
}
