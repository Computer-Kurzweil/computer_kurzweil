package org.woehlke.computer.kurzweil.tabs.randomwalk;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationTabbedPane;
import org.woehlke.computer.kurzweil.commons.tabs.Tab;
import org.woehlke.computer.kurzweil.commons.tabs.TabPanel;
import org.woehlke.computer.kurzweil.tabs.TabType;
import org.woehlke.computer.kurzweil.widgets.BottomButtonsPanel;

import java.awt.event.ActionEvent;

@Log4j2
@Getter
@ToString(callSuper = true, exclude = {"tabCtx"})
@EqualsAndHashCode(callSuper=true, exclude = {"tabCtx"})
public class WienerProcessTab extends TabPanel implements Tab {

    private final WienerProcessContext tabCtx;

    private final WienerProcessCanvas canvas;
    private final BottomButtonsPanel bottomButtonsPanel;

    public WienerProcessTab(ComputerKurzweilApplicationTabbedPane tabbedPane) {
        super(tabbedPane,TabType.RANDOM_WALK_WIENER_PROCESS);
        this.tabCtx = new WienerProcessContext(this);
        this.canvas = this.tabCtx.getCanvas();
        this.bottomButtonsPanel = new BottomButtonsPanel( this );
        this.add(this.panelSubtitle);
        this.add(this.canvas);
        this.add(this.bottomButtonsPanel);
        this.bottomButtonsPanel.getStartButton().addActionListener(this);
        this.bottomButtonsPanel.getStopButton().addActionListener(this);
        this.bottomButtonsPanel.getStartStopButtonsPanel().stop();
        this.ctx.getFrame().pack();
        showMe();
    }

    @Override
    public void start() {
        log.info("start");
        this.showMe();
        this.canvas.start();
        this.bottomButtonsPanel.getStartStopButtonsPanel().start();
        this.getTabCtx().startController();
        this.getTabCtx().getController().start();
        this.ctx.getFrame().pack();
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        log.info("started with canvas x="+x+" y="+y);
    }

    @Override
    public void stop() {
        log.info("stop");
        this.canvas.stop();
        this.bottomButtonsPanel.getStartStopButtonsPanel().stop();
        this.getTabCtx().stopController();
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        log.info("stopped with canvas x="+x+" y="+y);
    }

    @Override
    public void showMe() {
        log.info("showMe");
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        log.info("showMe with canvas x="+x+" y="+y+" this: "+this.toString());
    }

    @Override
    public String getTitle() {
        return ctx.getProperties().getRandomwalk().getView().getTitle();
    }

    @Override
    public String getSubTitle() {
        return ctx.getProperties().getRandomwalk().getView().getSubtitle();
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
