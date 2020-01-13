package org.woehlke.simulation.evolution.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.woehlke.simulation.all.model.LatticePoint;
import org.woehlke.simulation.evolution.config.SimulatedEvolutionProperties;
import org.woehlke.simulation.evolution.control.SimulatedEvolutionControllerThread;
import org.woehlke.simulation.evolution.model.statistics.SimulatedEvolutionStatistics;
import org.woehlke.simulation.evolution.model.world.SimulatedEvolutionWorld;
import org.woehlke.simulation.evolution.model.world.SimulatedEvolutionWorldLattice;
import org.woehlke.simulation.evolution.view.SimulatedEvolutionButtonRowPanel;
import org.woehlke.simulation.evolution.view.SimulatedEvolutionFrame;
import org.woehlke.simulation.evolution.view.SimulatedEvolutionStatisticsPanel;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.Random;


@Service
public class SimulatedEvolutionContext implements Serializable {

    @Getter @Setter
    private volatile int foodPerDay;

    @Getter @Setter
    private volatile boolean gardenOfEdenEnabled;

    @Getter @Setter
    private volatile int foodPerDayGardenOfEden;


    /**
     * Random Generator used for placing food.
     */
    @Getter
    private  final Random random;

    @Getter @Setter
    private volatile SimulatedEvolutionStatistics statisticsContainer;

    @Getter @Setter
    private volatile SimulatedEvolutionWorld world;

    @Getter @Setter
    private volatile SimulatedEvolutionWorldLattice lattice;

    @Getter @Setter
    private volatile SimulatedEvolutionFrame frame;

    @Getter @Setter
    private volatile SimulatedEvolutionStatisticsPanel panelStatistics;

    @Getter @Setter
    private volatile SimulatedEvolutionButtonRowPanel panelButtons;

    @Getter @Setter
    private volatile SimulatedEvolutionControllerThread controllerThread;

    @Getter
    private final SimulatedEvolutionProperties properties;

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

}
