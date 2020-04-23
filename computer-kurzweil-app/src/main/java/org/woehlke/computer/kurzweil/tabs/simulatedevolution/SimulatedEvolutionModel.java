package org.woehlke.computer.kurzweil.tabs.simulatedevolution;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.commons.tabs.TabModel;
import org.woehlke.computer.kurzweil.commons.model.LatticePoint;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.SimulatedEvolutionParameter;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.population.SimulatedEvolutionPopulationContainer;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.cell.Cell;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.SimulatedEvolutionWorldLattice;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.population.SimulatedEvolutionPopulation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;

/**
 * The World contains Water, Cells and Food.
 * It is the Data Model of the Simulation in a MVC Pattern.
 *
 * @author Thomas Woehlke
 * User: thomas
 * Date: 04.02.2006
 * Time: 19:06:20
 * @see Cell
 * @see SimulatedEvolutionWorldLattice
 * <p>
 * Simulated Evolution.
 * Artificial Life Simulation of Bacteria Motion depending on DNA.
 * <p>
 * &copy; 2006 - 2008 Thomas Woehlke.
 * http://thomas-woehlke.de/p/simulated-evolution/
 */
@Log4j2
@Getter
@ToString(exclude={"appCtx"})
@EqualsAndHashCode(exclude={"appCtx"},callSuper = false)
public class SimulatedEvolutionModel extends ForkJoinTask<Void> implements TabModel, SimulatedEvolution {

    private final SimulatedEvolutionContext appCtx;
    private final SimulatedEvolutionWorldLattice worldLattice;
    private final SimulatedEvolutionPopulationContainer populationContainer;
    private final SimulatedEvolutionParameter simulatedEvolutionParameter;
    private Boolean running;
    private SimulatedEvolutionPopulation population;

  public SimulatedEvolutionModel(
      SimulatedEvolutionContext appCtx
  ) {
      this.appCtx = appCtx;
      this.worldLattice = new SimulatedEvolutionWorldLattice(  this.appCtx);
      this.populationContainer = new SimulatedEvolutionPopulationContainer( this.appCtx);
      this.simulatedEvolutionParameter = new SimulatedEvolutionParameter();
      this.running = Boolean.FALSE;
      createNewState();
  }

  public boolean hasFood(int x, int y) {
    return worldLattice.hasFood(x, y);
  }

    public void toggleGardenOfEden() {
        log.info("toggleGardenOfEden");
        this.worldLattice.toggleGardenOfEden();
        this.simulatedEvolutionParameter.toggleGardenOfEden();
    }

    public void start() {
        log.info("start");
        synchronized (running) {
            running = Boolean.TRUE;
        }
        log.info("started "+this.toString());
    }

    public void stop() {
        log.info("stop");
        synchronized (running) {
            running = Boolean.FALSE;
        }
        log.info("stopped "+this.toString());
    }

    public List<Cell> getAllCells() {
        return populationContainer.getCells();
    }

    private void createNewState(){
        int foodPerDay = this.appCtx.getCtx().getProperties().getSimulatedevolution().getFood().getFoodPerDay();
        int foodPerDayGardenOfEden = this.appCtx.getCtx().getProperties().getSimulatedevolution().getGardenOfEden().getFoodPerDay();
        boolean gardenOfEdenEnabled = this.appCtx.getCtx().getProperties().getSimulatedevolution().getGardenOfEden().getGardenOfEdenEnabled();
        this.simulatedEvolutionParameter.setFoodPerDay(foodPerDay);
        this.simulatedEvolutionParameter.setFoodPerDayGardenOfEden(foodPerDayGardenOfEden);
        this.simulatedEvolutionParameter.setGardenOfEdenEnabled(gardenOfEdenEnabled);
    }

    public void increaseFoodPerDay() {
        simulatedEvolutionParameter.increaseFoodPerDay();
    }

    public void decreaseFoodPerDay(){
        simulatedEvolutionParameter.decreaseFoodPerDay();
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public Void getRawResult() {
        return null;
    }

    @Override
    protected void setRawResult(Void value) {

    }

    /**
     * One Step of Time in the World in which the Population of Bacteria Cell perform Life.
     * Every Cell moves, eats, dies of hunger, and it has sex. splitting into two children with changed DNA.
     */
    @Override
    protected boolean exec() {
        log.info("step");
        boolean step;
        synchronized (running) {
            step = running;
        }
        if(step) {
            worldLattice.letFoodGrow();
            List<Cell> nextPopulation = new ArrayList<>();
            LatticePoint pos;
            int food;
            for (Cell cell : populationContainer.getCells()) {
                pos = cell.getPosition();
                food = worldLattice.eat(pos);
                cell.eat(food);
                if (cell.move()) {
                    pos = cell.getPosition();
                    food = worldLattice.eat(pos);
                    cell.eat(food);
                    if (cell.isAbleForReproduction()) {
                        Cell child = cell.reproductionByCellDivision();
                        nextPopulation.add(child);
                    }
                }
                if(!cell.died()){
                    nextPopulation.add(cell);
                }
            }
            populationContainer.addNextPopulation(nextPopulation);
            log.info("stepped");
        } else {
            log.info("not stepped");
        }
        population = populationContainer.getCurrentGeneration();
        return true;
    }
}
