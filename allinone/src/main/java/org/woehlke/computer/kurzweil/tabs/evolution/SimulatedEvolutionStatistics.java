package org.woehlke.computer.kurzweil.tabs.evolution;

import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.tabs.evolution.population.SimulatedEvolutionPopulationCensus;

import java.util.concurrent.ConcurrentLinkedQueue;

@Log
public class SimulatedEvolutionStatistics {

  private final ConcurrentLinkedQueue<SimulatedEvolutionPopulationCensus> statistics = new ConcurrentLinkedQueue<>();

  private long worldIteration;

  private final SimulatedEvolutionContext appCtx;

  public SimulatedEvolutionStatistics(
      SimulatedEvolutionContext appCtx
  ) {
      this.appCtx = appCtx;
      worldIteration = 0L;
  }

  public void push(SimulatedEvolutionPopulationCensus populationCensus) {
    worldIteration++;
    populationCensus.setWorldIteration(worldIteration);
    statistics.add(populationCensus);
    if (statistics.size() >  this.appCtx.getCtx().getProperties().getEvolution().getControl().getQueueMaxLength()) {
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
