package org.woehlke.computer.kurzweil.view.tabs.common;

import org.woehlke.computer.kurzweil.control.commons.AppGuiComponent;
import org.woehlke.computer.kurzweil.control.commons.Startable;
import org.woehlke.computer.kurzweil.control.ctx.AppContext;

import javax.accessibility.Accessible;
import java.awt.image.ImageObserver;
import java.io.Serializable;

public interface TabPanel extends ImageObserver, Serializable,
    Accessible, Startable, AppGuiComponent {

    AppContext getAppCtx();
}
