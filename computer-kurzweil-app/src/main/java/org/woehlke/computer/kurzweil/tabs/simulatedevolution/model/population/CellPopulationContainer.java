package org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.population;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.config.SimulatedEvolution;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.config.SimulatedEvolutionContext;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.cell.Cell;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.cell.CellLifeCycleStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Log
@Getter
@ToString(callSuper = true, exclude={"tabCtx","statistics","cells"})
@EqualsAndHashCode(exclude={"tabCtx","statistics","cells"})
public class CellPopulationContainer implements SimulatedEvolution, Serializable {

    private static final long serialVersionUID = 7526471155622776147L;

    private final SimulatedEvolutionContext tabCtx;
    private final int initialPopulation;
    private final ConcurrentLinkedQueue<CellPopulationRecord> statistics = new ConcurrentLinkedQueue<>();
    private final List<Cell> cells;
    private long worldIteration;
    private final int queueMaxLength;

    public CellPopulationContainer(
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

    public void push(CellPopulationRecord populationCensus) {
        worldIteration++;
        populationCensus.setWorldIteration(worldIteration);
        statistics.add(populationCensus);
        if (statistics.size() > queueMaxLength) {
            statistics.poll();
        }
        //log.info(worldIteration + " : " + populationCensus);
    }

    private CellPopulationRecord countPopulation() {
        CellPopulationRecord onePopulation = new CellPopulationRecord();
        for (Cell cell : this.getCells()) {
            switch (cell.getLifeCycleStatus()) {
                case YOUNG:
                    onePopulation.countYoungCell();
                    break;
                case YOUNG_AND_FAT:
                    onePopulation.countYoungAndFatCell();
                    break;
                case FULL_AGE:
                    onePopulation.countFullAgeCell();
                    break;
                case HUNGRY:
                    onePopulation.countHungryCell();
                    break;
                case OLD:
                    onePopulation.countOldCell();
                    break;
                case DEAD:
                    onePopulation.countDeadCell();
                    break;
            }
        }
        this.push(onePopulation);
        return onePopulation;
    }

    public CellPopulationRecord addNextPopulation(List<Cell> nextPopulation) {
        this.cells.clear();
        this.cells.addAll(nextPopulation);
        return this.countPopulation();
    }

    public CellPopulationRecord getCurrentGeneration() {
        CellPopulationRecord currentGeneration;
        try {
            currentGeneration = statistics.poll();
            if (currentGeneration == null) {
                currentGeneration = this.countPopulation();
            }
        } catch (Exception e){
            currentGeneration = this.countPopulation();
        }
        return currentGeneration;
    }
}
