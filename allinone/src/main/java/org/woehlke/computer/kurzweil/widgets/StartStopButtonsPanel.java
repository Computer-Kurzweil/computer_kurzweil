package org.woehlke.computer.kurzweil.widgets;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.GuiComponentTab;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.commons.tabs.Tab;
import org.woehlke.computer.kurzweil.widgets.layouts.CenterFlowLayout;

import javax.swing.*;
import javax.swing.border.CompoundBorder;

@Log
@Getter
@ToString(exclude={"border","layout","startButton","stopButton"})
public class StartStopButtonsPanel extends JPanel implements Startable, GuiComponentTab {

    private final JButton startButton;
    private final JButton stopButton;
    private final CenterFlowLayout layout;
    private final CompoundBorder border;

    public StartStopButtonsPanel(Tab tab){
        String labelStartStopp = tab.getCtx().getProperties().getAllinone().getView().getStartStopp();
        String labelStart = tab.getCtx().getProperties().getAllinone().getView().getStart();
        String labelStop = tab.getCtx().getProperties().getAllinone().getView().getStop();
        layout = new CenterFlowLayout();
        border = tab.getCtx().getBottomButtonsPanelBorder(labelStartStopp);
        this.setLayout(layout);
        this.setBorder(border);
        this.startButton = new JButton(labelStart);
        this.stopButton = new JButton(labelStop);
        this.add(this.startButton);
        this.add(this.stopButton);
        this.startButton.setEnabled(false);
        this.stopButton.setEnabled(true);
    }

    @Override
    public void start() {
        log.info("start");
        this.startButton.setEnabled(true);
        this.stopButton.setEnabled(false);
        showMe();
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stop");
        this.startButton.setEnabled(false);
        this.stopButton.setEnabled(true);
        showMe();
        log.info("stopped");
    }

    @Override
    public void showMe() {
    }

}
