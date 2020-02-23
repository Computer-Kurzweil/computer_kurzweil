package org.woehlke.computer.kurzweil.commons.widgets;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.GuiComponentTab;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.tabs.Tab;
import org.woehlke.computer.kurzweil.commons.layouts.FlowLayoutLeft;

import javax.swing.*;
import javax.swing.border.CompoundBorder;

@Log4j2
@Getter
@ToString(exclude={"borderBottomButtonsPanel","layoutBottomButtonsPanel"})
public class BottomButtonsPanel extends JPanel implements Startable, GuiComponentTab {

    private final FlowLayoutLeft layoutBottomButtonsPanel;
    private final CompoundBorder borderBottomButtonsPanel;
    private final StartStopButtonsPanel startStopButtonsPanel;

    public BottomButtonsPanel(Tab tab) {
        this.layoutBottomButtonsPanel = new FlowLayoutLeft();
        this.borderBottomButtonsPanel = tab.getCtx().getBottomButtonsPanelBorder();
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
