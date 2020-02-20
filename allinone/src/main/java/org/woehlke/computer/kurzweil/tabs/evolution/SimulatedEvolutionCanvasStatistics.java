package org.woehlke.computer.kurzweil.tabs.evolution;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.tabs.evolution.population.SimulatedEvolutionPopulationCensus;

import java.util.concurrent.ConcurrentLinkedQueue;

@Log4j2
@Getter
@ToString(callSuper = true, exclude={"tabCtx","statistics"})
@EqualsAndHashCode(exclude={"tabCtx","statistics"})
public class SimulatedEvolutionCanvasStatistics {


    private final SimulatedEvolutionContext tabCtx;
    private final ConcurrentLinkedQueue<SimulatedEvolutionPopulationCensus> statistics = new ConcurrentLinkedQueue<>();
    private long worldIteration;

    public SimulatedEvolutionCanvasStatistics(
        SimulatedEvolutionContext tabCtx
    ) {
        this.tabCtx = tabCtx;
        worldIteration = 0L;
    }

    public void push(SimulatedEvolutionPopulationCensus populationCensus) {
        worldIteration++;
        populationCensus.setWorldIteration(worldIteration);
        statistics.add(populationCensus);
        if (statistics.size() > this.tabCtx.getCtx().getProperties().getEvolution().getControl().getQueueMaxLength()) {
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
