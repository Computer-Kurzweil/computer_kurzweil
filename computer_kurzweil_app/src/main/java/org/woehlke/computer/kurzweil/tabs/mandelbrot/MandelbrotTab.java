package org.woehlke.computer.kurzweil.tabs.mandelbrot;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.tabs.ComputerKurzweilTabbedPane;
import org.woehlke.computer.kurzweil.tabs.TabPanel;
import org.woehlke.computer.kurzweil.tabs.Tab;

import java.awt.event.ActionEvent;

@Log4j2
@Getter
@ToString(callSuper = true, exclude = {"tabCtx"})
@EqualsAndHashCode(callSuper=true, exclude = {"tabCtx"})
public class MandelbrotTab extends TabPanel implements Tab, Mandelbrot {

    private final MandelbrotContext tabCtx;
    private final MandelbrotCanvas canvas;
    private final MandelbrotTabPane mandelbrotTabPane;

    public MandelbrotTab(ComputerKurzweilTabbedPane tabbedPane) {
        super(tabbedPane, TAB_TYPE);
        this.tabCtx = new MandelbrotContext(this, ctx);
        this.canvas = this.tabCtx.getCanvas();
        this.mandelbrotTabPane = new MandelbrotTabPane(this);
        this.add(this.panelSubtitle);
        this.add(this.canvas);
        this.add(this.mandelbrotTabPane);
        this.mandelbrotTabPane.stop();
    }

    @Override
    public void start() {
        log.info("start");
        this.mandelbrotTabPane.start();
        this.tabCtx.getTabModel().start();
        this.tabCtx.startController();
        this.tabCtx.getController().start();
        this.ctx.getFrame().pack();
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        log.info("started with canvas x="+x+" y="+y);
    }

    @Override
    public void stop() {
        log.info("stop");
        this.tabCtx.getTabModel().stop();
        this.mandelbrotTabPane.stop();
        this.tabCtx.stopController();
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        log.info("stopped with canvas x="+x+" y="+y);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == this.mandelbrotTabPane.getPanelChooseMouseClickMode().getRadioButtonsSwitch()){
            this.canvas.setModeSwitch();
        }
        if(ae.getSource() == this.mandelbrotTabPane.getPanelChooseMouseClickMode().getRadioButtonsZoom()){
            this.canvas.setModeZoom();
        }
        if(ae.getSource() == this.mandelbrotTabPane.getPanelZoom().getZoomOutButton()){
            this.canvas.zoomOut();
        }
        if(ae.getSource() ==  this.mandelbrotTabPane.getStartButton()){
            super.ctx.getFrame().start();
        }
        if(ae.getSource() ==  this.mandelbrotTabPane.getStopButton()){
            super.ctx.getFrame().stop();
        }
    }
}
