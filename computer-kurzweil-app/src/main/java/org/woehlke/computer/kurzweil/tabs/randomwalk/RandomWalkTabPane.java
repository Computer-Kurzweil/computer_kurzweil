package org.woehlke.computer.kurzweil.tabs.randomwalk;

import lombok.experimental.Delegate;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.commons.Updateable;
import org.woehlke.computer.kurzweil.commons.widgets.PanelStartStopButtons;
import org.woehlke.computer.kurzweil.commons.widgets.SubTabImpl;

import javax.swing.*;

public class RandomWalkTabPane extends JTabbedPane implements Startable {

    private static final long serialVersionUID = 7526471155622776147L;

    @Delegate(excludes={SubTabImpl.class,JPanel.class, Updateable.class})
    private final PanelStartStopButtons startStopButtonsPanel;

    private final RandomWalkTab randomWalkTab;

    public RandomWalkTabPane(RandomWalkTab tab) {
        this.randomWalkTab = tab;
        this.startStopButtonsPanel = new PanelStartStopButtons( tab );
        this.addTab(this.startStopButtonsPanel.getTitle(), this.startStopButtonsPanel);
        this.startStopButtonsPanel.stop();
    }

}
