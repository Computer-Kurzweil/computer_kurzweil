package org.woehlke.computer.kurzweil.tabs.randomwalk;

import lombok.experimental.Delegate;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.commons.Updateable;
import org.woehlke.computer.kurzweil.commons.widgets.PanelStartStopButtons;
import org.woehlke.computer.kurzweil.commons.widgets.SubTabImpl;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.MandelbrotTab;

import javax.swing.*;

public class RandomWalkTabPane extends JTabbedPane implements Startable {

    @Delegate(excludes={SubTabImpl.class,JPanel.class, Updateable.class})
    private final PanelStartStopButtons startStopButtonsPanel;

    private final RandomWalkTab tab;

    public RandomWalkTabPane(RandomWalkTab tab) {
        this.tab = tab;
        this.startStopButtonsPanel = new PanelStartStopButtons( tab );
        this.addTab(this.startStopButtonsPanel.getTitle(), this.startStopButtonsPanel);
    }

    public void addActionListener(MandelbrotTab tab) {
        this.startStopButtonsPanel.addActionListener(tab);
    }
}
