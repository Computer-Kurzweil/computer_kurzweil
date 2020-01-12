package org.woehlke.simulation.evolution.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.woehlke.simulation.all.model.LatticePointSimulatedEvolution;
import org.woehlke.simulation.evolution.config.SimulatedEvolutionProperties;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.Random;


@Service
public class SimulatedEvolutionContext implements Serializable {

    @Getter @Setter
    private int foodPerDay;

    @Getter @Setter
    private boolean gardenOfEdenEnabled;

    /**
     * Random Generator used for placing food.
     */
    @Getter
    private final Random random;

    @Getter
    private final SimulatedEvolutionProperties properties;

    @Autowired
    public SimulatedEvolutionContext(SimulatedEvolutionProperties properties) {
      this.properties = properties;
      this.foodPerDay = properties.getFood().getFoodPerDay();
      this.gardenOfEdenEnabled = properties.getGardenOfEden().getGardenOfEdenEnabled();
      long seed = new Date().getTime();
      this.random = new Random(seed);
    }

    @Transient
    public LatticePointSimulatedEvolution getWorldDimensions() {
        return new LatticePointSimulatedEvolution(
            this.properties.getLattice().getWidth(),
            this.properties.getLattice().getHeight()
        );
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
