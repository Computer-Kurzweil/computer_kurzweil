package org.woehlke.computer.kurzweil.apps.evolution.model.statistics;

import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.ctx.ComputerKurzweilApplicationContext;

import java.util.concurrent.ConcurrentLinkedQueue;

@Log
public class SimulatedEvolutionStatistics {

  private final ConcurrentLinkedQueue<SimulatedEvolutionPopulationCensus> statistics = new ConcurrentLinkedQueue<>();

  private long worldIteration;

  private final ComputerKurzweilApplicationContext ctx;

  public SimulatedEvolutionStatistics(
      ComputerKurzweilApplicationContext ctx
  ) {
      this.ctx = ctx;
      worldIteration = 0L;
  }

  public void push(SimulatedEvolutionPopulationCensus populationCensus) {
    worldIteration++;
    populationCensus.setWorldIteration(worldIteration);
    statistics.add(populationCensus);
    if (statistics.size() > this.ctx.getProperties().getEvolution().getControl().getQueueMaxLength()) {
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
