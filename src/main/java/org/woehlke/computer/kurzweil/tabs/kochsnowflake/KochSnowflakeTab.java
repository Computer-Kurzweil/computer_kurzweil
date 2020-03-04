package org.woehlke.computer.kurzweil.tabs.kochsnowflake;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.widgets.PanelBottomButtons;
import org.woehlke.computer.kurzweil.tabs.ComputerKurzweilTabbedPane;
import org.woehlke.computer.kurzweil.tabs.Tab;
import org.woehlke.computer.kurzweil.tabs.TabPanel;
import org.woehlke.computer.kurzweil.tabs.TabType;
import org.woehlke.computer.kurzweil.tabs.kochsnowflake.canvas.KochSnowflakeButtons;

import java.awt.event.ActionEvent;

import static org.woehlke.computer.kurzweil.tabs.TabType.KOCH_SNOWFLAKE;

@Log4j2
@Getter
@ToString(callSuper = true, exclude = {"tabCtx"})
@EqualsAndHashCode(callSuper=true, exclude = {"tabCtx"})
public class KochSnowflakeTab extends TabPanel implements Tab {

    private final static TabType tabType = KOCH_SNOWFLAKE;

    private final KochSnowflakeContext tabCtx;
    private final KochSnowflakeCanvas canvas;

    private final KochSnowflakeButtons neighbourhoodButtonsPanel;
    private final PanelBottomButtons bottomButtonsPanel;

    public KochSnowflakeTab(ComputerKurzweilTabbedPane tabbedPane) {
        super(tabbedPane,tabType);
        this.tabCtx = new KochSnowflakeContext(this);
        this.canvas = this.tabCtx.getCanvas();
        this.neighbourhoodButtonsPanel = new KochSnowflakeButtons(this.canvas);
        this.bottomButtonsPanel = new PanelBottomButtons( this );
        this.bottomButtonsPanel.add(this.neighbourhoodButtonsPanel);
        this.add(this.panelSubtitle);
        this.add(this.canvas);
        this.add(this.bottomButtonsPanel);
        this.neighbourhoodButtonsPanel.getButtonVonNeumann().addActionListener(this);
        this.neighbourhoodButtonsPanel.getButtonMoore().addActionListener(this);
        this.neighbourhoodButtonsPanel.getButtonWoehlke().addActionListener(this);
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
        return ctx.getProperties().getKochsnowflake().getView().getTitle();
    }

    @Override
    public String getSubTitle() {
        return ctx.getProperties().getKochsnowflake().getView().getSubtitle();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() ==  this.neighbourhoodButtonsPanel.getButtonVonNeumann()) {
            this.canvas.startWithNeighbourhoodVonNeumann();
            this.start();
        } else if (ae.getSource() ==  this.neighbourhoodButtonsPanel.getButtonMoore()) {
            this.canvas.startWithNeighbourhoodMoore();
            this.start();
        } else if (ae.getSource() ==  this.neighbourhoodButtonsPanel.getButtonWoehlke()) {
            this.canvas.startWithNeighbourhoodWoehlke();
            this.start();
        }
        if(ae.getSource() == this.bottomButtonsPanel.getStartButton()){
            super.ctx.getFrame().start();
        }
        if(ae.getSource() == this.bottomButtonsPanel.getStopButton()){
            super.ctx.getFrame().stop();
        }
    }
}
