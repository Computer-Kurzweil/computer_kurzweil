package org.woehlke.computer.kurzweil.apps.cca;


import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.apps.TabType;
import org.woehlke.computer.kurzweil.widgets.borders.PanelBorder;
import org.woehlke.computer.kurzweil.commons.tabs.TabPanel;
import org.woehlke.computer.kurzweil.widgets.layouts.TabLayout;
import org.woehlke.computer.kurzweil.commons.tabs.Tab;

@Log
@Getter
public class CyclicCellularAutomatonTab extends TabPanel implements Tab {

    private final CyclicCellularAutomatonCanvas canvas;
    private final CyclicCellularAutomatonContext tabCtx;
    private final ComputerKurzweilApplicationContext ctx;

    private final static TabType TAB_TYPE = TabType.CYCLIC_CELLULAR_AUTOMATON;

    public CyclicCellularAutomatonTab(ComputerKurzweilApplicationContext ctx) {
        this.ctx = ctx;
        this.setBorder(PanelBorder.getBorder());
        this.setLayout(new TabLayout(this));
        this.setBounds(ctx.getFrameBounds());
        this.tabCtx = new CyclicCellularAutomatonContext(this);
        this.canvas=this.tabCtx.getCanvas();
        this.add(this.canvas.getPanelSubtitle());
        this.add(this.canvas);
        this.add(this.canvas.getNeighbourhoodButtonsPanel());
        this.add(this.canvas.getStartStopButtonsPanel());
    }

    @Override
    public void start() {
        log.info("start");
        this.showMe();
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
        this.canvas.getStartStopButtonsPanel().setVisible(true);
        this.canvas.getNeighbourhoodButtonsPanel().setVisible(true);
        this.canvas.getPanelSubtitle().setVisible(true);
        this.canvas.setVisible(true);
        this.setVisible(true);
    }

    @Override
    public String getTitle() {
        return ctx.getProperties().getCca().getView().getTitle();
    }

    @Override
    public String getSubTitle() {
        return ctx.getProperties().getCca().getView().getSubtitle();
    }
}
