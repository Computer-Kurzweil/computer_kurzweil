package org.woehlke.computer.kurzweil.tabs.mandelbrot;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.tabs.ComputerKurzweilTabbedPane;
import org.woehlke.computer.kurzweil.tabs.TabPanel;
import org.woehlke.computer.kurzweil.tabs.Tab;
import org.woehlke.computer.kurzweil.tabs.TabType;
import org.woehlke.computer.kurzweil.commons.widgets.PanelBottomButtons;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.canvas.MandelbrotButtons;

import java.awt.event.ActionEvent;

import static org.woehlke.computer.kurzweil.tabs.TabType.MANDELBROT_SET;

@Log4j2
@Getter
@ToString(callSuper = true, exclude = {"tabCtx"})
@EqualsAndHashCode(callSuper=true, exclude = {"tabCtx"})
public class MandelbrotTab extends TabPanel implements Tab {

    private final static TabType tabType = MANDELBROT_SET;

    private final MandelbrotContext tabCtx;
    private final MandelbrotButtons panelButtons;
    private final MandelbrotCanvas canvas;
    private final PanelBottomButtons bottomButtonsPanel;

    public MandelbrotTab(ComputerKurzweilTabbedPane tabbedPane) {
        super(tabbedPane, tabType);
        this.tabCtx = new MandelbrotContext(this, ctx);
        this.canvas = this.tabCtx.getCanvas();
        this.bottomButtonsPanel = new PanelBottomButtons( this );
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
        this.bottomButtonsPanel.getStartStopButtonsPanel().stop();
        this.ctx.getFrame().pack();
        showMe();
    }

    @Override
    public void start() {
        log.info("start");
        this.showMe();
        this.bottomButtonsPanel.getStartStopButtonsPanel().start();
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
        this.bottomButtonsPanel.getStartStopButtonsPanel().stop();
        this.tabCtx.stopController();
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        log.info("stopped with canvas x="+x+" y="+y);
    }

    @Override
    public void showMe() {
        log.info("showMe");
        int x = this.canvas.getWidth();
        int y = this.canvas.getHeight();
        log.info("showedMe with canvas x="+x+" y="+y+" this: "+this.toString());
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
            super.ctx.getFrame().start();
        }
        if(ae.getSource() ==  this.bottomButtonsPanel.getStopButton()){
            super.ctx.getFrame().stop();
        }
    }
}
