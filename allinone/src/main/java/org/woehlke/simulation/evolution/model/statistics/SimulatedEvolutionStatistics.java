package org.woehlke.simulation.evolution.model.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.woehlke.simulation.evolution.config.SimulatedEvolutionProperties;
import org.woehlke.simulation.evolution.control.SimulatedEvolutionControllerThread;
import org.woehlke.simulation.evolution.model.SimulatedEvolutionContext;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * TODO write doc.
 */
@Service
public class SimulatedEvolutionStatistics {

  /**
   * TODO write doc.
   */
  private ConcurrentLinkedQueue<SimulatedEvolutionPopulationCensus> statistics;

  private volatile long worldIteration;

  private SimulatedEvolutionPopulationCensus simulatedEvolutionWorldStatistics;

  private SimulatedEvolutionControllerThread controller;

  private final SimulatedEvolutionContext ctx;

  private final SimulatedEvolutionProperties properties;

  @Autowired
  public SimulatedEvolutionStatistics(
      SimulatedEvolutionContext ctx,
      SimulatedEvolutionProperties simulatedEvolutionProperties) {
      this.ctx = ctx;
      this.properties = simulatedEvolutionProperties;
      statistics = new ConcurrentLinkedQueue<>();
    worldIteration = 0L;
  }

  /**
   * TODO write doc.
   */
  public synchronized void add(SimulatedEvolutionPopulationCensus simulatedEvolutionWorldStatistics) {
    this.simulatedEvolutionWorldStatistics = simulatedEvolutionWorldStatistics;
      statistics.add(simulatedEvolutionWorldStatistics);
    if (statistics.size() > properties.getQueueMaxLength()) {
        statistics.poll();
    }
    worldIteration++;
    if(controller !=null){
        controller.updateLifeCycleCount();
    }
    //System.out.println(worldIteration + " : " + lifeCycleCount);
  }

  public SimulatedEvolutionPopulationCensus getSimulatedEvolutionWorldStatistics() {
    if(simulatedEvolutionWorldStatistics ==null){
      simulatedEvolutionWorldStatistics = new SimulatedEvolutionPopulationCensus();
    }
    return simulatedEvolutionWorldStatistics;
  }

    public long getWorldIteration() {
        return worldIteration;
    }
}
