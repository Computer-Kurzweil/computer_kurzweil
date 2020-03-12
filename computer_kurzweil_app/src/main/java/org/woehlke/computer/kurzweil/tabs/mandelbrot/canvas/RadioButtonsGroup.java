package org.woehlke.computer.kurzweil.tabs.mandelbrot.canvas;

import lombok.Getter;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.Mandelbrot;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.MandelbrotContext;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.MandelbrotTab;

import javax.swing.*;

import static org.woehlke.computer.kurzweil.tabs.mandelbrot.model.state.RadioButtons.RADIO_BUTTONS_SWITCH;
import static org.woehlke.computer.kurzweil.tabs.mandelbrot.model.state.RadioButtons.RADIO_BUTTONS_ZOOM;

@Getter
public class RadioButtonsGroup extends ButtonGroup implements Mandelbrot {

    private final JRadioButton radioButtonsSwitch;
    private final JRadioButton radioButtonsZoom;
    private final MandelbrotContext tabCtx;

    public RadioButtonsGroup(MandelbrotContext tabCtx) {
        this.tabCtx = tabCtx;
        String buttonsSwitch = this.tabCtx.getCtx().getProperties().getMandelbrot().getView().getButtonsSwitch();
        String buttonsZoom = this.tabCtx.getCtx().getProperties().getMandelbrot().getView().getButtonsZoom();
        this.radioButtonsSwitch = new JRadioButton(buttonsSwitch);
        this.radioButtonsSwitch.setMnemonic(RADIO_BUTTONS_SWITCH.ordinal());
        this.radioButtonsZoom = new JRadioButton(buttonsZoom);
        this.radioButtonsZoom.setMnemonic(RADIO_BUTTONS_ZOOM.ordinal());
        this.add(radioButtonsSwitch);
        this.add(radioButtonsZoom);
        this.radioButtonsSwitch.setSelected( true );
    }

    public void addActionListener(MandelbrotTab tab) {
        this.getRadioButtonsSwitch().addActionListener(tab);
        this.getRadioButtonsZoom().addActionListener(tab);
    }
}
