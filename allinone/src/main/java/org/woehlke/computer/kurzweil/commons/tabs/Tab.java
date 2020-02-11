package org.woehlke.computer.kurzweil.commons.tabs;

import org.woehlke.computer.kurzweil.commons.*;

import javax.accessibility.Accessible;
import java.awt.image.ImageObserver;
import java.io.Serializable;

public interface Tab extends ImageObserver, Serializable, Accessible,
    GuiComponentTab,
    Startable,
    HasApplicationContext,
    HasContext,
    HasTitle {

}
