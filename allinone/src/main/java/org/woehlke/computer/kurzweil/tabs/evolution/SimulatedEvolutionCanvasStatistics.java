package org.woehlke.computer.kurzweil.tabs.evolution;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.tabs.evolution.population.SimulatedEvolutionPopulationCensus;

import java.util.concurrent.ConcurrentLinkedQueue;

@Log
@Getter
@ToString
public class SimulatedEvolutionCanvasStatistics {

    @ToString.Exclude
    private final SimulatedEvolutionContext appCtx;

    @ToString.Exclude
    private final ConcurrentLinkedQueue<SimulatedEvolutionPopulationCensus> statistics = new ConcurrentLinkedQueue<>();

    private long worldIteration;

    public SimulatedEvolutionCanvasStatistics(
        SimulatedEvolutionContext appCtx
    ) {
        this.appCtx = appCtx;
        worldIteration = 0L;
    }

    public void push(SimulatedEvolutionPopulationCensus populationCensus) {
        worldIteration++;
        populationCensus.setWorldIteration(worldIteration);
        statistics.add(populationCensus);
        if (statistics.size() > this.appCtx.getCtx().getProperties().getEvolution().getControl().getQueueMaxLength()) {
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
