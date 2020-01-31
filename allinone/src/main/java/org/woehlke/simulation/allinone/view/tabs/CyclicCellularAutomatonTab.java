package org.woehlke.simulation.allinone.view.tabs;


import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.simulation.allinone.config.ComputerKurzweilApplicationContext;
import org.woehlke.simulation.allinone.view.apps.CyclicCellularAutomatonTabApp;
import org.woehlke.simulation.allinone.view.tabs.parts.TabPanel;

@Log
public class CyclicCellularAutomatonTab extends TabPanel {

    @Getter
    private final CyclicCellularAutomatonTabApp app;

    public CyclicCellularAutomatonTab(ComputerKurzweilApplicationContext ctx) {
        super(ctx,ctx.getProperties().getCca().getView().getSubtitle());
        this.app = new CyclicCellularAutomatonTabApp(this);
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
