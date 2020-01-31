package org.woehlke.computer.kurzweil.apps.evolution.model.cell;

import lombok.*;
import lombok.extern.java.Log;
import org.woehlke.computer.kurzweil.config.ComputerKurzweilProperties;

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
@Log
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class CellLifeCycle {

    @Getter private int fat;
    @Getter private int age;
    @Getter private int hunger;
    @Getter private int generation;
    @Getter private CellLifeCycleStatus status;

    @Getter private final ComputerKurzweilProperties.Evolution.CellConf cellConf;

    public CellLifeCycle(ComputerKurzweilProperties.Evolution.CellConf cellConf) {
        this.cellConf = cellConf;
        this.fat = cellConf.getFatAtBirth();
        this.age = 0;
        this.hunger = 0;
        this.generation = 0;
        updateLifeCycleStatus();
    }

    private CellLifeCycle(CellLifeCycle other) {
        this.cellConf =  other.cellConf;
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
            (age >= this.cellConf.getAgeOfAdulthood()) &&
            (fat >= this.cellConf.getFatMinimumForSex())
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
            (hunger >= this.cellConf.getFatHungerMax()) ||
            (age    >= this.cellConf.getAgeMax())
        );
    }

    /**
    * @param food eat the found food and add the energy to the cells fat.
    */
    public void eat(int food) {
        fat = Integer.min(
            fat + food * this.cellConf.getFatPerFood(),
            this.cellConf.getFatMax()
        );
    }

    private void updateLifeCycleStatus() {
        if (fat == 0 && hunger >= 0) {
            this.status = CellLifeCycleStatus.HUNGRY;
        }
        if (age < this.cellConf.getAgeOfAdulthood()) {
            if (fat < this.cellConf.getFatMinimumForSex()) {
                this.status = CellLifeCycleStatus.YOUNG;
            } else {
                this.status = CellLifeCycleStatus.YOUNG_AND_FAT;
            }
        } else {
            if (age < this.cellConf.getAgeOfAdulthood()) {
                this.status = CellLifeCycleStatus.FULL_AGE;
            } else {
                if (age < this.cellConf.getAgeMax()) {
                    this.status = CellLifeCycleStatus.OLD;
                } else {
                    this.status = CellLifeCycleStatus.DEAD;
                }
            }
        }
    }

}
