package org.woehlke.computer.kurzweil.commons.tabs;

import org.woehlke.computer.kurzweil.commons.*;

import javax.accessibility.Accessible;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.Serializable;

public interface Tab extends ImageObserver, Serializable, Accessible,
    ActionListener,
    GuiComponentTab,
    Startable,
    HasApplicationContext,
    HasContext,
    HasTitle {

}
