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

import java.awt.event.ActionEvent;


@Log
@Getter
public class SimulatedEvolutionTab extends TabPanel implements Tab {

    private final ComputerKurzweilApplicationContext ctx;
    private final SimulatedEvolutionContext tabCtx;
    private final StartStopButtonsPanel startStopButtonsPanel;
    private final PanelSubtitle panelSubtitle;
    private final SimulatedEvolutionCanvas canvas;

    public SimulatedEvolutionTab(ComputerKurzweilApplicationContext ctx) {
        this.ctx = ctx;
        this.setLayout(new TabLayout(this));
        this.tabCtx = new SimulatedEvolutionContext(this);
        this.canvas = this.tabCtx.getCanvas();
        this.startStopButtonsPanel = new StartStopButtonsPanel( this );
        String subtitle = ctx.getProperties().getEvolution().getView().getSubtitle();
        this.panelSubtitle = new PanelSubtitle(subtitle);
        this.add(this.panelSubtitle);
        this.add(this.canvas);
        this.add(this.startStopButtonsPanel);
        this.canvas.getPanelButtons().getFoodPanel().getButtonFoodPerDayIncrease().addActionListener(this);
        this.canvas.getPanelButtons().getFoodPanel().getButtonFoodPerDayDecrease().addActionListener(this);
        this.canvas.getPanelButtons().getGardenOfEdenPanel().getButtonToggleGardenOfEden().addActionListener(this);
        this.startStopButtonsPanel.stop();
       // this.setVisibleMe(true);
    }

    @Override
    public void start() {
        log.info("start");
        this.getTabCtx().startController();
        this.canvas.getWorld().start();
        this.getTabCtx().getController().start();
        this.setVisibleMe(true);
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stop");
        this.canvas.stop();
        this.getTabCtx().stopController();
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

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() ==  this.canvas.getPanelButtons().getFoodPanel().getButtonFoodPerDayIncrease()) {
            tabCtx.increaseFoodPerDay();
            this.canvas.getPanelButtons().getFoodPanel().getFoodPerDayField().setText(
                tabCtx.getSimulatedEvolution().getFoodPerDay()+""
            );
        } else if (ae.getSource() == this.canvas.getPanelButtons().getFoodPanel().getButtonFoodPerDayDecrease()) {
            tabCtx.decreaseFoodPerDay();
            this.canvas.getPanelButtons().getFoodPanel().getFoodPerDayField().setText(
                tabCtx.getSimulatedEvolution().getFoodPerDay()+""
            );
        } else if (ae.getSource() == this.canvas.getPanelButtons().getGardenOfEdenPanel().getButtonToggleGardenOfEden()) {
            tabCtx.toggleGardenOfEden();
            tabCtx.getStepper().toggleGardenOfEden();
            boolean selected = tabCtx.getSimulatedEvolution().isGardenOfEdenEnabled();
            this.canvas.getPanelButtons().getGardenOfEdenPanel().getGardenOfEdenEnabled().setSelected(selected);
        }
        if(ae.getSource() ==  this.startStopButtonsPanel.getStartButton()){
            this.start();
        }
        if(ae.getSource() ==  this.startStopButtonsPanel.getStopButton()){
            this.stop();
        }
    }
}
