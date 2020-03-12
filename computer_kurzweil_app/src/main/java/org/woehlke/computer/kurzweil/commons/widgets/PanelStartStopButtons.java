package org.woehlke.computer.kurzweil.commons.widgets;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.tabs.Tab;
import org.woehlke.computer.kurzweil.tabs.TabPanel;
import org.woehlke.computer.kurzweil.tabs.mandelbrot.MandelbrotTab;

import javax.swing.*;

@Log4j2
@ToString(exclude={"startButton","stopButton"})
public class PanelStartStopButtons extends SubTabImpl implements Startable, SubTab {

    private final String labelStart;
    private final String labelStop;
    @Getter
    private final JButton startButton;
    @Getter
    private final JButton stopButton;

    public PanelStartStopButtons(Tab tab){
        super(tab.getCtx().getProperties().getAllinone().getView().getStartStopp(),tab.getCtx().getProperties());
       // PanelWithTitleBorder panelWithTitleBorder = new PanelWithTitleBorder(super.getTitle(),tab.getCtx().getProperties());
        labelStart = tab.getCtx().getProperties().getAllinone().getView().getStart();
        labelStop = tab.getCtx().getProperties().getAllinone().getView().getStop();
        this.startButton = new JButton(labelStart);
        this.stopButton = new JButton(labelStop);
        this.startButton.setEnabled(true);
        this.stopButton.setEnabled(false);
        //panelWithTitleBorder.add(this.startButton);
        //panelWithTitleBorder.add(this.stopButton);
        this.add(this.startButton);
        this.add(this.stopButton);
        //this.add(panelWithTitleBorder);
        this.startButton.addActionListener(tab);
        this.stopButton.addActionListener(tab);
    }

    @Override
    public void start() {
        log.info("start");
        this.startButton.setEnabled(false);
        this.stopButton.setEnabled(true);
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stop");
        this.startButton.setEnabled(true);
        this.stopButton.setEnabled(false);
        log.info("stopped");
    }

}
