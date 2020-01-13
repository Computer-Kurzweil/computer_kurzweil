package org.woehlke.simulation.evolution.model.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.woehlke.simulation.evolution.control.SimulatedEvolutionControllerThread;
import org.woehlke.simulation.evolution.model.SimulatedEvolutionContext;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

/**
 * TODO write doc.
 */
@Service
public class SimulatedEvolutionStatistics {

  /**
   * TODO write doc.
   */
  private ConcurrentLinkedQueue<SimulatedEvolutionPopulationCensus> statistics;

  private long worldIteration;

  private SimulatedEvolutionControllerThread controller;

  private final SimulatedEvolutionContext ctx;

  @Autowired
  public SimulatedEvolutionStatistics(
      SimulatedEvolutionContext ctx
  ) {
      this.ctx = ctx;
      statistics = new ConcurrentLinkedQueue<>();
      worldIteration = 0L;
      this.ctx.setStatisticsContainer(this);
  }

  /**
   * TODO write doc.
   */
  public void add(SimulatedEvolutionPopulationCensus populationCensus) {
    worldIteration++;
    populationCensus.setWorldIteration(worldIteration);
    statistics.add(populationCensus);
    if (statistics.size() > this.ctx.getProperties().getQueueMaxLength()) {
        statistics.poll();
    }
    if(controller !=null){
        controller.updateLifeCycleCount();
    }
    log.info(worldIteration + " : " + populationCensus);
  }

  public SimulatedEvolutionPopulationCensus getPopulationCensus() {
    SimulatedEvolutionPopulationCensus populationCensus = statistics.element();
    if(populationCensus == null){
        populationCensus = new SimulatedEvolutionPopulationCensus();
    }
    return populationCensus;
  }

    public long getWorldIteration() {
        return worldIteration;
    }

    public void setController(SimulatedEvolutionControllerThread controller) {
        this.controller = controller;
    }

    private static Logger log = Logger.getLogger(SimulatedEvolutionStatistics.class.getName());
}
