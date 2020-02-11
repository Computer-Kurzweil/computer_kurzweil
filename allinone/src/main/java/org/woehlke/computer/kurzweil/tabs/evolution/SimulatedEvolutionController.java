package org.woehlke.computer.kurzweil.tabs.evolution;

import lombok.Getter;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.tabs.TabController;


/**
 * The ControllerThreadApplet controls the Interactions between Model and View (MVC-Pattern).
 * <p>
 * Simulated Evolution.
 * Artificial Life Simulation of Bacteria Motion depending on DNA.
 * <p>
 * &copy; 2006 - 2013 Thomas Woehlke.
 * http://thomas-woehlke.de/p/simulated-evolution/
 *
 * @author Thomas Woehlke
 * Date: 05.02.2006
 * Time: 00:36:20
 */
@Log
public class SimulatedEvolutionController extends Thread implements TabController {

    @Getter
    private final SimulatedEvolutionContext appCtx;

    private Boolean goOn;

    private final int time2wait;

  public SimulatedEvolutionController(
      SimulatedEvolutionContext appCtx
  ) {
      super("DNA-Controller");
      this.appCtx = appCtx;
      this.goOn = Boolean.TRUE;
      this.time2wait = this.appCtx.getCtx().getProperties().getEvolution().getControl().getTime2wait();
  }

  public void run() {
    boolean doMyJob;
    do {
      synchronized (goOn) {
        doMyJob = goOn.booleanValue();
      }
      if( this.appCtx != null){
        synchronized (this.appCtx) {
            this.appCtx.getStepper().step();
        }
        synchronized (this.appCtx) {
            this.appCtx.getCanvas().update();
        }
      }
      try {
        sleep( this.time2wait );
      } catch (InterruptedException e) {
        System.out.println(e.getLocalizedMessage());
      }
    }
    while (doMyJob);
  }

    @Override
    public void exit() {
        try {
            synchronized (goOn) {
                goOn = Boolean.FALSE;
            }
            join();
        } catch (InterruptedException e){
            log.info(e.getMessage());
        }
    }

}
