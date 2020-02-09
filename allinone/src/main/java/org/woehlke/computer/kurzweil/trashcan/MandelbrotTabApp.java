package org.woehlke.computer.kurzweil.trashcan;

import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.trashcan.signals.UserSignal;
import org.woehlke.computer.kurzweil.commons.AppGuiComponent;
import org.woehlke.computer.kurzweil.view.tabs.common.TabPanel;

import javax.swing.*;

@Deprecated
@Log
public class MandelbrotTabApp extends JPanel implements AppGuiComponent, Startable {

    public MandelbrotTabApp(TabPanel tab) {
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
        log.info("updated");
    }

    @Override
    public void showMe() {
        log.info("showMe");
    }

    @Override
    public void hideMe() {
        log.info("hideMe");
    }
}
