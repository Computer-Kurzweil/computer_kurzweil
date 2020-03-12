package org.woehlke.computer.kurzweil.tabs.mandelbrot;

import lombok.Getter;
import lombok.experimental.Delegate;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.commons.Updateable;
import org.woehlke.computer.kurzweil.commons.widgets.PanelStartStopButtons;
import org.woehlke.computer.kurzweil.commons.widgets.SubTabImpl;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.canvas.PanelChooseMouseClickModeButtons;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.canvas.PanelZoomButtons;

import javax.swing.*;

@Log4j2
@Getter
public class MandelbrotTabPane  extends JTabbedPane implements Startable {

    @Delegate(excludes={SubTabImpl.class,JPanel.class, Updateable.class})
    private final PanelStartStopButtons startStopButtonsPanel;
    private final PanelChooseMouseClickModeButtons panelChooseMouseClickModeButtons;
    private final PanelZoomButtons panelZoomButtons;

    private final MandelbrotTab tab;

    public MandelbrotTabPane(MandelbrotTab tab) {
        this.tab = tab;
        this.startStopButtonsPanel = new PanelStartStopButtons( tab );
        this.addTab(this.startStopButtonsPanel.getTitle(), this.startStopButtonsPanel);
        this.panelChooseMouseClickModeButtons = new PanelChooseMouseClickModeButtons(this.tab.getTabCtx());
        this.panelZoomButtons = new PanelZoomButtons(this.tab.getTabCtx());
        this.add(panelChooseMouseClickModeButtons);
        this.add(panelZoomButtons);
        this.startStopButtonsPanel.stop();
        this.disableZoomButton();
    }


    public void enableZoomButton() {
        this.panelZoomButtons.setEnabled(false);
    }

    public void disableZoomButton() {
        this.panelZoomButtons.setEnabled(true);
    }

    public JRadioButton getRadioButtonsSwitch() {
        return this.panelChooseMouseClickModeButtons.getRadioButtonsSwitch();
    }

    public JRadioButton getRadioButtonsZoom() {
        return this.panelChooseMouseClickModeButtons.getRadioButtonsZoom();
    }

    public AbstractButton getZoomOutButton() {
        return this.panelZoomButtons.getZoomOutButton();
    }

}
