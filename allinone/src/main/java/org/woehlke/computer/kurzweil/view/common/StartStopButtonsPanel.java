package org.woehlke.computer.kurzweil.view.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.control.signals.UserSignal;
import org.woehlke.computer.kurzweil.control.commons.Startable;
import org.woehlke.computer.kurzweil.control.commons.AppGuiComponent;
import org.woehlke.computer.kurzweil.view.tabs.common.TabPanel;

import javax.swing.*;

@Log
@ToString
@EqualsAndHashCode(callSuper=true)
public class StartStopButtonsPanel extends JPanel implements Startable, AppGuiComponent {

    @Getter private final JButton startButton;
    @Getter private final JButton stopButton;

    public StartStopButtonsPanel(TabPanel tab) {
        String getStartStopp = tab.getCtx().getProperties().getAllinone().getView().getStartStopp();
        String getStart = tab.getCtx().getProperties().getAllinone().getView().getStart();
        String getStop = tab.getCtx().getProperties().getAllinone().getView().getStop();
        this.setLayout(new StartStopButtonsPanelLayout());
        this.setBorder(PanelBorder.getBorder(getStartStopp));
        this.startButton = new JButton(getStart);
        this.stopButton = new JButton(getStop);
        this.add(this.startButton);
        this.add(this.stopButton);
        this.startButton.addActionListener(tab);
        this.stopButton.addActionListener(tab);
        this.startButton.setEnabled(true);
        this.stopButton.setEnabled(false);
    }

    @Override
    public void start() {
        this.setVisible(true);
        this.repaint();
    }

    public void stop() {
        this.setVisible(false);
    }

    @Override
    public void update() {
        this.setVisible(true);
        this.repaint();
    }

    @Override
    public void handleUserSignal(UserSignal userSignal) {
        log.info("handleUserSignal: "+userSignal.name());
    }
}
