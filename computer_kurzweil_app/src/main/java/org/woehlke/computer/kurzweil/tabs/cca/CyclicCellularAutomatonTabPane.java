package org.woehlke.computer.kurzweil.tabs.cca;

import lombok.Getter;
import lombok.experimental.Delegate;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.commons.Updateable;
import org.woehlke.computer.kurzweil.commons.widgets.PanelStartStopButtons;
import org.woehlke.computer.kurzweil.commons.widgets.SubTabImpl;
import org.woehlke.computer.kurzweil.tabs.cca.canvas.CyclicCellularAutomatonButtons;

import javax.swing.*;

@Log4j2
@Getter
public class CyclicCellularAutomatonTabPane extends JTabbedPane implements Startable {

    @Delegate(excludes={SubTabImpl.class,JPanel.class,Updateable.class})
    private final CyclicCellularAutomatonButtons neighbourhoodButtonsPanel;
    @Delegate(excludes={SubTabImpl.class,JPanel.class,Updateable.class})
    private final PanelStartStopButtons startStopButtonsPanel;

    private final CyclicCellularAutomatonTab tab;

    public CyclicCellularAutomatonTabPane(CyclicCellularAutomatonTab tab) {
        this.tab = tab;
        this.neighbourhoodButtonsPanel = new CyclicCellularAutomatonButtons(tab.getCanvas());
        this.startStopButtonsPanel = new PanelStartStopButtons( tab );
        this.addTab(this.startStopButtonsPanel.getTitle(), this.startStopButtonsPanel);
        this.addTab(this.neighbourhoodButtonsPanel.getTitle(), this.neighbourhoodButtonsPanel);
    }

    public void addActionListener(CyclicCellularAutomatonTab tab) {
        this.neighbourhoodButtonsPanel.getButtonVonNeumann().addActionListener( tab);
        this.neighbourhoodButtonsPanel.getButtonMoore().addActionListener( tab);
        this.neighbourhoodButtonsPanel.getButtonWoehlke().addActionListener(tab);
        this.startStopButtonsPanel.addActionListener(tab);
    }

}


