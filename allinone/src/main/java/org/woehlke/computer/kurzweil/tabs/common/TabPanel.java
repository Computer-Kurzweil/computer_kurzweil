package org.woehlke.computer.kurzweil.tabs.common;

import org.woehlke.computer.kurzweil.commons.AppGuiComponent;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.commons.AppContext;

import javax.accessibility.Accessible;
import java.awt.image.ImageObserver;
import java.io.Serializable;

public interface TabPanel extends ImageObserver, Serializable,
    Accessible, Startable, AppGuiComponent {

    AppContext getAppCtx();

    String getTitle();

    String getSubTitle();
}
