package org.woehlke.computer.kurzweil.tabs.evolution;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.widgets.borders.PanelBorder;
import org.woehlke.computer.kurzweil.commons.tabs.TabPanel;
import org.woehlke.computer.kurzweil.widgets.layouts.TabLayout;
import org.woehlke.computer.kurzweil.widgets.PanelSubtitle;
import org.woehlke.computer.kurzweil.widgets.StartStopButtonsPanel;
import org.woehlke.computer.kurzweil.commons.tabs.Tab;


@Log
@Getter
public class SimulatedEvolutionTab extends TabPanel implements Tab {

    private final ComputerKurzweilApplicationContext ctx;
    private final SimulatedEvolutionContext tabCtx;
    private final StartStopButtonsPanel startStopButtonsPanel;
    private final PanelSubtitle panelSubtitle;
    private final SimulatedEvolutionCanvas canvas;

    public SimulatedEvolutionTab(ComputerKurzweilApplicationContext ctx) {
        this.tabCtx = new SimulatedEvolutionContext(this);
        this.canvas = this.tabCtx.getCanvas();
        this.ctx = tabCtx.getCtx();
        this.setBorder( PanelBorder.getBorder() );
        this.setLayout(new TabLayout(this));
        this.setBounds(ctx.getFrameBounds());
        this.startStopButtonsPanel = new StartStopButtonsPanel( this );
        String subtitle = ctx.getProperties().getEvolution().getView().getSubtitle();
        this.panelSubtitle = new PanelSubtitle(subtitle);
        this.add(this.panelSubtitle);
        this.add(this.canvas);
        this.add(this.startStopButtonsPanel);
    }

    @Override
    public void start() {
        log.info("start");
        this.getTabCtx().start();
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stop");
        this.getTabCtx().stop();
        log.info("stopped");
    }

    @Override
    public void showMe() {
        log.info("showMe");
        this.setVisibleMe(true);
    }

    private void setVisibleMe(boolean visible){
        this.canvas.getPanelButtons().setVisible(visible);
        this.canvas.getStatisticsPanel().setVisible(visible);
        this.canvas.setVisible(visible);
        this.setVisible(visible);
    }

    @Override
    public String getTitle() {
        return this.tabCtx.getCtx().getProperties().getEvolution().getView().getTitle();
    }

    @Override
    public String getSubTitle() {
        return this.tabCtx.getCtx().getProperties().getEvolution().getView().getSubtitle();
    }

}
