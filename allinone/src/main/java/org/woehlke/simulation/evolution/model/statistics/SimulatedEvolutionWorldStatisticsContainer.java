package org.woehlke.simulation.evolution.model.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.evolution.config.SimulatedEvolutionProperties;
import org.woehlke.simulation.evolution.control.SimulatedEvolutionController;
import org.woehlke.simulation.evolution.control.SimulatedEvolutionContext;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * TODO write doc.
 */
@Component
public class SimulatedEvolutionWorldStatisticsContainer {

  /**
   * TODO write doc.
   */
  private ConcurrentLinkedQueue<SimulatedEvolutionWorldStatistics> count;

  private volatile long worldIteration;

  private SimulatedEvolutionWorldStatistics simulatedEvolutionWorldStatistics;

  private final SimulatedEvolutionController simulatedEvolutionController;

  private final SimulatedEvolutionContext ctx;

  private final SimulatedEvolutionProperties simulatedEvolutionProperties;

  @Autowired
  public SimulatedEvolutionWorldStatisticsContainer(SimulatedEvolutionController simulatedEvolutionController, SimulatedEvolutionContext ctx, SimulatedEvolutionProperties simulatedEvolutionProperties) {
      this.simulatedEvolutionController = simulatedEvolutionController;
      this.ctx = ctx;
      this.simulatedEvolutionProperties = simulatedEvolutionProperties;
      count = new ConcurrentLinkedQueue<>();
    worldIteration = 0L;
  }

  /**
   * TODO write doc.
   */
  public synchronized void add(SimulatedEvolutionWorldStatistics simulatedEvolutionWorldStatistics) {
    this.simulatedEvolutionWorldStatistics = simulatedEvolutionWorldStatistics;
    count.add(simulatedEvolutionWorldStatistics);
    if (count.size() > simulatedEvolutionProperties.getQueueMaxLength()) {
      count.poll();
    }
    worldIteration++;
    if(simulatedEvolutionController !=null){
        simulatedEvolutionController.updateLifeCycleCount();
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
