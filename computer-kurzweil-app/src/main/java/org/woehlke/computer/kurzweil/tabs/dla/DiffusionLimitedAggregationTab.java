package org.woehlke.computer.kurzweil.tabs.dla;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.tabs.ComputerKurzweilTabbedPane;
import org.woehlke.computer.kurzweil.tabs.TabPanel;
import org.woehlke.computer.kurzweil.tabs.Tab;
import org.woehlke.computer.kurzweil.tabs.dla.canvas.DiffusionLimitedAggregationCanvas;
import org.woehlke.computer.kurzweil.tabs.dla.config.DiffusionLimitedAggregation;
import org.woehlke.computer.kurzweil.tabs.dla.config.DiffusionLimitedAggregationContext;
import org.woehlke.computer.kurzweil.tabs.dla.model.DiffusionLimitedAggregationModel;
import org.woehlke.computer.kurzweil.tabs.dla.views.DiffusionLimitedAggregationTabPane;

import java.awt.event.ActionEvent;

@Log
@Getter
@ToString(callSuper = true, exclude = {"tabCtx","canvas"})
@EqualsAndHashCode(callSuper=true, exclude = {"tabCtx","canvas"})
public class DiffusionLimitedAggregationTab extends TabPanel implements Tab, DiffusionLimitedAggregation {

    private static final long serialVersionUID = 7526471155622776147L;

    private final DiffusionLimitedAggregationContext tabCtx;
    private final DiffusionLimitedAggregationCanvas canvas;
    private final DiffusionLimitedAggregationModel tabModel;
    private final DiffusionLimitedAggregationTabPane diffusionLimitedAggregationTabPane;

    public DiffusionLimitedAggregationTab(ComputerKurzweilTabbedPane tabbedPane) {
        super(tabbedPane, TAB_TYPE );
        this.tabCtx = new DiffusionLimitedAggregationContext(this );
        this.diffusionLimitedAggregationTabPane = new DiffusionLimitedAggregationTabPane( this );
        this.canvas = this.tabCtx.getCanvas();
        this.tabModel = this.canvas.getTabModel();
        this.add(this.panelSubtitle);
        this.add(this.canvas);
        this.add(this.diffusionLimitedAggregationTabPane);
        this.tabModel.stop();
        this.diffusionLimitedAggregationTabPane.stop();
    }

    @Override
    public void start() {
        log.info("start");
        this.tabModel.start();
        this.diffusionLimitedAggregationTabPane.start();
        this.tabCtx.startController();
        this.ctx.getFrame().pack();
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        super.ctx.getFrame().showMe();
        log.info("started with canvas x="+x+" y="+y);
    }

    @Override
    public void stop() {
        log.info("stop");
        this.tabModel.stop();
        this.tabCtx.stopController();
        this.diffusionLimitedAggregationTabPane.stop();
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        super.ctx.getFrame().showMe();
        log.info("stopped with canvas x="+x+" y="+y);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == this.diffusionLimitedAggregationTabPane.getStartButton()){
            super.ctx.getFrame().start();
            this.start();
        }
        if(ae.getSource() == this.diffusionLimitedAggregationTabPane.getStopButton()){
            super.ctx.getFrame().stop();
            this.stop();
        }
    }
}
