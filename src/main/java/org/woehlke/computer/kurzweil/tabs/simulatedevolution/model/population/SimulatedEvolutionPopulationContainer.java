package org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.population;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.Startable;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolution;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolutionContext;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.cell.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Log4j2
@Getter
@ToString(callSuper = true, exclude={"tabCtx","statistics"})
@EqualsAndHashCode(exclude={"tabCtx","statistics"})
public class SimulatedEvolutionPopulationContainer implements SimulatedEvolution {

    private final SimulatedEvolutionContext tabCtx;
    private final int initialPopulation;
    private final ConcurrentLinkedQueue<SimulatedEvolutionPopulation> statistics = new ConcurrentLinkedQueue<>();
    private final List<Cell> cells;
    private long worldIteration;

    public SimulatedEvolutionPopulationContainer(
        SimulatedEvolutionContext tabCtx
    ) {
        this.tabCtx = tabCtx;
        worldIteration = 0L;
        cells = new ArrayList<>();
        initialPopulation = this.tabCtx.getCtx().getProperties().getSimulatedevolution().getPopulation().getInitialPopulation();
        createInitialPopulation();
    }

    public void createInitialPopulation() {
        cells.clear();
        for (int i = 0; i < initialPopulation ; i++) {
            Cell cell = new Cell(this.tabCtx);
            cells.add(cell);
        }
    }

    public void push(SimulatedEvolutionPopulation populationCensus) {
        worldIteration++;
        populationCensus.setWorldIteration(worldIteration);
        statistics.add(populationCensus);
        if (statistics.size() > this.tabCtx.getCtx().getProperties().getSimulatedevolution().getControl().getQueueMaxLength()) {
            statistics.poll();
        }
        log.info(worldIteration + " : " + populationCensus);
    }

    public SimulatedEvolutionPopulation peek() {
        SimulatedEvolutionPopulation populationCensus = statistics.peek();
        if(populationCensus == null){
            populationCensus = new SimulatedEvolutionPopulation();
        }
        return populationCensus;
    }

}
