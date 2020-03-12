package org.woehlke.computer.kurzweil.tabs.dla;

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
public class DiffusionLimitedAggregationTabPane  extends JTabbedPane implements Startable {

    @Delegate(excludes={SubTabImpl.class,JPanel.class,Updateable.class})
    private final PanelStartStopButtons startStopButtonsPanel;

    private final DiffusionLimitedAggregationTab tab;

    public DiffusionLimitedAggregationTabPane(DiffusionLimitedAggregationTab tab) {
        this.tab = tab;
        this.startStopButtonsPanel = new PanelStartStopButtons( tab );
        this.addTab(this.startStopButtonsPanel.getTitle(), this.startStopButtonsPanel);
        this.startStopButtonsPanel.stop();
    }

}
