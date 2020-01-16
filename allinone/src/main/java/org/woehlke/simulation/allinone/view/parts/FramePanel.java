package org.woehlke.simulation.allinone.view.parts;

import org.woehlke.simulation.allinone.view.tabs.CyclicCellularAutomatonTab;
import org.woehlke.simulation.allinone.view.tabs.DiffusionLimitedAggregationTab;
import org.woehlke.simulation.allinone.view.tabs.MandelbrotTab;
import org.woehlke.simulation.allinone.view.tabs.SimulatedEvolutionTab;

import javax.swing.*;
import java.awt.*;

public class FramePanel extends JPanel {

    public FramePanel(CyclicCellularAutomatonTab tab){
        Dimension preferredSize = new Dimension(640,480);
        this.setPreferredSize(preferredSize);
    }

    public FramePanel(DiffusionLimitedAggregationTab tab){
        Dimension preferredSize = new Dimension(640,480);
        this.setPreferredSize(preferredSize);

    }

    public FramePanel(MandelbrotTab tab){
        Dimension preferredSize = new Dimension(640,480);
        this.setPreferredSize(preferredSize);
    }

    public FramePanel(SimulatedEvolutionTab tab){
        Dimension preferredSize = new Dimension(640,480);
        this.setPreferredSize(preferredSize);
    }
}
