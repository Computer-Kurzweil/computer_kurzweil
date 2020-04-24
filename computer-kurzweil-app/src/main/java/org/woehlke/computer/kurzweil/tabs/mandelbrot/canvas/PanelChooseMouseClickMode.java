package org.woehlke.computer.kurzweil.tabs.mandelbrot.canvas;

import lombok.Getter;
import org.woehlke.computer.kurzweil.commons.Updateable;
import org.woehlke.computer.kurzweil.commons.widgets.SubTab;
import org.woehlke.computer.kurzweil.commons.widgets.SubTabImpl;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.Mandelbrot;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.MandelbrotContext;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.MandelbrotModel;

import javax.swing.*;

import static org.woehlke.computer.kurzweil.tabs.mandelbrot.canvas.RradioButtons.RADIO_BUTTONS_SWITCH;
import static org.woehlke.computer.kurzweil.tabs.mandelbrot.canvas.RradioButtons.RADIO_BUTTONS_ZOOM;

@Getter
public class PanelChooseMouseClickMode extends SubTabImpl implements Mandelbrot, SubTab, Updateable {

    private final MandelbrotContext tabCtx;
    private final String buttonsSwitch;
    private final String buttonsZoom;
    private final JRadioButton radioButtonsSwitch;
    private final JRadioButton radioButtonsZoom;
    private final ButtonGroup radioButtonsGroup;
    private final MandelbrotModel mandelbrotModel;

    public PanelChooseMouseClickMode(MandelbrotContext tabCtx) {
        super(
            tabCtx.getCtx().getProperties().getMandelbrot().getView().getButtonsLabel(),
            tabCtx.getCtx().getProperties()
        );
        this.tabCtx = tabCtx;
        this.mandelbrotModel = tabCtx.getTabModel();

        this.buttonsSwitch = this.tabCtx.getCtx().getProperties().getMandelbrot().getView().getButtonsSwitch();
        this.buttonsZoom = this.tabCtx.getCtx().getProperties().getMandelbrot().getView().getButtonsZoom();

        this.radioButtonsSwitch = new JRadioButton(buttonsSwitch);
        this.radioButtonsSwitch.setMnemonic(RADIO_BUTTONS_SWITCH.ordinal());
        this.radioButtonsZoom = new JRadioButton(buttonsZoom);
        this.radioButtonsZoom.setMnemonic(RADIO_BUTTONS_ZOOM.ordinal());
        this.radioButtonsSwitch.setSelected( true );
        this.radioButtonsGroup = new ButtonGroup();
        this.radioButtonsGroup.add(radioButtonsSwitch);
        this.radioButtonsGroup.add(radioButtonsZoom);
        this.add(radioButtonsSwitch);
        this.add(radioButtonsZoom);
        this.getRadioButtonsSwitch().addActionListener(this.tabCtx.getTab());
        this.getRadioButtonsZoom().addActionListener(this.tabCtx.getTab());
    }

    @Override
    public void update() {
        //TODO:
    }
}
