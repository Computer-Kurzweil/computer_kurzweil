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
@EqualsAndHashCode(exclude={"appCtx"})
public class SimulatedEvolutionModel implements TabModel,SimulatedEvolution {

    private final SimulatedEvolutionContext appCtx;
    private final SimulatedEvolutionWorldLattice worldLattice;
    private final SimulatedEvolutionPopulationContainer populationContainer;
    private final SimulatedEvolutionParameter simulatedEvolutionParameter;
    private Boolean running;

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
        worldLattice.toggleGardenOfEden();
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

    /**
     * One Step of Time in the World in which the Population of Bacteria Cell perform Life.
     * Every Cell moves, eats, dies of hunger, and it has sex. splitting into two children with changed DNA.
     */
    @Override
    public void step() {
      log.info("step");
      boolean step;
        synchronized (running) {
            step = running;
        }
        if(step) {
            worldLattice.letFoodGrow();
            LatticePoint pos;
            List<Cell> children = new ArrayList<>();
            List<Cell> died = new ArrayList<>();
            for (Cell cell : populationContainer.getCells()) {
                cell.move();
                if (cell.died()) {
                    died.add(cell);
                } else {
                    pos = cell.getPosition();
                    int food = worldLattice.eat(pos);
                    cell.eat(food);
                    if (cell.isAbleForReproduction()) {
                        Cell child = cell.reproductionByCellDivision();
                        children.add(child);
                    }
                }
            }
            for (Cell dead : died) {
                populationContainer.getCells().remove(dead);
            }
            populationContainer.getCells().addAll(children);
            SimulatedEvolutionPopulation onePopulation = new SimulatedEvolutionPopulation();
            for (Cell cell : populationContainer.getCells()) {
                onePopulation.countStatusOfOneCell(cell.getLifeCycleStatus());
            }
            populationContainer.push(onePopulation);
            log.info("stepped");
        } else {
            log.info("not stepped");
        }
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

}
