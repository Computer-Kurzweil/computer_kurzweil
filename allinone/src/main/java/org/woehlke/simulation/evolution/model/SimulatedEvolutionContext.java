package org.woehlke.simulation.evolution.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.woehlke.simulation.allinone.model.LatticePoint;
import org.woehlke.simulation.evolution.config.SimulatedEvolutionProperties;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.Random;


@Log
@Service
public class SimulatedEvolutionContext implements Serializable {

    @Getter @Setter
    private volatile int foodPerDay;

    @Getter @Setter
    private volatile boolean gardenOfEdenEnabled;

    @Getter @Setter
    private volatile int foodPerDayGardenOfEden;

    @Getter
    private final SimulatedEvolutionProperties properties;

    @Getter
    private  final Random random;

    @Autowired
    public SimulatedEvolutionContext(SimulatedEvolutionProperties properties) {
      this.properties = properties;
      this.foodPerDay = properties.getFood().getFoodPerDay();
      this.gardenOfEdenEnabled = properties.getGardenOfEden().getGardenOfEdenEnabled();
      this.foodPerDayGardenOfEden = properties.getGardenOfEden().getFoodPerDay();
      long seed = new Date().getTime();
      this.random = new Random(seed);
    }

    @Transient
    public LatticePoint getWorldDimensions() {
        return new LatticePoint(
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

    /*
    public void updateLifeCycleCount() {
        getPanelStatistics().updateTextFields();
    }
    */
}
