package org.woehlke.computer.kurzweil.tabs.simulatedevolution.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Delegate;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.commons.Updateable;
import org.woehlke.computer.kurzweil.commons.tabs.TabModel;
import org.woehlke.computer.kurzweil.commons.model.LatticePoint;
import org.woehlke.computer.kurzweil.commons.widgets.SubTabImpl;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.config.SimulatedEvolution;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.config.SimulatedEvolutionContext;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.world.WorldParameter;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.population.CellPopulationContainer;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.cell.Cell;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.world.WorldLattice;
import org.woehlke.computer.kurzweil.tabs.simulatedevolution.model.population.CellPopulationRecord;

import javax.swing.*;
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
 * @see WorldLattice
 * <p>
 * Simulated Evolution.
 * Artificial Life Simulation of Bacteria Motion depending on DNA.
 * <p>
 * &copy; 2006 - 2008 Thomas Woehlke.
 * http://thomas-woehlke.de/p/simulated-evolution/
 */
@Log
@Getter
@ToString(exclude={"appCtx"})
@EqualsAndHashCode(exclude={"appCtx"},callSuper = false)
public class SimulatedEvolutionModel extends ForkJoinTask<Void> implements TabModel, SimulatedEvolution {

    private static final long serialVersionUID = 7526471155622776147L;

    private final SimulatedEvolutionContext appCtx;
    private final WorldLattice worldLattice;

    @Delegate(excludes={SubTabImpl.class, JPanel.class, Updateable.class})
    private final CellPopulationContainer populationContainer;
    private final WorldParameter worldParameter;
    private Boolean running;

  public SimulatedEvolutionModel(
      SimulatedEvolutionContext appCtx
  ) {
      this.appCtx = appCtx;
      this.worldLattice = new WorldLattice(  this.appCtx);
      this.populationContainer = new CellPopulationContainer( this.appCtx);
      this.worldParameter = new WorldParameter();
      this.running = Boolean.FALSE;
      createNewState();
  }

  public CellPopulationRecord getPopulation(){
      return populationContainer.getCurrentGeneration();
  }

  public boolean hasFood(int x, int y) {
    return worldLattice.hasFood(x, y);
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
        this.worldParameter.setFoodPerDay(foodPerDay);
        this.worldParameter.setFoodPerDayGardenOfEden(foodPerDayGardenOfEden);
        this.worldParameter.setGardenOfEdenEnabled(gardenOfEdenEnabled);
    }

    public void increaseFoodPerDay() {
        worldParameter.increaseFoodPerDay();
    }

    public void decreaseFoodPerDay(){
        worldParameter.decreaseFoodPerDay();
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
    public boolean exec() {
        //log.info("step");
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
            //log.info("stepped");
        } //else {
            //log.info("not stepped");
        //}
        return true;
    }




































    public void setGardenOfEdenEnabled() {
        this.worldParameter.setGardenOfEdenEnabled();
    }

    public void setGardenOfEdenDisabled() {
        this.worldParameter.setGardenOfEdenDisabled();
    }
}
