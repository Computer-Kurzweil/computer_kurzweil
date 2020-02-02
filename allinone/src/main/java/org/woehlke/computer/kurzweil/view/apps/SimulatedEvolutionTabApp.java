package org.woehlke.computer.kurzweil.view.apps;

import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.control.commons.Startable;
import org.woehlke.computer.kurzweil.control.signals.UserSignal;
import org.woehlke.computer.kurzweil.control.commons.AppGuiComponent;
import org.woehlke.computer.kurzweil.view.tabs.common.TabPanel;

import javax.swing.*;

@Log
public class SimulatedEvolutionTabApp extends JPanel implements AppGuiComponent, Startable {

    public SimulatedEvolutionTabApp(TabPanel tab) {

    }

    @Override
    public void start() {
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stopped");
    }

    @Override
    public void handleUserSignal(UserSignal userSignal) {
        log.info("handleUserSignal: "+userSignal.name());
    }

    @Override
    public void update() {

    }

    @Override
    public void showMe() {

    }

    @Override
    public void hideMe() {

    }
}
