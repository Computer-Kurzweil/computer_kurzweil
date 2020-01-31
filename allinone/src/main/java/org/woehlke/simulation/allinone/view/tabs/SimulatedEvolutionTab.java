package org.woehlke.simulation.allinone.view.tabs;

import lombok.extern.java.Log;
import org.woehlke.simulation.allinone.config.ComputerKurzweilApplicationContext;
import org.woehlke.simulation.allinone.view.apps.SimulatedEvolutionTabApp;
import org.woehlke.simulation.allinone.view.tabs.parts.TabPanel;

@Log
public class SimulatedEvolutionTab extends TabPanel {

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

}
