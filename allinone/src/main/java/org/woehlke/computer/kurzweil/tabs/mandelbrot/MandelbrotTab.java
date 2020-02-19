package org.woehlke.computer.kurzweil.tabs.mandelbrot;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.commons.tabs.TabPanel;
import org.woehlke.computer.kurzweil.commons.tabs.Tab;
import org.woehlke.computer.kurzweil.tabs.TabType;
import org.woehlke.computer.kurzweil.widgets.BottomButtonsPanel;
import org.woehlke.computer.kurzweil.widgets.StartStopButtonsPanel;

import java.awt.event.ActionEvent;

@Log
@Getter
@ToString(callSuper = true)
public class MandelbrotTab extends TabPanel implements Tab {

    @ToString.Exclude
    private final MandelbrotContext tabCtx;
    private final MandelbrotButtons panelButtons;
    private final MandelbrotCanvas canvas;
    private final BottomButtonsPanel bottomButtonsPanel;

    public MandelbrotTab(ComputerKurzweilApplicationContext ctx ) {
        super(ctx, TabType.MANDELBROT_SET, ctx.getProperties().getMandelbrot().getView().getSubtitle(),ctx.getProperties().getMandelbrot().getView().getTitle());
        this.tabCtx = new MandelbrotContext(this, ctx);
        this.canvas = this.tabCtx.getCanvas();
        this.bottomButtonsPanel = new BottomButtonsPanel( this );
        this.panelButtons = new MandelbrotButtons( this.tabCtx);
        this.bottomButtonsPanel.add(this.panelButtons);
        this.add(this.panelSubtitle);
        this.add(this.canvas);
        this.add(this.bottomButtonsPanel);
        this.panelButtons.getPanelZoomButtons().getZoomOutButton().addActionListener(this);
        this.panelButtons.getPanelButtonsGroup().getRadioButtonsGroup().getRadioButtonsSwitch().addActionListener(this);
        this.panelButtons.getPanelButtonsGroup().getRadioButtonsGroup().getRadioButtonsZoom().addActionListener(this);
        this.bottomButtonsPanel.getStartButton().addActionListener(this);
        this.bottomButtonsPanel.getStopButton().addActionListener(this);
        this.bottomButtonsPanel.stop();
        this.ctx.getFrame().pack();
        showMe();
    }

    @Override
    public void start() {
        log.info("start");
        this.showMe();
        this.bottomButtonsPanel.start();
        this.tabCtx.startController();
        this.tabCtx.getController().start();
        this.ctx.getFrame().pack();
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        log.info("start with canvas x="+x+" y="+y);
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stop");
        this.bottomButtonsPanel.stop();
        this.tabCtx.stopController();
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        log.info("stop with canvas x="+x+" y="+y);
        log.info("stopped");
    }

    @Override
    public void showMe() {
        log.info("showMe");
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        log.info("this: "+this.toString());
        log.info("showMe with canvas x="+x+" y="+y);
        //this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == this.panelButtons.getPanelButtonsGroup().getRadioButtonsGroup().getRadioButtonsSwitch()){
            this.canvas.setModeSwitch();
        }
        if(ae.getSource() == this.panelButtons.getPanelButtonsGroup().getRadioButtonsGroup().getRadioButtonsZoom()){
            this.canvas.setModeZoom();
        }
        if(ae.getSource() == this.panelButtons.getPanelZoomButtons().getZoomOutButton()){
            this.canvas.zoomOut();
        }
        if(ae.getSource() ==  this.bottomButtonsPanel.getStartButton()){
            start();
        }
        if(ae.getSource() ==  this.bottomButtonsPanel.getStopButton()){
            stop();
        }
    }
}
