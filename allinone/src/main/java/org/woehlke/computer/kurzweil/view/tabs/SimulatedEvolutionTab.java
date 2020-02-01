package org.woehlke.computer.kurzweil.view.tabs;

import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.config.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.control.signals.UserSignal;
import org.woehlke.computer.kurzweil.control.commons.Startable;
import org.woehlke.computer.kurzweil.control.commons.AppGuiComponent;
import org.woehlke.computer.kurzweil.view.tabs.common.TabPanel;

import javax.accessibility.Accessible;
import java.awt.image.ImageObserver;
import java.io.Serializable;

@Log
public class SimulatedEvolutionTab extends TabPanel implements ImageObserver,
    Serializable,
    Accessible, Startable, AppGuiComponent {

    public SimulatedEvolutionTab(ComputerKurzweilApplicationContext ctx) {
        super(ctx,ctx.getProperties().getEvolution().getView().getSubtitle());
        this.add(this.panelSubtitle);
        this.add(this.startStopButtonsPanel);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void handleUserSignal(UserSignal userSignal) {
        log.info("handleUserSignal: "+userSignal.name());
    }

}
