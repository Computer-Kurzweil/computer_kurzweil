package org.woehlke.computer.kurzweil.commons.widgets;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.gui.GuiComponent;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.tabs.Tab;
import org.woehlke.computer.kurzweil.commons.layouts.FlowLayoutLeft;
import org.woehlke.computer.kurzweil.tabs.TabPanel;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolutionTab;

import javax.swing.*;
import javax.swing.border.CompoundBorder;

@Log4j2
@Getter
@ToString(exclude={"borderBottomButtonsPanel","layoutBottomButtonsPanel"})
public class PanelBottomButtons extends JPanel implements Startable, GuiComponent {

    private final FlowLayoutLeft layoutBottomButtonsPanel;
    private final CompoundBorder borderBottomButtonsPanel;
    private final PanelStartStopButtons startStopButtonsPanel;

    public PanelBottomButtons(Tab tab) {
        this.layoutBottomButtonsPanel = new FlowLayoutLeft();
        this.borderBottomButtonsPanel = tab.getCtx().getBottomButtonsPanelBorder();
        this.startStopButtonsPanel = new PanelStartStopButtons( tab );
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

    public void addActionListener(TabPanel myTabPanel) {
        startStopButtonsPanel.addActionListener(myTabPanel);
    }
}
