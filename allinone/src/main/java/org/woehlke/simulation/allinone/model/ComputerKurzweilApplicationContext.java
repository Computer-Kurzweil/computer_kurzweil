package org.woehlke.simulation.allinone.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.woehlke.simulation.allinone.config.ComputerKurzweilProperties;
import org.woehlke.simulation.cca.config.CyclicCellularAutomatonProperties;
import org.woehlke.simulation.dla.config.DiffusionLimitedAggregationProperties;
import org.woehlke.simulation.evolution.config.SimulatedEvolutionProperties;
import org.woehlke.simulation.mandelbrot.config.MandelbrotProperties;

@Log
@Component
@ToString
@EqualsAndHashCode
public class ComputerKurzweilApplicationContext {

    @Getter private final ComputerKurzweilProperties properties;

    @Getter private final MandelbrotProperties mandelbrotProperties;
    @Getter private final SimulatedEvolutionProperties simulatedEvolutionProperties;
    @Getter private final DiffusionLimitedAggregationProperties diffusionLimitedAggregationProperties;
    @Getter private final CyclicCellularAutomatonProperties cyclicCellularAutomatonProperties;

    @Autowired
    public ComputerKurzweilApplicationContext(
        ComputerKurzweilProperties computerKurzweilProperties,
        MandelbrotProperties mandelbrotProperties,
        SimulatedEvolutionProperties simulatedEvolutionProperties,
        DiffusionLimitedAggregationProperties diffusionLimitedAggregationProperties,
        CyclicCellularAutomatonProperties cyclicCellularAutomatonProperties
    ) {
        this.properties = computerKurzweilProperties;
        this.mandelbrotProperties = mandelbrotProperties;
        this.simulatedEvolutionProperties = simulatedEvolutionProperties;
        this.diffusionLimitedAggregationProperties = diffusionLimitedAggregationProperties;
        this.cyclicCellularAutomatonProperties = cyclicCellularAutomatonProperties;
    }

    public void exit() {
        System.exit(0);
    }
}
