package org.woehlke.computer.kurzweil.apps.mandelbrot.view;

import org.woehlke.computer.kurzweil.apps.mandelbrot.control.ApplicationContext;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

import static org.woehlke.computer.kurzweil.apps.mandelbrot.config.ConfigProperties.BORDER_PADDING;
import static org.woehlke.computer.kurzweil.apps.mandelbrot.control.state.RadioButtons.RADIO_BUTTONS_SWITCH;
import static org.woehlke.computer.kurzweil.apps.mandelbrot.control.state.RadioButtons.RADIO_BUTTONS_ZOOM;


/**
 * Mandelbrot Set drawn by a Turing Machine.
 *
 * (C) 2006 - 2015 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html
 * @author Thomas Woehlke
 *
 * Created by tw on 16.12.2019.
 */
public class PanelButtons extends JPanel {

    private JRadioButton radioButtonsSwitch;
    private JRadioButton radioButtonsZoom;
    private JButton zoomOutButton;
    private ButtonGroup radioButtonsGroup;
    private JPanel panelButtonsGroup;
    private JPanel panelZoomButtons;
    private TextField zoomLevelField;

    private ApplicationContext ctx;

    public PanelButtons(ApplicationContext ctx) {
        this.ctx = ctx;
        FlowLayout layout = new FlowLayout();
        this.radioButtonsSwitch = new JRadioButton(ctx.getConfig().getButtonsSwitch());
        this.radioButtonsSwitch.setMnemonic(RADIO_BUTTONS_SWITCH.ordinal());
        this.radioButtonsZoom = new JRadioButton(ctx.getConfig().getButtonsZoom());
        this.radioButtonsZoom.setMnemonic(RADIO_BUTTONS_ZOOM.ordinal());
        this.radioButtonsGroup = new ButtonGroup();
        this.radioButtonsGroup.add(radioButtonsSwitch);
        this.radioButtonsGroup.add(radioButtonsZoom);
        this.zoomOutButton = new JButton(ctx.getConfig().getButtonsZoomOut());
        this.panelButtonsGroup = new JPanel();
        CompoundBorder borderPanelRadioButtons = getBorder(ctx.getConfig().getButtonsLabel());
        CompoundBorder borderPanelPushButtons = getBorder(ctx.getConfig().getButtonsZoomLabel());
        JLabel zoomLevelFieldLabel = new JLabel("Zoom Level");
        zoomLevelField = new TextField("0",3);
        zoomLevelField.setText(ctx.getZoomLevel());
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
        this.radioButtonsSwitch.addActionListener(this.ctx);
        this.radioButtonsZoom.addActionListener(this.ctx);
        this.zoomOutButton.addActionListener(this.ctx);
        this.disableZoomButton();
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
            zoomLevelField = new TextField("1");
        }
        String text;
        try {
            text = ctx.getZoomLevel();
        } catch (NullPointerException e){
            text = "ERR";
        }
        this.zoomLevelField.setText(text);
        this.zoomLevelField.repaint();
        super.repaint();
    }

    public JRadioButton getRadioButtonsSwitch() {
        return radioButtonsSwitch;
    }

    public JRadioButton getRadioButtonsZoom() {
        return radioButtonsZoom;
    }

    public JButton getZoomOutButton() {
        return zoomOutButton;
    }

    public void enableZoomButton() {
        this.panelZoomButtons.setEnabled(false);
    }

    public void disableZoomButton() {
        this.panelZoomButtons.setEnabled(true);
    }
}
