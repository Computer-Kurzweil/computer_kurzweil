package org.woehlke.simulation.evolution.model.cell;

import lombok.Getter;
import org.woehlke.simulation.evolution.model.SimulatedEvolutionContext;

/**
 * State of the Cell which monitors age and getting enough food.
 * After an minimum age and at a minimum af eaten food,
 * the cell becomes able to reproduce by cell division.
 * If there is not enough food, the cell will not move and later it will die.
 * <p>
 * Simulated Evolution.
 * Artificial Life Simulation of Bacteria Motion depending on DNA.
 * <p>
 * &copy; 2006 - 2008 Thomas Woehlke.
 * http://thomas-woehlke.de/p/simulated-evolution/
 *
 * @author Thomas Woehlke
 * Date: 04.02.2006
 * Time: 23:12:31
 */
public class CellLifeCycle {

      /**
       * Status of the LifeCycle is fat, age and hunger.
       */
      @Getter
      private int fat;

      /**
       * Status of the LifeCycle is fat, age and hunger.
       */
      @Getter
      private int age;

      /**
       * Status of the LifeCycle is fat, age and hunger.
       */
      @Getter
      private int hunger;

    /**
     * Status of the LifeCycle is fat, age and hunger.
     */
    @Getter
    private int generation;

    @Getter
    private CellLifeCycleStatus status;

    @Getter
    private final CellLifeCycleConf conf;

    public CellLifeCycle(SimulatedEvolutionContext ctx) {
        this.conf = new CellLifeCycleConf(ctx);
        this.generation = 0;
        this.fat = 0;
        this.hunger = 0;
        this.age = 0;
        updateLifeCycleStatus();
    }

    private CellLifeCycle(CellLifeCycle other) {
        this.conf = other.conf;
        this.generation = other.generation;
        this.fat = other.fat;
        hunger = other.hunger;
        age = other.age;
        updateLifeCycleStatus();
    }

    public CellLifeCycle reproduction() {
        this.generation++;
        this.fat /= 2;
        this.hunger = 0;
        this.age = 0;
        return new CellLifeCycle(this);
    }

    /**
     * @return has enough age and fat for having sex.
     */
    public boolean isAbleForReproduction() {
        return (
            (age >= this.getConf().getConfFullAge()) &&
            (fat >= this.getConf().getConfFatMinimumForSex())
        );
    }

    /**
    * moving consumes food energy.
    *
    * @return true, if cell has enough energy to move.
    */
    public boolean move() {
        age++;
        boolean hasEnoughEnergyToMove = fat > 0;
        if (hasEnoughEnergyToMove) {
          fat--;
        } else {
          hunger++;
        }
        updateLifeCycleStatus();
        return hasEnoughEnergyToMove;
    }

    /**
    * @return died by hunger. found and eaten too few food and so too few fat.
    */
    public boolean isDead() {
        return (
            (hunger >= this.getConf().getConfMaxHunger()) ||
            (age    >= this.getConf().getConfMaxAge())
        );
    }

    /**
    * @param food eat the found food and add the energy to the cells fat.
    */
    public void eat(int food) {
        fat = Integer.min(fat + food * this.getConf().getConfFatPerFood(), this.getConf().getConfMaxFat());
    }

    private void updateLifeCycleStatus() {
        if (fat == 0 && hunger >= 0) {
            this.status = CellLifeCycleStatus.HUNGRY;
        }
        if (age < this.getConf().getConfFullAge()) {
            if (fat < this.getConf().getConfFatMinimumForSex()) {
                this.status = CellLifeCycleStatus.YOUNG;
            } else {
                this.status = CellLifeCycleStatus.YOUNG_AND_FAT;
            }
        } else {
            if (age < this.getConf().getConfAgeOfAdulthood()) {
                this.status = CellLifeCycleStatus.FULL_AGE;
            } else {
                if (age < this.getConf().getConfMaxAge()) {
                    this.status = CellLifeCycleStatus.OLD;
                } else {
                    this.status = CellLifeCycleStatus.DEAD;
                }
            }
        }
    }

}
