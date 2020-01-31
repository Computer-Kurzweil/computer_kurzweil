package org.woehlke.simulation.allinone.view.parts;

import org.woehlke.simulation.allinone.view.tabs.CyclicCellularAutomatonTab;
import org.woehlke.simulation.allinone.view.tabs.DiffusionLimitedAggregationTab;
import org.woehlke.simulation.allinone.view.tabs.MandelbrotTab;
import org.woehlke.simulation.allinone.view.tabs.SimulatedEvolutionTab;

import javax.swing.*;
import java.awt.*;

public class FramePanel extends JPanel {

    public FramePanel(CyclicCellularAutomatonTab tab){
        int x = tab.getCtx().getWorldDimensions().getX();
        int y = tab.getCtx().getWorldDimensions().getY();
        Dimension preferredSize = new Dimension(x,y);
        this.setPreferredSize(preferredSize);
    }

    public FramePanel(DiffusionLimitedAggregationTab tab){
        int x = tab.getCtx().getWorldDimensions().getX();
        int y = tab.getCtx().getWorldDimensions().getY();
        Dimension preferredSize = new Dimension(x,y);
        this.setPreferredSize(preferredSize);
    }

    public FramePanel(MandelbrotTab tab){
        int x = tab.getCtx().getWorldDimensions().getX();
        int y = tab.getCtx().getWorldDimensions().getY();
        Dimension preferredSize = new Dimension(x,y);
        this.setPreferredSize(preferredSize);
    }

    public FramePanel(SimulatedEvolutionTab tab){
        int x = tab.getCtx().getWorldDimensions().getX();
        int y = tab.getCtx().getWorldDimensions().getY();
        Dimension preferredSize = new Dimension(x,y);
        this.setPreferredSize(preferredSize);
    }
}
