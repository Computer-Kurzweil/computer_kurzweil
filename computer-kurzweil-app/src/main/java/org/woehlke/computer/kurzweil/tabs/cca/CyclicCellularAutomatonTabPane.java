package org.woehlke.computer.kurzweil.tabs.cca;

import lombok.Getter;
import lombok.experimental.Delegate;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.commons.Updateable;
import org.woehlke.computer.kurzweil.commons.widgets.PanelStartStopButtons;
import org.woehlke.computer.kurzweil.commons.widgets.SubTabImpl;
import org.woehlke.computer.kurzweil.tabs.cca.canvas.PanelNeighbourhoodButtons;

import javax.swing.*;

@Log4j2
@Getter
public class CyclicCellularAutomatonTabPane extends JTabbedPane implements Startable {

    private static final long serialVersionUID = 7526471155622776147L;

    @Delegate(excludes={SubTabImpl.class,JPanel.class,Updateable.class})
    private final PanelNeighbourhoodButtons panelNeighbourhoodButtons;
    @Delegate(excludes={SubTabImpl.class,JPanel.class,Updateable.class})
    private final PanelStartStopButtons startStopButtonsPanel;

    private final CyclicCellularAutomatonTab tab;

    public CyclicCellularAutomatonTabPane(CyclicCellularAutomatonTab tab) {
        this.tab = tab;
        this.panelNeighbourhoodButtons = new PanelNeighbourhoodButtons(tab.getCanvas());
        this.startStopButtonsPanel = new PanelStartStopButtons( tab );
        this.addTab(this.startStopButtonsPanel.getTitle(), this.startStopButtonsPanel);
        this.addTab(this.panelNeighbourhoodButtons.getTitle(), this.panelNeighbourhoodButtons);
        this.panelNeighbourhoodButtons.getButtonVonNeumann().addActionListener( tab);
        this.panelNeighbourhoodButtons.getButtonMoore().addActionListener( tab);
        this.panelNeighbourhoodButtons.getButtonWoehlke().addActionListener(tab);
        this.startStopButtonsPanel.stop();
    }

}


