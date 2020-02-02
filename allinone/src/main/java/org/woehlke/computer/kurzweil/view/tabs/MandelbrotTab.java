package org.woehlke.computer.kurzweil.view.tabs;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.apps.mandelbrot.ctx.MandelbrotContext;
import org.woehlke.computer.kurzweil.control.ctx.AppContext;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
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

    @Getter
    private final MandelbrotContext appCtx;

    public MandelbrotTab(ComputerKurzweilApplicationContext ctx ) {
        super(ctx, ctx.getProperties().getMandelbrot().getView().getSubtitle());
        this.appCtx = new MandelbrotContext();
        this.app = new MandelbrotTabApp(this);
        this.add(this.panelSubtitle);
        this.add(this.app);
        this.add(this.startStopButtonsPanel);
        /*
        this.startableContainer.registerStartables(
            this.panelSubtitle, this.app, this.startStopButtonsPanel
        );
        */
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
    public void update() {

    }

    @Override
    public void showMe() {

    }

    @Override
    public void hideMe() {

    }

    @Override
    public void handleUserSignal(UserSignal userSignal) {
        log.info("handleUserSignal: "+userSignal.name());
    }

}
