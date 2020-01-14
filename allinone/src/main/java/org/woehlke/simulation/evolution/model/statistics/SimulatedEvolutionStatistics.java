package org.woehlke.simulation.evolution.model.statistics;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.woehlke.simulation.evolution.control.SimulatedEvolutionControllerThread;
import org.woehlke.simulation.evolution.model.SimulatedEvolutionContext;

import java.util.concurrent.ConcurrentLinkedQueue;

@Log
@Service
public class SimulatedEvolutionStatistics {

  private ConcurrentLinkedQueue<SimulatedEvolutionPopulationCensus> statistics;
  private long worldIteration;
  private final SimulatedEvolutionContext ctx;

  @Autowired
  public SimulatedEvolutionStatistics(
      SimulatedEvolutionContext ctx
  ) {
      this.ctx = ctx;
      statistics = new ConcurrentLinkedQueue<>();
      worldIteration = 0L;
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
    if(this.ctx !=null){
        this.ctx.updateLifeCycleCount();
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

}
