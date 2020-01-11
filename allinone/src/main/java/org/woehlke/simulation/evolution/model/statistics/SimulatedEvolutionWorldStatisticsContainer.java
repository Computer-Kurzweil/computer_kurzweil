package org.woehlke.simulation.evolution.model.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.evolution.config.SimulatedEvolutionProperties;
import org.woehlke.simulation.evolution.control.SimulatedEvolutionController;
import org.woehlke.simulation.evolution.model.SimulatedEvolutionContext;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * TODO write doc.
 */
@Component
public class SimulatedEvolutionWorldStatisticsContainer {

  /**
   * TODO write doc.
   */
  private ConcurrentLinkedQueue<SimulatedEvolutionWorldStatistics> statistics;

  private volatile long worldIteration;

  private SimulatedEvolutionWorldStatistics simulatedEvolutionWorldStatistics;

  private SimulatedEvolutionController controller;

  private final SimulatedEvolutionContext ctx;

  private final SimulatedEvolutionProperties properties;

  @Autowired
  public SimulatedEvolutionWorldStatisticsContainer(
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
  public synchronized void add(SimulatedEvolutionWorldStatistics simulatedEvolutionWorldStatistics) {
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

  public SimulatedEvolutionWorldStatistics getSimulatedEvolutionWorldStatistics() {
    if(simulatedEvolutionWorldStatistics ==null){
      simulatedEvolutionWorldStatistics = new SimulatedEvolutionWorldStatistics();
    }
    return simulatedEvolutionWorldStatistics;
  }

    public long getWorldIteration() {
        return worldIteration;
    }
}
