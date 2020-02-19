package org.woehlke.computer.kurzweil.tabs.dla;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.commons.tabs.TabPanel;
import org.woehlke.computer.kurzweil.tabs.TabType;
import org.woehlke.computer.kurzweil.widgets.BottomButtonsPanel;
import org.woehlke.computer.kurzweil.commons.tabs.Tab;
import java.awt.event.ActionEvent;

@Log
@Getter
@ToString(callSuper = true, exclude = {"tabCtx"})
@EqualsAndHashCode(callSuper=true, exclude = {"tabCtx"})
public class DiffusionLimitedAggregationTab extends TabPanel implements Tab {

    private final DiffusionLimitedAggregationContext tabCtx;
    private final DiffusionLimitedAggregationCanvas canvas;
    private final BottomButtonsPanel bottomButtonsPanel;

    public DiffusionLimitedAggregationTab(ComputerKurzweilApplicationContext ctx) {
        super(ctx, TabType.DIFFUSION_LIMITED_AGGREGATION,ctx.getProperties().getDla().getView().getSubtitle(),ctx.getProperties().getDla().getView().getTitle());
        this.tabCtx = new DiffusionLimitedAggregationContext(this );
        this.bottomButtonsPanel = new BottomButtonsPanel( this );
        this.canvas = this.tabCtx.getCanvas();
        this.add(this.panelSubtitle);
        this.add(this.canvas);
        this.add(this.bottomButtonsPanel);
        this.bottomButtonsPanel.getStartButton().addActionListener(this);
        this.bottomButtonsPanel.getStopButton().addActionListener(this);
        this.bottomButtonsPanel.getStartStopButtonsPanel().start();
        this.ctx.getFrame().pack();
        showMe();
    }

    @Override
    public void start() {
        log.info("start");
        this.bottomButtonsPanel.getStartStopButtonsPanel().start();
        this.tabCtx.startController();
        this.showMe();
        this.ctx.getFrame().pack();
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        log.info("start with canvas x="+x+" y="+y);
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stop");
        this.tabCtx.stopController();
        this.bottomButtonsPanel.getStartStopButtonsPanel().stop();
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        log.info("stop with canvas x="+x+" y="+y);
        log.info("stopped");
    }

    @Override
    public void showMe() {
        log.info("showMe" );
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        log.info("this: "+this.toString());
        log.info("showMe with canvas x="+x+" y="+y);
    }

    @Override
    public String getTitle() {
        return ctx.getProperties().getDla().getView().getTitle();
    }

    @Override
    public String getSubTitle() {
        return ctx.getProperties().getDla().getView().getSubtitle();
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == this.bottomButtonsPanel.getStartButton()){
            this.start();
        }
        if(ae.getSource() == this.bottomButtonsPanel.getStopButton()){
            this.stop();
        }
    }
}
