package org.woehlke.computer.kurzweil.apps.mandelbrot.view.widgets;

import lombok.Getter;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;

import javax.swing.*;

import static org.woehlke.computer.kurzweil.apps.mandelbrot.model.state.RadioButtons.RADIO_BUTTONS_SWITCH;
import static org.woehlke.computer.kurzweil.apps.mandelbrot.model.state.RadioButtons.RADIO_BUTTONS_ZOOM;

public class RadioButtonsGroup extends ButtonGroup {

    @Getter private final JRadioButton radioButtonsSwitch;
    @Getter private final JRadioButton radioButtonsZoom;

    private final ComputerKurzweilApplicationContext ctx;

    public RadioButtonsGroup(ComputerKurzweilApplicationContext ctx) {
        this.ctx = ctx;
        this.radioButtonsSwitch = new JRadioButton(this.ctx.getProperties().getMandelbrot().getView().getButtonsSwitch());
        this.radioButtonsSwitch.setMnemonic(RADIO_BUTTONS_SWITCH.ordinal());
        this.radioButtonsZoom = new JRadioButton(this.ctx.getProperties().getMandelbrot().getView().getButtonsZoom());
        this.radioButtonsZoom.setMnemonic(RADIO_BUTTONS_ZOOM.ordinal());
        this.add(radioButtonsSwitch);
        this.add(radioButtonsZoom);
        this.radioButtonsSwitch.setSelected( true );
    }
}
