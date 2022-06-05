package org.woehlke.computer.kurzweil.tabs.mandelbrot2julia.views;

import lombok.Getter;
import lombok.experimental.Delegate;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.commons.Updateable;
import org.woehlke.computer.kurzweil.commons.widgets.PanelStartStopButtons;
import org.woehlke.computer.kurzweil.commons.widgets.SubTabImpl;
import org.woehlke.computer.kurzweil.tabs.mandelbrot2julia.MandelbrotTab;
import org.woehlke.computer.kurzweil.tabs.mandelbrot2julia.canvas.PanelChooseMouseClickMode;
import org.woehlke.computer.kurzweil.tabs.mandelbrot2julia.canvas.PanelZoom;

import javax.swing.*;

@Log
@Getter
public class MandelbrotTabPane  extends JTabbedPane implements Startable {

    private static final long serialVersionUID = 7526471155622776147L;

    @Delegate(excludes={SubTabImpl.class,JPanel.class, Updateable.class})
    private final PanelStartStopButtons startStopButtonsPanel;
    private final PanelChooseMouseClickMode panelChooseMouseClickMode;
    private final PanelZoom panelZoom;

    private final MandelbrotTab tab;

    public MandelbrotTabPane(MandelbrotTab tab) {
        this.tab = tab;
        this.startStopButtonsPanel = new PanelStartStopButtons( tab );
        this.addTab(this.startStopButtonsPanel.getTitle(), this.startStopButtonsPanel);
        this.panelChooseMouseClickMode = new PanelChooseMouseClickMode(this.tab.getTabCtx());
        this.panelZoom = new PanelZoom(this.tab.getTabCtx());
        this.addTab(panelChooseMouseClickMode.getTitle(),panelChooseMouseClickMode);
        this.addTab(panelZoom.getTitle(),panelZoom);
        this.startStopButtonsPanel.stop();
        this.disableZoomButton();
    }


    public void enableZoomButton() {
        this.panelZoom.setEnabled(false);
    }

    public void disableZoomButton() {
        this.panelZoom.setEnabled(true);
    }

    public JRadioButton getRadioButtonsSwitch() {
        return this.panelChooseMouseClickMode.getRadioButtonsSwitch();
    }

    public JRadioButton getRadioButtonsZoom() {
        return this.panelChooseMouseClickMode.getRadioButtonsZoom();
    }

    public AbstractButton getZoomOutButton() {
        return this.panelZoom.getZoomOutButton();
    }

}
