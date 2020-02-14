package org.woehlke.computer.kurzweil.widgets;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.tabs.cca.CyclicCellularAutomatonTab;
import org.woehlke.computer.kurzweil.commons.GuiComponentTab;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.commons.tabs.Tab;
import org.woehlke.computer.kurzweil.widgets.borders.PanelBorder;
import org.woehlke.computer.kurzweil.widgets.layouts.StartStopButtonsPanelLayout;

import javax.swing.*;

@Log
@Getter
public class StartStopButtonsPanel extends JPanel implements Startable, GuiComponentTab {

    private final JButton startButton;
    private final JButton stopButton;

    public StartStopButtonsPanel(Tab tab){
        String labelStartStopp = tab.getCtx().getProperties().getAllinone().getView().getStartStopp();
        String labelStart = tab.getCtx().getProperties().getAllinone().getView().getStart();
        String labelStop = tab.getCtx().getProperties().getAllinone().getView().getStop();
        this.setLayout(new StartStopButtonsPanelLayout());
        this.setBorder(PanelBorder.getBorder(labelStartStopp));
        this.startButton = new JButton(labelStart);
        this.stopButton = new JButton(labelStop);
        this.add(this.startButton);
        this.add(this.stopButton);
        this.startButton.addActionListener(tab.getTabCtx());
        this.stopButton.addActionListener(tab.getTabCtx());
        /*
        if(tab instanceof CyclicCellularAutomatonTab){
            start();
        } else {
            stop();
        }*/
    }

    @Override
    public void start() {
        this.startButton.setEnabled(false);
        this.stopButton.setEnabled(true);
        showMe();
    }

    @Override
    public void stop() {
        this.startButton.setEnabled(true);
        this.stopButton.setEnabled(false);
        showMe();
    }

    @Override
    public void showMe() {
        this.setVisible(true);
        repaint();
    }

}
