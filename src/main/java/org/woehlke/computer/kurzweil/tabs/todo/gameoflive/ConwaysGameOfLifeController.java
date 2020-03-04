package org.woehlke.computer.kurzweil.tabs.todo.gameoflive;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.application.ComputerKurzweilContext;
import org.woehlke.computer.kurzweil.commons.tabs.TabController;

@Log4j2
@Getter
public class ConwaysGameOfLifeController extends Thread implements TabController {

    private final ComputerKurzweilContext ctx;
    private final ConwaysGameOfLifeContext tabCtx;
    private final int threadSleepTime;

    private Boolean goOn;

    public ConwaysGameOfLifeController(
        ConwaysGameOfLifeContext tabCtx
    ) {
        super("Conways-Controller");
        this.tabCtx = tabCtx;
        this.ctx = tabCtx.getCtx();
        this.goOn = Boolean.TRUE;
        this.threadSleepTime = this.tabCtx.getCtx().getProperties().getGameoflive().getControl().getThreadSleepTime();
    }

    public void run() {
        log.info("run() - begin");
        boolean doIt;
        do {
            synchronized (this.goOn) {
                doIt = goOn.booleanValue();
            }
            synchronized (this.tabCtx) {
                //log.info("running");
                this.tabCtx.getTabModel().step();
                this.tabCtx.getCanvas().update();
                this.tabCtx.getTab().repaint();
            }
            try { super.sleep( this.threadSleepTime ); }
            catch (InterruptedException e) { log.info(e.getMessage()); }
        }
        while (doIt);
        log.info("run() - finished");
    }

    public void exit() {
        log.info("exit");
        try {
            synchronized (goOn) {
                goOn = Boolean.FALSE;
            }
            log.info("join");
            join();
            log.info("joined");
        } catch (InterruptedException e){
            log.info(e.getMessage());
        }
        log.info("exited");
    }
}
