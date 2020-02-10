package org.woehlke.computer.kurzweil.tabs;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.apps.mandelbrot.MandelbrotContext;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.trashcan.signals.UserSignal;
import org.woehlke.computer.kurzweil.tabs.common.Tab;
import org.woehlke.computer.kurzweil.tabs.common.TabPanel;

@Log
@Getter
public class MandelbrotTab extends Tab implements TabPanel {

    private final MandelbrotContext appCtx;
    private final ComputerKurzweilApplicationContext ctx;

    private final String title;
    private final String subTitle;

    public MandelbrotTab(ComputerKurzweilApplicationContext ctx ) {
        this.ctx = ctx;
        this.title = ctx.getProperties().getMandelbrot().getView().getTitle();
        this.subTitle = ctx.getProperties().getMandelbrot().getView().getSubtitle();
        this.appCtx = new MandelbrotContext(this);
    }

    @Override
    public void start() {
        log.info("start");
        this.showMe();
        this.getAppCtx().start();
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stop");
        this.getAppCtx().stop();
        log.info("stopped");
    }

    @Override
    public void update() {
        log.info("update");
        this.getAppCtx().update();
        log.info("updated");
    }

    @Override
    public void showMe() {
        log.info("showMe");
        this.setVisible(true);
    }

    @Override
    public void hideMe() {
        log.info("hideMe");
        this.setVisible(false);
    }

    @Override
    public void handleUserSignal(UserSignal userSignal) {
        log.info("handleUserSignal: "+userSignal.name());
    }


}
