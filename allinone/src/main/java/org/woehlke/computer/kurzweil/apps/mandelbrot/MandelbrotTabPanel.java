package org.woehlke.computer.kurzweil.apps.mandelbrot;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilApplicationContext;
import org.woehlke.computer.kurzweil.commons.tabs.TabPanel;
import org.woehlke.computer.kurzweil.commons.tabs.Tab;

@Log
@Getter
public class MandelbrotTabPanel extends TabPanel implements Tab {

    private final MandelbrotContext tabCtx;
    private final ComputerKurzweilApplicationContext ctx;

    private final String title;
    private final String subTitle;

    public MandelbrotTabPanel(ComputerKurzweilApplicationContext ctx ) {
        this.ctx = ctx;
        this.title = ctx.getProperties().getMandelbrot().getView().getTitle();
        this.subTitle = ctx.getProperties().getMandelbrot().getView().getSubtitle();
        this.tabCtx = new MandelbrotContext(this);
    }

    @Override
    public void start() {
        log.info("start");
        this.showMe();
        this.getTabCtx().start();
        log.info("started");
    }

    @Override
    public void stop() {
        log.info("stop");
        this.getTabCtx().stop();
        log.info("stopped");
    }

    @Override
    public void showMe() {
        log.info("showMe");
        this.setVisible(true);
    }

}
