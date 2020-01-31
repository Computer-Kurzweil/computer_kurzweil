package org.woehlke.computer.kurzweil.view.tabs;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.config.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.view.apps.MandelbrotTabApp;
import org.woehlke.computer.kurzweil.view.tabs.parts.TabPanel;

@Log
public class MandelbrotTab extends TabPanel {

    @Getter
    private final MandelbrotTabApp app;

    public MandelbrotTab(ComputerKurzweilApplicationContext ctx) {
        super(ctx, ctx.getProperties().getMandelbrot().getView().getSubtitle());
        this.app = new MandelbrotTabApp(this);
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

}
