package org.woehlke.computer.kurzweil.view.tabs;

import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.config.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.control.events.UserSignal;
import org.woehlke.computer.kurzweil.control.events.UserSlot;
import org.woehlke.computer.kurzweil.control.startables.Startable;
import org.woehlke.computer.kurzweil.view.apps.SimulatedEvolutionTabApp;
import org.woehlke.computer.kurzweil.view.tabs.parts.TabPanel;

import javax.accessibility.Accessible;
import java.awt.image.ImageObserver;
import java.io.Serializable;

@Log
public class SimulatedEvolutionTab extends TabPanel implements ImageObserver,
    Serializable,
    Accessible, Startable, UserSlot {

    private final SimulatedEvolutionTabApp app;

    public SimulatedEvolutionTab(ComputerKurzweilApplicationContext ctx) {
        super(ctx,ctx.getProperties().getEvolution().getView().getSubtitle());
        this.app = new SimulatedEvolutionTabApp(this);
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
