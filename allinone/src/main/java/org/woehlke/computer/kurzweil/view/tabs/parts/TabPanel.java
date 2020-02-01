package org.woehlke.computer.kurzweil.view.tabs.parts;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.config.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.control.events.UserSlot;
import org.woehlke.computer.kurzweil.control.startables.Startable;
import org.woehlke.computer.kurzweil.control.startables.StartablePanel;
import org.woehlke.computer.kurzweil.view.common.PanelBorder;
import org.woehlke.computer.kurzweil.view.common.PanelSubtitle;
import org.woehlke.computer.kurzweil.view.common.StartStopButtonsPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Log
public abstract class TabPanel extends StartablePanel implements ActionListener, Startable, UserSlot {

    @Getter
    protected final ComputerKurzweilApplicationContext ctx;

    @Getter
    protected final StartStopButtonsPanel startStopButtonsPanel;

    @Getter
    protected final PanelSubtitle panelSubtitle;

    public TabPanel(
        ComputerKurzweilApplicationContext ctx, String subtitle
    ) {
        this.ctx = ctx;
        this.startStopButtonsPanel = new StartStopButtonsPanel(this);
        this.panelSubtitle = new PanelSubtitle(subtitle);
        super.registerStartables(this.startStopButtonsPanel,this.panelSubtitle);
        this.setLayout(new TabPanelLayout(this));
        this.setBorder(PanelBorder.getBorder());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.startStopButtonsPanel.getStartButton()){
            this.startStopButtonsPanel.getStartButton().setEnabled(false);
            this.startStopButtonsPanel.getStopButton().setEnabled(true);
            this.start();
        }
        if(e.getSource() == this.startStopButtonsPanel.getStopButton()){
            this.startStopButtonsPanel.getStartButton().setEnabled(true);
            this.startStopButtonsPanel.getStopButton().setEnabled(false);
            this.stop();
        }
    }

    public void start(){
        super.start();
        showMe();
    }

    public void stop(){
        super.stop();
        hideMe();
    }

    public void update(){
        super.update();
        showMe();
    }

    public void showMe() {
        log.info("showMe");
        this.setVisible(true);
    }

    public void hideMe() {
        log.info("hideMe");
        this.setVisible(false);
    }
}
