package org.woehlke.computer.kurzweil.tabs.mandelbrot;

import lombok.Getter;
import lombok.experimental.Delegate;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.commons.Updateable;
import org.woehlke.computer.kurzweil.commons.widgets.PanelStartStopButtons;
import org.woehlke.computer.kurzweil.commons.widgets.SubTabImpl;

import javax.swing.*;

@Log4j2
@Getter
public class MandelbrotTabPane  extends JTabbedPane implements Startable {

    @Delegate(excludes={SubTabImpl.class,JPanel.class, Updateable.class})
    private final PanelStartStopButtons startStopButtonsPanel;

    private final MandelbrotTab tab;

    public MandelbrotTabPane(MandelbrotTab tab) {
        this.tab = tab;
        this.startStopButtonsPanel = new PanelStartStopButtons( tab );
        this.addTab(this.startStopButtonsPanel.getTitle(), this.startStopButtonsPanel);
    }

    public void addActionListener(MandelbrotTab tab) {
        this.startStopButtonsPanel.addActionListener(tab);
    }
}
