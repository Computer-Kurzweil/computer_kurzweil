package org.woehlke.simulation.evolution.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.evolution.config.SimulatedEvolutionProperties;
import org.woehlke.simulation.evolution.control.ControllerThreadDesktop;
import org.woehlke.simulation.evolution.control.ObjectRegistry;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * TODO write doc.
 */
@Component
public class LifeCycleCountContainer {

  /**
   * TODO write doc.
   */
  private ConcurrentLinkedQueue<LifeCycleCount> count;

  private volatile long worldIteration;

  private LifeCycleCount lifeCycleCount;

  private final ControllerThreadDesktop controllerThreadDesktop;

  private final ObjectRegistry ctx;

  private final SimulatedEvolutionProperties simulatedEvolutionProperties;

  @Autowired
  public LifeCycleCountContainer(ControllerThreadDesktop controllerThreadDesktop, ObjectRegistry ctx, SimulatedEvolutionProperties simulatedEvolutionProperties) {
      this.controllerThreadDesktop = controllerThreadDesktop;
      this.ctx = ctx;
      this.simulatedEvolutionProperties = simulatedEvolutionProperties;
      count = new ConcurrentLinkedQueue<>();
    worldIteration = 0L;
  }

  /**
   * TODO write doc.
   */
  public synchronized void add(LifeCycleCount lifeCycleCount) {
    this.lifeCycleCount = lifeCycleCount;
    count.add(lifeCycleCount);
    if (count.size() > simulatedEvolutionProperties.getQueueMaxLength()) {
      count.poll();
    }
    worldIteration++;
    if(controllerThreadDesktop!=null){
        controllerThreadDesktop.updateLifeCycleCount();
    }
    //System.out.println(worldIteration + " : " + lifeCycleCount);
  }

  public LifeCycleCount getLifeCycleCount() {
    if(lifeCycleCount==null){
      lifeCycleCount = new LifeCycleCount();
    }
    return lifeCycleCount;
  }

    public long getWorldIteration() {
        return worldIteration;
    }
}
