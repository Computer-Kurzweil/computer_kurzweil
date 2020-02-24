package org.woehlke.computer.kurzweil.tabs.randomwalk;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.tabs.TabController;

/**
 * Cyclic Cellular Automaton.
 *
 * (C) 2006 - 2013 Thomas Woehlke.
 * http://thomas-woehlke.de/p/cyclic-cellular-automaton/
 * @author Thomas Woehlke
 *
 * Date: 05.02.2006
 * Time: 00:36:20
 */
@Log4j2
@Getter
public class RandomWalkController extends Thread
        implements TabController {

    private static final long serialVersionUID = 3642865135701767557L;
    private final RandomWalkContext tabCtx;
    private final int threadSleepTime;

    private Boolean goOn;

    public RandomWalkController(
        RandomWalkContext tabCtx
    ) {
        super("Random Walk-Controller");
        this.tabCtx = tabCtx;
        this.goOn = Boolean.TRUE;
        this.threadSleepTime = tabCtx.getCtx().getProperties().getRandomwalk().getControl().getThreadSleepTime();;
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
                this.tabCtx.getStepper().step();
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
