package org.woehlke.computer.kurzweil.commons.widgets;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.gui.GuiComponent;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.tabs.Tab;
import org.woehlke.computer.kurzweil.commons.layouts.FlowLayoutCenter;

import javax.swing.*;
import javax.swing.border.CompoundBorder;

@Log4j2
@Getter
@ToString(exclude={"border","layout","startButton","stopButton"})
public class PanelStartStopButtons extends JPanel implements Startable, GuiComponent {

    private final JButton startButton;
    private final JButton stopButton;
    private final FlowLayoutCenter layout;
    private final CompoundBorder border;

    public PanelStartStopButtons(Tab tab){
        String labelStartStopp = tab.getCtx().getProperties().getAllinone().getView().getStartStopp();
        String labelStart = tab.getCtx().getProperties().getAllinone().getView().getStart();
        String labelStop = tab.getCtx().getProperties().getAllinone().getView().getStop();
        layout = new FlowLayoutCenter();
        border = tab.getCtx().getBottomButtonsPanelBorder(labelStartStopp);
        this.setLayout(layout);
        this.setBorder(border);
        this.startButton = new JButton(labelStart);
        this.stopButton = new JButton(labelStop);
        this.add(this.startButton);
        this.add(this.stopButton);
        this.startButton.setEnabled(true);
        this.stopButton.setEnabled(false);
    }

    @Override
    public void start() {
        log.info("start");
        this.startButton.setEnabled(false);
        this.stopButton.setEnabled(true);
        showMe();
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stop");
        this.startButton.setEnabled(true);
        this.stopButton.setEnabled(false);
        showMe();
        log.info("stopped");
    }

    @Override
    public void showMe() {
    }

}
