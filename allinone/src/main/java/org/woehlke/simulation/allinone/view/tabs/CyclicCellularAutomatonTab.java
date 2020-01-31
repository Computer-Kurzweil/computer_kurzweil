package org.woehlke.simulation.allinone.view.tabs;


import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.simulation.allinone.model.ComputerKurzweilApplicationContext;
import org.woehlke.simulation.allinone.view.apps.CyclicCellularAutomatonApp;
import org.woehlke.simulation.allinone.view.tabs.parts.TabPanel;

@Log
public class CyclicCellularAutomatonTab extends TabPanel {

    @Getter
    private final CyclicCellularAutomatonApp app;

    public CyclicCellularAutomatonTab(ComputerKurzweilApplicationContext ctx) {
        super(ctx,ctx.getProperties().getCca().getView().getSubtitle());
        this.app = new CyclicCellularAutomatonApp(this);
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
