package org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.population;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolution;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.SimulatedEvolutionContext;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.Cell;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.WorldPoint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
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
        WorldPoint max = new WorldPoint(640,400);
        WorldPoint position = new WorldPoint(40,40);
        Random random = new Random((new Date()).getTime());
        for (int i = 0; i < initialPopulation ; i++) {
            Cell cell = new Cell(max,position,random);
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
            currentGeneration = new SimulatedEvolutionPopulation();
        }
        return currentGeneration;
    }
}
