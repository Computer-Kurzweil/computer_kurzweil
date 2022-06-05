package org.woehlke.computer.kurzweil.tabs.simulatedevolution.control;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.tabs.TabController;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.config.SimulatedEvolutionContext;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolutionTab;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.config.SimulatedEvolution;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.SimulatedEvolutionModel;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.population.CellPopulationRecord;

import java.io.Serializable;


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
@Getter
@ToString(callSuper = true, exclude={"tabCtx"})
@EqualsAndHashCode(callSuper = true, exclude={"tabCtx"})
/**
 * https://docs.oracle.com/en/java/javase/13/docs/api/java.base/java/util/concurrent/ThreadPoolExecutor.html
 * https://docs.oracle.com/en/java/javase/13/docs/api/java.base/java/util/concurrent/Executor.html
 * TODO: https://github.com/Computer-Kurzweil/computer_kurzweil/issues/18
 * TODO: https://github.com/Computer-Kurzweil/computer_kurzweil/issues/19
 * http://openbook.rheinwerk-verlag.de/javainsel9/javainsel_14_004.htm
 */
public class SimulatedEvolutionController extends Thread implements TabController, SimulatedEvolution, Serializable {

    private static final long serialVersionUID = 7526471155622776147L;

    private final SimulatedEvolutionContext tabCtx;
    private Boolean goOn;
    private final int threadSleepTimeConf;
    private final int initialPopulation;

    private final SimulatedEvolutionTab view;
    private final SimulatedEvolutionModel model;

    @Override
    public UncaughtExceptionHandler getUncaughtExceptionHandler() {
        return super.getUncaughtExceptionHandler();
    }

    public SimulatedEvolutionController(
      SimulatedEvolutionContext tabCtx
  ) {
      super(TAB_TYPE.name()+"-Controller");
      this.tabCtx = tabCtx;
      this.goOn = Boolean.TRUE;
      this.threadSleepTimeConf = this.tabCtx.getCtx().getProperties().getSimulatedevolution().getControl().getThreadSleepTime();
      this.initialPopulation = this.tabCtx.getCtx().getProperties().getSimulatedevolution().getPopulation().getInitialPopulation();
      this.view = this.tabCtx.getTab();
      this.model = this.tabCtx.getTabModel();
    }

  public void run() {
    boolean doMyJob;
    int threadSleepTime = this.threadSleepTimeConf;
      CellPopulationRecord population;
    do {
      synchronized (goOn) {
        doMyJob = goOn.booleanValue();
      }
      synchronized (this.tabCtx) {
          this.model.exec();
          this.view.updateStep();
          population = this.model.getPopulation();
          if(population.getPopulation() > this.initialPopulation) {
              threadSleepTime = this.threadSleepTimeConf + this.initialPopulation * (population.getPopulation() / this.initialPopulation);
          } else {
              threadSleepTime = this.threadSleepTimeConf;
          }
      }
      this.view.repaint();
      try {
        sleep( threadSleepTime );
      } catch (InterruptedException e) {
          log.info(e.getMessage());
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
