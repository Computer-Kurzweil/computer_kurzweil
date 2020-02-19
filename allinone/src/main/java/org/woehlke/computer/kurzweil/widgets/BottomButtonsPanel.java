package org.woehlke.computer.kurzweil.widgets;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.GuiComponentTab;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.commons.tabs.Tab;
import org.woehlke.computer.kurzweil.widgets.layouts.FlowLayoutLeft;

import javax.swing.*;
import javax.swing.border.CompoundBorder;

@Log
@Getter
@ToString(exclude={"borderBottomButtonsPanel","layoutBottomButtonsPanel","startStopButtonsPanel"})
public class BottomButtonsPanel extends JPanel implements Startable, GuiComponentTab {

    private final FlowLayoutLeft layoutBottomButtonsPanel;
    private final CompoundBorder borderBottomButtonsPanel;
    private final StartStopButtonsPanel startStopButtonsPanel;

    public BottomButtonsPanel(Tab tab) {
        this.layoutBottomButtonsPanel = new FlowLayoutLeft();
        this.borderBottomButtonsPanel = tab.getCtx().getBorder();
        this.startStopButtonsPanel = new StartStopButtonsPanel( tab );
        this.add(this.startStopButtonsPanel);
    }

    public JButton getStartButton(){
       return this.startStopButtonsPanel.getStartButton();
    }

    public JButton getStopButton(){
        return this.startStopButtonsPanel.getStopButton();
    }

    @Override
    public void showMe() {
        this.startStopButtonsPanel.showMe();
    }

    @Override
    public void start() {
        this.startStopButtonsPanel.start();
    }

    @Override
    public void stop() {
        this.startStopButtonsPanel.stop();
    }
}
