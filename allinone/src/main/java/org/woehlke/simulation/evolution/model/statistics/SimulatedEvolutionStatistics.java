package org.woehlke.simulation.evolution.model.statistics;

import lombok.extern.java.Log;
import org.woehlke.simulation.evolution.model.SimulatedEvolutionContext;

import java.util.concurrent.ConcurrentLinkedQueue;

@Log
public class SimulatedEvolutionStatistics {

  private final ConcurrentLinkedQueue<SimulatedEvolutionPopulationCensus> statistics = new ConcurrentLinkedQueue<>();
  private long worldIteration;
  private final SimulatedEvolutionContext ctx;

  public SimulatedEvolutionStatistics(
      SimulatedEvolutionContext ctx
  ) {
      this.ctx = ctx;
      worldIteration = 0L;
  }

  /**
   * TODO write doc.
   */
  public void push(SimulatedEvolutionPopulationCensus populationCensus) {
    worldIteration++;
    populationCensus.setWorldIteration(worldIteration);
    statistics.add(populationCensus);
    if (statistics.size() > this.ctx.getProperties().getQueueMaxLength()) {
        statistics.poll();
    }
    log.info(worldIteration + " : " + populationCensus);
  }


    public SimulatedEvolutionPopulationCensus peek() {
        SimulatedEvolutionPopulationCensus populationCensus = statistics.peek();
        if(populationCensus == null){
            populationCensus = new SimulatedEvolutionPopulationCensus();
        }
        return populationCensus;
    }
}
