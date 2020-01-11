package org.woehlke.simulation.evolution.model.cell;

import org.woehlke.simulation.evolution.config.SimulatedEvolutionProperties;

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
public class LifeCycle {

  /**
   * Status of the LifeCycle is fat, age and hunger.
   */
  private int fat;

  /**
   * Status of the LifeCycle is fat, age and hunger.
   */
  private int age;

  /**
   * Status of the LifeCycle is fat, age and hunger.
   */
  private int hunger;

  private final SimulatedEvolutionProperties simulatedEvolutionProperties;

  public LifeCycle(SimulatedEvolutionProperties simulatedEvolutionProperties) {
    this.simulatedEvolutionProperties = simulatedEvolutionProperties;
    hunger = 0;
    age = 0;
    fat = simulatedEvolutionProperties.getFatAtBirth();
  }

    public LifeCycle(int fat, SimulatedEvolutionProperties simulatedEvolutionProperties) {
        this.simulatedEvolutionProperties = simulatedEvolutionProperties;
        hunger = 0;
        age = 0;
        this.fat = fat;
    }

  /**
   * moving consumes food energy.
   *
   * @return true, if cell has enough energy to move.
   */
  public boolean move() {
    age++;
    if (fat > 0) {
      fat--;
      return true;
    } else {
      hunger++;
      return false;
    }
  }

  /**
   * having sex by cell division changes age and fat.
   */
  public void haveSex() {
    fat /= 2;
    age = 0;
  }

  /**
   * @return has enough age and fat for having sex.
   */
  public boolean isPregnant() {
    return (age >= simulatedEvolutionProperties.getFullAge()) && (fat >= simulatedEvolutionProperties.getFatMinimumForSex());
  }

  /**
   * @return died by hunger. found and eaten too few food and so too few fat.
   */
  public boolean isDead() {
    return (hunger >= simulatedEvolutionProperties.getMaxHunger()) || (age >= simulatedEvolutionProperties.getMaxAge());
  }

  /**
   * @param food eat the found food and add the energy to the cells fat.
   */
  public void eat(int food) {
    if (fat + food * simulatedEvolutionProperties.getFoodPerDay() <= simulatedEvolutionProperties.getMaxFat()) {
      fat += food * simulatedEvolutionProperties.getFatPerFood();
    } else {
      fat = simulatedEvolutionProperties.getMaxFat();
    }
  }

  public int getFat() {
    return fat;
  }

  public LifeCycleStatus getLifeCycleStatus() {
    if (fat == 0 && hunger >= 0) {
      return LifeCycleStatus.HUNGRY;
    }
    if (age < simulatedEvolutionProperties.getFullAge()) {
      if (fat < simulatedEvolutionProperties.getFatMinimumForSex()) {
        return LifeCycleStatus.YOUNG;
      } else {
        return LifeCycleStatus.YOUNG_AND_FAT;
      }
    } else {
      if (age < simulatedEvolutionProperties.getOldAge()) {
        return LifeCycleStatus.FULL_AGE;
      } else {
        if (age < simulatedEvolutionProperties.getMaxAge()) {
          return LifeCycleStatus.OLD;
        } else {
          return LifeCycleStatus.DEAD;
        }
      }
    }
  }

}
