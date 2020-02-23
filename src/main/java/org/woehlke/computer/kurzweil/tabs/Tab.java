package org.woehlke.computer.kurzweil.tabs;

import org.woehlke.computer.kurzweil.commons.*;
import org.woehlke.computer.kurzweil.commons.tabs.HasTabContext;

import javax.accessibility.Accessible;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.Serializable;

public interface Tab extends ImageObserver, Serializable, Accessible,
    ActionListener,
    GuiComponentTab,
    Startable,
    HasApplicationContext,
    HasTabContext,
    HasTitle {

}
