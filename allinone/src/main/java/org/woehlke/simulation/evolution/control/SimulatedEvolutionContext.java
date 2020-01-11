package org.woehlke.simulation.evolution.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.evolution.config.SimulatedEvolutionProperties;

import java.util.Date;
import java.util.Random;

@Component
public class SimulatedEvolutionContext {

  /**
   * Random Generator used for placing food.
   */
  private final Random random;

  private int foodPerDay;

  private boolean gardenOfEdenEnabled;

  @Autowired
  public SimulatedEvolutionContext(SimulatedEvolutionProperties simulatedEvolutionProperties) {
      this.foodPerDay = simulatedEvolutionProperties.getFoodPerDay();
      this.gardenOfEdenEnabled = simulatedEvolutionProperties.getGardenOfEdenEnabled();
      long seed = new Date().getTime();
      this.random = new Random(seed);
  }

  public Random getRandom() {
    return random;
  }

    public int getFoodPerDay() {
        return foodPerDay;
    }

    public boolean isGardenOfEdenEnabled() {
        return gardenOfEdenEnabled;
    }

    public void increaseFoodPerDay() {
        this.foodPerDay++;
    }

    public void decreaseFoodPerDay() {
        this.foodPerDay--;
    }

    public void toggleGardenOfEden() {
      this.gardenOfEdenEnabled = ! this.gardenOfEdenEnabled;
    }
}
