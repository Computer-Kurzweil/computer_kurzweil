package org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.population;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolution;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolutionContext;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.cell.Cell;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Log
@Getter
@ToString(callSuper = true, exclude={"tabCtx","statistics","cells"})
@EqualsAndHashCode(exclude={"tabCtx","statistics","cells"})
public class SimulatedEvolutionPopulationContainer implements SimulatedEvolution, Serializable {

    private static final long serialVersionUID = 7526471155622776147L;

    private final SimulatedEvolutionContext tabCtx;
    private final int initialPopulation;
    private final ConcurrentLinkedQueue<SimulatedEvolutionPopulation> statistics = new ConcurrentLinkedQueue<>();
    private final List<Cell> cells;
    private long worldIteration;
    private final int queueMaxLength;

    public SimulatedEvolutionPopulationContainer(
        SimulatedEvolutionContext tabCtx
    ) {
        this.tabCtx = tabCtx;
        worldIteration = 0L;
        cells = new ArrayList<>();
        queueMaxLength = this.tabCtx.getCtx().getProperties().getSimulatedevolution().getControl().getQueueMaxLength();
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
        if (statistics.size() > queueMaxLength) {
            statistics.poll();
        }
        log.info(worldIteration + " : " + populationCensus);
    }

    public SimulatedEvolutionPopulation getCurrentGeneration() {
        SimulatedEvolutionPopulation currentGeneration = statistics.peek();
        if(currentGeneration == null){
            log.info(worldIteration + "statistics.peek() == null ");
            SimulatedEvolutionPopulation onePopulation = new SimulatedEvolutionPopulation();
            for (Cell cell : this.getCells()) {
                onePopulation.countStatusOfOneCell(cell.getLifeCycleStatus());
            }
            this.push(onePopulation);
            currentGeneration = onePopulation;
        }
        return currentGeneration;
    }

    public void addNextPopulation(List<Cell> nextPopulation) {
        cells.clear();
        cells.addAll(nextPopulation);
        SimulatedEvolutionPopulation onePopulation = new SimulatedEvolutionPopulation();
        for (Cell cell : this.getCells()) {
            onePopulation.countStatusOfOneCell(cell.getLifeCycleStatus());
        }
        this.push(onePopulation);
    }
}
