package org.woehlke.simulation.allinone.view.apps.parts;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.simulation.allinone.model.ComputerKurzweilApplicationContext;
import org.woehlke.simulation.allinone.view.tabs.parts.TabPanel;

import javax.swing.*;
import java.awt.*;

@Log
public abstract class AppTabPanel extends JPanel {

    @Getter
    protected final ComputerKurzweilApplicationContext ctx;

    public AppTabPanel(TabPanel tab){
        this.ctx = tab.getCtx();
        int x = tab.getCtx().getWorldDimensions().getX();
        int y = tab.getCtx().getWorldDimensions().getY();
        Dimension preferredSize = new Dimension(x,y);
        this.setPreferredSize(preferredSize);
    }

    public abstract void start();
    public abstract void stop();

}
