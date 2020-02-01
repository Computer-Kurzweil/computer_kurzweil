package org.woehlke.computer.kurzweil.view.tabs.common;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.config.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.control.signals.UserSlot;
import org.woehlke.computer.kurzweil.control.commons.Startable;

import javax.swing.*;
import java.awt.*;

@Log
public abstract class TabAppPanel extends JPanel implements UserSlot, Startable {

    @Getter
    protected final ComputerKurzweilApplicationContext ctx;

    public TabAppPanel(TabPanel tab){
        this.ctx = tab.getCtx();
        int x = tab.getCtx().getWorldDimensions().getX();
        int y = tab.getCtx().getWorldDimensions().getY();
        Dimension preferredSize = new Dimension(x,y);
        this.setPreferredSize(preferredSize);
    }

    public abstract void start();
    public abstract void stop();

}
