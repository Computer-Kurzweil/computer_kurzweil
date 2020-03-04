package org.woehlke.computer.kurzweil.tabs.dla;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.tabs.TabController;
import org.woehlke.computer.kurzweil.tabs.TabType;

import static org.woehlke.computer.kurzweil.tabs.TabType.DIFFUSION_LIMITED_AGGREGATION;

/**
 * Diffusion Limited Aggregation.
 *
 * (C) 2006 - 2013 Thomas Woehlke.
 * https://thomas-woehlke.blogspot.com/2016/01/diffusion-limited-aggregation.html
 * @author Thomas Woehlke
 *
 * Date: 05.02.2006
 * Time: 00:36:20
 */
@Log4j2
@Getter
@ToString(callSuper=true,exclude={"tabCtx"})
@EqualsAndHashCode(callSuper=true,exclude={"tabCtx"})
public class DiffusionLimitedAggregationController extends Thread
        implements TabController {

    private final static TabType tabType = DIFFUSION_LIMITED_AGGREGATION;

    private final DiffusionLimitedAggregationContext tabCtx;
    private final int threadSleepTime;
    private Boolean goOn;

    public DiffusionLimitedAggregationController(
        DiffusionLimitedAggregationContext tabCtx
    ) {
        super(tabType.name()+"-Controller");
        this.tabCtx = tabCtx;
        this.goOn = Boolean.TRUE;
        this.threadSleepTime = this.tabCtx.getCtx().getProperties().getDla().getControl().getThreadSleepTime();
    }

    public void run() {
        log.info("run() started");
        boolean doIt;
        do {
            synchronized (goOn) {
                doIt = goOn.booleanValue();
            }
            synchronized ( this.tabCtx){
                this.tabCtx.getTabModel().step();
                this.tabCtx.getCanvas().update();
                this.tabCtx.getTab().repaint();
            }
            try { sleep( this.threadSleepTime ); }
            catch (InterruptedException e) { e.printStackTrace(); }
        }
        while (doIt);
        log.info("run() finished");
    }

    public void exit() {
        log.info("exit");
        try {
            synchronized (goOn) {
                goOn = Boolean.FALSE;
            }
            join();
            log.info("exited");
        } catch (InterruptedException e){
            log.info(e.getMessage());
        }
    }

}
