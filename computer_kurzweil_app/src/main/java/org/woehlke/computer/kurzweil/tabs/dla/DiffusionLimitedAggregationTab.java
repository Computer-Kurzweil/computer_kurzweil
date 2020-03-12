package org.woehlke.computer.kurzweil.tabs.dla;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.widgets.PanelStartStopButtons;
import org.woehlke.computer.kurzweil.tabs.ComputerKurzweilTabbedPane;
import org.woehlke.computer.kurzweil.tabs.TabPanel;
import org.woehlke.computer.kurzweil.tabs.Tab;

import java.awt.event.ActionEvent;

@Log4j2
@Getter
@ToString(callSuper = true, exclude = {"tabCtx","canvas"})
@EqualsAndHashCode(callSuper=true, exclude = {"tabCtx","canvas"})
public class DiffusionLimitedAggregationTab extends TabPanel implements Tab, DiffusionLimitedAggregation {

    private final DiffusionLimitedAggregationContext tabCtx;
    private final DiffusionLimitedAggregationCanvas canvas;
    private final DiffusionLimitedAggregationModel tabModel;
    private final PanelStartStopButtons bottomButtonsPanel;

    public DiffusionLimitedAggregationTab(ComputerKurzweilTabbedPane tabbedPane) {
        super(tabbedPane, TAB_TYPE );
        this.tabCtx = new DiffusionLimitedAggregationContext(this );
        this.bottomButtonsPanel = new PanelStartStopButtons( this );
        this.canvas = this.tabCtx.getCanvas();
        this.tabModel = this.canvas.getTabModel();
        this.add(this.panelSubtitle);
        this.add(this.canvas);
        this.add(this.bottomButtonsPanel);
        this.bottomButtonsPanel.getStartButton().addActionListener(this);
        this.bottomButtonsPanel.getStopButton().addActionListener(this);
        this.bottomButtonsPanel.start();
        showMe();
    }

    @Override
    public void start() {
        log.info("start");
        this.tabModel.start();
        this.bottomButtonsPanel.start();
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
        this.bottomButtonsPanel.stop();
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        super.ctx.getFrame().showMe();
        log.info("stopped with canvas x="+x+" y="+y);
    }

    @Override
    public void showMe() {
        log.info("showMe" );
        this.ctx.getFrame().pack();
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        log.info("this: "+this.toString());
        log.info("showMe with canvas x="+x+" y="+y);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == this.bottomButtonsPanel.getStartButton()){
            super.ctx.getFrame().start();
        }
        if(ae.getSource() == this.bottomButtonsPanel.getStopButton()){
            super.ctx.getFrame().stop();
        }
    }
}
