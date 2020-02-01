package org.woehlke.computer.kurzweil.view.tabs;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.config.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.control.signals.UserSignal;
import org.woehlke.computer.kurzweil.control.commons.Startable;
import org.woehlke.computer.kurzweil.control.commons.AppGuiComponent;
import org.woehlke.computer.kurzweil.view.apps.MandelbrotTabApp;
import org.woehlke.computer.kurzweil.view.tabs.common.TabPanel;

import javax.accessibility.Accessible;
import java.awt.image.ImageObserver;
import java.io.Serializable;

@Log
public class MandelbrotTab extends TabPanel implements ImageObserver,
    Serializable,
    Accessible, Startable, AppGuiComponent {

    @Getter
    private final MandelbrotTabApp app;

    public MandelbrotTab(ComputerKurzweilApplicationContext ctx) {
        super(ctx, ctx.getProperties().getMandelbrot().getView().getSubtitle());
        this.app = new MandelbrotTabApp(this);
        /*
        this.startableContainer.registerStartables(
            this.panelSubtitle, this.app, this.startStopButtonsPanel
        );
        */
        this.add(this.panelSubtitle);
        this.add(this.app);
        this.add(this.startStopButtonsPanel);
    }

    @Override
    public void start() {
        this.app.start();
    }

    @Override
    public void stop() {
        this.app.stop();
    }

    @Override
    public void handleUserSignal(UserSignal userSignal) {
        log.info("handleUserSignal: "+userSignal.name());
    }

}
